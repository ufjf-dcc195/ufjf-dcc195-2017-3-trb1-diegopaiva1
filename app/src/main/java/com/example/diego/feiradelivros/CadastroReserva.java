package com.example.diego.feiradelivros;

import android.content.Intent;
import android.icu.text.MessagePattern;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class CadastroReserva extends AppCompatActivity {

    private Spinner spinnerParticipantes;
    private Spinner spinnerLivros;
    private Button btnCadastrar;
    private TextView labelEscolherParticipante;
    private TextView labelEscolherLivro;
    private ArrayList<Participante> participantesPresentes = new ArrayList<Participante>();
    private ArrayList<Livro> livrosCadastrados = new ArrayList<Livro>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_reserva);

        spinnerParticipantes = (Spinner) findViewById(R.id.spinnerParticipantes);
        spinnerLivros = (Spinner) findViewById(R.id.spinnerLivros);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        labelEscolherParticipante = (TextView) findViewById(R.id.labelEscolherParticipante);
        labelEscolherLivro = (TextView) findViewById(R.id.labelEscolherLivro);

        // Recebe o ArrayList de participantes da activity principal
        ArrayList<Participante> participantes = new ArrayList<Participante>();
        participantes = (ArrayList<Participante>) getIntent().getSerializableExtra("participantes");

        // Recebe o ArrayList de livros da activity principal
        ArrayList<Livro> livros = new ArrayList<Livro>();
        livros = (ArrayList<Livro>) getIntent().getSerializableExtra("livros");

        // Preenche o spinner com os participantes que estão presentes
        for(int i = 0; i < participantes.size(); i++) {
            Participante participante = participantes.get(i);
            if(participante.estaPresente()) {
                participantesPresentes.add(participante);
            }
        }

        // Preenche o spinner com os livros cadastrados
        for(int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);
            livrosCadastrados.add(livro);
        }

        // Cria adaptador para realizar a listagem de participantes
        final ArrayAdapter<Participante> adaptador1 = new ArrayAdapter<Participante>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                participantesPresentes
        );

        // Cria adaptador para realizar a listagem de livros
        final ArrayAdapter<Livro> adaptador2 = new ArrayAdapter<Livro>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                livrosCadastrados
        );

        // Setta os adaptadores para os spinners
        spinnerParticipantes.setAdapter(adaptador1);
        spinnerLivros.setAdapter(adaptador2);



        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Pega o participante selecionado pelo usuario
                    Participante participante = (Participante) spinnerParticipantes.getSelectedItem();
                    // Pega o livro selecionado pelo usuario
                    Livro livro = (Livro) spinnerLivros.getSelectedItem();

                    // Adiciona ao ArrayList de livros reservados do participante o livro selecionado
                    participante.adicionarLivroReservado(livro);
                    // Adiciona Ao ArrayList de participantes do livro o participante selecionado
                    livro.adicionarParticipante(participante);
                }
                catch(Exception e) {
                    e.getLocalizedMessage();
                }

                // Volta para a tela inicial
                Intent intent = new Intent (CadastroReserva.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
