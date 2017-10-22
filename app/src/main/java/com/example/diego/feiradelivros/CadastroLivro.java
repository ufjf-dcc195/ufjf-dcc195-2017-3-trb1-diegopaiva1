package com.example.diego.feiradelivros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroLivro extends AppCompatActivity {

    private EditText txtTitulo;
    private EditText txtEditora;
    private EditText txtAno;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtEditora = (EditText) findViewById(R.id.txtEditora);
        txtAno = (EditText) findViewById(R.id.txtAno);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        // Primeiro campo a ser digitado quando a tela é aberta
        txtTitulo.requestFocus();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pega o titulo informado no cadastro
                String titulo = txtTitulo.getText().toString();
                // Pega a editora informada no cadastro
                String editora = txtTitulo.getText().toString();
                // Pega o ano informado no cadastro
                Integer ano = Integer.parseInt(txtAno.getText().toString());
                // Cria novo intent para mudar de activity
                Intent intent = new Intent ();
                intent.putExtra("titulo", titulo);
                intent.putExtra("ano", ano);
                setResult(RESULT_OK, intent);
                finish();
            }
        });



    }
}
