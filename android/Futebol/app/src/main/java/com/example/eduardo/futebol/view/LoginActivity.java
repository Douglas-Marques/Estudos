package com.example.eduardo.futebol.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.eduardo.futebol.R;
import com.example.eduardo.futebol.dao.UsuarioDao;

public class LoginActivity extends AppCompatActivity {

    private EditText eEmail;
    private EditText eSenha;
    private Button vBtnEntrar;
    private ProgressBar vLoading;
    private UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eEmail = (EditText) findViewById(R.id.vLogin);
        eSenha = (EditText) findViewById(R.id.vSenha);
        vBtnEntrar = (Button)findViewById(R.id.vbtnEntrar);
        vLoading = (ProgressBar)findViewById(R.id.vLoading);

        usuarioDao = new UsuarioDao(getApplicationContext());
        usuarioDao.cadastrarUsuario();

        if(usuarioDao.verificaUsuario()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        vBtnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){
        boolean logou = usuarioDao.login(eEmail.getText().toString(), eSenha.getText().toString());
        if(logou){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Errou", Toast.LENGTH_SHORT).show();
        }
    }
}
