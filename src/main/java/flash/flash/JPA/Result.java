package flash.flash.JPA;

import javax.persistence.*;

import java.util.HashMap;
import java.util.Map;


//@Entity
public class Result {

//추가.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int result_id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Dementia dementia;

    private String test_result;

    private int status;

    private Map<String, Object> result_set = new HashMap<>();

    public int getResult_id() {
        return result_id;
    }

    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }

    public String getTest_result() {
        return test_result;
    }

    public void setTest_result(String test_result) {
        this.test_result = test_result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, Object> getResult_set() {
        return result_set;
    }

    public void setResult_set(Map<String, Object> result_set) {
        this.result_set = result_set;
    }
}

