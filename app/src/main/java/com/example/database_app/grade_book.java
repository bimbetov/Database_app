package com.example.database_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class grade_book extends AppCompatActivity {

    private Button search_academic_performance_btn, add_academic_performance_btn, show_apDatabase_btn;
    EditText et_search_surname_in_journal, et_search_name_in_journal,
            et_search_fatherName_in_journal, et_search_semester_in_journal, et_search_discipline_in_journal;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_book);

        dbHelper = new DatabaseHelper(this);
        addListenerOnClick();
    }

    public void addListenerOnClick() {
        search_academic_performance_btn = (Button) findViewById(R.id.search_academic_performance_btn);
        add_academic_performance_btn = (Button) findViewById(R.id.add_academic_performance_btn);
        show_apDatabase_btn = (Button) findViewById(R.id.show_apDatabase_btn);

        et_search_surname_in_journal = (EditText) findViewById(R.id.et_search_surname_in_journal);
        et_search_name_in_journal = (EditText) findViewById(R.id.et_search_name_in_journal);
        et_search_fatherName_in_journal = (EditText) findViewById(R.id.et_search_fatherName_in_journal);
        et_search_discipline_in_journal = (EditText) findViewById(R.id.et_search_discipline_in_journal);
        et_search_semester_in_journal = (EditText) findViewById(R.id.et_search_semester_in_journal);

        db = dbHelper.getWritableDatabase();

        show_apDatabase_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Show_ap.class);
                startActivity(intent);
            }
        });

        search_academic_performance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = et_search_surname_in_journal.getText().toString() + " " + et_search_name_in_journal.getText().toString() + " " +
                        et_search_fatherName_in_journal.getText().toString();
                String semester = et_search_semester_in_journal.getText().toString();
                String discipline = et_search_discipline_in_journal.getText().toString();

                Intent intent = new Intent(getApplicationContext(), academic_performance_change.class);

                Log.d("mLogs", "---ПОИСК---");
                cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_AP + " where " +
                        DatabaseHelper.KEY_AP_FULLNAME + " = ? AND " +
                        DatabaseHelper.KEY_AP_SEMESTER + " = ? AND " +
                        DatabaseHelper.KEY_AP_DISCIPLINE + " = ?", new String[]{fullName, semester, discipline});

                String currentId = "";

                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        String str;
                        do {
                            str = "";
                            for (String cn : cursor.getColumnNames()) {
                                if (cn.equals("ID")) {
                                    currentId = cursor.getString(cursor.getColumnIndex(cn));
                                }
                                str = str.concat(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                            }
                            Log.d("mLogs", str);
                            //Toast.makeText(getApplicationContext(), "Ничего не найдено", Toast.LENGTH_LONG).show();
                        } while (cursor.moveToNext());
                        if (!str.equals("")) {
                            intent.putExtra("JOURNAL_INFO", str);
                            intent.putExtra("JOURNAL_ID", currentId);
                            startActivity(intent);
                        }
                    }
                    cursor.close();
                } else {
                    Log.d("mLogs", "Cursor is null");
                }
            }
        });

        add_academic_performance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), adding_academic_performance.class);
                startActivity(intent);
            }
        });

    }
}