package com.example.apk.interfaces;

import com.example.apk.response.R_ajuan;
import com.example.apk.response.R_cek;
import com.example.apk.response.R_diverifikasi;
import com.example.apk.response.R_login;
import com.example.apk.response.R_pertanyaan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRequest {
    @POST("login_api/login")
    @FormUrlEncoded
    Call<R_login> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @POST("daftar_ajuan/list")
    @FormUrlEncoded
    Call<R_ajuan> daftarAjuan(
            @Field("nik") String nik
    );

    @POST("Daftar_ajuan/hapus_pertanyaan")
    @FormUrlEncoded
    Call<R_pertanyaan>hapusPertanyaan
            (@Field("id") String id
    );

    @POST("Daftar_ajuan/pertanyaan")
    @FormUrlEncoded
    Call<R_pertanyaan> daftarPertanyaan(
            @Field("kd") String kd
    );

    @POST("Daftar_ajuan/edit_pertanyaan")
    @FormUrlEncoded
    Call<R_pertanyaan> editPertanyaan(
            @Field("id") String id,
            @Field("pertanyaan") String pertanyaan
    );

    @POST("Daftar_ajuan/verifikasi")
    @FormUrlEncoded
    Call<R_ajuan> verifikasi(
            @Field("kd_surat") String kd_surat
    );

    @POST("cek_api/cek")
    @FormUrlEncoded
    Call<R_cek> cekAjuan(
            @Field("kode") String kode
    );

    Call<R_cek> cekAjuan();

    @POST("Daftar_ajuan/tambah_pertanyaan")
    @FormUrlEncoded
    Call<R_pertanyaan> tambahPertanyaan(
            @Field("kd_surat") String kd_surat,
            @Field("pertanyaan") String pertanyaan
    );
    @POST("daftar_ajuan/diverifikasi")
    @FormUrlEncoded
    Call<R_diverifikasi> diVerifikasi(
            @Field("nik") String nik
    );
}
