package com.example.database_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adding_student extends AppCompatActivity {

    Button add_new_student_btn;
    EditText etName, etSurname, etFatherName, etYearAdmission, etFormEducation;
    DatabaseHelper myDb;
    String name, surName, fatherName, yearAdmission, formEducation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_student);

        add_new_student_btn = (Button)findViewById(R.id.add_new_student_btn);

        etName = (EditText)findViewById(R.id.etName);
        etSurname = (EditText)findViewById(R.id.etSurname);
        etFatherName = (EditText)findViewById(R.id.etFatherName);
        etYearAdmission = (EditText)findViewById(R.id.etYearAdmission);
        etFormEducation = (EditText)findViewById(R.id.etFormEducation);

        myDb = new DatabaseHelper(this);

        SQLiteDatabase database = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        add_new_student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                surName = etSurname.getText().toString();
                fatherName = etFatherName.getText().toString();
                yearAdmission = etYearAdmission.getText().toString();
                formEducation = etFormEducation.getText().toString();

                contentValues.put(DatabaseHelper.KEY_NAME, name);
                contentValues.put(DatabaseHelper.KEY_SURNAME, surName);
                contentValues.put(DatabaseHelper.KEY_FATHER_NAME, fatherName);
                contentValues.put(DatabaseHelper.KEY_YEAR_ADMISSION,yearAdmission);
                contentValues.put(DatabaseHelper.KEY_FORM_EDUCATION,formEducation);

                database.insert(DatabaseHelper.TABLE_NAME,null, contentValues);
                Toast.makeText(getApplicationContext(),"Добавлено", Toast.LENGTH_LONG).show();
            }
        });
    }
}