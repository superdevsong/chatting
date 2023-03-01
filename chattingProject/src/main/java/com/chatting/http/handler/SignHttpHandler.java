package com.chatting.http.handler;

import com.chatting.dao.SignService;
import com.chatting.dao.impl.SignServiceImpl;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class SignHttpHandler implements HttpHandler {
    private SignService signService = new SignServiceImpl();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Initialize Response Body
        OutputStream respBody = exchange.getResponseBody();
        System.out.println("Ddd");

        try {
            String path = exchange.getRequestURI().getPath();
            String method = exchange.getRequestMethod();
            if(path.equals("/sign/signup")){
                if(method.equals("POST")) {
                    byte[] bytes = exchange.getRequestBody().readAllBytes();
                    String data = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println(data);
                    signService.signup(new JSONObject(data));
                }
            } else if(path.equals("/sign/login")){

            } else if(path.equals("/sign/logout")){

            }

        } catch(Exception e) {
            System.out.println(e);
        } finally {
            exchange.close();
        }
    }
}