package flash.flash.Controller;


import flash.flash.JPA.User;
import flash.flash.repository.User_Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//////////////////////////////////////////////////////////

// UserControlller
//설명 : User 내용을 처리하는 Controller
//기능 : 회원가입, 로그인, 회원 페이지 API


//////
//front-end와 얘기해서 api 재정의 필요!!
//////

//////////////////////////////////////////////////////////

@Controller
@Slf4j
public class UserController {


    //데이터베이스
    User_Repository repository = new User_Repository();

    //login 화면<GET>
    @GetMapping("/login")
    public String Login() {
        return "/login.html";
    }


    //login API<POST>
    @PostMapping("/login")
    @ResponseBody
    public String Login(@RequestParam String id,
                        @RequestParam String password, Model model) {
        //데이터베이스로부터 해당 id에 해당하는 user가 있나 확인
        Optional<User> us = repository.findByUserId(id);

        //login 실패 : id 없음
        if(us.equals(Optional.empty())) {
            return "0";
        }

        //login 실패 : id는 있으나 비밀번호 틀림
        if(!us.get().getUser_pw().equals(password)) {
            return "0";
        }

        //login 성공
        return "1";
    }

    //회원가입 화면<GET>
    @GetMapping("/signup")
    public String Sign() {
        return "/signup.html";
    }

    //회원가입 API<POST>
    @PostMapping("/signup")
    public String Sign(@RequestParam String nickname,
                       @RequestParam String id,
                       @RequestParam String password, Model model) {

        //회원가입 요청이 오면
        //repository에 id 중복이 있나 확인을 하고
        //있는 경우 에러 return
        if(!repository.findByUserId(id).equals(Optional.empty())) {
            //restController 경우 에러 메세지로 0 반환
            //return "0";
            //controller 경우
            return "/";
        }
        //없는 경우 repository에 저장 후 return
        //////////////////////////////////////////
        //user 객체 생성
        User user = new User();
        user.setUser_id(id);
        user.setUser_pw(password);
        user.setName(nickname);
        user.setCreated_at(LocalDateTime.now());
        user.setUpdated_at(LocalDateTime.now());
        //repository에 저장
        repository.save(user);
        //////////////////////////////////////////

        //model에 user 추가
            model.addAttribute("user", user);
            //"SIGN UP SUCCESS"
        //restController 경우 정상 메세지로 1 반환
        //return "1";
        //controller 경우 html 파일 return
            return "/signup";

    }

}
