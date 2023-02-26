package flash.flash;

import flash.flash.local_repository.User_Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlashApplicationTests {

	@Test
	void contextLoads() {
	}

	User_Repository repository = new User_Repository();
	@AfterEach
	void afterEach() {
		repository.clearStore();
	}



}
