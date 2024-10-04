package br.com.gil.pickpay_desafio_backend.transaction.repositories;

import br.com.gil.pickpay_desafio_backend.transaction.models.Transaction;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {
}
