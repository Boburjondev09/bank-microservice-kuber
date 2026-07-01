package ru.otus.notificationservice.aggregate.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.otus.notificationservice.aggregate.entity.NotificationStatus;
import ru.otus.notificationservice.aggregate.entity.NotificationType;

import java.time.LocalDateTime;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: notification-service
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationDto {
    private Long id;
    private String recipient;
    private String subject;
    private String body;
    private NotificationType type;
    private NotificationStatus status;
    private String referenceId;
    private LocalDateTime sentAt;
    private LocalDateTime createdAt;
}
