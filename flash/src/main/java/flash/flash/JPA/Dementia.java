package flash.flash.JPA;


import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DEMENTIA_TABLE")
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
    private User user;

    //분석할 음성 내용 넣어야 됨
    private String user_dialog;

    private int status;

    @CreationTimestamp
    private LocalDateTime created_at;

    public Result getResult() {
        return result;
    }

    @Builder
    public Dementia(Result result, User user, String user_dialog, int status, LocalDateTime created_at) {
        this.result = result;
        this.user = user;
        this.user_dialog = user_dialog;
        this.status = status;
        this.created_at = created_at;
    }

    // Map: 데이터 저장 구조 중 한 종류. map은 key 값과 value값의 형태로 저장. Map<String,Object>에서 String은 key값의 자료형, Object는 value값의 자료형.
    //private Map<String,Object> result_set= new HashMap<>();

}
