package com.example.apk.response;

import com.example.apk.model.DataLogin;
import com.google.gson.annotations.SerializedName;

public class R_login {
    @SerializedName("data")
    private DataLogin dataLogin;

    @SerializedName("messages")
    private String messages;

    @SerializedName("status")
    private boolean status;


    public DataLogin getDataLogin() {
        return dataLogin;
    }
    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
