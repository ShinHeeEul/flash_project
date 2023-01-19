package flash.flash.Controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WebController {
    @GetMapping("/login")
    public String login() {
        return "주소 삭제 완료";
    }

    public String loginTest() {
        return " g";
    }



}
