package com.example.alvinkalango.showdatrigonometria;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Entrar extends AppCompatActivity {

    ManipulaBanco CRUD;
    private ListView Lista;
    Button Bt_voltar;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        CRUD = new ManipulaBanco(getBaseContext());
        final Cursor Cursor = CRUD.carregarDados();

        String[] nomeCampos = new String[]{CriarBanco.NOME};
        int[] idViews = new int[]{R.id.nomeUsuario};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.usuario, Cursor, nomeCampos, idViews, 0);

        Bt_voltar = (Button) findViewById(R.id.bt_voltar);
        Lista = (ListView) findViewById(R.id.listView);
        Lista.setAdapter(adaptador);

        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor.moveToPosition(position);
                codigo = Cursor.getString(Cursor.getColumnIndexOrThrow(CriarBanco.ID));
                Toast.makeText(getApplicationContext(), codigo, Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(Entrar.this, Quiz.class);
                //intent.putExtra("codigo", codigo);
                //startActivity(intent);
                //finish();
            }
        });

        Bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entrar, menu);
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
