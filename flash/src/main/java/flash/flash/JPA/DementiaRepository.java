package flash.flash.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DementiaRepository extends JpaRepository<Dementia, Long> {
    Optional<Dementia> findByUser_Uid(String uid);

}