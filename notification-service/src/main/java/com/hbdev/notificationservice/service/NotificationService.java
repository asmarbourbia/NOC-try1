package com.hbdev.notificationservice.service;

import com.hbdev.notificationservice.entity.Notification;
import com.hbdev.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Créer une nouvelle notification
    public Notification createNotification(Long userId, String message) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setRead(false); // Par défaut, la notification est non lue
        return notificationRepository.save(notification);
    }

    // Récupérer les notifications d'un utilisateur
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Marquer une notification comme lue
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        notification.setRead(true); // Mettre à jour pour utiliser setIsRead(true)
        notificationRepository.save(notification);
    }
}