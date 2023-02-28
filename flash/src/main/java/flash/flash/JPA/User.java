package flash.flash.JPA;


import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "USER_TABLE")
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

    public long getUser_id() {
        return user_id;
    }

    public String getUid() {
        return uid;
    }

    public String getUpw() {
        return upw;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    /*public Map<String, Object> getResult_set() {
        return result_set;
    }*/
}

