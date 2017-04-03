package com.service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by nikita on 26.03.2017.
 */

@ServerEndpoint("/websocket")
public class Websocket {
    @OnOpen
    public void eventOpen(){
        System.out.println("Соединение установленно...");
    }

    @OnClose
    public void eventClose(){
        System.out.println("Соединение закрыто");

    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println("Input: " + msg);
        try {
            session.getBasicRemote().sendText("Hello " + msg);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @OnError
    public void eventError(Throwable t){
        System.err.println("Error WebSocket");
    }
}
