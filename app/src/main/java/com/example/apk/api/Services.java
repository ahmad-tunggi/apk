package com.example.apk.api;

import com.example.apk.utils.Config;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Services {
    public static final String  baseURL = Config.BASE_URL;
    public static Retrofit retrofit;
    public static Retrofit koneksi(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
