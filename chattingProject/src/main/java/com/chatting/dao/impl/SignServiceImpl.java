package com.chatting.dao.impl;

import com.chatting.dao.JdbcConnector;
import com.chatting.dao.SignService;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

public class SignServiceImpl implements SignService {
    JdbcConnector jdbcConnector = new JdbcConnector();

    @Override
    public String login() {
        Connection conn = jdbcConnector.connect();
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public void signup(JSONObject jsonObject) {
        Connection conn = jdbcConnector.connect();
        try {
            String sql = "insert into user(user_id, user_password, user_name, birth,user_email,phone_number,gender,salt)" +
                    " values(?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            System.out.println(sql);
            pstmt.setString(1,jsonObject.getString("user_id"));
            String salt = getSalt();
            pstmt.setString(2,getEncrypt(jsonObject.getString("user_password"),salt));
            pstmt.setString(3,jsonObject.getString("user_name"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            pstmt.setDate(4, new Date(sdf.parse(jsonObject.getString("birth"),new ParsePosition(0)).getTime()));
            pstmt.setString(5,jsonObject.getString("user_email"));
            pstmt.setString(6,jsonObject.getString("phone_number"));
            pstmt.setString(7,jsonObject.getString("gender"));
            pstmt.setString(8,salt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public String getSalt(){
        SecureRandom r = new SecureRandom();
        byte[] salt = new byte[20];

        r.nextBytes(salt);

        StringBuilder sb = new StringBuilder();
        for(byte b : salt)
            sb.append(String.format("%02x",b));

        return sb.toString();
    }

    public String getEncrypt(String pwd,String salt){
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((pwd+salt).getBytes());
            byte[] pwdsalt = md.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b : pwdsalt)
                sb.append(String.format("%02x",b));

            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }
}
