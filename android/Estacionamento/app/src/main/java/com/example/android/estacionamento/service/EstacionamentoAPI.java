package com.example.android.estacionamento.service;

import com.example.android.estacionamento.model.Carro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

/**
 * Created by eribas on 17/05/2017.
 */

public interface EstacionamentoAPI {

    @GET("cars/{placa}")
    Call<List<Carro>> getAllCars(@Path("placa") String placa );

    @GET("vagas")
    Call<Integer> getCapacity();

    @PATCH("cars/{placa}")
    Call<Carro> payParking(@Path("placa") String placa);

    @DELETE("cars/{placa}")
    Call<Carro> deleteCar(@Path("placa") String placa);



     Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.96.127.138:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

      //EstacionamentoService service = retrofit.create(EstacionamentoService.class);

}
