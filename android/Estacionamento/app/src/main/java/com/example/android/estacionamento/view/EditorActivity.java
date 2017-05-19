package com.example.android.estacionamento.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.estacionamento.R;
import com.example.android.estacionamento.service.EstacionamentoAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity {

    private final static EstacionamentoAPI api = EstacionamentoAPI.retrofit.create(EstacionamentoAPI.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        getCapacity();
    }

    public void getCapacity(){
        final Call<Integer> capacity = api.getCapacity();
        final TextView capacity_text = (TextView)findViewById(R.id.capacity);

        capacity.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                capacity_text.setText("Vagas restantes: " +  response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                capacity_text.setText("Erro");
            }
        });
    }
}
