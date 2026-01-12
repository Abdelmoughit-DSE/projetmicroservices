package com.org.emprunt.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "emprunt-created";

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmpruntCreatedEvent(Long empruntId, Long userId, Long bookId) {
        Map<String, Object> event = new HashMap<>();
        event.put("empruntId", empruntId);
        event.put("userId", userId);
        event.put("bookId", bookId);
        event.put("eventType", "EMPRUNT_CREATED");
        event.put("timestamp", LocalDateTime.now().toString());
        
        kafkaTemplate.send(TOPIC, event);
    }
}

