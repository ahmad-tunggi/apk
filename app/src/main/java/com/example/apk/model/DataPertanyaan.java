package com.example.apk.model;

public class DataPertanyaan {
    private String id_pertanyaan;
    private String kd_surat;
    private String pertanyaan;

    public String getId_pertanyaan() {
        return id_pertanyaan;
    }

    public void setId_pertanyaan(String id_pertanyaan) {
        this.id_pertanyaan = id_pertanyaan;
    }

    public String getKd_surat() {
        return kd_surat;
    }

    public void setKd_surat(String kd_surat) {
        this.kd_surat = kd_surat;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }
}
