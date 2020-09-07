package com.abchina.sh.dev.hs.server;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.WebSocket;
import org.junit.jupiter.api.Test;

public class WebSocketTest {

    @Test
    public void sockJS() throws InterruptedException {
        HttpClient client = Vertx.vertx().createHttpClient();

        client.webSocket(8080, "localhost", "/binary", res -> {
            if (res.succeeded()) {
                WebSocket ws = res.result();
                System.out.println("Connected!");
                ws
                        .write(Buffer.buffer("lll"))
                        .handler(buffer -> {
                            System.out.println(buffer.getString(0, buffer.length()));
                            ws.write(Buffer.buffer("aaaa"));
//                            ws.end();
                        })
                        .endHandler(v -> System.out.println("The socket has been closed"));
            } else {
                System.out.println("Failed!");
                System.exit(-1);
            }
        });

        Thread.sleep(600000L);
    }
}
