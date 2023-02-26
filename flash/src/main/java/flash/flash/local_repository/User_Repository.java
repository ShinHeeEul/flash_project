package flash.flash.local_repository;

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
        store.put(us.getUser_id(), us);
        return us;
    }

    //user_id로 User 검색
    public User findByuser_id(Long user_id) {
        return store.get(user_id);
    }


    //로 User 검색
    public Optional<User> findById(String user_id) {
        Optional<User> tmp = store.values().stream().filter(u -> u.getUid().equals(user_id)).findFirst();
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
