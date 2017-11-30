package com.example.diego.feiradelivros;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BibliotecaDpHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Biblioteca.db";

    public BibliotecaDpHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BibliotecaContract.SQL_CREATE_PARTICIPANTE);
        sqLiteDatabase.execSQL(BibliotecaContract.SQL_CREATE_LIVRO);
        sqLiteDatabase.execSQL(BibliotecaContract.SQL_CREATE_RESERVA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {
        sqLiteDatabase.execSQL(BibliotecaContract.SQL_DROP_LIVRO);
        sqLiteDatabase.execSQL(BibliotecaContract.SQL_DROP_PARTICIPANTE);
        sqLiteDatabase.execSQL(BibliotecaContract.SQL_DROP_RESERVA);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void participantes(SQLiteDatabase db, int i, int j) {
        db.execSQL("SELECT * FROM "  + BibliotecaContract.Participante.TABLE_NAME + " WHERE " + BibliotecaContract.Participante.COLUMN_NAME_ENTRADA + " <> NULL");
    }

}
