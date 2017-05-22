package com.example.android.estacionamento.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.estacionamento.R;
import com.example.android.estacionamento.model.Carro;
import com.example.android.estacionamento.service.EstacionamentoAPI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.estacionamento.R.id.placa;

/**
 * Created by eribas on 18/05/2017.
 */

public class CarAdapter extends ArrayAdapter<Carro>{

    private final static EstacionamentoAPI api = EstacionamentoAPI.retrofit.create(EstacionamentoAPI.class);

    public CarAdapter(Context context, ArrayList<Carro> carros){
        super(context, 0, carros);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_car, parent, false);
        }
        Carro carroAtual = getItem(position);

        TextView placaView = (TextView) listItemView.findViewById(placa);
        placaView.setText(carroAtual.getPlaca());

        TextView dataView = (TextView) listItemView.findViewById(R.id.data);
        dataView.setText(formatDate(carroAtual.getDate()));

        TextView payParkingView = (TextView) listItemView.findViewById(R.id.pago);
        String verificarPagamento = carroAtual.isPago() ? "Pago com sucesso" : "Pendente";
        payParkingView.setText(verificarPagamento);

        final Call<Integer> price = api.getPaymentValue(carroAtual.getPlaca().toLowerCase());
        final TextView priceParkingView = (TextView)listItemView.findViewById(R.id.price_parking);

        price.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                priceParkingView.setText(response.body() + "R$");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                priceParkingView.setText("Erro");
            }
        });

        return listItemView;
    }

    private String formatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        return format.format(date);
    }

}
