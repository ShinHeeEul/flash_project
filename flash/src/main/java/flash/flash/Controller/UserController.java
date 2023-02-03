package flash.flash.Controller;


import flash.flash.JPA.Dementia;
import flash.flash.JPA.User;
import flash.flash.repository.User_Repository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
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

    ///////////////////////////////////
    //데이터베이스
    //local
    User_Repository repository = new User_Repository();
    ///////////////////////////////////



    ///////////////////////////////////
    //상수 선언
    final String ID = "ID";
    ///////////////////////////////////



    //login 화면<GET>
    @GetMapping("/login")
    public String Login() {
        return "/login.html";
    }

    //login API<POST>
    @PostMapping("/login")
    //@ResponseBody
    public String Login(@RequestParam String user_id,
                        @RequestParam String password, Model model,
                        HttpServletResponse response) {
        //데이터베이스로부터 해당 user_id에 해당하는 user가 있나 확인
        Optional<User> us = repository.findById(user_id);

        //login 실패 : user_id 없음
        if(us.equals(Optional.empty())) {
            return "0";
        }

        //login 실패 : user_id는 있으나 비밀번호 틀림
        if(!us.get().getPw().equals(password)) {
            return "0";
        }

        //login 성공
        Cookie user_idCookie = new Cookie(ID,
                String.valueOf(us.get().getUser_id()));
        response.addCookie(user_idCookie);


        //return "1";
        return "redirect:/";
    }

    //LogOut 화면<POST>
    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie(ID,null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

    //회원가입 화면<GET>
    @GetMapping("/signup")
    public String Sign() {
        return "/signup.html";
    }

    //회원가입 API<POST>
    @PostMapping("/signup")
    public String Sign(@RequestParam String nickname,
                       @RequestParam String user_id,
                       @RequestParam String password, Model model) {

        //회원가입 요청이 오면
        //repository에 user_id 중복이 있나 확인을 하고
        //있는 경우 에러 return
        if(!repository.findById(user_id).equals(Optional.empty())) {
            //restController 경우 에러 메세지로 0 반환
            //return "0";
            //controller 경우
            return "redirect:/";
        }
        //없는 경우 repository에 저장 후 return
        //////////////////////////////////////////
        //user 객체 생성
        User user = new User();
        user.setStatus(1);
        user.setId(user_id);
        user.setPw(password);
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

    //회원페이지

    @GetMapping("/")
    public String homeLogin(
            @CookieValue(name = ID, required = false) Long user_id,
            Model model)
    {

        if(user_id == null) return "home";
        User us = repository.findByuser_id(user_id);
        if(us == null) return "home";
        model.addAttribute("user", us);

        return "loginHome";
    }

    @GetMapping("/user")
    @ResponseBody
    public String User_info(@CookieValue(name = ID, required = false) Long user_id)
    {

        User us = repository.findByuser_id(user_id);

        Dementia dem = new Dementia();


        JSONArray jsonArray = null;

        try {
            //JSONArray 객체 생성
            jsonArray = new JSONArray();

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("user_id", us.getId());
            jsonObject.put("name", us.getName());
            jsonObject.put("result",us.getResult_set());

            jsonArray.put(jsonObject);

        }

        catch(Exception e) {
            e.printStackTrace();
        }

        return jsonArray.toString();
    }

}
