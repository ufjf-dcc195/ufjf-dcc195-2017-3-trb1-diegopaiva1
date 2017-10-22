package com.example.diego.feiradelivros;


import android.graphics.Typeface;

import java.io.Serializable;
import java.util.ArrayList;

public class Livro implements Serializable {
    private String titulo;
    private String editora;
    private Integer ano;
    private ArrayList<Participante> participantes;

    public Livro(String titulo, String editora, Integer ano) {
        this.participantes = new ArrayList<Participante>();
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
    }

    public Livro() {
        this.participantes = new ArrayList<Participante>();
    }

    public void adicionarParticipante(Participante participante) {
        if (participante != null) {
            this.participantes.add(participante);
        }

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        String dadosVisualizacao =  "\nTítulo: " + this.titulo + "\n" +
                                    "\nEditora: " + this.editora + "\n" +
                                    "\nAno: " + this.ano + "\n";
        return dadosVisualizacao;
    }
}