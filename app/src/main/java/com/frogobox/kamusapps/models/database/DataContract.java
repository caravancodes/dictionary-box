package com.frogobox.kamusapps.models.database;

import android.provider.BaseColumns;

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
public class DataContract {
    // Class ini tujuannya untuk menjadi object table

    private DataContract() {
    }

    public static final String DB = "kamus.db"; // Nama Database

    // Deklarasi Elemen Table ----------------------------------------------------------------------
    public static final class DataEntry implements BaseColumns {
        public static final String TABLE_IN_TO_EN = "table_intoen"; // Deklarasi Nama Table
        public static final String TABLE_EN_TO_IN = "table_entoin"; // Deklarasi Nama Table
        public static final String _ID  = BaseColumns._ID; // Dekalarasi ID
        public static final String COLUMN_WORD = "word";
        public static final String COLUMN_DESCRIPTION = "description";
    }
    // ---------------------------------------------------------------------------------------------

}
