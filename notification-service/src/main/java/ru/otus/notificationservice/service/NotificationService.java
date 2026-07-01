package ru.otus.notificationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.notificationservice.aggregate.DTO.NotificationDto;
import ru.otus.notificationservice.aggregate.entity.Notification;
import ru.otus.notificationservice.aggregate.entity.NotificationStatus;
import ru.otus.notificationservice.aggregate.entity.NotificationType;
import ru.otus.notificationservice.aggregate.mapper.NotificationMapper;
import ru.otus.notificationservice.repository.NotificationRepository;
import ru.otus.notificationservice.service.business.NotificationInterface;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: notification-service
 * @description sends email/SMS notifications and persists every notification
 * for audit; called by transaction-service and account-service
 */
@Service
public class NotificationService implements NotificationInterface {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository repository;
    private final NotificationMapper mapper;
    private final JavaMailSender mailSender;

    public NotificationService(NotificationRepository repository,
                                NotificationMapper mapper,
                                JavaMailSender mailSender) {
        this.repository = repository;
        this.mapper = mapper;
        this.mailSender = mailSender;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> findAll() {
        LOG.info("Finding all notifications");
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationDto findById(Long id) {
        LOG.info("REQUEST: (findById) : {}", id);
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Notification not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> findByRecipient(String recipient) {
        LOG.info("REQUEST: (findByRecipient) : {}", recipient);
        return repository.findByRecipient(recipient).stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> findByReferenceId(String referenceId) {
        LOG.info("REQUEST: (findByReferenceId) : {}", referenceId);
        return repository.findByReferenceId(referenceId).stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional
    public NotificationDto sendEmail(NotificationDto dto) {
        LOG.info("REQUEST: (sendEmail) : recipient={}", dto.getRecipient());

        Notification notification = Notification.builder()
                .recipient(dto.getRecipient())
                .subject(dto.getSubject())
                .body(dto.getBody())
                .type(NotificationType.EMAIL)
                .status(NotificationStatus.PENDING)
                .referenceId(dto.getReferenceId())
                .createdAt(LocalDateTime.now())
                .build();

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(dto.getRecipient());
            message.setSubject(dto.getSubject());
            message.setText(dto.getBody());
            mailSender.send(message);

            notification.setStatus(NotificationStatus.SENT);
            notification.setSentAt(LocalDateTime.now());
            LOG.info("Email sent successfully to {}", dto.getRecipient());
        } catch (Exception ex) {
            LOG.error("Failed to send email to {}: {}", dto.getRecipient(), ex.getMessage());
            notification.setStatus(NotificationStatus.FAILED);
        }

        return mapper.toDto(repository.save(notification));
    }

    @Override
    @Transactional
    public NotificationDto sendSms(NotificationDto dto) {
        LOG.info("REQUEST: (sendSms) : recipient={}", dto.getRecipient());

        Notification notification = Notification.builder()
                .recipient(dto.getRecipient())
                .subject(dto.getSubject())
                .body(dto.getBody())
                .type(NotificationType.SMS)
                .status(NotificationStatus.PENDING)
                .referenceId(dto.getReferenceId())
                .createdAt(LocalDateTime.now())
                .build();

        // SMS gateway integration placeholder - connect Twilio or similar
        LOG.warn("SMS gateway not yet configured; marking as SENT for dev purposes. Recipient={}", dto.getRecipient());
        notification.setStatus(NotificationStatus.SENT);
        notification.setSentAt(LocalDateTime.now());

        return mapper.toDto(repository.save(notification));
    }
}
