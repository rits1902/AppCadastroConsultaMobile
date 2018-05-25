package com.example.rodrigosouza.desafio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import com.example.rodrigosouza.desafio.Uteis.LinhaConsultarAdapter;
import com.example.rodrigosouza.desafio.Model.NotaModel;
import com.example.rodrigosouza.desafio.Repository.NotaRepository;


public class ConsultarActivity extends AppCompatActivity {

    ListView listViewPessoas;

    Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        listViewPessoas = (ListView)this.findViewById(R.id.listViewPessoas);

        buttonVoltar    = (Button)this.findViewById(R.id.buttonVoltar);


        this.CarregarPessoasCadastradas();

        this.CriarEvento();
    }

    protected  void CriarEvento(){

        buttonVoltar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMainActivity);

                finish();
            }
        });
    }

    protected  void CarregarPessoasCadastradas(){

        NotaRepository notaRepository =  new NotaRepository(this);

        List<NotaModel> nota = notaRepository.SelecionarTodos();

        listViewPessoas.setAdapter(new LinhaConsultarAdapter(this, nota));
    }

}
