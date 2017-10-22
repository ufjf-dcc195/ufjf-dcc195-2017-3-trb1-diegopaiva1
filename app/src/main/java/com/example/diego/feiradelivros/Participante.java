package com.example.diego.feiradelivros;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Participante implements Serializable {
    private String nome;
    private String email;
    private String horaEntrada;
    private String horaSaida;
    private SimpleDateFormat dateFormatHora;
    private ArrayList<Livro> livrosReservados;

    public Participante() {
        this.livrosReservados = new ArrayList<Livro>();
        this.horaEntrada = null;
        this.horaSaida = null;
        this.dateFormatHora = new SimpleDateFormat("HH:mm:ss");
    }

    public Participante(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.horaEntrada = null;
        this.livrosReservados = new ArrayList<Livro>();
        this.horaSaida = null;
        this.dateFormatHora = new SimpleDateFormat("HH:mm:ss");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public ArrayList<Livro> getLivrosReservados() {
        return livrosReservados;
    }

    public void setLivrosReservados(ArrayList<Livro> livrosReservados) {
        this.livrosReservados = livrosReservados;
    }

    public void adicionarLivroReservado(Livro livro) {
        if(livro != null) {
            this.livrosReservados.add(livro);
        }
    }

    public void registraHora(Context context){
        if(this.horaEntrada==null){
            Date hora = Calendar.getInstance().getTime();
            this.horaEntrada = dateFormatHora.format(hora);
            Toast.makeText(context, "Hora de entrada registrada!", Toast.LENGTH_LONG).show();
        }
        else if(horaSaida==null){
            Date hora = Calendar.getInstance().getTime();
            this.horaSaida = dateFormatHora.format(hora);
            Toast.makeText(context, "Hora de saída registrada!", Toast.LENGTH_LONG).show();
        }
        else{
            this.horaEntrada = null;
            this.horaSaida = null;
            Toast.makeText(context, "Horários limpos!", Toast.LENGTH_LONG).show();
        }
    }

    public boolean estaPresente() {
        // Se o participante ja entrou mas ainda nao saiu, ele está presente
        if(this.horaEntrada != null && this.horaSaida == null) {
            return true;
        }
        else {
            return false;
        }
    }

    // Método para que, ao realizar a listagem, seja exibido apenas o nome do participante
    @Override
    public String toString() {
        return this.nome;
    }

}

