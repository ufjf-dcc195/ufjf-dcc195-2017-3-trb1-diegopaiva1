package com.example.diego.feiradelivros;

import android.provider.BaseColumns;

public final class BibliotecaContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_LIVRO = "CREATE TABLE " + Livro.TABLE_NAME + " (" +
            Livro._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT" + SEP +
            Livro.COLUMN_NAME_TITULO + TEXT_TYPE + SEP +
            Livro.COLUMN_NAME_EDITORA + TEXT_TYPE + SEP +
            Livro.COLUMN_NAME_ANO + INT_TYPE + ")";
    public static final String SQL_DROP_LIVRO = "DROP TABLE IF EXISTS " + Livro.TABLE_NAME;

    public BibliotecaContract() { }

    public static final class Livro implements BaseColumns {
        public static final String TABLE_NAME = "livro";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_EDITORA= "editora";
        public static final String COLUMN_NAME_ANO = "ano";
    }
}
