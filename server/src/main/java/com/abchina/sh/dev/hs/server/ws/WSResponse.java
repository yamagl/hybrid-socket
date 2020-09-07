package com.abchina.sh.dev.hs.server.ws;

public class WSResponse {

    private int code;
    private String reply;

    public static WSResponse connected() {
        WSResponse response = new WSResponse();
        response.code = 20000;
        response.reply = "connected";
        return response;
    }


    public static WSResponse connectFailed() {
        WSResponse response = new WSResponse();
        response.code = 50000;
        response.reply = "connect failed";
        return response;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\": " + code +
                ", \"reply\":\"" + reply + '\"' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
