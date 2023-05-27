package flash.flash.Controller;


import flash.flash.JPA.Result;
import flash.flash.JPA.ResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;


@Slf4j
@ClientEndpoint
public class WebSocketClient {

    ResultRepository resultRepository;

    public WebSocketClient() {

    }

    public WebSocketClient(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }


    @OnMessage
    public void onMessage(String message, Session session) {
        // 서버로부터 메시지를 수신할 때마다 실행되는 코드
        // 받은 message를 분석하여 사용자 입력 값을 체크하는 코드 작성
        float accuracy = Float.parseFloat(message);


        log.info("accuracy : " + accuracy);
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            session = container.connectToServer(WebSocketClient.class, URI.create("ws://localhost:8080/dementia/result?accuracy=" + accuracy));

        } catch(Exception e) {
            //e.printStackTrace(); //throw e;
        }

    }

    public void analysisSTT (String result, ResultRepository resultRepository) throws DeploymentException, IOException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            Session session = container.connectToServer(WebSocketClient.class, URI.create("ws://localhost:8765"));
            session.getBasicRemote().sendText(result);  // 서버에 메시지 전송
        } catch(Exception e) {
            //e.printStackTrace(); //throw e;
        }

    }
}
