package flash.flash.local_repository;


import flash.flash.JPA.Dementia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class Dementia_Repository {

    private Map<Long, Dementia> store = new HashMap<>();

    private Long sequence = 0L;

    public Dementia save(Dementia de) {

        return de;
    }
}
