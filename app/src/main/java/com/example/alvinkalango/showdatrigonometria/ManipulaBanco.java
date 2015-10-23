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
        String[] campos =  {banco.ID, banco.NOME, banco.MODULO, banco.MOD1, banco.MOD2, banco.MOD3, banco.MOD4, banco.TOTAL};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public Cursor carregarDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID, banco.NOME, banco.MODULO, banco.MOD1, banco.MOD2, banco.MOD3, banco.MOD4, banco.TOTAL};
        String where = banco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public String inserirRegistro(String nome, String modulo, String mod1, String mod2, String mod3, String mod4, String total){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        if (nome != null) valores.put(banco.NOME, nome);
        if (modulo != null) valores.put(banco.MODULO, modulo);
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

    public void alterarRegistro(int id, String nome, String modulo, String mod1, String mod2, String mod3, String mod4, String total){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = banco.ID + "=" + id;

        valores = new ContentValues();

        if (nome != null) valores.put(banco.NOME, nome);
        if (modulo != null) valores.put(banco.MODULO, modulo);
        if (mod1 != null) valores.put(banco.MOD1, mod1);
        if (mod2 != null) valores.put(banco.MOD2, mod2);
        if (mod3 != null) valores.put(banco.MOD3, mod3);
        if (mod4 != null) valores.put(banco.MOD4, mod4);
        if (total != null) valores.put(banco.TOTAL, total);

        db.update(banco.TABELA,valores,where,null);
    }

    public void deletarRegistro(int id){
        String where = banco.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(banco.TABELA, where, null);
    }

    public void addQuestao() {

        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM " + banco.TABELA2, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getInt(0) == 0) {
                Questao q1 = new Questao("Pergunta 1?", "Jalur Pesawat", "chicle", "Jasa Programmer", "JP", "chicle");
                this.addQuestao(q1);
                Questao q2 = new Questao("Pergunta 2?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q2);
                Questao q3 = new Questao("Pergunta 3?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q3);
                Questao q4 = new Questao("Pergunta 4?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q4);
                Questao q5 = new Questao("Pergunta 5?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q5);
                Questao q6 = new Questao("Pergunta 6?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q6);
                Questao q7 = new Questao("Pergunta 7?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q7);
                Questao q8 = new Questao("Pergunta 8?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q8);
                Questao q9 = new Questao("Pergunta 9?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q9);
                Questao q10 = new Questao("Pergunta 10?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q10);
                Questao q11 = new Questao("Pergunta 11?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q11);
                Questao q12 = new Questao("Pergunta 12?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q12);
                Questao q13 = new Questao("Pergunta 13?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q13);
                Questao q14 = new Questao("Pergunta 14?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q14);
                Questao q15 = new Questao("Pergunta 15?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q15);
                Questao q16 = new Questao("Pergunta 16?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q16);
                Questao q17 = new Questao("Pergunta 17?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q17);
                Questao q18 = new Questao("Pergunta 18?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q18);
                Questao q19 = new Questao("Pergunta 19?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q19);
                Questao q20 = new Questao("Pergunta 20?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q20);
                Questao q21 = new Questao("Pergunta 21?", "Jalur Pesawat", "chicle", "Jasa Programmer", "JP", "chicle");
                this.addQuestao(q21);
                Questao q22 = new Questao("Pergunta 22?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q22);
                Questao q23 = new Questao("Pergunta 23?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q23);
                Questao q24 = new Questao("Pergunta 24?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q24);
                Questao q25 = new Questao("Pergunta 25?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q25);
                Questao q26 = new Questao("Pergunta 26?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q26);
                Questao q27 = new Questao("Pergunta 27?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q27);
                Questao q28 = new Questao("Pergunta 28?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q28);
                Questao q29 = new Questao("Pergunta 29?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q29);
                Questao q30 = new Questao("Pergunta 30?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q30);
                Questao q31 = new Questao("Pergunta 31?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q31);
                Questao q32 = new Questao("Pergunta 32?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q32);
                Questao q33 = new Questao("Pergunta 33?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q33);
                Questao q34 = new Questao("Pergunta 34?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q34);
                Questao q35 = new Questao("Pergunta 35?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q35);
                Questao q36 = new Questao("Pergunta 36?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q36);
                Questao q37 = new Questao("Pergunta 37?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q37);
                Questao q38 = new Questao("Pergunta 38?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q38);
                Questao q39 = new Questao("Pergunta 39?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q39);
                Questao q40 = new Questao("Pergunta 40?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "chicle", "chicle");
                this.addQuestao(q40);
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
