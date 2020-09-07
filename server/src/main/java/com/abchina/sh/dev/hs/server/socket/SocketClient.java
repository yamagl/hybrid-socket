package com.abchina.sh.dev.hs.server.socket;

import com.abchina.sh.dev.hs.server.ws.WSRequest;
import com.abchina.sh.dev.hs.server.ws.WSResponse;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;


@Component
public class SocketClient {

    private final NetClient netClient = Vertx.vertx().createNetClient();

    public void sendRequest(final WSRequest request, final WebSocketSession session) {
        netClient.connect(request.port, request.addr, socket -> {
            if (socket.succeeded()) {
                try {
                    session.sendMessage(new BinaryMessage(WSResponse.connected().toString().getBytes()));
                    session.
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    session.sendMessage(new BinaryMessage(WSResponse.connectFailed().toString().getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
