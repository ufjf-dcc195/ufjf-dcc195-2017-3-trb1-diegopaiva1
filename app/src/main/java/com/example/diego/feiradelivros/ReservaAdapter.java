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

public class ReservaAdapter extends CursorAdapter {
    private BibliotecaDpHelper bibliotecaHelper;

    public ReservaAdapter(Context context, Cursor c) {
        super(context, c, 0);
        bibliotecaHelper = new BibliotecaDpHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.activity_layout_livro, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTitulo = (TextView) view.findViewById(R.id.text_titulo);
        TextView txtAno = (TextView) view.findViewById(R.id.text_ano);
        TextView txtAutor = (TextView) view.findViewById(R.id.text_editora);
        String titulo = cursor.getString(cursor.getColumnIndexOrThrow(BibliotecaContract.Livro.COLUMN_NAME_TITULO));
        String autor = cursor.getString(cursor.getColumnIndexOrThrow(BibliotecaContract.Livro.COLUMN_NAME_EDITORA));
        int ano = cursor.getInt(cursor.getColumnIndexOrThrow(BibliotecaContract.Livro.COLUMN_NAME_ANO));
        txtTitulo.setText(titulo);
        txtAutor.setText(autor);
        txtAno.setText(String.valueOf(ano));
    }

    public void atualizar() {
        try {
            SQLiteDatabase db = bibliotecaHelper.getReadableDatabase();
            String[] visao = {
                    BibliotecaContract.Reserva._ID,
                    BibliotecaContract.Reserva.COLUMN_NAME_NOME,
                    BibliotecaContract.Reserva.COLUMN_NAME_EMAIL,
                    BibliotecaContract.Reserva.COLUMN_NAME_ENTRADA,
                    BibliotecaContract.Reserva.COLUMN_NAME_SAIDA,
            };
            String sort = BibliotecaContract.Reserva.COLUMN_NAME_NOME + " ASC";
            Cursor c = db.query(BibliotecaContract.Reserva.TABLE_NAME, visao, null, null, null, null, sort);
            this.changeCursor(c);
        }
        catch(Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
        }
    }

    public void inserirReserva(String nome, String email, String horaEntrada, String horaSaida) {
        try {
            SQLiteDatabase db = bibliotecaHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(BibliotecaContract.Reserva.COLUMN_NAME_PARTICIPANTE, nome);
            values.put(BibliotecaContract.Reserva.COLUMN_NAME_LIVRO, email);
            long id = db.insert(BibliotecaContract.Reserva.TABLE_NAME, null, values);
        }
        catch (Exception e){
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
        }
    }
}
