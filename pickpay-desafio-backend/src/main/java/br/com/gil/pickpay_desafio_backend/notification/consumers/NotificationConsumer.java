package br.com.gil.pickpay_desafio_backend.notification.consumers;

import br.com.gil.pickpay_desafio_backend.notification.exceptions.NotificationException;
import br.com.gil.pickpay_desafio_backend.notification.models.Notification;
import br.com.gil.pickpay_desafio_backend.transaction.models.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NotificationConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);
    private final RestClient restClient;

    public NotificationConsumer(RestClient.Builder builder){
        this.restClient = builder
                .baseUrl("https://util.devi.tools/api/v1/notify")
                .build();
    }

    @KafkaListener(topics = "transaction-notification", groupId = "pickpay-desafio-backend")
    public void receiveNotification(Transaction transaction){
        LOGGER.info("Notifying transaction: {}", transaction);

        var response = restClient.get()
                .retrieve()
                .toEntity(Notification.class);

        if(response.getStatusCode().isError() || !response.getBody().message())
            throw new NotificationException("Error to send notification!");

        LOGGER.info("Notification has been sent {}...", response.getBody());
    }
}
