package flash.flash.Controller;


import flash.flash.JPA.User;
import flash.flash.repository.User_Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class UserController {


    User_Repository repository = new User_Repository();
    @GetMapping("/login")
    public String Login() {
        return "/login.html";
    }

    @PostMapping("/login")
    @ResponseBody
    public String Login(@RequestParam String id,
                        @RequestParam String password, Model model) {
        User us = null;
        List<User> list = repository.findAll();
        Boolean login_check = false;
        for(int i = 0; i < list.size(); i++) {
            us = list.get(i);
            if((us.getUser_id() == id) && (us.getUser_pw() == password)) {
                login_check = true;
                break;
            }
        }

        //login 성공
        if(login_check) {
            model.addAttribute("user", us);
            return "1";
        }

        //login 실패
        return "0";
    }

    @GetMapping("/signup")
    public String Sign() {
        return "/signup.html";
    }

    @PostMapping("/signup")
    public String Sign(@RequestParam String nickname,
                       @RequestParam String id,
                       @RequestParam String password, Model model) {

        List<User> list = repository.findAll();
        Boolean check_id = true;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getUser_id() == id) {
                check_id = false;
                break;
            }
        }

        if(check_id) {
            User user = new User();
            user.setUser_id(id);
            user.setUser_pw(password);
            user.setName(nickname);

            repository.save(user);

            model.addAttribute("user", user);
            //"SIGN UP SUCCESS"
            return "/signup";
        }

        //"SIGN UP FAIL : YOUR ID IS DUPLICATE"
        return "0";
    }

    @GetMapping("/test")
    public String test(Model model) {
        User user = new User();
        user.setUser_id("1");
        user.setName("hello");
        user.setUser_pw("dfs");
        model.addAttribute("user", user);
        return "/test";
    }



}
