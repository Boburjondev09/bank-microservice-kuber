package ru.otus.notificationservice.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.notificationservice.aggregate.DTO.NotificationDto;
import ru.otus.notificationservice.service.business.NotificationInterface;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: notification-service
 */
@RestController
@RequestMapping("/api/notifications")
public class NotificationEndpoint {

    private final NotificationInterface notificationInterface;
    private static final Logger LOG = LoggerFactory.getLogger(NotificationEndpoint.class);

    public NotificationEndpoint(NotificationInterface notificationInterface) {
        this.notificationInterface = notificationInterface;
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(notificationInterface.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<NotificationDto> getById(@PathVariable Long id) {
        LOG.info("GET /api/notifications/get/{}", id);
        return ResponseEntity.ok(notificationInterface.findById(id));
    }

    @GetMapping("/get/recipient/{recipient}")
    public ResponseEntity<?> getByRecipient(@PathVariable String recipient) {
        LOG.info("GET /api/notifications/get/recipient/{}", recipient);
        return ResponseEntity.ok(notificationInterface.findByRecipient(recipient));
    }

    @GetMapping("/get/reference/{referenceId}")
    public ResponseEntity<?> getByReferenceId(@PathVariable String referenceId) {
        LOG.info("GET /api/notifications/get/reference/{}", referenceId);
        return ResponseEntity.ok(notificationInterface.findByReferenceId(referenceId));
    }

    @PostMapping("/send/email")
    public ResponseEntity<NotificationDto> sendEmail(@RequestBody NotificationDto dto) {
        LOG.info("POST /api/notifications/send/email : recipient={}", dto.getRecipient());
        return ResponseEntity.ok(notificationInterface.sendEmail(dto));
    }

    @PostMapping("/send/sms")
    public ResponseEntity<NotificationDto> sendSms(@RequestBody NotificationDto dto) {
        LOG.info("POST /api/notifications/send/sms : recipient={}", dto.getRecipient());
        return ResponseEntity.ok(notificationInterface.sendSms(dto));
    }
}
