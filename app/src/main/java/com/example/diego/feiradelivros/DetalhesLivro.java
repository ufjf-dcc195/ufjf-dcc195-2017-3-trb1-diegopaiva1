package com.example.diego.feiradelivros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DetalhesLivro extends AppCompatActivity {

    TextView txtTitulo;
    TextView txtEditora;
    TextView txtAno;
    ListView lstParticipantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_livro);

        txtTitulo= (TextView) findViewById(R.id.txtTitulo);
        txtEditora= (TextView) findViewById(R.id.txtEditora);
        txtAno= (TextView) findViewById(R.id.txtAno);
        lstParticipantes= (ListView) findViewById(R.id.lstParticipantes);

        Intent intent = getIntent();
        Livro livro = (Livro) intent.getSerializableExtra("livro");

        txtTitulo.setText("Título: "+livro.getTitulo());
        txtEditora.setText("Editora: "+livro.getEditora());
        txtAno.setText("Ano: "+livro.getAno().toString());

        // Cria adaptador para realizar a listagem
        final ArrayAdapter<Participante> adaptador = new ArrayAdapter<Participante>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                livro.getParticipantes()
        );

        // Lista os participantes
        lstParticipantes.setAdapter(adaptador);
    }
}
