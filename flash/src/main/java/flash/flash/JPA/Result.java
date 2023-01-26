package flash.flash.JPA;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Result {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    private String test_result;

    private int status;

    private Map<String, Object> result_set = new HashMap<>();




}


