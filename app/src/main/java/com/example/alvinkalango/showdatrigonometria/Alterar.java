package com.example.alvinkalango.showdatrigonometria;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Alterar extends Activity{

    EditText Nome;
    Button Editar;
    Button Deletar;

    ManipulaBanco CRUD;
    Cursor Cursor;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");
        CRUD = new ManipulaBanco(getBaseContext());

        Nome = (EditText)findViewById(R.id.editTextUsuarioED);
        Editar = (Button)findViewById(R.id.bt_editar);
        Deletar = (Button)findViewById(R.id.bt_deletar);

        Cursor = CRUD.carregarDadoById(Integer.parseInt(codigo));
        Nome.setText(Cursor.getString(Cursor.getColumnIndexOrThrow(CriarBanco.NOME)));

        Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CRUD.alterarRegistro(Integer.parseInt(codigo), Nome.getText().toString(), "", "", "", "", "", "", "");
                Intent intent = new Intent(Alterar.this, Consultar.class);
                startActivity(intent);
                finish();
            }
        });

        Deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CRUD.deletarRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(Alterar.this, Consultar.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
