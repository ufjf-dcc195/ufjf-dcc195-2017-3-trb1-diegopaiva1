package com.example.diego.feiradelivros;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class ParticipanteAdapter extends CursorAdapter {
    private BibliotecaDpHelper bibliotecaHelper;

    public ParticipanteAdapter(Context context, Cursor c) {
        super(context, c, 0);
        bibliotecaHelper = new BibliotecaDpHelper(context);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.activity_layout_participante, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtNomeParticipante = (TextView) view.findViewById(R.id.text_nome);
        TextView txtEmailParticipante = (TextView) view.findViewById(R.id.text_email);
        String nome = cursor.getString(cursor.getColumnIndexOrThrow(BibliotecaContract.Participante.COLUMN_NAME_NOME));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(BibliotecaContract.Participante.COLUMN_NAME_EMAIL));
        txtNomeParticipante.setText(nome);
        txtEmailParticipante.setText(email);
    }


    public void atualizar() {
        try {
            SQLiteDatabase db = bibliotecaHelper.getReadableDatabase();
            String[] visao = {
                    BibliotecaContract.Participante._ID,
                    BibliotecaContract.Participante.COLUMN_NAME_NOME,
                    BibliotecaContract.Participante.COLUMN_NAME_EMAIL,
                    BibliotecaContract.Participante.COLUMN_NAME_ENTRADA,
                    BibliotecaContract.Participante.COLUMN_NAME_SAIDA,
            };
            String sort = BibliotecaContract.Participante.COLUMN_NAME_NOME + " ASC";
            Cursor c = db.query(BibliotecaContract.Participante.TABLE_NAME, visao, null, null, null, null, sort);
            this.changeCursor(c);
        }
        catch(Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
        }
    }

    public void inserirParticipante(String nome, String email, String horaEntrada, String horaSaida) {
        try {
            SQLiteDatabase db = bibliotecaHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(BibliotecaContract.Participante.COLUMN_NAME_NOME, nome);
            values.put(BibliotecaContract.Participante.COLUMN_NAME_EMAIL, email);
            values.put(BibliotecaContract.Participante.COLUMN_NAME_ENTRADA, horaEntrada);
            values.put(BibliotecaContract.Participante.COLUMN_NAME_SAIDA, horaSaida);
            long id = db.insert(BibliotecaContract.Participante.TABLE_NAME, null, values);
            atualizar();
        }
        catch (Exception e){
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
        }
    }
}
