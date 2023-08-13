package com.example.apk.response;

import com.example.apk.model.Datadiverifikasi;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class R_diverifikasi {
    @SerializedName("data")
    private List<Datadiverifikasi> data;
    @SerializedName("messages")
    private String messages;

    @SerializedName("status")
    private String status;

    public List<Datadiverifikasi> getData() {
        return data;
    }
    public void setData(List<Datadiverifikasi> data) {
        this.data = data;
    }
    public String getMessages(){
        return messages;
    }

    public void setMessages(String messages){
        this.messages = messages;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
