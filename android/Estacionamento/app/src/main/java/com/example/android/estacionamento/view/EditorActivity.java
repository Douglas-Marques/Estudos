package com.example.android.estacionamento.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.estacionamento.R;
import com.example.android.estacionamento.service.EstacionamentoAPI;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditorActivity extends AppCompatActivity {

    private final static EstacionamentoAPI api = EstacionamentoAPI.retrofit.create(EstacionamentoAPI.class);

    private Button qrCode;

    private Button pagar;

    private TextView qrPlaca;

    private String placaPaga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        setTitle("Vagas/Pagamento");

        //botão voltar para main activity
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        qrCode = (Button) this.findViewById(R.id.qrcode);
        final Activity activity = this;

        //leitor de qr code (receita de bolo)
        qrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        qrPlaca = (TextView)findViewById(R.id.placa_qrcode);

        getCapacity();

        //caso clique chama o método para pagar o estacionamento
        pagar = (Button) this.findViewById(R.id.pagar_estacionamento);
        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payParking();
            }
        });
    }

    //usuário clicou em uma das opções do radio button
    public void onRadioButtonClicked(View view) {
        // Radio button foi clicado?
        boolean checked = ((RadioButton) view).isChecked();
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.list_item_payment);

        // Verificar qual radio button foi clicado
        switch(view.getId()) {
            case R.id.credito:
                if (checked){
                    linearLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.debito:
                if (checked){
                    linearLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.paypal:
                if (checked){
                    linearLayout.setVisibility(View.GONE);
                }
                 break;
        }
        findViewById(R.id.pagar_estacionamento).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
                paymentValue(result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void getCapacity(){
        final Call<Integer> capacity = api.getCapacity();
        final TextView capacity_text = (TextView)findViewById(R.id.capacity);

        capacity.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() > 0){
                    capacity_text.setText("Vagas restantes: " + response.body());
                }
                else{
                    capacity_text.setText("Não há vagas!");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                capacity_text.setText("Erro");
            }
        });
    }

    private void paymentValue(final String placa){
        final Call<Integer> price = api.getPaymentValue(placa.toLowerCase());
        placaPaga = placa;

        final TextView qrCode = (TextView)findViewById(R.id.price_qrcode);
        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radio_group);

        qrPlaca = (TextView)findViewById(R.id.placa_qrcode);

        price.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                qrCode.setText("Preço: " + response.body() + "R$");
                qrPlaca.setText("Placa: " + placaPaga);
                radioGroup.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                qrCode.setText("Placa não encontrada");
            }
        });
    }

    private void payParking(){
        final Call<String> carroAtual = api.payParking(placaPaga.toLowerCase());

        carroAtual.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
              //  Intent intent = new Intent(EditorActivity.this, MainActivity.class);
              //  startActivity(intent);
                Toast.makeText(EditorActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(EditorActivity.this, "Não foi encontrado nenhum carro", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

}
