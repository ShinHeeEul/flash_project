package flash.flash.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    @Transactional
    @Modifying
    @Query("update Result r set r.status = ?1")
    int updateStatusBy(int status);


}