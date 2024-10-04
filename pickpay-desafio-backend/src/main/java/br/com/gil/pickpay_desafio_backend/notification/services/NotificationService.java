package br.com.gil.pickpay_desafio_backend.notification.services;

import br.com.gil.pickpay_desafio_backend.notification.producers.NotificationProducer;
import br.com.gil.pickpay_desafio_backend.transaction.models.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationProducer notificationProducer;

    public NotificationService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    public void notify(Transaction transaction){
        LOGGER.info("Notifying transaction {}...", transaction);
        notificationProducer.sendNotification(transaction);
    }
}
