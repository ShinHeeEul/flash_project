package flash.flash.JPA;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

import java.util.HashMap;
import java.util.Map;


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

    @Builder
    public Result(String test_result, int status) {
        this.test_result = test_result;
        this.status = status;
    }
}

