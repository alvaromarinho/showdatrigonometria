package com.example.alvinkalango.showdatrigonometria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public void alterarRegistro(int id, String nome, String pergunta, String acertosmodulo,
                                String percentual1, String percentual2, String percentual3, String percentual4, String percentualtotal){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriarBanco.ID + "=" + id;

        valores = new ContentValues();
        valores.put(banco.NOME, nome);
        valores.put(banco.PERGUNTA, pergunta);
        valores.put(banco.ACERTOSMODULO, acertosmodulo);
        valores.put(banco.PERCENTUAL1, percentual1);
        valores.put(banco.PERCENTUAL2, percentual2);
        valores.put(banco.PERCENTUAL3, percentual3);
        valores.put(banco.PERCENTUAL4, percentual4);
        valores.put(banco.PERCENTUALTOTAL, percentualtotal);

        db.update(CriarBanco.TABELA,valores,where,null);
    }

    public void deletarRegistro(int id){
        String where = CriarBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriarBanco.TABELA,where,null);
    }

}
