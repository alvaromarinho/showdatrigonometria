package com.example.alvinkalango.showdatrigonometria;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Alterar extends AppCompatActivity {

    EditText Nome;
    Button Bt_editar, Bt_deletar, Bt_voltar;

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
        Bt_editar = (Button)findViewById(R.id.bt_editar);
        Bt_deletar = (Button)findViewById(R.id.bt_deletar);
        Bt_voltar = (Button)findViewById(R.id.bt_voltar);

        Cursor = CRUD.carregarDadoById(Integer.parseInt(codigo));
        Nome.setText(Cursor.getString(Cursor.getColumnIndexOrThrow(CriarBanco.NOME)));

        Bt_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CRUD.alterarRegistro(Integer.parseInt(codigo), Nome.getText().toString(), null, null, null, null, null, null);
                Toast.makeText(getApplicationContext(), "Usuário editado com sucesso!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        Bt_deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CRUD.deletarRegistro(Integer.parseInt(codigo));
                Toast.makeText(getApplicationContext(), "Usuário deletado com sucesso!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        Bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
