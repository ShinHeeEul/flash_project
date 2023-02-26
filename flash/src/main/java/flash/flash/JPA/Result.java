package flash.flash.JPA;

import javax.persistence.*;

import java.util.HashMap;
import java.util.Map;


@Entity
@Table
public class Result {

    //추가
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int result_id;


    private String test_result;

    private int status;


    public int getResult_id() {
        return result_id;
    }


    public String getTest_result() {
        return test_result;
    }

    public int getStatus() {
        return status;
    }
}

