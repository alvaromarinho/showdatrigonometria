package com.example.alvinkalango.showdatrigonometria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RatingBar bar;
        TextView resultado;
        int pontuacao;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        resultado = (TextView)findViewById(R.id.textResultado);
        bar = (RatingBar)findViewById(R.id.ratingBar);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);

        Bundle b = getIntent().getExtras();
        pontuacao = b.getInt("pontuacao");
        bar.setRating(pontuacao);

        switch (pontuacao) {
            case 1:
            case 2: resultado.setText("Opps, try again bro, keep learning");
                break;
            case 3:
            case 4:resultado.setText("Hmmmm.. maybe you have been reading a lot of JasaProgrammer quiz");
                break;
            case 5:resultado.setText("Who are you? A student in JP???");
                break;
        }

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
