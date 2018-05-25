package com.example.rodrigosouza.desafio.Uteis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String NOME_BASE_DE_DADOS   = "SISTEMA.db";

    private static final int    VERSAO_BASE_DE_DADOS = 1;

    public Database(Context context){

        super(context,NOME_BASE_DE_DADOS,null,VERSAO_BASE_DE_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder stringBuilderCreateTable = new StringBuilder();

        stringBuilderCreateTable.append(" CREATE TABLE tb_nota_fiscal (");
        stringBuilderCreateTable.append("        id_nota          INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuilderCreateTable.append("        ds_emissor       TEXT    NOT NULL,                  ");
        stringBuilderCreateTable.append("        ds_receptor      TEXT    NOT NULL,                  ");
        stringBuilderCreateTable.append("        ds_servico       TEXT    NOT NULL,                  ");
        stringBuilderCreateTable.append("        dt_valor_total   TEXT    NOT NULL,                  ");
        stringBuilderCreateTable.append("        fl_valor_imposto TEXT    NOT NULL,                  ");
        stringBuilderCreateTable.append("        fl_ativo         INT     NOT NULL )                 ");


        db.execSQL(stringBuilderCreateTable.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS tb_nota_fiscal");
        onCreate(db);

    }

    public SQLiteDatabase GetConexaoDataBase(){

        return this.getWritableDatabase();
    }
}
