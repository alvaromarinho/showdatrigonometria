package com.example.alvinkalango.showdatrigonometria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ManipulaBanco {

    private SQLiteDatabase db;
    private CriarBanco banco;

    public ManipulaBanco(Context context){
        banco = new CriarBanco(context);
    }

    public Cursor carregarDados(){
        Cursor cursor;
        String[] campos =  {CriarBanco.ID, CriarBanco.NOME, CriarBanco.MOD1, CriarBanco.MOD2, CriarBanco.MOD3, CriarBanco.MOD4, CriarBanco.TOTAL};
        db = banco.getReadableDatabase();
        cursor = db.query(CriarBanco.TABELA, campos, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public Cursor carregarDadoById(int id){
        Cursor cursor;
        String[] campos = {
                banco.ID,
                banco.NOME,
                banco.PERGUNTA,
                banco.ACERTOSMODULO,
                banco.MOD1,
                banco.MOD2,
                banco.MOD3,
                banco.MOD4,
                banco.TOTAL
        };
        String where = CriarBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriarBanco.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public String inserirRegistro(String nome, String pergunta, String acertosmodulo,
                                  String mod1, String mod2, String mod3, String mod4, String total){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        if (nome != null) valores.put(banco.NOME, nome);
        if (pergunta != null) valores.put(banco.PERGUNTA, pergunta);
        if (acertosmodulo != null) valores.put(banco.ACERTOSMODULO, acertosmodulo);
        if (mod1 != null) valores.put(banco.MOD1, mod1);
        if (mod2 != null) valores.put(banco.MOD2, mod2);
        if (mod3 != null) valores.put(banco.MOD3, mod3);
        if (mod4 != null) valores.put(banco.MOD4, mod4);
        if (total != null) valores.put(banco.TOTAL, total);

        resultado = db.insertOrThrow(banco.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    public void alterarRegistro(int id, String nome, String pergunta, String acertosmodulo,
                                String mod1, String mod2, String mod3, String mod4, String total){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriarBanco.ID + "=" + id;

        valores = new ContentValues();

        if (nome != null) valores.put(banco.NOME, nome);
        if (pergunta != null) valores.put(banco.PERGUNTA, pergunta);
        if (acertosmodulo != null) valores.put(banco.ACERTOSMODULO, acertosmodulo);
        if (mod1 != null) valores.put(banco.MOD1, mod1);
        if (mod2 != null) valores.put(banco.MOD2, mod2);
        if (mod3 != null) valores.put(banco.MOD3, mod3);
        if (mod4 != null) valores.put(banco.MOD4, mod4);
        if (total != null) valores.put(banco.TOTAL, total);

        db.update(CriarBanco.TABELA,valores,where,null);
    }

    public void deletarRegistro(int id){
        String where = CriarBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriarBanco.TABELA, where, null);
    }

    public void addQuestao() {

        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM " + CriarBanco.TABELA2, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getInt(0) == 0) {
                Questao q1 = new Questao("quem é o negao delicia 1?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q1);
                Questao q2 = new Questao("quem é o negao delicia 2?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q2);
                Questao q3 = new Questao("quem é o negao delicia 3?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q3);
                Questao q4 = new Questao("quem é o negao delicia 4?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q4);
                Questao q5 = new Questao("quem é o negao delicia 5?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q5);
                Questao q6 = new Questao("quem é o negao delicia 6?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q6);
                Questao q7 = new Questao("quem é o negao delicia 7?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q7);
                Questao q8 = new Questao("quem é o negao delicia 8?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q8);
                Questao q9 = new Questao("quem é o negao delicia 9?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q9);
                Questao q10 = new Questao("quem é o negao delicia 10?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q10);
                Questao q11 = new Questao("quem é o negao delicia 11?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q11);
                Questao q12 = new Questao("quem é o negao delicia 12?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q12);
                Questao q13 = new Questao("quem é o negao delicia 13?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q13);
                Questao q14 = new Questao("quem é o negao delicia 14?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q14);
                Questao q15 = new Questao("quem é o negao delicia 15?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q15);
                Questao q16 = new Questao("quem é o negao delicia 16?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q16);
                Questao q17 = new Questao("quem é o negao delicia 17?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q17);
                Questao q18 = new Questao("quem é o negao delicia 18?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q18);
                Questao q19 = new Questao("quem é o negao delicia 19?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q19);
                Questao q20 = new Questao("quem é o negao delicia 20?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "kalango", "kalango");
                this.addQuestao(q20);
            }
        }
    }

    private void addQuestao(Questao quest) {

        db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(banco.QUESTAO, quest.getQUESTAO());
        valores.put(banco.RESPOSTA, quest.getRESPOSTA());
        valores.put(banco.OPTA, quest.getOPTA());
        valores.put(banco.OPTB, quest.getOPTB());
        valores.put(banco.OPTC, quest.getOPTC());
        valores.put(banco.OPTD, quest.getOPTD());

        db.insert(banco.TABELA2, null, valores);
        db.close();
    }

    public List<Questao> getTodasQuestoes() {
        List<Questao> quesList = new ArrayList<Questao>();

        String selectQuery = "SELECT  * FROM " + banco.TABELA2;
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Questao quest = new Questao();
                quest.setID(cursor.getInt(0));
                quest.setQUESTAO(cursor.getString(1));
                quest.setRESPOSTA(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quest.setOPTD(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }

        return quesList;
    }

}
