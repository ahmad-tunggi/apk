package com.example.apk.response;

import com.example.apk.model.DataAjuan;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class R_ajuan {
    @SerializedName("data")
    private List<DataAjuan> data;

    @SerializedName("messages")
    private String messages;

    @SerializedName("status")
    private boolean status;

    public List<DataAjuan> getData() {
        return data;
    }

    public void setData(List<DataAjuan> data) {
        this.data = data;
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
