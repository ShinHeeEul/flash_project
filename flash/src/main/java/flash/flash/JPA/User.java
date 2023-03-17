package flash.flash.JPA;


import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_TABLE")
@Getter
public class User {
    //추가
    public User() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;

    private String uid;
    private String upw;
    private String name;
    private int status;

    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    /*private Map<String,Object> result_set= new HashMap<>();*/

    @Builder
    public User(String id, String pw, String name, int status,
                LocalDateTime created_at, LocalDateTime updated_at) {
        this.uid = id;
        this.upw = pw;
        this.name = name;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}

