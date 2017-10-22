package com.example.diego.feiradelivros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalhesParticipante extends AppCompatActivity {

    TextView txtNome;
    TextView txtEmail;
    TextView txtHoraEntrada;
    TextView txtHoraSaida;
    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_participante);

        txtNome= (TextView) findViewById(R.id.txtNome);
        txtEmail= (TextView) findViewById(R.id.txtEmail);
        txtHoraEntrada= (TextView) findViewById(R.id.txtHoraEntrada);
        txtHoraSaida= (TextView) findViewById(R.id.txtHoraSaida);
        btnVoltar= (Button) findViewById(R.id.btnVoltar);

        Intent intent = getIntent();
        Participante participante = (Participante) intent.getSerializableExtra("participante");

        txtNome.setText("Nome: "+participante.getNome());
        txtEmail.setText("Email: "+participante.getEmail());
        if(participante.getHoraEntrada()!=null) {
            txtHoraEntrada.setText("Horário Entrada: " + participante.getHoraEntrada());
        }
        else{
            txtHoraEntrada.setText("Participante ainda não chegou!");
        }
        if(participante.getHoraSaida()!=null) {
            txtHoraSaida.setText("Horário Saída: " + participante.getHoraSaida());
        }
        else{
            txtHoraSaida.setText("Participante ainda não saiu!");
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (DetalhesParticipante.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}