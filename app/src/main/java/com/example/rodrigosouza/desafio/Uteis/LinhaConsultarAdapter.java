package com.example.rodrigosouza.desafio.Uteis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.rodrigosouza.desafio.ConsultarActivity;
import com.example.rodrigosouza.desafio.R;
import com.example.rodrigosouza.desafio.Model.NotaModel;
import com.example.rodrigosouza.desafio.Repository.NotaRepository;


public class LinhaConsultarAdapter extends BaseAdapter {

    //CRIANDO UM OBJETO LayoutInflater PARA FAZER LINK A NOSSA VIEW(activity_linha_consultar.xml)
    private static LayoutInflater layoutInflater = null;

    List<NotaModel> notaModels =  new ArrayList<NotaModel>();

    NotaRepository  notaRepository;

    private ConsultarActivity consultarActivity;

    public LinhaConsultarAdapter(ConsultarActivity consultarActivity, List<NotaModel> notaModels ) {

        this.notaModels       =  notaModels;
        this.consultarActivity  =  consultarActivity;
        this.layoutInflater     = (LayoutInflater) this.consultarActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.notaRepository   = new NotaRepository(consultarActivity);
    }

    //RETORNA A QUANTIDADE DE REGISTROS DA LISTA
    @Override
    public int getCount(){

        return notaModels.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        //CRIANDO UM OBJETO DO TIPO View PARA ACESSAR O NOSSO ARQUIVO DE LAYOUT activity_linha_consultar.xml
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha_consultar,null);

        TextView textViewCodigo          = (TextView) viewLinhaLista.findViewById(R.id.textViewCodigo);
        TextView textViewEmissor         = (TextView) viewLinhaLista.findViewById(R.id.textViewEmissor);
        TextView textViewReceptor        = (TextView) viewLinhaLista.findViewById(R.id.textViewReceptor);
        TextView textViewServico         = (TextView) viewLinhaLista.findViewById(R.id.textViewServico);
        TextView textViewValorImposto    = (TextView) viewLinhaLista.findViewById(R.id.textViewValorImposto);
        TextView textViewValorTotal      = (TextView) viewLinhaLista.findViewById(R.id.textViewValorTotal);
        TextView textViewRegsitroAtivo   = (TextView) viewLinhaLista.findViewById(R.id.textViewRegistroAtivo);

        Button buttonExcluir             = (Button)   viewLinhaLista.findViewById(R.id.buttonExcluir);
        Button   buttonEditar            = (Button)   viewLinhaLista.findViewById(R.id.buttonEditar);

        textViewCodigo.setText(String.valueOf(notaModels.get(position).getCodigo()));

        textViewEmissor.setText(notaModels.get(position).getEmissor());
        textViewReceptor.setText(notaModels.get(position).getReceptor());
        textViewServico.setText(notaModels.get(position).getServico());
        textViewValorImposto.setText(notaModels.get(position).getValorImposto());
        textViewValorTotal.setText(notaModels.get(position).getValorTotal());

        //SETANDO SE O REGISTRO ESTA ATIVO OU NÃO
        if(notaModels.get(position).getRegistroAtivo() == 1)
            textViewRegsitroAtivo.setText("Registro Ativo:Sim");
        else
            textViewRegsitroAtivo.setText("Registro Ativo:Não");


        //CRIANDO EVENTO CLICK PARA O BOTÃO DE EXCLUIR REGISTRO
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                notaRepository.Excluir(notaModels.get(position).getCodigo());

                Toast.makeText(consultarActivity, "Registro excluido com sucesso!", Toast.LENGTH_LONG).show();

                AtualizarLista();

            }
        });

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        return viewLinhaLista;
    }


    public void AtualizarLista(){

        this.notaModels.clear();
        this.notaModels = notaRepository.SelecionarTodos();
        this.notifyDataSetChanged();
    }

}