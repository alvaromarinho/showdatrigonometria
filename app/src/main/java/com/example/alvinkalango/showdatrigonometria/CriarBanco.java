package com.example.alvinkalango.showdatrigonometria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriarBanco extends SQLiteOpenHelper{

    protected static final String NOME_BANCO = "bancoSDT";
    protected static final String TABELA = "cadastro";
    protected static final String TABELA2 = "quiz";
    protected static final int VERSAO = 1;
    protected static final String ID = "_id";
    protected static final String NOME = "nome";
    protected static final String MODULO = "modulo";
    protected static final String MOD1 = "mod1";
    protected static final String MOD2 = "mod2";
    protected static final String MOD3 = "mod3";
    protected static final String MOD4 = "mod4";
    protected static final String TOTAL = "total";
    protected static final String QUESTAO = "questao";
    protected static final String RESPOSTA = "resposta";
    protected static final String OPTA = "opta";
    protected static final String OPTB = "optb";
    protected static final String OPTC = "optc";
    protected static final String OPTD = "optd";

    private static final String sql = "CREATE TABLE IF NOT EXISTS " + TABELA + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NOME + " TEXT, " +
            MODULO + " INTERGER, " +
            MOD1 + " INTERGER, " +
            MOD2 + " INTERGER, " +
            MOD3 + " INTERGER, " +
            MOD4 + " INTERGER, " +
            TOTAL + " INTERGER); ";

    private static final String sql2 = "CREATE TABLE IF NOT EXISTS " + TABELA2 + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QUESTAO + " TEXT, " +
            RESPOSTA + " TEXT, " +
            OPTA + " TEXT, " +
            OPTB + " TEXT, " +
            OPTC + " TEXT, " +
            OPTD + " TEXT)";

    public CriarBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA2);

        onCreate(db);
    }
}
