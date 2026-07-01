package ru.otus.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.notificationservice.aggregate.entity.Notification;
import ru.otus.notificationservice.aggregate.entity.NotificationStatus;
import ru.otus.notificationservice.aggregate.entity.NotificationType;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: notification-service
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipient(String recipient);
    List<Notification> findByStatus(NotificationStatus status);
    List<Notification> findByType(NotificationType type);
    List<Notification> findByReferenceId(String referenceId);
}
