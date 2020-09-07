package com.abchina.sh.dev.hs.server.ws;

import com.abchina.sh.dev.hs.server.socket.SocketClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WSServerHandler extends BinaryWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SocketClient socketClient;
    private final ConcurrentHashMap<String, WSStatus> statusMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    private final NetClient netClient = Vertx.vertx().createNetClient();


    @Autowired
    public WSServerHandler(SocketClient socketClient) {
        this.socketClient = socketClient;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        statusMap.put(session.getId(), WSStatus.HANDSHAKE);
        sessionMap.put(session.getId(), session);
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        statusMap.remove(session.getId());
        sessionMap.remove(session.getId());
    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        WSRequest request = WSRequest.parse(message.getPayload().array());
        netClient.connect(request.port, request.addr, socket -> {
            if (socket.succeeded()) {

            } else {
                session.
            }
        });

    }

}
