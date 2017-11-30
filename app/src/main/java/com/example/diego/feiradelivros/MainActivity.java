package com.example.diego.feiradelivros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastrarParticipante;
    private Button btnCadastrarReserva;
    private Button btnCadastrarLivro;
    private Button btnVisualizarLivros;
    private ListView lstParticipantes;
    private ArrayList<Participante> participantes = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();
    private static final int PEDE_PARTICIPANTE = 0;
    private static final int PEDE_LIVRO = 1;
    private LivroAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrarParticipante = (Button) findViewById(R.id.btnCadastrarParticipante);
        btnCadastrarReserva = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrarLivro = (Button) findViewById(R.id.btnCadastrarLivro);
        btnVisualizarLivros = (Button) findViewById(R.id.btnVisualizarLivros);
        lstParticipantes = (ListView) findViewById(R.id.lstParticipantes);
        adapter = new LivroAdapter(getBaseContext(), null);

        // Cria adaptador para realizar a listagem
        final ArrayAdapter<Participante> adaptador = new ArrayAdapter<Participante>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                participantes
        );


        // Lista os participantes
        lstParticipantes.setAdapter(adaptador);

        // Adiciona evento de mudança de activity ao clicar em cadastrar participante
        btnCadastrarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, CadastroParticipante.class);
                startActivityForResult(intent, PEDE_PARTICIPANTE);
            }
        });


        lstParticipantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l){
                Participante participante = adaptador.getItem(i);
                if(participante!=null){
                    participante.registraHora(getApplicationContext());
                    return true;
                }
                return false;
            }
        });

        lstParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Participante participante = adaptador.getItem(i);
                if (participante != null) {
                    Intent intent = new Intent(MainActivity.this, DetalhesParticipante.class);
                    intent.putExtra("participante", participante);
                    startActivity(intent);
                }
            }
        });

        // Adiciona evento de mudança de activity ao clicar em cadastrar livro
        btnCadastrarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroLivro.class);
                startActivityForResult(intent, PEDE_LIVRO);
            }
        });

        // Adiciona evento de mudança de activity ao clicar em visualizar livro
        btnVisualizarLivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VisualizarLivros.class);
                intent.putExtra("livros", livros);
                startActivity(intent);
            }
        });

        // Adiciona evento de mudança de activity ao clicar em cadastrar reserva
        btnCadastrarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroReserva.class);
                intent.putExtra("participantes", participantes);
                intent.putExtra("livros", livros);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Checa o resultCode recebido do intent
        if(resultCode == RESULT_OK && data != null) {
            switch(requestCode) {
                case PEDE_PARTICIPANTE:
                    String nome = data.getStringExtra("nome");
                    String email = data.getStringExtra("email");
                    Participante participante = new Participante(nome, email);
                    participantes.add(participante);
                    break;

                case PEDE_LIVRO:
                    String titulo = data.getStringExtra("titulo");
                    String editora = data.getStringExtra("editora");
                    Integer ano = Integer.parseInt(data.getStringExtra("ano"));
                    Livro livro = new Livro(titulo, editora, ano);
                    livros.add(livro);
                    break;

                default: break;
            }
        }
    }

}
