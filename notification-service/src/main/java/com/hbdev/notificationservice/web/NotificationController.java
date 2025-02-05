package com.hbdev.notificationservice.web;

import com.hbdev.notificationservice.entity.Notification;
import com.hbdev.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Endpoint pour créer une notification
    @PostMapping
    public ResponseEntity<Notification> createNotification(
            @RequestParam Long userId, // ID de l'utilisateur
            @RequestParam String message // Message de la notification
    ) {
        Notification notification = notificationService.createNotification(userId, message);
        return ResponseEntity.ok(notification); // Retourne la notification créée
    }

    // Endpoint pour récupérer les notifications d'un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUserId(
            @PathVariable Long userId // ID de l'utilisateur
    ) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications); // Retourne la liste des notifications
    }

    // Endpoint pour marquer une notification comme lue
    @PatchMapping("/{notificationId}/mark-as-read")
    public ResponseEntity<Void> markAsRead(
            @PathVariable Long notificationId // ID de la notification
    ) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.noContent().build(); // Retourne un statut 204 (No Content)
    }

    // Endpoint pour gérer les requêtes OPTIONS
    @RequestMapping(value = "/{notificationId}/mark-as-read", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions() {
        return ResponseEntity.noContent().build(); // Répondre avec un statut 204 (No Content)
    }
}