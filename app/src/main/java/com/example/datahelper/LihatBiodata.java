package com.example.datahelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatBiodata extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton2;
    TextView text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_biodata);

        dbHelper = new DataHelper(this);

        text1 = (TextView) findViewById(R.id.TextView01);
        text2 = (TextView) findViewById(R.id.TextView02);
        text3 = (TextView) findViewById(R.id.TextView03);
        text4 = (TextView) findViewById(R.id.TextView04);
        text5 = (TextView) findViewById(R.id.TextView05);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Get the "nama" value from the intent extras
        String nama = getIntent().getStringExtra("nama");

        // Define the selectionArgs with the value of "nama"
        String[] selectionArgs = {nama};

        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = ?", selectionArgs);

        if (cursor.moveToFirst()) {
            text1.setText(cursor.getString(0));
            text2.setText(cursor.getString(1));
            text3.setText(cursor.getString(2));
            text4.setText(cursor.getString(3));
            text5.setText(cursor.getString(4));
        }

        ton2 = (Button) findViewById(R.id.button1);
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}