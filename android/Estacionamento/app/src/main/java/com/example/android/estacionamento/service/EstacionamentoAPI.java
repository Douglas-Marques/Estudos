package com.example.android.estacionamento.service;

import com.example.android.estacionamento.model.Carro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

/**
 * Created by eribas on 17/05/2017.
 */

public interface EstacionamentoAPI {

    //obter um ou todos os carros
    @GET("cars/{placa}")
    Call<List<Carro>> getAllCars(@Path("placa") String placa );

    //obter vagas restantes
    @GET("vagas")
    Call<Integer> getCapacity();

    //obter valor total de determinada placa
    @GET("paymentValue/{placa}")
    Call<Integer> getPaymentValue(@Path("placa") String placa);

    //pagar o estacionamento
    @FormUrlEncoded
    @PATCH("cars")
    Call<String> payParking(@Field("placa") String placa);

     Retrofit retrofit = new Retrofit.Builder()
            //CI
            //.baseUrl("http://10.96.127.138:3000/")
             //CASA
            .baseUrl("http://192.168.1.5:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

      //EstacionamentoService service = retrofit.create(EstacionamentoService.class);

}
