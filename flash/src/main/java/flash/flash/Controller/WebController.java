package flash.flash.Controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebController {

    @GetMapping("/logintest")
    public String login2() {
        return "login";
    }


    @GetMapping("/login")
    public String login() {
        return "hello world";
    }

    @GetMapping("/test")
    public String dfd() {
        return "hello";
    }



}
