package com.example.android.estacionamento.controller;

import com.example.android.estacionamento.model.Carro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit; import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by eribas on 17/05/2017.
 */

public interface EstacionamentoAPI {

    @GET("cars/{placa}")
    Call<List<Carro>> getCarro(
            @Path("placa") String placa
    );

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.96.127.138:3000/cars/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
