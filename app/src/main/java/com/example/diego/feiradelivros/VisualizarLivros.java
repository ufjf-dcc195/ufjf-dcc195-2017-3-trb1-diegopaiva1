package com.example.diego.feiradelivros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class VisualizarLivros extends AppCompatActivity {

    private ListView lstLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_livros);

        lstLivros = (ListView) findViewById(R.id.lstLivros);

        // Recebe o ArrayList de livros da activty principal
        ArrayList<Livro> livros = new ArrayList<Livro>();
        livros = (ArrayList<Livro>) getIntent().getSerializableExtra("livros");


        // Cria adaptador para realizar a listagem
        final ArrayAdapter<Livro> adaptador = new ArrayAdapter<Livro>(
            getApplicationContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            livros
        );


        // Exibe listagem de livros
        lstLivros.setAdapter(adaptador);

    }




}
