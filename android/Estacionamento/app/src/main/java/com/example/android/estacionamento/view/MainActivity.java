package com.example.android.estacionamento.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
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

    //declarar variavel que vai exibir o loader
    ProgressDialog mProgressDialog;

    //valor do estacionamento
    private static final int PRICE_PARKING = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //evento de clique do botão pagar
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

        //iniciar adapter da minha lista de carros
        cars_listView = (ListView)findViewById(R.id.list_cars_view);
        carAdapter = new CarAdapter(this, new ArrayList<Carro>());
        cars_listView.setAdapter(carAdapter);

        //evento de clique de cada carro
        cars_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //se quiser pegar o evento de clique do carro atual descomente o codigo abaixo
                Carro carroAtual = carAdapter.getItem(position);

                exibirModal(carroAtual);
            }
        });
    }

    //modal que exibe a quantidade de horas totais que o carro está no estacionamento
    private void exibirModal(Carro carroAtual){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Holo_Dialog_NoActionBar);
        } else {
            builder = new AlertDialog.Builder(this);
        }

        //variavel que guarda a quantidade de horas que determinado carro está no estacionamento
        int totalHoursParking = carroAtual.getValorTotalEstacionamento() / PRICE_PARKING;

        builder.setTitle("Estacionamento")
                .setMessage("Seu carro está " + totalHoursParking + " hora(s) no estacionamento")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //clicou no ok
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //clicou no cancelar
                    }
                })
                .setIcon(R.drawable.ic_time_to_leave_black_24dp)
                .show();
    }

    //obter todos os carros
    public void getAllCars(String placa){
        //instanciar a variavel que vai exibir o loader
        mProgressDialog = new ProgressDialog(this);
        //chamar funções para exibir loader
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        final Call<List<Carro>> carros = api.getAllCars(placa);

        cars_listView = (ListView)findViewById(R.id.list_cars_view);


        carros.enqueue(new Callback<List<Carro>>() {
            @Override
            public void onResponse(Call<List<Carro>> call, Response<List<Carro>> response) {
                List<Carro> carrosResponseBody = response.body();

                carAdapter.addAll(carrosResponseBody);
                cars_listView.setAdapter(carAdapter);

                //fechar loader
                mProgressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<List<Carro>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Não foi encontrado nenhum carro", Toast.LENGTH_SHORT).show();
                //fechar loader
                mProgressDialog.dismiss();
            }
        });
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

}
