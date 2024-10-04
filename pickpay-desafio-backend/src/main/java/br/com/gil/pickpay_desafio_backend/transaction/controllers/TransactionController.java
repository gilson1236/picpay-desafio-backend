package br.com.gil.pickpay_desafio_backend.transaction.controllers;

import br.com.gil.pickpay_desafio_backend.transaction.models.Transaction;
import br.com.gil.pickpay_desafio_backend.transaction.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction){
        return  transactionService.create(transaction);
    }

    @GetMapping
    public List<Transaction> list(){
        return transactionService.list();
    }
}
