package flash.flash.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

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
            file.transferTo(new File(fullPath));
        }

        return "upload-form";
    }
}
