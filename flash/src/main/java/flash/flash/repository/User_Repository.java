package flash.flash.repository;

import flash.flash.JPA.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

//////////////////////////////////////////////////////////

//- User_Repository
//설명 : user local repository

//////////////////////////////////////////////////////////

@Repository
@RequiredArgsConstructor
public class User_Repository {


    private Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    //user를 map에 저장
    public User save(User us) {
        us.setId(sequence++);
        store.put(us.getId(), us);
        return us;
    }

    //id로 User 검색
    public User findById(Long id) {
        return store.get(id);
    }


    //User_id로 User 검색
    public Optional<User> findByUserId(String user_id) {
        Optional<User> tmp = store.values().stream().filter(u -> u.getUser_id().equals(user_id)).findFirst();
        return tmp;
    }

    //repository 비우기
    public void clearStore() {
        store.clear();
    }

    //repository 전체 검색
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }


}
