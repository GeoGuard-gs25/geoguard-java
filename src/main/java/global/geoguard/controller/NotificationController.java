package global.geoguard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import global.geoguard.model.Notification;
import global.geoguard.repository.NotificationRepository;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getById(@PathVariable Long id) {
        Optional<Notification> optional = notificationRepository.findById(id);
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Notification create(@RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> update(@PathVariable Long id, @RequestBody Notification updatedNotification) {
        Optional<Notification> optional = notificationRepository.findById(id);

        if (optional.isPresent()) {
            Notification existing = optional.get();
            existing.setTitle(updatedNotification.getTitle());
            existing.setMessage(updatedNotification.getMessage());
            existing.setMensagem(updatedNotification.getMensagem());
            existing.setLida(updatedNotification.isLida());

            return ResponseEntity.ok(notificationRepository.save(existing));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
