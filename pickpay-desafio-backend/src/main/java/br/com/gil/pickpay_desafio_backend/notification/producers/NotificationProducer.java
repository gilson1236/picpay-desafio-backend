package br.com.gil.pickpay_desafio_backend.notification.producers;

import br.com.gil.pickpay_desafio_backend.transaction.models.Transaction;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    private final KafkaTemplate<String, Transaction> template;

    public NotificationProducer(KafkaTemplate<String, Transaction> template) {
        this.template = template;
    }

    public void sendNotification(Transaction transaction){
        template.send("transaction-notification", transaction);
    }
}
