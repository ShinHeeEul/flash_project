package flash.flash.Controller;

import flash.flash.JPA.*;
import flash.flash.STT.NestRequestEntity;
import flash.flash.STT.SpeechToText;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.*;


//////////////////////////////////////////////////////////

// DementiaControlller
//설명 : 음성 데이터를 받고 이를 stt 서버와 ai로 넘겨주는 서버
//기능 : 업로드 API(이 부분에서 stt 서버로 전달), 결과 반환 API

//////////////////////////////////////////////////////////
enum FILETYPE {
    MP3("audio/mpeg"),
    M4A("audio/x-m4a");

    private String type;
    FILETYPE(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
@Controller
@Slf4j
public class DementiaController {

    final String ID = "ID";

    @Autowired
    UserRepository userRepository;

    @Autowired
    DementiaRepository dementiaRepository;

    @Autowired
    ResultRepository resultRepository;


    //업로드 화면<GET>
    @GetMapping("/upload")
    public String Upload() {
        return "upload-form";
    }

    //파일이 업로드 되었을 떄 동작하는 함수<POST>
    //이 부분은 아직 개선이 필요함
    @PostMapping("/upload")
    //@ResponseBody
    public String saveFile(@RequestParam("voice_file") MultipartFile voice_file,
                           HttpServletRequest request,
                           @CookieValue(name = ID, required = false) Long user_id,
                           Model model) throws Exception {
        String result = null;
        //파일이 있다면, 파일 위치를 동적으로 확인해서 서버에 저장
        if(!voice_file.isEmpty()) {
            String fileDir = request.getServletContext().getRealPath("/upload");
            String fullPath = fileDir + "\\" + voice_file.getOriginalFilename();
            //log.info("file name = " + voice_file.getContentType());
            //파일이 m4a, mp3 파일일 경우 stt로 보내 분석
            if((voice_file.getContentType().equals(FILETYPE.M4A.getType())) ||
                    (voice_file.getContentType().equals(FILETYPE.MP3.getType()))) {

                //전송 받은 파일로 분석
                File Local_File = new File(fileDir);
                voice_file.transferTo(Local_File);

                //기존 있는 파일로 분석
                //File Local_File = new File(fileDir);

                //stt에 보내서 분석
                SpeechToText stt = new SpeechToText();
                NestRequestEntity requestEntity = new NestRequestEntity();
                result = stt.upload(Local_File, requestEntity);

                //AI Model에 데이터 전송
                String AI_ans = null;

                /*
                //AI Model 결과 Result Repository에 저장
                Result res = Result.builder()
                        .test_result(AI_ans)
                        .status(1)
                        .build();

                // 데이터베이스에 결과 저장
                Optional<User> us = userRepository.findById(user_id);
                if(us == null) return "error";
                Dementia dem = Dementia.builder().user(us.get())
                        .result(res)
                        .status(1)
                        .user_dialog(0)
                        .created_at(LocalDateTime.now())
                        .build();
                */

                //log.info(result);
                JSONObject jsonObject = new JSONObject(result);
                result = (String) jsonObject.get("text");
                //결과 반환
                WebSocketClient webSocketClient = new WebSocketClient();
                webSocketClient.analysisSTT(result);

                model.addAttribute("result", result);
                return "Analysis_Result";
            }
        }

        return "redirect:/";
    }

    //분석 요청한 dementia의 result를 클라이언트에 반환하는 기능
    @PostMapping("/dementia/result")
    @ResponseBody
    public String res_result(@ModelAttribute String result) { return result; }

}
