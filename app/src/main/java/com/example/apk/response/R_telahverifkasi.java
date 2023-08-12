package com.example.apk.response;

import com.example.apk.model.DataTelahverifikasi;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class R_telahverifkasi {
    @SerializedName("data")
    private List<DataTelahverifikasi> data;

    @SerializedName("messages")
    private String messages;

    @SerializedName("status")
    private boolean status;

    public List<DataTelahverifikasi> getData() {return data;}

    public void setData(List<DataTelahverifikasi> data) {
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
