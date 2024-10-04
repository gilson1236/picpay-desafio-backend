package br.com.gil.pickpay_desafio_backend.authorizations.services;

import br.com.gil.pickpay_desafio_backend.authorizations.exceptions.UnauthorizedTransactionException;
import br.com.gil.pickpay_desafio_backend.authorizations.models.Authorization;
import br.com.gil.pickpay_desafio_backend.transaction.models.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthorizeService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AuthorizeService.class);
    private final RestClient restClient;

    public AuthorizeService(RestClient.Builder builder){
        this.restClient = builder
                .baseUrl("https://util.devi.tools/api/v2/authorize")
                .build();
    }
    public void authorize(Transaction transaction){
        LOGGER.info("Authorizing transaction: {}", transaction);
        var response = restClient.get()
                .retrieve()
                .toEntity(Authorization.class);

        if(response.getStatusCode().isError() || !response.getBody().isAuthorized())
            throw new UnauthorizedTransactionException("Unauthorized transaction!");

        LOGGER.info("Transaction authorized: {}", transaction);
    }
}
