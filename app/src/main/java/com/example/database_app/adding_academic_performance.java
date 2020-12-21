package com.example.database_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adding_academic_performance extends AppCompatActivity {

    Button add_new_ap_btn;
    EditText et_new_surname_in_journal, et_new_name_in_journal, et_new_fatherName_in_journal,
            et_new_discipline_in_journal, et_new_semester_in_journal, et_new_rating_in_journal;
    String surname_in_journal, name_in_journal, fatherName_in_journal,
            discipline_in_journal, semester_in_journal, rating_in_journal;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_academic_performance);

        add_new_ap_btn = (Button)findViewById(R.id.add_new_ap_btn);

        et_new_surname_in_journal = (EditText)findViewById(R.id.et_surname_in_journal);
        et_new_name_in_journal = (EditText)findViewById(R.id.et_name_in_journal);
        et_new_fatherName_in_journal = (EditText)findViewById(R.id.et_fatherName_in_journal);
        et_new_discipline_in_journal = (EditText)findViewById(R.id.et_discipline_in_journal);
        et_new_semester_in_journal = (EditText)findViewById(R.id.et_semester_in_journal);
        et_new_rating_in_journal = (EditText)findViewById(R.id.et_rating_in_journal);

        myDb = new DatabaseHelper(this);

        SQLiteDatabase database = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        add_new_ap_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surname_in_journal = et_new_surname_in_journal.getText().toString();
                name_in_journal = et_new_name_in_journal.getText().toString();
                fatherName_in_journal = et_new_fatherName_in_journal.getText().toString();
                semester_in_journal = et_new_semester_in_journal.getText().toString();
                discipline_in_journal = et_new_discipline_in_journal.getText().toString();
                rating_in_journal = et_new_rating_in_journal.getText().toString();

                String fullName = surname_in_journal + " " + name_in_journal + " " + fatherName_in_journal;

                contentValues.put(DatabaseHelper.KEY_AP_FULLNAME, fullName);
                contentValues.put(DatabaseHelper.KEY_AP_DISCIPLINE, discipline_in_journal);
                contentValues.put(DatabaseHelper.KEY_AP_SEMESTER, semester_in_journal);
                contentValues.put(DatabaseHelper.KEY_RATING, rating_in_journal);

                database.insert(DatabaseHelper.TABLE_AP, null, contentValues);
                Toast.makeText(getApplicationContext(),"Добавлено", Toast.LENGTH_LONG).show();
            }
        });
    }
}