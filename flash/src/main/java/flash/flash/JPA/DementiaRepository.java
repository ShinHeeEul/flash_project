package flash.flash.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DementiaRepository extends JpaRepository<Dementia, Long> {
    @Query("SELECT d FROM Dementia d JOIN d.user u WHERE u.user_id = :userId")
    List<Dementia> findByUserIdWithJoin(@Param("userId") Long userId);

}