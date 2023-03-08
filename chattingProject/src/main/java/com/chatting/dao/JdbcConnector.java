package com.chatting.dao;


import com.chatting.http.HttpServerManager;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {
    static final String url;
    static final String id;
    static final String password;

    static {
        InputStream inputStream = HttpServerManager.class.getClassLoader().getResourceAsStream("setting.json");
        if (inputStream != null) {
            byte[] bytes = null;
            try {
                bytes = inputStream.readAllBytes();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = new JSONObject(new String(bytes, StandardCharsets.UTF_8));
            url = "jdbc:mysql://localhost:3306/" + jsonObject.getString("database");
            id = jsonObject.getString("id");
            password = jsonObject.getString("password");
            System.out.println("jdbc setup");
        } else {
            url = null;
            id = null;
            password = null;
            System.out.println("jdbc not setup");
        }
    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,
                    id, password);
            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

}
