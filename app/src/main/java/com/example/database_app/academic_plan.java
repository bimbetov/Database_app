package com.example.database_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class academic_plan extends AppCompatActivity {

    private Button search_discipline_btn, add_discipline_btn, show_curriculumDatabase_btn;
    EditText et_specialty, et_discipline;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_plan);

        dbHelper = new DatabaseHelper(this);


        addListenerOnClick();
    }

    public void addListenerOnClick() {
        search_discipline_btn = (Button)findViewById(R.id.search_discipline_btn);
        add_discipline_btn = (Button)findViewById(R.id.add_discipline_btn);
        show_curriculumDatabase_btn = (Button)findViewById(R.id.show_curriculumDatabase_btn);

        et_specialty = (EditText)findViewById(R.id.specialty_editText);
        et_discipline = (EditText)findViewById(R.id.discipline_editText);

        db = dbHelper.getWritableDatabase();

        show_curriculumDatabase_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Show_curriculum.class);
                startActivity(intent);

                /*cursor = db.query(DatabaseHelper.TABLE_CURRICULUM,null,null,null,null,null,null,null);

                if (cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DatabaseHelper.KEY_ID_CURRICULUM);
                    int specialityNameIndex = cursor.getColumnIndex(DatabaseHelper.KEY_SPECIALITY_NAME);
                    int disciplineIndex = cursor.getColumnIndex(DatabaseHelper.KEY_DISCIPLINE);
                    int semesterIndex = cursor.getColumnIndex(DatabaseHelper.KEY_SEMESTER);
                    int hoursIndex = cursor.getColumnIndex(DatabaseHelper.KEY_HOURS);
                    int reportingFormIndex = cursor.getColumnIndex(DatabaseHelper.KEY_REPORTING_FORM);
                    do {
                        Log.d("mLogs", "ID = " + cursor.getInt(idIndex) +
                                ", Специальность = " + cursor.getString(specialityNameIndex) +
                                ", Дисциплина = " + cursor.getString(disciplineIndex) +
                                ", Семестр = " + cursor.getString(semesterIndex) +
                                ", Часов = " + cursor.getString(hoursIndex) +
                                ", Форма отчетности = " + cursor.getString(reportingFormIndex));
                    } while (cursor.moveToNext());
                } else {
                    Log.d("mLogs", "0 rows");
                }
                cursor.close();*/
            }
        });

        search_discipline_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String speciality = et_specialty.getText().toString();
                String discipline = et_discipline.getText().toString();

                Intent intent = new Intent(getApplicationContext(), curriculum_change.class);
                Log.d("mLogs", "---ПОИСК---");
                cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_CURRICULUM + " where " +
                        DatabaseHelper.KEY_SPECIALITY_NAME + " = ? AND " +
                        DatabaseHelper.KEY_DISCIPLINE + " = ?", new String[]{speciality, discipline});

                String currentDisciplineId = "";

                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        String str;
                        do {
                            str = "";
                            for (String cn : cursor.getColumnNames()) {
                                if (cn.equals("ID")) {
                                    currentDisciplineId = cursor.getString(cursor.getColumnIndex(cn));
                                }
                                str = str.concat(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                            }
                            Log.d("mLogs", str);
                            //Toast.makeText(getApplicationContext(), "Ничего не найдено", Toast.LENGTH_LONG).show();
                        } while (cursor.moveToNext());
                        if (!str.equals("")) {
                            intent.putExtra("DISCIPLINE_INFO", str);
                            intent.putExtra("DISCIPLINE_ID", currentDisciplineId);
                            startActivity(intent);
                        }
                    }
                    cursor.close();
                } else {
                    Log.d("mLogs", "Cursor is null");
                }
            }
        });

        add_discipline_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), adding_discipline.class);
                startActivity(intent);
            }
        });
    }
}