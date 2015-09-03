package com.example.alvinkalango.showdatrigonometria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriarBanco extends SQLiteOpenHelper{

    protected static final String NOME_BANCO = "banco.db";
    protected static final String TABELA2 = "quiz";
    protected static final String TABELA = "cadastro";
    protected static final int VERSAO = 1;
    protected static final String ID = "_id";
    protected static final String NOME = "nome";
    protected static final String PERGUNTA = "pergunta";
    protected static final String ACERTOSMODULO = "acertosmodulo";
    protected static final String PERCENTUAL1 = "percentual1";
    protected static final String PERCENTUAL2 = "percentual2";
    protected static final String PERCENTUAL3 = "percentual3";
    protected static final String PERCENTUAL4 = "percentual4";
    protected static final String PERCENTUALTOTAL = "percentualtotal";
    protected static final String QUESTAO = "questao";
    protected static final String RESPOSTA = "resposta";
    protected static final String OPTA = "opta";
    protected static final String OPTB = "optb";
    protected static final String OPTC = "optc";
    protected static final String OPTD = "optd";

    public CriarBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOME + " TEXT, " +
                PERGUNTA + " INTERGER, " +
                ACERTOSMODULO + " INTERGER, " +
                PERCENTUAL1 + " INTERGER, " +
                PERCENTUAL2 + " INTERGER, " +
                PERCENTUAL3 + " INTERGER, " +
                PERCENTUAL4 + " INTERGER, " +
                PERCENTUALTOTAL + " INTERGER)";

        String sql2 = "CREATE TABLE IF NOT EXISTS " + TABELA2 + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QUESTAO + " TEXT, " +
                RESPOSTA + " TEXT, " +
                OPTA + " TEXT, " +
                OPTB + " TEXT, " +
                OPTC + " TEXT, " +
                OPTD + " TEXT)";

        db.execSQL(sql);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }
}
