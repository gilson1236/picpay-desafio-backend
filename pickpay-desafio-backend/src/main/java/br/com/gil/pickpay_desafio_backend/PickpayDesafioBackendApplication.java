package br.com.gil.pickpay_desafio_backend;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.kafka.config.TopicBuilder;

@EnableJdbcAuditing
@SpringBootApplication
public class PickpayDesafioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PickpayDesafioBackendApplication.class, args);
	}

	@Bean
	NewTopic notificationTopic(){
		return TopicBuilder.name("transaction-notification")
				.build();
	}
}
