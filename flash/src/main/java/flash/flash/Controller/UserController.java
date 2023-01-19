package flash.flash.Controller;


import flash.flash.JPA.User;
import flash.flash.repository.User_Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class UserController {


    User_Repository repository = new User_Repository();
    @GetMapping("/login")
    @ResponseBody
    public String login() {

        return "Login OK";
    }

    @GetMapping("/signup")
    public String Sign() {
        return "Sign UP OK11";
    }

    @PostMapping("/signup")
    @ResponseBody
    public String Sign(@RequestParam String nickname,
                       @RequestParam String id,
                       @RequestParam String password, Model model) {

        User user = new User();
        user.setUser_id(id);
        user.setUser_pw(password);
        user.setName(nickname);

        repository.save(user);

        model.addAttribute("user", user);


        System.out.println(user.getUser_id());

        return user.getUser_pw();
    }

}
