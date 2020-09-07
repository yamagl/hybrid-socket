package com.abchina.sh.dev.hs.server.ws;

public class WSRequest {

    public final String addr;
    public final int port;
    public final String rsv;


    public WSRequest(String addr, int port, String rsv) {
        this.addr = addr;
        this.port = port;
        this.rsv = rsv;
    }

    public static WSRequest parse(byte[] bytes) {
//        TODO
        return new WSRequest("www.baidu.com", 80, "");
    }

}
