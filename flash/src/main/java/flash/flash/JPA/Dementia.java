package flash.flash.JPA;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Dementia {

    public Dementia() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private long user_dialog;
    private String user_id;
    private int status;

    @CreationTimestamp
    private LocalDateTime created_at;

    private Map<String,Object> result_set= new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getUser_dialog() {
        return user_dialog;
    }

    public void setUser_dialog(long user_dialog) {
        this.user_dialog = user_dialog;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Map<String, Object> getResult_set() {
        return result_set;
    }

    public void setResult_set(Map<String, Object> result_set) {
        this.result_set = result_set;
    }
}
