package com.example.diego.feiradelivros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class VisualizarLivros extends AppCompatActivity {

    private ListView lstLivros;
    private LivroAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_livros);

        lstLivros = (ListView) findViewById(R.id.lstLivros);
        adaptador = new LivroAdapter(getBaseContext(), null);

        // Recebe o ArrayList de livros da activty principal
        ArrayList<Livro> livros = new ArrayList<Livro>();
        livros = (ArrayList<Livro>) getIntent().getSerializableExtra("livros");

        /*
        // Cria adaptador para realizar a listagem
        final ArrayAdapter<Livro> adaptador = new ArrayAdapter<Livro>(
            getApplicationContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            livros
        );
        */

        lstLivros.setAdapter(adaptador);
        adaptador.atualizar();


        lstLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), "ID do registro: "+1, Toast.LENGTH_SHORT).show();
                /*
                if (livro != null) {
                    Intent intent = new Intent(VisualizarLivros.this, DetalhesLivro.class);
                    intent.putExtra("livro", livro);
                    startActivity(intent);
                }
                */
            }
        });


    }




}
