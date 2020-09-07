package com.abchina.sh.dev.hs.server;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;
import org.junit.jupiter.api.Test;

public class SocketClientTest {

    @Test
    public void sendSocket() throws InterruptedException {
        NetClientOptions  options = new NetClientOptions().setLogActivity(true);

        NetClient netClient = Vertx.vertx().createNetClient(options);

        netClient.connect(80, "www.baidu.com", result -> {
            if (result.succeeded()) {
                NetSocket clientSocket = result.result();
                String request = "GET / HTTP/1.1\n" +
                        "Host: www.baidu.com\n" +
                        "User-Agent: curl/7.55.1\n" +
                        "Accept: */*\r\n\r\n";
//                clientSocket.handler(buffer -> {
//                    System.out.println(buffer.length());
//                    System.out.println(buffer.getString(0, 4));
//                });

                clientSocket.write(request).handler(buffer -> {
                    System.out.println(buffer.length());
                    System.out.println(buffer.getString(0, buffer.length()));
                }).closeHandler(v -> {
                    System.out.println("The socket has been closed");
                }).exceptionHandler(Throwable::printStackTrace);
            } else {
                System.out.println("fail");
            }
        });

        Thread.sleep(60000000000L);
    }
}
