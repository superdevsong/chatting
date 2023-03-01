package com.chatting.dao;

import org.json.JSONObject;

public interface SignService {
    String login();
    void logout();
    void signup(JSONObject jsonObject);
}
