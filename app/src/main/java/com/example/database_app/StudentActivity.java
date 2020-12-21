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
import android.widget.Toast;

public class StudentActivity extends AppCompatActivity {

    private Button daytime_btn, evening_btn, extramural_btn, count_the_number_of_hours_btn, get_certificate_btn;
    private EditText et_for_discipline, et_for_semester, surname_editText, name_editText, patronymic_editText, sem_editText;
    //private String DAYTIME_COUNT = "DAYTIME_COUNT";
    //private String EVENING_COUNT = "EVENING_COUNT";
    //private String EXTRAMURAL_COUNT = "EXTRAMURAL_COUNT";

    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        dbHelper = new DatabaseHelper(this);

        addListenerOnButton();
    }


    private void addListenerOnButton() {
        daytime_btn = (Button) findViewById(R.id.daytime_btn);
        evening_btn = (Button) findViewById(R.id.evening_btn);
        extramural_btn = (Button) findViewById(R.id.extramural_btn);
        count_the_number_of_hours_btn = (Button) findViewById(R.id.count_the_number_of_hours_btn);
        get_certificate_btn = (Button) findViewById(R.id.get_certificate_btn);

        et_for_discipline = (EditText) findViewById(R.id.et_for_discipline);
        et_for_semester = (EditText) findViewById(R.id.et_for_semester);

        surname_editText = (EditText) findViewById(R.id.surname_editText);
        name_editText = (EditText) findViewById(R.id.name_editText);
        patronymic_editText = (EditText) findViewById(R.id.patronymic_editText);
        sem_editText = (EditText) findViewById(R.id.sem_editText);

        db = dbHelper.getWritableDatabase();

        daytime_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_NAME + " where " +
                        DatabaseHelper.KEY_FORM_EDUCATION + " = ?", new String[]{"Дневная"});

                Toast.makeText(getApplicationContext(), "ДНЕВНАЯ: " + cursor.getCount(), Toast.LENGTH_LONG).show();
                cursor.close();
            }
        });
        evening_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_NAME + " where " +
                        DatabaseHelper.KEY_FORM_EDUCATION + " = ?", new String[]{"Вечерняя"});

                Toast.makeText(getApplicationContext(), "ВЕЧЕРНЯЯ: " + cursor.getCount(), Toast.LENGTH_LONG).show();
                cursor.close();
            }
        });
        extramural_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_NAME + " where " +
                        DatabaseHelper.KEY_FORM_EDUCATION + " = ?", new String[]{"Заочная"});

                Toast.makeText(getApplicationContext(), "ЗАЧОНАЯ: " + cursor.getCount(), Toast.LENGTH_LONG).show();
                cursor.close();
            }
        });

        count_the_number_of_hours_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchDiscipline = et_for_discipline.getText().toString();
                String searchSemester = et_for_semester.getText().toString();

                cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_CURRICULUM + " where " +
                        DatabaseHelper.KEY_DISCIPLINE + " = ? AND " +
                        DatabaseHelper.KEY_SEMESTER + " = ?", new String[]{searchDiscipline, searchSemester});
                cursor.moveToFirst();
                String hours = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_HOURS));

                Toast.makeText(getApplicationContext(), searchDiscipline + ": " + hours, Toast.LENGTH_LONG).show();
                cursor.close();
            }
        });

        get_certificate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = surname_editText.getText().toString() + " " + name_editText.getText().toString() + " " + patronymic_editText.getText().toString();
                String semester = sem_editText.getText().toString();


                if (!fullName.equals("  ") && !semester.equals("")) {
                    Intent intent = new Intent(getApplicationContext(), Data_output.class);

                    intent.putExtra("FULL_NAME", fullName);
                    intent.putExtra("SEMESTER", semester);

                    startActivity(intent);
                }
            }
        });
    }


}