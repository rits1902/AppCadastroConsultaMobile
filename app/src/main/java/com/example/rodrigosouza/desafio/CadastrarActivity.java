package com.example.rodrigosouza.desafio;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.example.rodrigosouza.desafio.Uteis.Uteis;
import com.example.rodrigosouza.desafio.Model.NotaModel;
import com.example.rodrigosouza.desafio.Repository.NotaRepository;

public class CadastrarActivity extends AppCompatActivity {


    /*COMPONENTES DA TELA*/
    EditText         editTextEmissor;
    EditText         editTextReceptor;
    EditText         editTextServico;
    EditText         editTextValorTotal;
    EditText         editTextValorImposto;
    CheckBox         checkBoxRegistroAtivo;
    Button           buttonSalvar;
    Button           buttonVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        this.Localizacao();

        this.CriarComponentes();

        this.CriarEventos();

    }

    //VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
    protected  void CriarComponentes(){

        editTextEmissor        = (EditText) this.findViewById(R.id.editTextEmissor);
        editTextReceptor       = (EditText) this.findViewById(R.id.editTextReceptor);
        editTextServico        = (EditText) this.findViewById(R.id.editTextServico);
        editTextValorTotal     = (EditText) this.findViewById(R.id.editTextValorTotal);
        editTextValorImposto   = (EditText) this.findViewById(R.id.editTextValorImposto);
        checkBoxRegistroAtivo  = (CheckBox)this.findViewById(R.id.checkBoxRegistroAtivo);
        buttonSalvar           = (Button) this.findViewById(R.id.buttonSalvar);
        buttonVoltar           = (Button) this.findViewById(R.id.buttonVoltar);

    }

    protected  void CriarEventos(){

        buttonSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Salvar_onClick();
            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMainActivity);
                finish();
            }
        });
    }

    //VALIDA OS CAMPOS E SALVA AS INFORMAÇÕES NO BANCO DE DADOS
    protected  void Salvar_onClick(){

        if(editTextEmissor.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.emissor_obrigatorio));

            editTextEmissor.requestFocus();
        }
        else if(editTextReceptor.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.receptor_obrigatorio));

            editTextReceptor.requestFocus();

        }
        else if(editTextServico.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.servico_obrigatorio));

            editTextServico.requestFocus();

        }
        else if(editTextValorTotal.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.valor_total_obrigatorio));

            editTextValorTotal.requestFocus();
        }
        else if(editTextValorImposto.getText().toString().trim().equals("")){

            Uteis.Alert(this, this.getString(R.string.valor_imposto_obrigatorio));

            editTextValorImposto.requestFocus();
        }
        else{
            /*CRIANDO UM OBJETO PESSOA*/
            NotaModel notaModel = new NotaModel();

            notaModel.setEmissor(editTextEmissor.getText().toString().trim());
            notaModel.setReceptor(editTextReceptor.getText().toString().trim());
            notaModel.setServico(editTextServico.getText().toString().trim());
            notaModel.setValorTotal(editTextValorTotal.getText().toString().trim());
            notaModel.setValorImposto(editTextValorImposto.getText().toString().trim());
            notaModel.setRegistroAtivo((byte)0);

            if(checkBoxRegistroAtivo.isChecked())
                notaModel.setRegistroAtivo((byte)1);

            new NotaRepository(this).Salvar(notaModel);

            Uteis.Alert(this,this.getString(R.string.registro_salvo_sucesso));

            LimparCampos();
        }


    }

    protected void LimparCampos(){

        editTextEmissor.setText(null);
        editTextReceptor.setText(null);
        editTextServico.setText(null);
        editTextValorTotal.setText(null);
        editTextValorImposto.setText(null);
        checkBoxRegistroAtivo.setChecked(false);
    }

    protected  void Localizacao(){

        Locale locale = new Locale("pt", "BR");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, null);
    }

}