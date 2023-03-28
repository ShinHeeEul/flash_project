package flash.flash.Controller;


import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class WebSocketClient {
    @OnMessage
    public String onMessage(String message, Session session) {
        // 서버로부터 메시지를 수신할 때마다 실행되는 코드
        // 받은 message를 분석하여 사용자 입력 값을 체크하는 코드 작성
        return message;
    }

    public void analysisSTT (String result) throws DeploymentException, IOException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
                Session session = container.connectToServer(WebSocketClient.class, URI.create("ws://localhost:8765"));
                session.getBasicRemote().sendText(result);  // 서버에 메시지 전송
        } catch(Exception e) {//e.printStackTrace(); //throw e;
            }

    }
}
