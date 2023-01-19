package flash.flash.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @GetMapping("/login")
    public String login() {

        return "Login OK";
    }

    @GetMapping("/signup")
    public String Sign(Model model) {
        return "Sign UP OK";
    }

}
