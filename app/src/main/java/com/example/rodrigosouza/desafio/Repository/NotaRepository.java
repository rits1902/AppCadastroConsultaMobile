package com.example.rodrigosouza.desafio.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.example.rodrigosouza.desafio.Uteis.Database;
import com.example.rodrigosouza.desafio.Model.NotaModel;


public class NotaRepository {

    Database database;


    public NotaRepository(Context context){

        database =  new Database(context);

    }

    public void Salvar(NotaModel notaModel){

        ContentValues contentValues =  new ContentValues();

        contentValues.put("ds_emissor", notaModel.getEmissor());
        contentValues.put("ds_receptor", notaModel.getReceptor());
        contentValues.put("ds_servico", notaModel.getServico());
        contentValues.put("dt_valor_total", notaModel.getValorTotal());
        contentValues.put("fl_valor_imposto", notaModel.getValorImposto());
        contentValues.put("fl_ativo", notaModel.getRegistroAtivo());

        database.GetConexaoDataBase().insert("tb_nota_fiscal",null,contentValues);

    }


    public void Atualizar(NotaModel notaModel){

        ContentValues contentValues =  new ContentValues();

        contentValues.put("ds_emissor",       notaModel.getEmissor());
        contentValues.put("ds_receptor",   notaModel.getReceptor());
        contentValues.put("ds_servico",       notaModel.getServico());
        contentValues.put("dt_valor_total", notaModel.getValorImposto());
        contentValues.put("fl_valor_imposto",notaModel.getValorImposto());
        contentValues.put("fl_ativo",      notaModel.getRegistroAtivo());

        database.GetConexaoDataBase().update("tb_nota_fiscal", contentValues, "id_nota = ?", new String[]{Integer.toString(notaModel.getCodigo())});
    }


    public Integer Excluir(int codigo){

        return database.GetConexaoDataBase().delete("tb_nota_fiscal","id_nota = ?", new String[]{Integer.toString(codigo)});
    }


    public NotaModel GetPessoa(int codigo){


        Cursor cursor =  database.GetConexaoDataBase().rawQuery("SELECT * FROM tb_nota_fiscal WHERE id_nota= "+ codigo,null);

        cursor.moveToFirst();

        NotaModel notaModel =  new NotaModel();

        notaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_nota")));
        notaModel.setEmissor(cursor.getString(cursor.getColumnIndex("ds_emissor")));
        notaModel.setReceptor(cursor.getString(cursor.getColumnIndex("ds_receptor")));
        notaModel.setServico(cursor.getString(cursor.getColumnIndex("ds_servico")));
        notaModel.setValorTotal(cursor.getString(cursor.getColumnIndex("dt_valor_total")));
        notaModel.setValorImposto(cursor.getString(cursor.getColumnIndex("fl_valor_imposto")));
        notaModel.setRegistroAtivo((byte)cursor.getShort(cursor.getColumnIndex("fl_ativo")));

        return notaModel;

    }


    public List<NotaModel> SelecionarTodos(){

        List<NotaModel> nota = new ArrayList<NotaModel>();

        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id_nota,         ");
        stringBuilderQuery.append("        ds_emissor,      ");
        stringBuilderQuery.append("        ds_receptor,     ");
        stringBuilderQuery.append("        ds_servico,      ");
        stringBuilderQuery.append("        dt_valor_total,  ");
        stringBuilderQuery.append("        fl_valor_imposto,");
        stringBuilderQuery.append("        fl_ativo         ");
        stringBuilderQuery.append("  FROM  tb_nota_fiscal   ");
        stringBuilderQuery.append(" ORDER BY ds_emissor     ");

        Cursor cursor = database.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        cursor.moveToFirst();

        NotaModel notaModel;

        while (!cursor.isAfterLast()){

            notaModel =  new NotaModel();

            notaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_nota")));
            notaModel.setEmissor(cursor.getString(cursor.getColumnIndex("ds_emissor")));
            notaModel.setReceptor(cursor.getString(cursor.getColumnIndex("ds_receptor")));
            notaModel.setServico(cursor.getString(cursor.getColumnIndex("ds_servico")));
            notaModel.setValorTotal(cursor.getString(cursor.getColumnIndex("dt_valor_total")));
            notaModel.setValorImposto(cursor.getString(cursor.getColumnIndex("fl_valor_imposto")));
            notaModel.setRegistroAtivo((byte)cursor.getShort(cursor.getColumnIndex("fl_ativo")));

            nota.add(notaModel);

            cursor.moveToNext();
        }

        return nota;

    }
}