package com.org.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @KafkaListener(topics = "emprunt-created", groupId = "notification-service-group")
    public void consumeEmpruntCreatedEvent(Map<String, Object> event) {
        logger.info("=== NOTIFICATION ===");
        logger.info("Nouvel emprunt créé:");
        logger.info("Emprunt ID: {}", event.get("empruntId"));
        logger.info("User ID: {}", event.get("userId"));
        logger.info("Book ID: {}", event.get("bookId"));
        logger.info("Event Type: {}", event.get("eventType"));
        logger.info("Timestamp: {}", event.get("timestamp"));
        logger.info("Notification envoyée à l'utilisateur");
        logger.info("===================");
    }
}

