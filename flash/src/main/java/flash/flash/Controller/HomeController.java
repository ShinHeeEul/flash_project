package flash.flash.Controller;

import flash.flash.JPA.User;
import flash.flash.JPA.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class HomeController {

    ///////////////////////////////////
    //상수 선언
    final String ID = "ID";
    ///////////////////////////////////


    private final UserRepository repository;

    @GetMapping("/")
    public String homeLogin(@CookieValue(value = ID,required = false) Long user_id, Model model)
    {
            if(user_id == null) return "home";
            Optional<User> us = repository.findById(user_id);
            model.addAttribute("user", us.get());
            return "loginHome";
    }
}
