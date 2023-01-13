package flash.flash.JPA;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//@Entity

public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String user_id;
    private String user_pw;
    private String name;
    private String status;

    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    private Map<String,Object> result_set= new HashMap<>();


}
