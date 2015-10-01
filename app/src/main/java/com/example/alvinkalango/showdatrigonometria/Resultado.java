package com.example.alvinkalango.showdatrigonometria;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    ManipulaBanco CRUD;

    TextView resultado;
    TextView msg;
    Button Bt_voltar;

    Cursor cursor;
    String codigo;
    int percTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        CRUD = new ManipulaBanco(getBaseContext());

        codigo = this.getIntent().getStringExtra("codigo");

        Bt_voltar = (Button) findViewById(R.id.bt_voltar);
        resultado = (TextView)findViewById(R.id.textResultado);
        msg = (TextView)findViewById(R.id.textMsg);

        cursor = CRUD.carregarDadoById(Integer.parseInt(codigo));
        percTotal = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.TOTAL));

        resultado.setText(Integer.toString(percTotal));

        if (percTotal < 40) msg.setText("Voce precisa estudar mais!");
        if (percTotal >= 40 && percTotal < 80 ) msg.setText("Voce esta na media!");
        if (percTotal >= 80) msg.setText("Voce esta detendo o conhecimento da materia!");

        Bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resultado.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Resultado.this, MainActivity.class);
        startActivity(intent);
    }
}
