package com.example.alvinkalango.showdatrigonometria;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ManipulaBanco {

    private SQLiteDatabase db;
    private CriarBanco banco;

    public ManipulaBanco(Context context){
        banco = new CriarBanco(context);
    }

    public String inserirDados(String nome, String pergunta, String acertosmodulo,
                             String percentual1, String percentual2, String percentual3, String percentual4, String percentualtotal){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(banco.NOME, nome);
        valores.put(banco.PERGUNTA, pergunta);
        valores.put(banco.ACERTOSMODULO, acertosmodulo);
        valores.put(banco.PERCENTUAL1, percentual1);
        valores.put(banco.PERCENTUAL2, percentual2);
        valores.put(banco.PERCENTUAL3, percentual3);
        valores.put(banco.PERCENTUAL4, percentual4);
        valores.put(banco.PERCENTUALTOTAL, percentualtotal);

        resultado = db.insertOrThrow(banco.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

}
