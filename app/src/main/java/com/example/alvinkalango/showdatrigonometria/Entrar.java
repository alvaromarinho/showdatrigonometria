package com.example.alvinkalango.showdatrigonometria;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class Entrar extends AppCompatActivity {

    ManipulaBanco CRUD;
    private ListView Lista;
    Button Bt_voltar;
    String codigo;
    private AlertDialog VerificaJogo;
    Cursor cursor;
    int nmodulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        CRUD = new ManipulaBanco(getBaseContext());
        final Cursor Cursor = CRUD.carregarDados();

        String[] nomeCampos = new String[]{CriarBanco.NOME};
        int[] idViews = new int[]{R.id.nomeUsuario};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.usuario_entrar, Cursor, nomeCampos, idViews, 0);

        Bt_voltar = (Button) findViewById(R.id.bt_voltar);
        Lista = (ListView) findViewById(R.id.listView);
        Lista.setAdapter(adaptador);

        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor.moveToPosition(position);
                codigo = Cursor.getString(Cursor.getColumnIndexOrThrow(CriarBanco.ID));

                cursor = CRUD.carregarDadoById(Integer.parseInt(codigo));
                nmodulo = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MODULO));

                if (nmodulo != 0) {
                    AlertDialog.Builder adBuilder = new AlertDialog.Builder(Entrar.this);
                    adBuilder.setTitle("Atenção");

                    adBuilder.setMessage("Voce deseja continuar o jogo anterior?");
                    adBuilder.setCancelable(false);
                    adBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Entrar.this, Quiz.class);
                            intent.putExtra("codigo", codigo);
                            startActivity(intent);
                        }
                    });
                    adBuilder.setNegativeButton("Não (Novo Jogo)", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            CRUD.alterarRegistro(Integer.parseInt(codigo), null, "0", "Nao realizado", "Nao realizado", "Nao realizado", "Nao realizado", "Nao realizado");
                            Intent intent = new Intent(Entrar.this, Quiz.class);
                            intent.putExtra("codigo", codigo);
                            startActivity(intent);
                        }
                    });

                    VerificaJogo = adBuilder.create();
                    VerificaJogo.show();
                }

                else {
                    Intent intent = new Intent(Entrar.this, Quiz.class);
                    intent.putExtra("codigo", codigo);
                    startActivity(intent);
                }
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
