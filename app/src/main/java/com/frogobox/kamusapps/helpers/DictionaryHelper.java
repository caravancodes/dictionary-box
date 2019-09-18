package com.frogobox.kamusapps.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.frogobox.kamusapps.models.database.DataContract.DataEntry;
import com.frogobox.kamusapps.models.database.DatabaseHelper;
import com.frogobox.kamusapps.models.dataclass.Dictionary;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

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
public class DictionaryHelper {

    // ---------------------------------------------------------------------------------------------
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;
    // ---------------------------------------------------------------------------------------------

    public DictionaryHelper(Context context){
        this.context = context;
    }

    public DictionaryHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public long insert(String TABLE_NAME, Dictionary mDictionary){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(DataEntry.COLUMN_WORD, mDictionary.getWord());
        initialValues.put(DataEntry.COLUMN_DESCRIPTION, mDictionary.getDescription());
        return database.insert(TABLE_NAME, null, initialValues);
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

    public void insertTransaction(String TABLE_NAME, Dictionary mDictionary){
        String sql = "INSERT INTO "+TABLE_NAME+" ("+DataEntry.COLUMN_WORD+", "+DataEntry.COLUMN_DESCRIPTION+") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, mDictionary.getWord());
        stmt.bindString(2, mDictionary.getDescription());
        stmt.execute();
        stmt.clearBindings();

    }

    public ArrayList<Dictionary> getDataByWord(String TABLE_NAME, String word){
        Cursor cursor = database.query(TABLE_NAME,null, DataEntry.COLUMN_WORD +" LIKE ?",new String[]{word},null,null,_ID + " ASC",null);
        cursor.moveToFirst();
        ArrayList<Dictionary> arrayList = new ArrayList<>();
        Dictionary mDictionary;
        if (cursor.getCount()>0) {
            do {
                mDictionary = new Dictionary();
                mDictionary.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                mDictionary.setWord(cursor.getString(cursor.getColumnIndexOrThrow(DataEntry.COLUMN_WORD)));
                mDictionary.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DataEntry.COLUMN_DESCRIPTION)));
                arrayList.add(mDictionary);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<Dictionary> getAllData(String TABLE_NAME){
        Cursor cursor = database.query(TABLE_NAME,null,null,null,null,null,_ID+ " ASC",null);
        cursor.moveToFirst();
        ArrayList<Dictionary> arrayList = new ArrayList<>();
        Dictionary mDictionary;
        if (cursor.getCount()>0) {
            do {
                mDictionary = new Dictionary();
                mDictionary.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                mDictionary.setWord(cursor.getString(cursor.getColumnIndexOrThrow(DataEntry.COLUMN_WORD)));
                mDictionary.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DataEntry.COLUMN_DESCRIPTION)));
                arrayList.add(mDictionary);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public int update(String TABLE_NAME, Dictionary mDictionary){
        ContentValues args = new ContentValues();
        args.put(DataEntry.COLUMN_WORD, mDictionary.getWord());
        args.put(DataEntry.COLUMN_DESCRIPTION, mDictionary.getDescription());
        return database.update(TABLE_NAME, args, _ID + "= '" + mDictionary.getId() + "'", null);
    }

    public int delete(String TABLE_NAME, int id){
        return database.delete(TABLE_NAME, _ID + " = '"+id+"'", null);
    }

}
