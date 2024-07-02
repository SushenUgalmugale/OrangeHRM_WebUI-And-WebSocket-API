package websockets.client;

import org.testng.Assert;

import java.net.http.WebSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;

import static websockets.tests.publicMessages.WebsocketSpreadTest.STATE_lIST;

public class WebSocketClient implements WebSocket.Listener {
    private final CountDownLatch latch;
    SocketData data;

    String expectedData="{\"echo_req\":{\"states_list\":\"id\"},\"msg_type\":\"states_list\",\"states_list\":[{\"text\":\"Aceh\",\"value\":\"AC\"},{\"text\":\"Bali\",\"value\":\"BA\"},{\"text\":\"Bangka Belitung\",\"value\":\"BB\"},{\"text\":\"Banten\",\"value\":\"BT\"},{\"text\":\"Bengkulu\",\"value\":\"BE\"},{\"text\":\"Gorontalo\",\"value\":\"GO\"},{\"text\":\"Jakarta Raya\",\"value\":\"JK\"},{\"text\":\"Jambi\",\"value\":\"JA\"},{\"text\":\"Jawa\",\"value\":\"JW\"},{\"text\":\"Jawa Barat\",\"value\":\"JB\"},{\"text\":\"Jawa Tengah\",\"value\":\"JT\"},{\"text\":\"Jawa Timur\",\"value\":\"JI\"},{\"text\":\"Kalimantan\",\"value\":\"KA\"},{\"text\":\"Kalimantan Barat\",\"value\":\"KB\"},{\"text\":\"Kalimantan Selatan\",\"value\":\"KS\"},{\"text\":\"Kalimantan Tengah\",\"value\":\"KT\"},{\"text\":\"Kalimantan Timur\",\"value\":\"KI\"},{\"text\":\"Kepulauan Riau\",\"value\":\"KR\"},{\"text\":\"Lampung\",\"value\":\"LA\"},{\"text\":\"Maluku\",\"value\":\"ML\"},{\"text\":\"Maluku Utara\",\"value\":\"MU\"},{\"text\":\"Nusa Tenggara\",\"value\":\"NU\"},{\"text\":\"Nusa Tenggara Barat\",\"value\":\"NB\"},{\"text\":\"Nusa Tenggara Timur\",\"value\":\"NT\"},{\"text\":\"Papua\",\"value\":\"PA\"},{\"text\":\"Papua Barat\",\"value\":\"PB\"},{\"text\":\"Riau\",\"value\":\"RI\"},{\"text\":\"Sulawesi\",\"value\":\"SL\"},{\"text\":\"Sulawesi Barat\",\"value\":\"SR\"},{\"text\":\"Sulawesi Selatan\",\"value\":\"SN\"},{\"text\":\"Sulawesi Tengah\",\"value\":\"ST\"},{\"text\":\"Sulawesi Tenggara\",\"value\":\"SG\"},{\"text\":\"Sulawesi Utara\",\"value\":\"SA\"},{\"text\":\"Sumatera\",\"value\":\"SM\"},{\"text\":\"Sumatera Utara\",\"value\":\"SU\"},{\"text\":\"Sumatra Barat\",\"value\":\"SB\"},{\"text\":\"Sumatra Selatan\",\"value\":\"SS\"},{\"text\":\"Yogyakarta\",\"value\":\"YO\"}]}";

    public WebSocketClient(CountDownLatch latch, SocketData data) {
        this.latch = latch;
        this.data = data;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()) + ": Connected to WebSocket server: " + this.data.getURI());
        WebSocket.Listener.super.onOpen(webSocket);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        DateTimeFormatter dtfs = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dtfs.format(LocalDateTime.now()) + ": Sent Request is: "+STATE_lIST);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()) + ": Received data : " + data);
        Assert.assertEquals(data.toString(), expectedData, "Response is mismatched");
        latch.countDown();
        webSocket.abort();
        return WebSocket.Listener.super.onText(webSocket, data, false);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        System.out.println("ERROR OCCURRED: " + webSocket.toString());
        WebSocket.Listener.super.onError(webSocket, error);
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int code, String reason) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()) + ": Disconnected from websocket server: " + this.data.getURI());
        return WebSocket.Listener.super.onClose(webSocket, code, reason);
    }

}