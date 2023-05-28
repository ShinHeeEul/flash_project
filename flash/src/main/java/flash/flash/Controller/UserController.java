package flash.flash.Controller;


import com.google.api.client.json.Json;
import flash.flash.DTO.LoginForm;
import flash.flash.JPA.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;

//////////////////////////////////////////////////////////

// UserControlller
//설명 : User 내용을 처리하는 Controller
//기능 : 회원가입, 로그인, 회원 페이지 API

//////////////////////////////////////////////////////////


@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    ///////////////////////////////////
    //데이터베이스
    //local
    @Autowired
    UserRepository repository;
    @Autowired
    DementiaRepository dementiaRepository;

    @Autowired
    ResultRepository resultRepository;
    ///////////////////////////////////



    ///////////////////////////////////
    //상수 선언
    final String ID = "ID";
    ///////////////////////////////////



    //login 화면<GET>
    @GetMapping("/login")
    public String Login(@ModelAttribute("form") LoginForm form) {
        return "loginform";
    }


    //login API<POST>
    @PostMapping("/login")
    public String Login(@Valid @ModelAttribute("form") LoginForm form,
                        BindingResult bindingResult,
                        HttpServletResponse response) {

        //데이터베이스로부터 해당 user_id에 해당하는 user가 있나 확인
        Optional<User> us = repository.findByuid(form.getUid());

        if(bindingResult.hasErrors()) {
            return "loginform";
        }

        //login 실패 : user_id 없음
        if(us.equals(Optional.empty())) {
            bindingResult.reject("loginFail", "아이디 혹은 비밀번호가 일치하지 않습니다.");
            return "loginform";
        }

        //login 실패 : user_id는 있으나 비밀번호 틀림
        if(!us.get().getUpw().equals(form.getUpw())) {
            bindingResult.reject("loginFail", "아이디 혹은 비밀번호가 일치하지 않습니다.");
            return "loginform";
        }

        if(us.get().getStatus() == 0) {
            bindingResult.reject("loginFail", "삭제된 회원입니다.");
            return "loginform";
        }

        //login 성공
        Cookie user_idCookie = new Cookie(ID,
                String.valueOf(us.get().getUser_id()));
        user_idCookie.setPath("/");
        response.addCookie(user_idCookie);


        return "redirect:/";
    }

    //LogOut 화면<POST>
    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie(ID,null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
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
    public ResponseEntity<User> Sign(@RequestParam String nickname,
                                     @RequestParam String user_id,
                                     @RequestParam String password, Model model) {

        //회원가입 요청이 오면
        //repository에 user_id 중복이 있나 확인을 하고
        //있는 경우 에러 return


        if(!repository.findByuid(user_id).equals(Optional.empty())) {
            //restController 경우 에러 메세지로 0 반환
            //return "0";
            //controller 경우
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("redirect:/"));
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }

        //없는 경우 repository에 저장 후 return
        //////////////////////////////////////////
        //user 객체 생성
        User user = User.builder()
                .status(1)
                .id(user_id)
                .pw(password)
                .name(nickname)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        //repository에 저장
        repository.save(user);
        //////////////////////////////////////////

        //model에 user 추가
        model.addAttribute("user", user);
        //"SIGN UP SUCCESS"
        //restController 경우 정상 메세지로 1 반환
        //return "1";
        //controller 경우 html 파일 return
        return new ResponseEntity<>(user, null, HttpStatus.OK);
    }

    //회원페이지
//혜원 수정
    @PatchMapping("/delete")
    public ResponseEntity<String> deleteUserPage(@CookieValue(value = ID, required = false) long user_id) {

        repository.updateStatusByStatus(0);

        return new ResponseEntity<>(repository.findById(user_id).get().getStatus() + "", null, HttpStatus.OK);
    }

    @PatchMapping("/resultdelete")
    public ResponseEntity<String> deleteResult(@CookieValue(value = ID, required = false) long user_id, @RequestParam("result_id") long result_id) {

        resultRepository.updateStatusBy(0);

        return new ResponseEntity<>(resultRepository.findById(user_id).get().getStatus() + "", null, HttpStatus.OK);
    }


    @GetMapping("/")
    @ResponseBody
    public String User_info(@CookieValue(name = ID, required = false) Long user_id)
    {

        Optional<User> us = repository.findById(user_id);
        List<Dementia> dem = dementiaRepository.findByUserIdWithJoin(user_id);


        JSONArray jsonArray = null;

        try {
            //JSONArray 객체 생성
            jsonArray = new JSONArray();

            //결과 데이터 query로 추출해서 반환해줘야함
            for(Dementia d : dem) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("user_id", us.get().getUid());
                jsonObject.put("name", us.get().getUpw());
                log.info("d : " + d.getDementia_id());
                log.info("result : " + d.getResult().getTest_result());
                if(d.getStatus() == 0) continue;
                jsonObject.put("dementia_id", d.getDementia_id());
                jsonObject.put("result", d.getResult().getTest_result());
                jsonArray.put(jsonObject);
            }

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("date", LocalDateTime.now());
            jsonObject1.put("testResult","high");
            jsonArray.put(jsonObject1);
            jsonObject1 = new JSONObject();
            jsonObject1.put("date",LocalDateTime.of(2022,01,01,01,01) );
            jsonObject1.put("testResult","low");

            jsonArray.put(jsonObject1);
        }

        catch(Exception e) {
            e.printStackTrace();
        }

        return jsonArray.toString();
    }


}
