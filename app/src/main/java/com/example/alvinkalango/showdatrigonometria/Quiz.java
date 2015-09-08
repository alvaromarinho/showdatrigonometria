package com.example.alvinkalango.showdatrigonometria;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends Activity {

    List<Questao> quesList;
    Questao QuestaoAtual = new Questao();
    ManipulaBanco Banco;
    String codigo;

    TextView txtQuestao;
    RadioGroup rGroup;
    RadioButton optA, optB, optC, optD, resposta;
    Button btConfirma;

    private AlertDialog Concluido;

    int pontuacao = 0;
    int nques = 1;
    int qid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quesList = new ArrayList<>();
        Banco = new ManipulaBanco(this);
        codigo = this.getIntent().getStringExtra("codigo");

        Banco.addQuestao();
        quesList = Banco.getTodasQuestoes();

        if(quesList!= null && quesList.size() != 0) {
            QuestaoAtual = quesList.get(qid);
        }

        txtQuestao=(TextView)findViewById(R.id.textView1);
        optA=(RadioButton)findViewById(R.id.radio0);
        optB=(RadioButton)findViewById(R.id.radio1);
        optC=(RadioButton)findViewById(R.id.radio2);
        optD=(RadioButton)findViewById(R.id.radio3);
        btConfirma=(Button)findViewById(R.id.buttonConfirma);

        setQuestaoView();

        btConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rGroup = (RadioGroup) findViewById(R.id.radioGroup1);
                resposta = (RadioButton) findViewById(rGroup.getCheckedRadioButtonId());

                if (QuestaoAtual.getRESPOSTA().equals(resposta.getText())) {
                    pontuacao++;
                }

                if (nques == 5) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Quiz.this);
                    builder.setTitle("Etapa concluída");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Concluido.dismiss();
                            nques = 0;
                        }
                    });
                    Concluido = builder.create();
                    Concluido.show();
                }

                if (qid < 10 && quesList != null && quesList.size() != 0) {
                    nques++;
                    QuestaoAtual = quesList.get(qid);
                    setQuestaoView();
                }

                else {
                    Intent intent = new Intent(Quiz.this, Resultado.class);
                    intent.putExtra("pontuacao", pontuacao);
                    intent.putExtra("codigo", codigo);
                    startActivity(intent);
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
        qid++;
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
