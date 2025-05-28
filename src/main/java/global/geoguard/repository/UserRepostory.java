package global.geoguard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import global.geoguard.model.User;

public interface UserRepostory extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
