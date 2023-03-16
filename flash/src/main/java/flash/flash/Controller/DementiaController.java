package flash.flash.Controller;

import flash.flash.STT.NestRequestEntity;
import flash.flash.STT.SpeechToText;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


//////////////////////////////////////////////////////////

// DementiaControlller
//설명 : 음성 데이터를 받고 이를 stt 서버와 ai로 넘겨주는 서버
//기능 : 업로드 API(이 부분에서 stt 서버로 전달), 결과 반환 API

//////////////////////////////////////////////////////////

@Controller
@Slf4j
public class DementiaController {


    //업로드 화면<GET>
    @GetMapping("/upload")
    public String Upload() {
        return "upload-form";
    }

    //파일이 업로드 되었을 떄 동작하는 함수<POST>
    //이 부분은 아직 개선이 필요함
    @PostMapping("/upload")
    @ResponseBody
    public String saveFile(@RequestParam("voice_file") MultipartFile voice_file,
                           HttpServletRequest request) throws IOException {
        String result = null;
        //파일이 있다면, 파일 위치를 동적으로 확인해서 서버에 저장
        if(!voice_file.isEmpty()) {
            String fileDir = request.getServletContext().getRealPath("/upload");
            String fullPath = fileDir + "\\" + voice_file.getOriginalFilename();
            //log.info("file name = " + fullPath);
            //파일이 mp3 파일일 경우 stt로 보내 분석
            if(voice_file.getContentType().equals("audio/mpeg")) {
                //전송 받은 파일로 분석 할 수 있게 하기
                File Local_File = new File(fileDir);
                voice_file.transferTo(Local_File);
                //기존 있는 파일로 분석
                //File Local_File = new File(fileDir);
                SpeechToText stt = new SpeechToText();
                NestRequestEntity requestEntity = new NestRequestEntity();
                result = stt.upload(Local_File, requestEntity);

                log.info(result);

                return result;
            }
        }

        return "error";
    }

    //분석 요청한 dementia의 result를 클라이언트에 반환하는 기능
    @PostMapping("/dementia/result")
    public String res_result() {
        return null;
    }
}
