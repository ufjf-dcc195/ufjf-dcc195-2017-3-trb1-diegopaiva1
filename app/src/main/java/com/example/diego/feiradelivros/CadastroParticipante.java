package com.example.diego.feiradelivros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class CadastroParticipante extends AppCompatActivity {

    private Button btnCadastrarParticipante;
    private EditText txtNome;
    private EditText txtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);

        btnCadastrarParticipante = (Button) findViewById(R.id.btnCadastrarParticipante);

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtEmail = (EditText) findViewById(R.id.txtEmail);

        // Primeiro campo a ser digitado quando é aberta a tela
        txtNome.requestFocus();

        btnCadastrarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pega o nome informado no cadastro
                String nome = txtNome.getText().toString();
                // Pega o email informado no cadastro
                String email = txtEmail.getText().toString();

                // Cria novo intent para mudar de activity
                Intent intent = new Intent ();
                intent.putExtra("nome", nome);
                intent.putExtra("email", email);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
