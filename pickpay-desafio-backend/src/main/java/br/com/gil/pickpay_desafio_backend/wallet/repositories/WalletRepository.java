package br.com.gil.pickpay_desafio_backend.wallet.repositories;

import br.com.gil.pickpay_desafio_backend.wallet.models.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {
}
