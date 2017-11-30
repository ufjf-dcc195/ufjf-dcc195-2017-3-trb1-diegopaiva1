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

public class LivroAdapter extends CursorAdapter {

    private BibliotecaDpHelper bibliotecaHelper;

    public LivroAdapter(Context context, Cursor c) {
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
                    BibliotecaContract.Livro._ID,
                    BibliotecaContract.Livro.COLUMN_NAME_TITULO,
                    BibliotecaContract.Livro.COLUMN_NAME_EDITORA,
                    BibliotecaContract.Livro.COLUMN_NAME_ANO,
            };
            String selecao = BibliotecaContract.Livro.COLUMN_NAME_ANO + " > ?";
            String[] args = {"1950"};
            String sort = BibliotecaContract.Livro.COLUMN_NAME_EDITORA + " DESC";
            Cursor c = db.query(BibliotecaContract.Livro.TABLE_NAME, visao, selecao, args, null, null, sort);
            this.changeCursor(c);
        }
        catch(Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
        }
    }

    public void inserirLivro(String titulo, String editora, String ano) {
        try {
            SQLiteDatabase db = bibliotecaHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(BibliotecaContract.Livro.COLUMN_NAME_TITULO, titulo);
            values.put(BibliotecaContract.Livro.COLUMN_NAME_EDITORA, editora);
            values.put(BibliotecaContract.Livro.COLUMN_NAME_ANO, ano);
            long id = db.insert(BibliotecaContract.Livro.TABLE_NAME, null, values);
            atualizar();
        }
        catch (Exception e){
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
        }
    }
}
