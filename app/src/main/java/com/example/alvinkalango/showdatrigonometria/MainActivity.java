package com.example.alvinkalango.showdatrigonometria;

/* http://codigonatela.blogspot.com.br/2013/08/android-e-sqlite.html */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Usuario;
    Button Entrar, Cadastrar, Editar, Excluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usuario = (EditText) findViewById(R.id.usuario);
        Entrar = (Button) findViewById(R.id.buttonEntrar);
        Cadastrar = (Button) findViewById(R.id.buttonCadastrar);
        Editar = (Button) findViewById(R.id.buttonEditar);
        Excluir = (Button) findViewById(R.id.buttonExcluir);

        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastrar.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sobre) {
            Toast.makeText(getApplicationContext(), "App by Chiclete!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
