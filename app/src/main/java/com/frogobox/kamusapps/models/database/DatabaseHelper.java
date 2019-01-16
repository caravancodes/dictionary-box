package com.frogobox.kamusapps.models.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.frogobox.kamusapps.models.database.DataContract.DataEntry;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * KamusApps
 * Copyright (C) 16/01/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Deklarasi Nama DatabaseHelper dan Versinya --------------------------------------------------------
    private static final String DATABASE = DataContract.DB;
    private static final int DATABASE_VERSION = 1;
    // ---------------------------------------------------------------------------------------------

    // Constants ini gunanya adalah untuk mendapatkan fungsi dari library SQLiteDatabase ------------
    private SQLiteDatabase sqLiteDatabase = getWritableDatabase();
    // ada dua macam, "WriteableDatabase" dan "ReadableDatabase"
    // ---------------------------------------------------------------------------------------------

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
        onCreate(sqLiteDatabase);
    }

    // Disini Code Untuk Create Table di database SQLite -------------------------------------------
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql_create_table_in_to_en = "CREATE TABLE IF NOT EXISTS " + DataEntry.TABLE_IN_TO_EN + " (" +
                    DataContract.DataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DataEntry.COLUMN_WORD + " TEXT NOT NULL," +
                    DataEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL);";

            String sql_create_table_en_to_in = "CREATE TABLE IF NOT EXISTS " + DataEntry.TABLE_EN_TO_IN + " (" +
                    DataContract.DataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DataEntry.COLUMN_WORD + " TEXT NOT NULL," +
                    DataEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL);";

            db.execSQL(sql_create_table_in_to_en);
            db.execSQL(sql_create_table_en_to_in);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ---------------------------------------------------------------------------------------------

    // Untuk mengupgrade table ---------------------------------------------------------------------
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql_drop_table_in_to_en = "DROP TABLE IF EXISTS " + DataEntry.TABLE_IN_TO_EN;
        String sql_drop_table_en_to_in = "DROP TABLE IF EXISTS " + DataEntry.TABLE_EN_TO_IN;
        db.execSQL(sql_drop_table_in_to_en);
        db.execSQL(sql_drop_table_en_to_in);
        onCreate(db);
    }
    // ---------------------------------------------------------------------------------------------

}
