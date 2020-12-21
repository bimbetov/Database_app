package com.example.database_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adding_discipline extends AppCompatActivity {

    EditText etSpeciality, etDiscipline, etSemester, etHours, etReporting_form;
    Button add_new_discipline_btn;
    DatabaseHelper myDb;
    String speciality, discipline, semester, hours, reporting_form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_discipline);

        add_new_discipline_btn = (Button)findViewById(R.id.add_new_discipline_btn);

        etSpeciality =(EditText)findViewById(R.id.et_speciality);
        etDiscipline =(EditText)findViewById(R.id.et_discipline);
        etSemester =(EditText)findViewById(R.id.et_semester);
        etHours =(EditText)findViewById(R.id.et_hours);
        etReporting_form =(EditText)findViewById(R.id.et_reporting_form);

        myDb = new DatabaseHelper(this);

        SQLiteDatabase database = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        add_new_discipline_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speciality = etSpeciality.getText().toString();
                discipline = etDiscipline.getText().toString();
                semester = etSemester.getText().toString();
                hours = etHours.getText().toString();
                reporting_form = etReporting_form.getText().toString();

                contentValues.put(DatabaseHelper.KEY_SPECIALITY_NAME, speciality);
                contentValues.put(DatabaseHelper.KEY_DISCIPLINE, discipline);
                contentValues.put(DatabaseHelper.KEY_SEMESTER, semester);
                contentValues.put(DatabaseHelper.KEY_HOURS, hours);
                contentValues.put(DatabaseHelper.KEY_REPORTING_FORM, reporting_form);

                database.insert(DatabaseHelper.TABLE_CURRICULUM, null, contentValues);
                Toast.makeText(getApplicationContext(),"Добавлено", Toast.LENGTH_LONG).show();
            }
        });
    }

}