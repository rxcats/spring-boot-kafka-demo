package io.github.rxcats.springbootkafkademo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringBootKafkaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaDemoApplication.class, args);
    }

    @Service
    static class KafkaService {

        @Resource(type = KafkaTemplate.class)
        KafkaTemplate<String, String> kafkaTemplate;

        public void send(String message) {
            kafkaTemplate.send("news_topic", message);
        }

    }

    @Autowired
    KafkaService kafkaService;

    @Bean
    CommandLineRunner runner() {
        return args -> {
            kafkaService.send("hello");
            log.info("send");

        };
    }

    @Service
    static class Listener {
        @KafkaListener(topics = "news_topic")
        public void listen(@Payload String payload) {
            log.info("payload:{}", payload);
        }
    }

}

