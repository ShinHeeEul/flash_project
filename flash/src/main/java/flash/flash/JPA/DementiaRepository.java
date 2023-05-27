package flash.flash.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DementiaRepository extends JpaRepository<Dementia, Long> {
    Optional<Dementia> findByUser_Uid(String uid);

}