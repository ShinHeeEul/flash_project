package flash.flash.Controller;


import flash.flash.JPA.user;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserController {


    user us = new user();

    private Map<Long, user> user_respository = new HashMap<>();

    @GetMapping("/login")
    public String login() {

        return "Login OK";
    }

    @GetMapping("/signup")
    public String Sign(Model model) {
        return "Sign UP OK";
    }

}
