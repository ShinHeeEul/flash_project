package flash.flash.Controller;

import flash.flash.STT.SpeechToText;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;


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
    public String saveFile(@RequestParam String file_name,
                           @RequestParam MultipartFile file,
                           HttpServletRequest request) throws IOException {

        //파일이 있다면, 파일 위치를 동적으로 확인해서 서버에 저장
        if(!file.isEmpty()) {
            String fileDir = request.getServletContext().getRealPath("/upload");
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("file",file.getContentType());
            //파일이 mp3 파일일 경우 stt로 보내 분석
            if(file.getContentType() == ".mp3") {
                SpeechToText stt = new SpeechToText(fullPath);
            }
            file.transferTo(new File(fullPath));
        }

        return "upload-form";
    }

    //분석 요청한 dementia의 result를 클라이언트에 반환하는 기능
    @PostMapping("/dementia/result")
    public String res_result() {
        return null;
    }
}
