package flash.flash;

import flash.flash.Controller.UserController;
import flash.flash.JPA.User;
import flash.flash.repository.User_Repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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
