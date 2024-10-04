package br.com.gil.pickpay_desafio_backend.notification.models;

import org.springframework.data.relational.core.mapping.Table;

@Table("notifications")
public record Notification(boolean message) {
}
