package com.example.alvinkalango.showdatrigonometria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Resultado extends AppCompatActivity {

    ManipulaBanco CRUD;

    TextView resultado;
    TextView msg;
    Button Bt_voltar;

    String codigo;
    int pontuacao;
    int percTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        CRUD = new ManipulaBanco(getBaseContext());

        codigo = this.getIntent().getStringExtra("codigo");
        pontuacao = this.getIntent().getIntExtra("pontuacao", pontuacao);

        Bt_voltar = (Button) findViewById(R.id.bt_voltar);
        resultado = (TextView)findViewById(R.id.textResultado);
        msg = (TextView)findViewById(R.id.textMsg);

        percTotal = (pontuacao*100)/10;
        resultado.setText(percTotal + " %");

        CRUD.alterarRegistro(Integer.parseInt(codigo), null, null, null, null, null, null, null, Integer.toString(percTotal) + " %");

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resultado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
