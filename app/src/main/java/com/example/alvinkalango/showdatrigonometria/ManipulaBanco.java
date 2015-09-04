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
        String[] campos =  {CriarBanco.ID, CriarBanco.NOME};
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
                banco.PERCENTUAL1,
                banco.PERCENTUAL2,
                banco.PERCENTUAL3,
                banco.PERCENTUAL4,
                banco.PERCENTUALTOTAL
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
                                  String percentual1, String percentual2, String percentual3, String percentual4, String percentualtotal){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        if (nome != null) valores.put(banco.NOME, nome);
        if (pergunta != null) valores.put(banco.PERGUNTA, pergunta);
        if (acertosmodulo != null) valores.put(banco.ACERTOSMODULO, acertosmodulo);
        if (percentual1 != null) valores.put(banco.PERCENTUAL1, percentual1);
        if (percentual2 != null) valores.put(banco.PERCENTUAL2, percentual2);
        if (percentual3 != null) valores.put(banco.PERCENTUAL3, percentual3);
        if (percentual4 != null) valores.put(banco.PERCENTUAL4, percentual4);
        if (percentualtotal != null) valores.put(banco.PERCENTUALTOTAL, percentualtotal);

        resultado = db.insertOrThrow(banco.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    public void alterarRegistro(int id, String nome, String pergunta, String acertosmodulo,
                                String percentual1, String percentual2, String percentual3, String percentual4, String percentualtotal){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriarBanco.ID + "=" + id;

        valores = new ContentValues();

        if (nome != null) valores.put(banco.NOME, nome);
        if (pergunta != null) valores.put(banco.PERGUNTA, pergunta);
        if (acertosmodulo != null) valores.put(banco.ACERTOSMODULO, acertosmodulo);
        if (percentual1 != null) valores.put(banco.PERCENTUAL1, percentual1);
        if (percentual2 != null) valores.put(banco.PERCENTUAL2, percentual2);
        if (percentual3 != null) valores.put(banco.PERCENTUAL3, percentual3);
        if (percentual4 != null) valores.put(banco.PERCENTUAL4, percentual4);
        if (percentualtotal != null) valores.put(banco.PERCENTUALTOTAL, percentualtotal);

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
        if (cursor.moveToFirst()) {
            Log.d("tabela", "tem tabela");
        }
        else {
            Questao q1 = new Questao("What is JP?", "Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "JP", "Jasa Programmer");
            this.addQuestao(q1);
            Questao q2 = new Questao("where the JP place?", "Monas, Jakarta", "Gelondong, Bangun Tapan, bantul", "JP", "Gelondong, Bangun Tapan, bandul", "Gelondong, Bangun Tapan, bantul");
            this.addQuestao(q2);
            Questao q3 = new Questao("who is CEO of the JP?", "Usman and Jack", "Jack and Rully", "Rully and Usman", "JP", "Rully and Usman");
            this.addQuestao(q3);
            Questao q4 = new Questao("what do you know about JP?", "JP is programmer home", "JP also realigy home", "JP", "all answer is true", "all answer is true");
            this.addQuestao(q4);
            Questao q5 = new Questao("what do you learn in JP?", "Realigy", "Programming", "all answer is true", "JP", "all answer is true");
            this.addQuestao(q5);
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
