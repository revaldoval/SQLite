package com.example.datahelper;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    public static MainActivity ma;
    String[] daftar;
    ListView listView;
    protected Cursor cursor;
    DataHelper dataHelper;
    public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, BuatBiodata.class);
                startActivity(intent);
                instance = MainActivity.this;
                dataHelper = new DataHelper(MainActivity.this);
                RefreshList();
            }
        });
    }

    public void RefreshList() {
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata", null);
        daftar = new String[cursor.getCount()];

        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1);
        }

        listView = findViewById(R.id.listView1);
        listView.setSelected(true);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftar));

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                final CharSequence[] dialogItems = {"Lihat Biodata", "Update Biodata", "Hapus Biodata"};
                final String selection = daftar[arg2];

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItems, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), LihatBiodata.class);
                                intent.putExtra("nama", selection);
                                startActivity(intent);
                                break;
                            case 1:
                                Intent intent2 = new Intent(getApplicationContext(), UpdateBiodata.class);
                                intent2.putExtra("nama", selection);
                                startActivity(intent2);
                                break;
                            case 2:
                                SQLiteDatabase db = dataHelper.getWritableDatabase();
                                db.execSQL("DELETE FROM biodata WHERE nama = '" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
                ((ArrayAdapter) listView.getAdapter()).notifyDataSetChanged();
            }
        });
    }
}