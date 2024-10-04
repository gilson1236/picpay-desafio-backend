package br.com.gil.pickpay_desafio_backend.wallet.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("WALLETS")
public record Wallet(
        @Id Long id,
        @Column("FULL_NAME")
        String fullname,
        Long cpf,
        String email,
        String password,
        int type,
        BigDecimal balance
) {
    public Wallet debit(BigDecimal value) {
        return new Wallet(id, fullname, cpf, email, password, type, balance.subtract(value) );
    }

    public Wallet credit(BigDecimal value) {
        return new Wallet(id, fullname, cpf, email, password, type, balance.add(value));
    }
}
