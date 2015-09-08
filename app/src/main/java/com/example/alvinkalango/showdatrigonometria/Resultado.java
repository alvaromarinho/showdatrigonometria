package com.example.alvinkalango.showdatrigonometria;

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

    RatingBar bar;
    TextView resultado;
    Button Bt_voltar;

    String codigo;
    int pontuacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        CRUD = new ManipulaBanco(getBaseContext());

        codigo = this.getIntent().getStringExtra("codigo");
        pontuacao = this.getIntent().getIntExtra("pontuacao", pontuacao);

        Bt_voltar = (Button) findViewById(R.id.bt_voltar);
        resultado = (TextView)findViewById(R.id.textResultado);
        bar = (RatingBar)findViewById(R.id.ratingBar);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);

        //Toast.makeText(this, codigo, Toast.LENGTH_LONG).show();
        CRUD.alterarRegistro(Integer.parseInt(codigo), null, null, null, Integer.toString(pontuacao), null, null, null, null);

        bar.setRating(pontuacao);

        switch (pontuacao) {
            case 0:
                resultado.setText("MANO, TU ERROU TUDO?");
                break;
            case 1:
                resultado.setText("ACERTOU 1");
                break;
            case 2:
                resultado.setText("Opps, try again bro, keep learning");
                break;
            case 3:
                resultado.setText("ACERTOU 3");
                break;
            case 4:
                resultado.setText("Hmmmm.. maybe you have been reading a lot of JasaProgrammer quiz");
                break;
            case 5:
                resultado.setText("Who are you? A student in JP???");
                break;
        }

        Bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
