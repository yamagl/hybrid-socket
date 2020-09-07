package com.abchina.sh.dev.hs.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@EnableWebSocket
@SpringBootApplication
public class HybridSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(HybridSocketApplication.class, args);
    }

    @Component
    public static class MyWebSocketConfigurer implements WebSocketConfigurer {

        @Override
        public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            registry.addHandler(new MyTextHandler(), "/text").setAllowedOrigins("*");
            registry.addHandler(new MyBinaryHandler(), "/binary").setAllowedOrigins("*");
        }
    }

    @Component
    public static class MyTextHandler extends TextWebSocketHandler {
        public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            session.sendMessage(new TextMessage("hello world"));
        }
    }

    @Component
    public static class MyBinaryHandler extends BinaryWebSocketHandler {
        public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
            try {
                session.sendMessage(new BinaryMessage("hello world".getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
