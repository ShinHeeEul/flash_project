package flash.flash.JPA;

import flash.flash.JPA.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByuid(String uid);

}