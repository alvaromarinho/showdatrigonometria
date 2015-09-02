package com.example.alvinkalango.showdatrigonometria;

import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Consultar extends Activity{

    ManipulaBanco CRUD;
    private ListView Lista;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        CRUD = new ManipulaBanco(getBaseContext());
        final Cursor Cursor = CRUD.carregarDados();

        String[] nomeCampos = new String[]{CriarBanco.ID, CriarBanco.NOME};
        int[] idViews = new int[]{R.id.idUsuario, R.id.nomeUsuario};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.usuario, Cursor, nomeCampos, idViews, 0);

        Lista = (ListView) findViewById(R.id.listView);
        Lista.setAdapter(adaptador);

        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor.moveToPosition(position);
                codigo = Cursor.getString(Cursor.getColumnIndexOrThrow(CriarBanco.ID));
                Intent intent = new Intent(Consultar.this, Alterar.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }
}
