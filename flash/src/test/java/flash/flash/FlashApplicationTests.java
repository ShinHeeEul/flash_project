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

	@Test
	void save() {
		User user = new User();
		User user2 = new User();
		user.setName("hi");
		user.setUser_pw("dfdf");
		user.setUser_id("dfsd");

		user2.setName("hi");
		user2.setUser_pw("dfdf");
		user2.setUser_id("dfsd");

		User finduser = repository.save(user);

		repository.findAll().stream().forEach(System.out::println);

		System.out.println("-----------------------");
		System.out.println(repository.findByUserId(user2.getUser_id()));

		if(!repository.findByUserId(user2.getUser_id()).equals(Optional.empty()))
			Assertions.fail("id 중복");
		else
			Assertions.assertThat(user == finduser);


	}

	@Test
	void login_test() {
		User user = new User();
		User user2 = new User();
		user.setName("hi");
		user.setUser_pw("dfdf");
		user.setUser_id("dfsd");

		User finduser = repository.save(user);


		//데이터베이스로부터 해당 id에 해당하는 user가 있나 확인
		Optional<User> us = repository.findByUserId("dfsd");

		//login 실패 : id 없음
		if(us.equals(Optional.empty())) {
			Assertions.fail("id 없음");

		}

		//login 실패 : id는 있으나 비밀번호 틀림
		if(!us.get().getUser_pw().equals("dfdㅇf")) {
			Assertions.fail("비밀번호 없음");
		}

		//login 성공
		Assertions.assertThat(user.equals(us.get()));


	}
}
