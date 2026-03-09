package com.example.taskmanager.service.interafces;

import com.example.taskmanager.model.User;

// Contrat du service de gestion des utilisateurs
public interface UserService {

    // Recherche un utilisateur par son email
    User findByEmail(String email);

    // Vérifie si un email est déjà utilisé
    boolean existsByEmail(String email);
}
