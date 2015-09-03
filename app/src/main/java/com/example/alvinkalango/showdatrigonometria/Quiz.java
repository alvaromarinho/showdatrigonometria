package com.example.alvinkalango.showdatrigonometria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class Quiz extends Activity {

    List<Questao> quesList;
    Questao QuestaoAtual;
    ManipulaBanco Banco;

    TextView txtQuestao;
    RadioGroup rGroup;
    RadioButton optA, optB, optC, optD, resposta;
    Button btConfirma;

    int pontuacao=0;
    int qid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Banco = new ManipulaBanco(this);
        quesList = Banco.getTodasQuestoes();
        QuestaoAtual = quesList.get(qid);

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
                if (qid < 5) {
                    QuestaoAtual = quesList.get(qid);
                    setQuestaoView();
                }
                else {
                    Intent intent = new Intent(Quiz.this, Resultado.class);
                    Bundle b = new Bundle();
                    b.putInt("pontuacao", pontuacao);
                    intent.putExtras(b);
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
