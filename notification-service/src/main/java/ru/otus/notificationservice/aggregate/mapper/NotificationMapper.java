package ru.otus.notificationservice.aggregate.mapper;

import org.mapstruct.Mapper;
import ru.otus.notificationservice.aggregate.DTO.NotificationDto;
import ru.otus.notificationservice.aggregate.entity.Notification;

import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: notification-service
 */
@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDto toDto(Notification entity);

    Notification toEntity(NotificationDto dto);

    List<NotificationDto> toDto(List<Notification> entities);
}
