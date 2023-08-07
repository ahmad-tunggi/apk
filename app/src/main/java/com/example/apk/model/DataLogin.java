package com.example.apk.model;
import com.google.gson.annotations.SerializedName;

public class DataLogin {

    @SerializedName("nik")
        private String nik;

    @SerializedName("user")
        private String user;

    @SerializedName("nama_lengkap")
        private String nama_lengkap;

    @SerializedName("status")
        private String status;

    @SerializedName("lv")
        private String lv;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }


}
