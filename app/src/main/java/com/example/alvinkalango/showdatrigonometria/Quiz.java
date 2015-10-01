package com.example.alvinkalango.showdatrigonometria;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz extends Activity {

    List<Questao> quesList;
    Questao QuestaoAtual = new Questao();
    ManipulaBanco Banco;
    String codigo;
    Cursor cursor;

    TextView txtQuestao;
    RadioGroup rGroup;
    RadioButton optA, optB, optC, optD, resposta;
    Button btConfirma;

    private AlertDialog Concluido, alertDialog;

    int pontuacaoTemp = 0, percTemp, modulo = 1, nques = 1, qid = 0, nmodulo, mod1, mod2, mod3, mod4, T;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quesList = new ArrayList<>();
        Banco = new ManipulaBanco(this);
        codigo = this.getIntent().getStringExtra("codigo");

        Banco.addQuestao();
        quesList = Banco.getTodasQuestoes();

        txtQuestao=(TextView)findViewById(R.id.textView1);
        optA=(RadioButton)findViewById(R.id.radio0);
        optB=(RadioButton)findViewById(R.id.radio1);
        optC=(RadioButton)findViewById(R.id.radio2);
        optD=(RadioButton)findViewById(R.id.radio3);
        btConfirma=(Button)findViewById(R.id.buttonConfirma);

        cursor = Banco.carregarDadoById(Integer.parseInt(codigo));
        nmodulo = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MODULO));

        if (nmodulo == 2) {
            qid = 10;
            modulo = modulo + 1;
        }
        if (nmodulo == 3) {
            qid = 20;
            modulo = modulo + 2;
        }
        if (nmodulo == 4) {
            qid = 30;
            modulo = modulo + 3;
        }

        if(quesList!= null && quesList.size() != 0) {
            Collections.shuffle(quesList.subList(0, 9));
            Collections.shuffle(quesList.subList(10, 19));
            Collections.shuffle(quesList.subList(20, 29));
            Collections.shuffle(quesList.subList(30, 39));
            QuestaoAtual = quesList.get(qid);
        }

        setQuestaoView();

        btConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rGroup = (RadioGroup) findViewById(R.id.radioGroup1);
                resposta = (RadioButton) findViewById(rGroup.getCheckedRadioButtonId());

                if (QuestaoAtual.getRESPOSTA().equals(resposta.getText())) {
                    pontuacaoTemp++;
                }

                if (nques == 5) {
                    nques = 0;
                    percTemp = (pontuacaoTemp*100)/5;

                    switch (modulo) {
                        case 1:
                            cursor = Banco.carregarDadoById(Integer.parseInt(codigo));
                            mod2 = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MOD2));
                            mod3 = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MOD3));
                            mod4 = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MOD4));
                            T = (percTemp + mod2 + mod3 + mod4)/4;
                            Banco.alterarRegistro(Integer.parseInt(codigo), null, "2", Integer.toString(percTemp) + " %", null, null, null, Integer.toString(T) + " %");
                            break;
                        case 2:
                            mod1 = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MOD1));
                            mod3 = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MOD3));
                            mod4 = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MOD4));
                            T = (mod1 + percTemp + mod3 + mod4)/4;
                            Banco.alterarRegistro(Integer.parseInt(codigo), null, "3", null, Integer.toString(percTemp) + " %", null, null, Integer.toString(T) + " %");
                            break;
                        case 3:
                            mod1 = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MOD1));
                            mod2 = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MOD2));
                            mod4 = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MOD4));
                            T = (mod1 + mod2 + percTemp + mod4)/4;
                            Banco.alterarRegistro(Integer.parseInt(codigo), null, "4", null, null, Integer.toString(percTemp) + " %", null, Integer.toString(T) + " %");
                            break;
                        case 4:
                            mod1 = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MOD1));
                            mod2 = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MOD2));
                            mod3 = cursor.getInt(cursor.getColumnIndexOrThrow(CriarBanco.MOD3));
                            T = (mod1 + mod2 + mod3 + percTemp)/4;
                            Banco.alterarRegistro(Integer.parseInt(codigo), null, "0", null, null, null, Integer.toString(percTemp) + " %", Integer.toString(T) + " %");
                            break;
                    }

                    modulo++;

                    AlertDialog.Builder builder = new AlertDialog.Builder(Quiz.this);
                    builder.setTitle("Etapa concluída");

                    builder.setMessage("Acertos: " + percTemp + "%");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Concluido.dismiss();
                            if (qid >= 40) {
                                Intent intentR = new Intent(Quiz.this, Resultado.class);
                                intentR.putExtra("codigo", codigo);
                                startActivity(intentR);
                            }
                        }
                    });

                    pontuacaoTemp = 0;

                    Concluido = builder.create();
                    Concluido.show();
                }

                if (qid < 40 && quesList != null && quesList.size() != 0) {
                    nques++;
                    QuestaoAtual = quesList.get(qid);
                    setQuestaoView();
                }
            }
        });
    }

    private void setQuestaoView() {
        txtQuestao.setText(QuestaoAtual.getQUESTAO());
        optA.setText(QuestaoAtual.getOPTA());
        optB.setText(QuestaoAtual.getOPTB());
        optC.setText(QuestaoAtual.getOPTC());
        optD.setText(QuestaoAtual.getOPTD());
        qid = qid + 2;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder Builder = new AlertDialog.Builder(Quiz.this);
        Builder.setTitle("Atenção");

        Builder.setMessage("Deseja realmente sair?");
        Builder.setCancelable(false);
        Builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(Quiz.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        alertDialog = Builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
