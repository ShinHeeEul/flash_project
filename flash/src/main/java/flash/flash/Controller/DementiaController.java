package flash.flash.Controller;

import flash.flash.JPA.*;
import flash.flash.Model.FILETYPE;
import flash.flash.Model.TextbySpeaker;
import flash.flash.STT.NestRequestEntity;
import flash.flash.STT.SpeechToText;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.DeploymentException;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;


//////////////////////////////////////////////////////////

// DementiaControlller
//설명 : 음성 데이터를 받고 이를 stt 서버와 ai로 넘겨주는 서버
//기능 : 업로드 API(이 부분에서 stt 서버로 전달), 결과 반환 API

//////////////////////////////////////////////////////////
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

                //result = "test";
                //해당 text를 view로 반환
                //log.info("result : " + result);

                ArrayList<TextbySpeaker> seperate = seperateText(result);


                model.addAttribute("seperate", seperate);
                return "modifyText";
            }
        }

        return "redirect:/";
    }

    private static ArrayList<TextbySpeaker> seperateText(String result) {

        ArrayList<TextbySpeaker> speakerRepository = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("segments");


        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            TextbySpeaker tbs = new TextbySpeaker();
            boolean b = false;
            String name = obj.getJSONObject("speaker").getString("name");
            String text = obj.getString("text");
            for(TextbySpeaker tmp : speakerRepository) {
                if(tmp.getName() == name) {
                    tbs = tmp;
                     b= true;
                }
            }
            tbs.addText(text);
            if(!b) {
                tbs.setName(name);
                speakerRepository.add(tbs);
            }
        }

        return speakerRepository;
    }

    //view에서 수정한 파일이 오면 해당 데이터 받아서 처리

    @PostMapping("/modified")
    @ResponseBody
    public String ModifiedText(@ModelAttribute("result") ArrayList<TextbySpeaker> textbySpeakers) throws DeploymentException, IOException {

            return result;
            //AI Model에 데이터 전송
            //String AI_ans = null;

           // JSONObject jsonObject = new JSONObject(result);
            //result = (String) jsonObject.get("text");
            //결과 반환
            //WebSocketClient webSocketClient = new WebSocketClient();
           // webSocketClient.analysisSTT(result);

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
        //return "Analysis_Result";
    }

    //분석 요청한 dementia의 result를 클라이언트에 반환하는 기능
    @PostMapping("/dementia/result")
    @ResponseBody
    public String res_result(@ModelAttribute String result) { return result; }

}
