package ru.otus.notificationservice.service.business;

import ru.otus.notificationservice.aggregate.DTO.NotificationDto;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: notification-service
 */
public interface NotificationInterface {
    List<NotificationDto> findAll();
    NotificationDto findById(Long id);
    List<NotificationDto> findByRecipient(String recipient);
    List<NotificationDto> findByReferenceId(String referenceId);
    NotificationDto sendEmail(NotificationDto dto);
    NotificationDto sendSms(NotificationDto dto);
}
