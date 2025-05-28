package global.geoguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import global.geoguard.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
