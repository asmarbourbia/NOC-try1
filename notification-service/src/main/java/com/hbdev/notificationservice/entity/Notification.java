package com.hbdev.notificationservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId; // ID de l'utilisateur concerné

    @Column(nullable = false)
    private String message; // Message de la notification

    @Column(name = "is_read", nullable = false) // Renommer la colonne en "is_read"
    private boolean isRead = false; // Statut de lecture

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Date de création
}