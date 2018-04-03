package ru.vlg_ktu.myapplication.api;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by V_Semibratov on 01.04.2018.
 */

public class RetroClient extends Application {

    private static final String ROOT_URL = "https://jsonplaceholder.typicode.com/";

    private static Retrofit getRettofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static Api getApi(){
        return getRettofitInstance().create(Api.class);
    }
}
