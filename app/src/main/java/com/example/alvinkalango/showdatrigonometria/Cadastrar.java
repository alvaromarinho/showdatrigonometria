package com.example.alvinkalango.showdatrigonometria;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastrar extends Activity{

    ManipulaBanco CRUD;
    Button Bt_cadastrar, Bt_voltar;
    EditText ET_usuario;
    String usuarioString;
    String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        Bt_cadastrar = (Button)findViewById(R.id.bt_cadastrar);
        Bt_voltar = (Button)findViewById(R.id.bt_voltar);
        CRUD = new ManipulaBanco(getBaseContext());
        ET_usuario = (EditText)findViewById(R.id.editTextUsuario);

        Bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioString = ET_usuario.getText().toString();
                resultado = CRUD.inserirDados(usuarioString, null, null, null, null, null, null, null);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                finish();
            }
        });

        Bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
