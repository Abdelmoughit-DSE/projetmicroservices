# Application de Gestion d'Emprunts - Architecture Microservices

## Auteur
[Nom] [Prénom]

## Description
Application de gestion d'emprunts basée sur une architecture microservices avec MySQL et Kafka.

## Architecture

Le projet est composé des microservices suivants :

1. **Eureka Server** - Découverte des services
2. **Gateway** - Point d'entrée unique, routage dynamique vers les microservices
3. **User Service** - Gestion des utilisateurs
4. **Book Service** - Gestion des livres
5. **Emprunter Service** - Gestion des emprunts
6. **Notification Service** - Gestion des notifications (nouveau)

## Technologies

- Spring Boot 3.4.1
- Spring Cloud
- Netflix Eureka (Service Discovery)
- Spring Cloud Gateway
- MySQL 8.0
- Apache Kafka
- Docker & Docker Compose

## Base de Données MySQL

Chaque microservice possède sa propre base de données (Database per Service) :

- `user-service` → MySQL (`db_user`)
- `book-service` → MySQL (`db_book`)
- `emprunter-service` → MySQL (`db_emprunter`)

## Kafka - Communication Asynchrone

### Topic
- `emprunt-created`

### Producteur
- `emprunter-service` : Publie des événements lorsqu'un emprunt est créé

### Consommateur
- `notification-service` : Consomme les événements et envoie des notifications

### Format du message
```json
{
  "empruntId": 1,
  "userId": 3,
  "bookId": 5,
  "eventType": "EMPRUNT_CREATED",
  "timestamp": "2025-01-01T14:00:00"
}
```

## Notification Service

Le Notification Service est un microservice découplé qui :
- Consomme les événements Kafka depuis le topic `emprunt-created`
- Gère les notifications de manière asynchrone
- Aucun appel REST entrant
- Kafka Consumer uniquement
- Notification simulée par log/console

## Déploiement

### Prérequis
- Docker
- Docker Compose

### Lancement

```bash
docker-compose up --build
```

### Services et Ports

- Eureka Server: http://localhost:8761
- Gateway: http://localhost:9999
- User Service: http://localhost:8082
- Book Service: http://localhost:8081
- Emprunter Service: http://localhost:8085
- Notification Service: http://localhost:8086
- Kafka: localhost:9092
- MySQL User: localhost:3307
- MySQL Book: localhost:3308
- MySQL Emprunter: localhost:3309

## Structure du Projet

```
microservicesapp/
├── eurika/          # Eureka Server
├── gateway/         # Spring Cloud Gateway
├── user/            # User Service
├── book/            # Book Service
├── emprunter/       # Emprunter Service
├── notification/    # Notification Service
└── docker-compose.yaml
```

## Utilisation

1. Démarrer tous les services avec Docker Compose
2. Accéder à Eureka Dashboard : http://localhost:8761
3. Utiliser la Gateway comme point d'entrée : http://localhost:9999
4. Créer un emprunt via l'API : POST `/emprunts/{userId}/{bookId}`
5. Le Notification Service consommera automatiquement l'événement Kafka et affichera la notification dans les logs

