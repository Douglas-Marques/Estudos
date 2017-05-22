package com.example.android.estacionamento.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.estacionamento.R;
import com.example.android.estacionamento.model.Carro;
import com.example.android.estacionamento.service.EstacionamentoAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static EstacionamentoAPI api = EstacionamentoAPI.retrofit.create(EstacionamentoAPI.class);


    private ListView cars_listView;

    CarAdapter carAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cars_listView = (ListView)findViewById(R.id.list_cars_view);

        final Activity activity = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        //Obter todos os carros
        getAllCars("");

        carAdapter = new CarAdapter(this, new ArrayList<Carro>());
        cars_listView.setAdapter(carAdapter);

        cars_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Carro carroAtual = carAdapter.getItem(position);
                avisarBotao(carroAtual);
            }
        });
    }

    private void avisarBotao(Carro carroAtual){
        Toast.makeText(this, "Clique pay para pagar placa: " + carroAtual.getPlaca() , Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getAllCars(String placa){

        final Call<List<Carro>> carros = api.getAllCars(placa);

        cars_listView = (ListView)findViewById(R.id.list_cars_view);

        carros.enqueue(new Callback<List<Carro>>() {
            @Override
            public void onResponse(Call<List<Carro>> call, Response<List<Carro>> response) {
                List<Carro> carrosResponseBody = response.body();

                carAdapter.addAll(carrosResponseBody);
                cars_listView.setAdapter(carAdapter);

            }
            @Override
            public void onFailure(Call<List<Carro>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Não foi encontrado nenhum carro", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
