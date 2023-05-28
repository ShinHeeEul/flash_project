package flash.flash.Controller;

import flash.flash.DTO.FILETYPE;
import flash.flash.DTO.TextbySpeakerList;
import flash.flash.JPA.*;
import flash.flash.DTO.TextbySpeaker;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.DeploymentException;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;


//////////////////////////////////////////////////////////

// DementiaControlller
//설명 : 음성 데이터를 받고 이를 stt 서버와 ai로 넘겨주는 서버
//기능 : 업로드 API(이 부분에서 stt 서버로 전달), 결과 반환 API

//////////////////////////////////////////////////////////
@Controller
@Slf4j
@RequestMapping("/dementia")
public class DementiaController {

    final String ID = "ID";

    @Autowired
    UserRepository userRepository;

    @Autowired
    DementiaRepository dementiaRepository;

    @Autowired
    ResultRepository resultRepository;

    Dementia d;



    //업로드 화면<GET>
    @GetMapping("/upload")
    public String Upload() {
        return "upload-form";
    }

    //파일이 업로드 되었을 떄 동작하는 함수<POST>
    //이 부분은 아직 개선이 필요함
    @PostMapping("/upload")
    //@ResponseBody
    public /*ResponseEntity<ArrayList<TextbySpeaker>>*/ String saveFile(@RequestParam("voice_file") MultipartFile voice_file,
                                   HttpServletRequest request, Model model) throws Exception {
        String result = "";
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
                //SpeechToText stt = new SpeechToText();
                //NestRequestEntity requestEntity = new NestRequestEntity();
                //result = stt.upload(Local_File, requestEntity);

                //result = "test";
                result = """
                       {"result":"SUCCEEDED","message":"Succeeded","token":"e976110594224f30a3255a6fd90a87c7","version":"ncp_v2_v2.1.6-d90fef3-20230420_v1.5.9_v4.1.5_ko_ncp_20221227_","params":{"service":"ncp","domain":"general","lang":"ko","completion":"async","diarization":{"enable":true,"speakerCountMin":-1,"speakerCountMax":-1},"boostings":[],"forbiddens":"","wordAlignment":true,"fullText":true,"noiseFiltering":true,"priority":0,"userdata":{"_ncp_DomainCode":"flashtest","_ncp_DomainId":4760,"_ncp_TaskId":12918880,"_ncp_TraceId":"9975c97da14b4ed6a76a033a7cbc4c68"}},"progress":100,"keywords":{},"segments":[{"start":380,"end":1690,"text":"처음. 뵙겠습니다.","confidence":0.9911771,"diarization":{"label":"1"},"speaker":{"label":"1","name":"A","edited":false},"words":[[610,820,"처음."],[820,1380,"뵙겠습니다."]],"textEdited":"처음. 뵙겠습니다."},{"start":2480,"end":5230,"text":"안녕하세요. 저는 김혜원입니다.","confidence":0.9973996,"diarization":{"label":"2"},"speaker":{"label":"2","name":"B","edited":false},"words":[[2570,3320,"안녕하세요."],[3930,4200,"저는"],[4230,5100,"김혜원입니다."]],"textEdited":"안녕하세요. 저는 김혜원입니다."},{"start":5230,"end":10240,"text":"김혜영 씨시군요. 반갑습니다. 저는 신희을이라고 합니다.","confidence":0.9438745,"diarization":{"label":"1"},"speaker":{"label":"1","name":"A","edited":false},"words":[[6040,6410,"김혜영"],[6480,7090,"씨시군요."],[7300,7990,"반갑습니다."],[8180,8490,"저는"],[8560,9637,"신희을이라고"],[9637,10010,"합니다."]],"textEdited":"김혜영 씨시군요. 반갑습니다. 저는 신희을이라고 합니다."},{"start":10240,"end":20540,"text":"그렇군요. 저는 건국대학교에 재학 중이고요 영어 영문학과를 전공하고 있습니다. 희은 님은 어떤 학과를 전공하고 계신가요","confidence":0.89164644,"diarization":{"label":"2"},"speaker":{"label":"2","name":"B","edited":false},"words":[[11450,12060,"그렇군요."],[12570,12860,"저는"],[12970,13860,"건국대학교에"],[13890,14160,"재학"],[14230,14780,"중이고요"],[15310,15600,"영어"],[15600,16300,"영문학과를"],[16300,16847,"전공하고"],[16847,17400,"있습니다."],[17910,18220,"희은"],[18230,18500,"님은"],[18550,18860,"어떤"],[18870,19280,"학과를"],[19280,19820,"전공하고"],[19820,20420,"계신가요"]],"textEdited":"그렇군요. 저는 건국대학교에 재학 중이고요 영어 영문학과를 전공하고 있습니다. 희은 님은 어떤 학과를 전공하고 계신가요"},{"start":20540,"end":23590,"text":"저는 컴퓨터 공학부에 재학 중에 있습니다.","confidence":0.9579974,"diarization":{"label":"1"},"speaker":{"label":"1","name":"A","edited":false},"words":[[20950,21240,"저는"],[21310,21700,"컴퓨터"],[21700,22260,"공학부에"],[22260,22460,"재학"],[22490,22760,"중에"],[22760,23260,"있습니다."]],"textEdited":"저는 컴퓨터 공학부에 재학 중에 있습니다."},{"start":23730,"end":27740,"text":"그렇습니까 컴퓨터 공학부는 재밌습니까","confidence":0.9759528,"diarization":{"label":"2"},"speaker":{"label":"2","name":"B","edited":false},"words":[[24180,24890,"그렇습니까"],[25820,26250,"컴퓨터"],[26260,26750,"공학부는"],[26780,27550,"재밌습니까"]],"textEdited":"그렇습니까 컴퓨터 공학부는 재밌습니까"},{"start":28430,"end":31930,"text":"많이 힘듭니다. 영문학과는 좀 어떻습니까","confidence":0.9859037,"diarization":{"label":"1"},"speaker":{"label":"1","name":"A","edited":false},"words":[[29060,29330,"많이"],[29340,29910,"힘듭니다."],[30040,30770,"영문학과는"],[30820,30970,"좀"],[30980,31710,"어떻습니까"]],"textEdited":"많이 힘듭니다. 영문학과는 좀 어떻습니까"},{"start":31930,"end":40130,"text":"영어영문학과는 여러 가지 연리 문학과 문법 등을 배울 수 있어서 매우 흥미로운 학과입니다.","confidence":0.924594,"diarization":{"label":"2"},"speaker":{"label":"2","name":"B","edited":false},"words":[[32140,33210,"영어영문학과는"],[33300,33570,"여러"],[33580,33930,"가지"],[34200,34850,"연리"],[34960,35530,"문학과"],[36300,36670,"문법"],[36800,37110,"등을"],[37120,37357,"배울"],[37357,37457,"수"],[37457,37830,"있어서"],[38020,38290,"매우"],[38290,38770,"흥미로운"],[38780,39490,"학과입니다."]],"textEdited":"영어영문학과는 여러 가지 연리 문학과 문법 등을 배울 수 있어서 매우 흥미로운 학과입니다."},{"start":40130,"end":42990,"text":"그렇군요. 정말 흥미로운 학과인 것 같습니다.","confidence":0.98396504,"diarization":{"label":"1"},"speaker":{"label":"1","name":"A","edited":false},"words":[[40220,40770,"그렇군요."],[40900,41190,"정말"],[41200,41710,"흥미로운"],[41740,42150,"학과인"],[42150,42290,"것"],[42290,42770,"같습니다."]],"textEdited":"그렇군요. 정말 흥미로운 학과인 것 같습니다."},{"start":42990,"end":49490,"text":"그렇다면 어 영문학과를 복수 전공해보는 건 어떠세요.","confidence":0.946396,"diarization":{"label":"2"},"speaker":{"label":"2","name":"B","edited":false},"words":[[43240,43850,"그렇다면"],[45960,46110,"어"],[46120,47330,"영문학과를"],[47660,47970,"복수"],[47970,48630,"전공해보는"],[48630,48750,"건"],[48750,49290,"어떠세요."]],"textEdited":"그렇다면 어 영문학과를 복수 전공해보는 건 어떠세요."},{"start":50830,"end":51390,"text":"하하","confidence":0.46162903,"diarization":{"label":"2"},"speaker":{"label":"2","name":"B","edited":false},"words":[[50920,51230,"하하"]],"textEdited":"하하"},{"start":51730,"end":57690,"text":"갑자기 그렇게 가버리면 그렇게 가버리시면 제가 좀 곤란합니다.","confidence":0.957597,"diarization":{"label":"1"},"speaker":{"label":"1","name":"A","edited":false},"words":[[52000,52550,"갑자기"],[52680,53050,"그렇게"],[53100,53630,"가버리면"],[55100,55450,"그렇게"],[55520,56110,"가버리시면"],[56240,56510,"제가"],[56520,56670,"좀"],[56720,57390,"곤란합니다."]],"textEdited":"갑자기 그렇게 가버리면 그렇게 가버리시면 제가 좀 곤란합니다."},{"start":58380,"end":62390,"text":"흥미가 있으신 줄 알았어요. 그렇다면 죄송합니다.","confidence":0.98204625,"diarization":{"label":"2"},"speaker":{"label":"2","name":"B","edited":false},"words":[[58710,59140,"흥미가"],[59140,59460,"있으신"],[59490,59640,"줄"],[59650,60280,"알았어요."],[60470,61040,"그렇다면"],[61190,61920,"죄송합니다."]],"textEdited":"흥미가 있으신 줄 알았어요. 그렇다면 죄송합니다."}],"text":"처음. 뵙겠습니다. 안녕하세요. 저는 김혜원입니다. 김혜영 씨시군요. 반갑습니다. 저는 신희을이라고 합니다. 그렇군요. 저는 건국대학교에 재학 중이고요 영어 영문학과를 전공하고 있습니다. 희은 님은 어떤 학과를 전공하고 계신가요 저는 컴퓨터 공학부에 재학 중에 있습니다. 그렇습니까 컴퓨터 공학부는 재밌습니까 많이 힘듭니다. 영문학과는 좀 어떻습니까 영어영문학과는 여러 가지 연리 문학과 문법 등을 배울 수 있어서 매우 흥미로운 학과입니다. 그렇군요. 정말 흥미로운 학과인 것 같습니다. 그렇다면 어 영문학과를 복수 전공해보는 건 어떠세요. 하하 갑자기 그렇게 가버리면 그렇게 가버리시면 제가 좀 곤란합니다. 흥미가 있으신 줄 알았어요. 그렇다면 죄송합니다.","confidence":0.9443699,"speakers":[{"label":"1","name":"A","edited":false},{"label":"2","name":"B","edited":false}]}
                       """;
                //해당 text를 view로 반환
                log.info("result : " + result);

                ArrayList<TextbySpeaker> seperate = seperateText(result);

                model.addAttribute("seperate", seperate);
                //return new ResponseEntity<>(seperate, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return "modifyText";
        //return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/result/save")
    @ResponseBody
    public void getResult(@RequestParam String accuracy) {


        String res = "";
        double ac = Double.parseDouble(accuracy);
        if(ac <= 0.4) res = "양호";
        else if(ac <= 0.7) res = "주의";
        else res = "위험";


        Result r = Result.builder().
                created_at(LocalDateTime.now()).
                status(1).
                test_result(res).
                updated_at(LocalDateTime.now()).
                build();


        resultRepository.save(r);
        d.setResult(r);


        log.info("result1 : " + res);


        dementiaRepository.save(d);
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
                if(tmp.getName().equals(name)) {
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
    public void ModifiedText(@ModelAttribute("result") TextbySpeakerList textbySpeakerList,
                                   @CookieValue(name = ID, required = false) Long user_id) throws DeploymentException, IOException {
        String radio = textbySpeakerList.getRadio();
        String result = "";

        log.info("nameA" + textbySpeakerList.getNameA());
        log.info("TextA" + textbySpeakerList.getTextA());
        log.info("nameB" + textbySpeakerList.getNameB());
        log.info("TextB" + textbySpeakerList.getTextB());

        switch(radio) {
            case "A":
                for(String s : textbySpeakerList.getTextA()) {
                    result += s;
                    result += "\n";
                }

                break;
            case "B":
                for(String s : textbySpeakerList.getTextB()) {
                    result += s;
                    result += "\n";
                }
                break;
        }

        d = Dementia.builder().
                created_at(LocalDateTime.now()).
                status(1).
                user(userRepository.findById(user_id).get()).
                user_dialog(result).
                build();

        //AI Model에 데이터 전송
        WebSocketClient webSocketClient = new WebSocketClient();
        webSocketClient.analysisSTT(result);



        log.info(result);
    }


    //분석 요청한 dementia의 result를 클라이언트에 반환하는 기능
    @GetMapping("/result")
    @ResponseBody
    public String res_result(@CookieValue(value = ID,required = false) Long user_id) {
        // user_id로 dementia List 조회
        List<Dementia> dementias = dementiaRepository.findByUserIdWithJoin(user_id);

        LocalDateTime recent = LocalDateTime.MIN;
        Dementia recentDem = null;
        //가장 최신 dementia 조회
        for(Dementia dementia : dementias) {
            if(dementia.getCreated_at().isAfter(recent)) {
                recent = dementia.getCreated_at();
                recentDem = dementia;
            }
        }

        if(recentDem != null) return recentDem.getResult().getTest_result();
        return null;
    }

}
