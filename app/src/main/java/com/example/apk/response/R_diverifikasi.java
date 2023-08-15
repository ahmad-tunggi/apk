package com.example.apk.response;

import com.example.apk.model.DataDiverifikasi;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class R_diverifikasi {
    @SerializedName("data")
    private List<DataDiverifikasi> data;
    @SerializedName("messages")
    private String messages;

    @SerializedName("status")
    private String status;

    public List<DataDiverifikasi> getData() {
        return data;
    }
    public void setData(List<DataDiverifikasi> data) {
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
