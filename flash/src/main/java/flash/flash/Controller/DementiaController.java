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

@Controller
@Slf4j
public class DementiaController {


    @GetMapping("/upload")
    public String Upload() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam String file_name,
                           @RequestParam MultipartFile file,
                           HttpServletRequest request) throws IOException {

        if(!file.isEmpty()) {
            //log.info(request.getServletContext().getRealPath("/upload"));
            String fileDir = request.getServletContext().getRealPath("/upload");
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("file",file.getContentType());
            if(file.getContentType() == ".m4a") {
                SpeechToText stt = new SpeechToText(fullPath);
            }
            file.transferTo(new File(fullPath));
        }

        return "upload-form";
    }
}
