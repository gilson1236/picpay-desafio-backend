package br.com.gil.pickpay_desafio_backend.transaction.services;

import br.com.gil.pickpay_desafio_backend.authorizations.services.AuthorizeService;
import br.com.gil.pickpay_desafio_backend.notification.services.NotificationService;
import br.com.gil.pickpay_desafio_backend.transaction.exceptions.InvalidTransactionException;
import br.com.gil.pickpay_desafio_backend.transaction.models.Transaction;
import br.com.gil.pickpay_desafio_backend.transaction.repositories.TransactionRepository;
import br.com.gil.pickpay_desafio_backend.wallet.WalletType;
import br.com.gil.pickpay_desafio_backend.wallet.models.Wallet;
import br.com.gil.pickpay_desafio_backend.wallet.repositories.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizeService authorizeService;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository, AuthorizeService authorizeService, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizeService = authorizeService;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transaction create(Transaction transaction){

        validate(transaction);

        var newTransaction = transactionRepository.save(transaction);

        var walletPayer = walletRepository.findById(transaction.payer()).get();
        var walletPayee = walletRepository.findById(transaction.payee()).get();
        walletRepository.save(walletPayer.debit(transaction.value()));
        walletRepository.save(walletPayee.credit(transaction.value()));

        authorizeService.authorize(transaction);

        notificationService.notify(transaction);

        return newTransaction;
    }

    private void validate(Transaction transaction){
        LOGGER.info("Validating transaction {}...", transaction);

        walletRepository.findById(transaction.payee())
                .map(payee -> walletRepository.findById(transaction.payer())
                .map(payer -> isTransactionValue(transaction, payer) ? transaction : null)
                        .orElseThrow(() -> new InvalidTransactionException("Invalid transaction - %s".formatted(transaction))))
                .orElseThrow(() -> new InvalidTransactionException("Invalid transaction - %s".formatted(transaction)));
    }

    private static boolean isTransactionValue(Transaction transaction, Wallet payer){
        return payer.type() == WalletType.COMUM.getValue() &&
                payer.balance().compareTo(transaction.value()) >= 0 &&
                !payer.id().equals(transaction.payee());
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
