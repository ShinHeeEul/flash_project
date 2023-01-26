package flash.flash.JPA;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//@Entity
public class Dementia {

    public Dementia() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dementia_id;

    private long user_dialog;
    private String user_id;
    private int status;

    @CreationTimestamp
    private LocalDateTime created_at;

    // Map: 데이터 저장 구조 중 한 종류. map은 key 값과 value값의 형태로 저장. Map<String,Object>에서 String은 key값의 자료형, Object는 value값의 자료형.
    private Map<String,Object> result_set= new HashMap<>();


    public void setDementia_id(Long dementia_id) {
        this.dementia_id = dementia_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public long getUser_dialog() {
        return user_dialog;
    }

    public void setUser_dialog(long user_dialog) {
        this.user_dialog = user_dialog;
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