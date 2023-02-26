package flash.flash.JPA;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table
public class Dementia {

    public Dementia() {

    }
    //추가
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dementia_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private Result result;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User id;
    private long user_dialog;

    private int status;

    @CreationTimestamp
    private LocalDateTime created_at;

    // Map: 데이터 저장 구조 중 한 종류. map은 key 값과 value값의 형태로 저장. Map<String,Object>에서 String은 key값의 자료형, Object는 value값의 자료형.
    //private Map<String,Object> result_set= new HashMap<>();


    public Long getDementia_id() {
        return dementia_id;
    }


    public User getId() {
        return id;
    }

    public long getUser_dialog() {
        return user_dialog;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    /*public Map<String, Object> getResult_set() {
        return result_set;
    }*/
}
