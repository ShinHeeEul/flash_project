package flash.flash.JPA;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "RESULT_TABLE")
@Getter
public class Result {

    public Result() {

    }

    //추가
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long result_id;

    private String test_result;

    private int status;

    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @Builder
    public Result(String test_result, int status, LocalDateTime created_at, LocalDateTime updated_at) {
        this.test_result = test_result;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}

