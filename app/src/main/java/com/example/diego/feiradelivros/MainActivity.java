package com.example.diego.feiradelivros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastrarParticipante;
    private Button btnCadastrarReserva;
    private Button btnCadastrarLivro;
    private ListView lstParticipantes;
    private ArrayList<Participante> participantes = new ArrayList<>();
    private static final int PEDE_PARTICIPANTE = 1;

    // Adiciona dados iniciais no aplicativo
    private void adicionaParticipantesIniciais() {
        Participante participante1 = new Participante("Diego", "diego.paiva13@gmail.com");
        Participante participante2 = new Participante("Thaynara", "thaynaraferreira1996@hotmail.com");

        participantes.add(participante1);
        participantes.add(participante2);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrarParticipante = (Button) findViewById(R.id.btnCadastrarParticipante);
        btnCadastrarReserva = (Button) findViewById(R.id.btnCadastrarReserva);
        btnCadastrarLivro = (Button) findViewById(R.id.btnCadastrarLivro);

        lstParticipantes = (ListView) findViewById(R.id.lstParticipantes);

        adicionaParticipantesIniciais();

        final ArrayAdapter<Participante> adaptador = new ArrayAdapter<Participante>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1,
                participantes
        );

        // Lista os participantes
        lstParticipantes.setAdapter(adaptador);

        btnCadastrarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, CadastroParticipante.class);
                startActivityForResult(intent, PEDE_PARTICIPANTE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MainActivity.PEDE_PARTICIPANTE && resultCode == RESULT_OK && data != null){
            String nome = data.getStringExtra("nome");
            String email = data.getStringExtra("email");
            Participante participante = new Participante(nome, email);
            participantes.add(participante);
        }
    }

}
