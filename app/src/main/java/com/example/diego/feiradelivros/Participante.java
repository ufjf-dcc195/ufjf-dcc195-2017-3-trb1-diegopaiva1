package com.example.diego.feiradelivros;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Participante implements Serializable {
    private String nome;
    private String email;
    private String horaEntrada=null;
    private String horaSaida=null;
    private SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");

    public Participante() {
        // construtor vazio
    }

    public Participante(String nome, String email) {
        this.nome = nome;
        this.email = email;
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

    public void registraHora(Context context){
        if(horaEntrada==null){
            Date hora = Calendar.getInstance().getTime();
            horaEntrada = dateFormat_hora.format(hora);
            Toast.makeText(context, "Hora de entrada registrada!", Toast.LENGTH_LONG).show();
        }
        else if(horaSaida==null){
            Date hora = Calendar.getInstance().getTime();
            horaSaida = dateFormat_hora.format(hora);
            Toast.makeText(context, "Hora de saída registrada!", Toast.LENGTH_LONG).show();
        }
        else{
            horaEntrada = null;
            horaSaida = null;
            Toast.makeText(context, "Horários limpos!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public String toString() {
        return this.nome;
    }
}

