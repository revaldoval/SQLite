package com.example.datahelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "biodatadiri.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE biodata (no INTEGER PRIMARY KEY, nama TEXT NULL, tgl TEXT NULL, jk TEXT NULL, alamat TEXT NULL);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

        // Insert sample data
        sql = "INSERT INTO biodata (no, nama, tgl, jk, alamat) VALUES (1, 'Darsiwan', '1996-07-12', 'Laki-laki', 'Indramayu');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database schema upgrades here if needed
        // This method is called when DATABASE_VERSION is increased.
    }
}