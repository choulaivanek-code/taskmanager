QUESTION 1 — CONCEPTION ARCHITECTURALE ET MODÉLISATION
1. Analyse du besoin fonctionnel

L’application Task Manager est une API REST permettant à un utilisateur authentifié de gérer ses tâches personnelles. Elle permet de créer, consulter, modifier et supprimer des tâches.

Acteurs

Deux types d’acteurs interagissent avec le système :

Utilisateur (ROLE_USER)
Peut gérer uniquement ses propres tâches.

Administrateur (ROLE_ADMIN)
Peut accéder à toutes les ressources et superviser les données.

Principales fonctionnalités

L’application propose les fonctionnalités suivantes :

Authentification sécurisée

inscription d’un utilisateur

connexion avec génération d’un token JWT

Gestion des tâches (CRUD)

création d’une tâche

consultation d’une tâche

modification d’une tâche

suppression d’une tâche

Filtrage et pagination

filtrer les tâches par statut

pagination des résultats

Sécurité

authentification via JWT

gestion des rôles

accès restreint aux ressources

Contraintes techniques

Le projet doit respecter les contraintes suivantes :

Architecture RESTful

Backend développé avec Spring Boot

Base de données MySQL

Conteneurisation avec Docker

Pipeline CI/CD

Analyse de qualité avec SonarQube

Sécurité via JWT

2. Modélisation des données

L’application repose sur deux entités principales :

User

Task

Table User
Champ	  Type	    Description
id	      Long	    identifiant unique
name	 String 	nom de l’utilisateur
email	 String 	email unique
password	String	mot de passe chiffré
role  Enum	rôle utilisateur

Table Task
Champ	Type	Description
id	Long	identifiant de la tâche
title	String	titre
description	String	description
status	Enum	statut de la tâche
createdAt	Date	date de création
user_id	Long	propriétaire de la tâche

Relations

Relation :

User 1 ---- * Task

Un utilisateur peut posséder plusieurs tâches.

Clés

Clés primaires

User.id
Task.id

Clé étrangère

Task.user_id → User.id
Contraintes d’intégrité

email utilisateur unique

mot de passe obligatoire

une tâche doit appartenir à un utilisateur

statut de tâche contrôlé par enum

3. Structure REST de l’API

L’API suit les principes REST.

Authentification
Méthode	Route	Description
POST	/api/auth/register	créer un utilisateur
POST	/api/auth/login	connexion
Gestion des tâches
Méthode	Route	Description
POST	/api/tasks	créer une tâche
GET	/api/tasks	liste des tâches
GET	/api/tasks/{id}	détail d’une tâche
PUT	/api/tasks/{id}	modifier
DELETE	/api/tasks/{id}	supprimer
Filtrage
Méthode	Route
GET	/api/tasks/status/{status}
Codes de réponse
Code	Signification
200	succès
201	ressource créée
400	erreur de validation
401	non authentifié
403	accès interdit
404	ressource introuvable
500	erreur serveur
Gestion des erreurs

L’application utilise un GlobalExceptionHandler permettant de centraliser les erreurs :

TaskNotFoundException

UnauthorizedException

UserAlreadyExistsException

ValidationException

Versioning de l’API

L’API utilise une version dans l’URL :

/api/v1/

Cela permet d’assurer la compatibilité lors d’évolutions futures.

4. Architecture applicative (Spring Boot)

L’application respecte une architecture en couches afin d’assurer la maintenabilité et la séparation des responsabilités.

Structure du projet
controller
service
repository
model
dto
mapper
security
exception
config
Rôle des couches

Controller

Gestion des requêtes HTTP.

Exemple :

TaskController
AuthController

Service

Contient la logique métier.

Exemple :

TaskService
AuthService

Repository

Accès aux données via Spring Data JPA.

Exemple :

UserRepository
TaskRepository

DTO

Objets utilisés pour la communication avec l’API.

Exemple :

TaskRequest
TaskResponse
LoginRequest
RegisterRequest

Mapper

Transformation des entités vers DTO.

Security

Gestion de l’authentification JWT.

Exception

Gestion centralisée des erreurs.

Respect des principes SOLID

Le projet applique plusieurs principes SOLID :

Single Responsibility

Chaque classe possède une responsabilité unique.

Open/Closed

Les services peuvent être étendus sans modification majeure.

Dependency Injection

Les dépendances sont injectées par Spring.

Clean Code

Le code respecte plusieurs principes de qualité :

méthodes courtes

nommage explicite

séparation claire des responsabilités

gestion centralisée des erreurs

validation des entrées

Justification des choix techniques

Les technologies choisies permettent :

Spring Boot : rapidité de développement

JWT : authentification stateless

MySQL : base relationnelle robuste

Docker : portabilité de l’application

CI/CD : automatisation des déploiements

SonarQube : analyse de qualité et sécurité