package flash.flash.repository;

import flash.flash.JPA.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
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

    public void clearStore() {
        store.clear();
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }


}
