package flash.flash.repository;

import flash.flash.JPA.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class User_Repository {

    private Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    public User save(User us) {
        us.setId(sequence++);
        store.put(us.getId(), us);
        return us;
    }

    public User findById(Long id) {
        return store.get(id);
    }


}
