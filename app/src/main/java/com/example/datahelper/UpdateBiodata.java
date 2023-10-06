package com.example.datahelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateBiodata extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_biodata);
        dbHelper = new DataHelper(this);

        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        text5 = (EditText) findViewById(R.id.editText5);

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

        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE biodata SET nama='" + text2.getText().toString()
                        + "', tgl='" + text3.getText().toString()
                        + "', jk='" + text4.getText().toString()
                        + "', alamat='" + text5.getText().toString()
                        + "' WHERE no=" + text1.getText().toString());

                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();

                MainActivity.ma.RefreshList();

                finish();
            }
        });

        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}