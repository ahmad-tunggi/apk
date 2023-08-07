package com.example.apk.response;

import com.example.apk.model.DataAjuan;
import com.example.apk.model.DataPertanyaan;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class R_pertanyaan {
    @SerializedName("data")
    private List<DataPertanyaan> data;

    @SerializedName("messages")
    private String messages;

    @SerializedName("status")
    private boolean status;

    public List<DataPertanyaan> getData() {
        return data;
    }

    public void setData(List<DataPertanyaan> data) {
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
