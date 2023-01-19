package flash.flash;

import flash.flash.JPA.User;
import flash.flash.repository.User_Repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
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

	@Test
	void save() {
		User user = new User();

		user.setName("hi");
		user.setUser_pw("dfdf");
		user.setUser_id("dfsd");

		User finduser = repository.save(user);

		Assertions.assertThat(user.getUser_id())
				.isEqualTo(user.getUser_id());
	}
}
