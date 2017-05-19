package com.example.android.estacionamento.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

    private CarAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

      /*  adapter = new CarAdapter(this, new ArrayList<Carro>());
        cars_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Carro carroAtual = adapter.getItem(position);


            }
        });
*/
        //Obter todos os carros
        getAllCars("");
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

        final ListView cars_listView = (ListView)findViewById(R.id.list_cars_view);
        final Call<List<Carro>> carros = api.getAllCars(placa);

        carros.enqueue(new Callback<List<Carro>>() {
            @Override
            public void onResponse(Call<List<Carro>> call, Response<List<Carro>> response) {
                List<Carro> carrosResponseBody = response.body();
                CarAdapter carAdapter = new CarAdapter(getApplicationContext(), new ArrayList<Carro>());

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
