package com.example.alvinkalango.showdatrigonometria;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Consultar extends AppCompatActivity {

    ManipulaBanco CRUD;
    private ListView Lista;
    Button Bt_voltar;
    int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        CRUD = new ManipulaBanco(getBaseContext());
        final Cursor Cursor = CRUD.carregarDados();
        String[] nomeCampos = new String[]{CriarBanco.NOME, CriarBanco.MOD1, CriarBanco.MOD2, CriarBanco.MOD3, CriarBanco.MOD4, CriarBanco.TOTAL};
        int[] idViews = new int[]{R.id.nomeUsuario, R.id.textM1, R.id.textM2, R.id.textM3, R.id.textM4, R.id.textMT};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.usuario_consultar, Cursor, nomeCampos, idViews, 0);

        Bt_voltar = (Button) findViewById(R.id.bt_voltar);
        Lista = (ListView) findViewById(R.id.listView);
        Lista.setAdapter(adaptador);

        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor.moveToPosition(position);
                codigo = Cursor.getInt(Cursor.getColumnIndexOrThrow(CriarBanco.ID));
                Intent intent = new Intent(Consultar.this, Alterar.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
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
