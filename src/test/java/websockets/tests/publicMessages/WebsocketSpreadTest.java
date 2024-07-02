package websockets.tests.publicMessages;

import websockets.client.SocketData;
import websockets.factory.WebSocketFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class WebsocketSpreadTest {

    protected SocketData socketData;

    @BeforeTest
    public void createSocketData(){
        socketData = new SocketData();
        socketData.setURI("wss://ws.derivws.com/websockets/v3?app_id=36544");
        socketData.setTimeOut(0);
    }

    public static final String STATE_lIST ="{\"states_list\": \"id\"}";

    @Test(description = "Validate connection and response")
    public void validateSpreadPayloadSsTATEListchemaTest(){
        socketData.setCountDown(1);
        socketData.setSubscriptionMessage(STATE_lIST);
        SocketData responseContext= WebSocketFactory.OpenAndStreamWebSocketSubscription(socketData);
    }

    @AfterTest
    public void closeWebSocket(){
        try {
            // Check if the WebSocket is not null before attempting to close it
            if (socketData != null && socketData.getWebSocket() != null) {
                socketData.getWebSocket().close();
                System.out.println("WebSocket connection closed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}