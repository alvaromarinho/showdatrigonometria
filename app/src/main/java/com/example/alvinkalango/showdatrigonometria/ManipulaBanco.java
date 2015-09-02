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
        valores.put(CriarBanco.NOME, nome);
        valores.put(CriarBanco.PERGUNTA, pergunta);
        valores.put(CriarBanco.ACERTOSMODULO, acertosmodulo);
        valores.put(CriarBanco.PERCENTUAL1, percentual1);
        valores.put(CriarBanco.PERCENTUAL2, percentual2);
        valores.put(CriarBanco.PERCENTUAL3, percentual3);
        valores.put(CriarBanco.PERCENTUAL4, percentual4);
        valores.put(CriarBanco.PERCENTUALTOTAL, percentualtotal);

        resultado = db.insert(CriarBanco.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

}
