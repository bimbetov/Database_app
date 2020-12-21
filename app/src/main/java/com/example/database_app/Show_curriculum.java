package com.example.database_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Show_curriculum extends AppCompatActivity {

    TextView show_curriculum;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_curriculum);

        show_curriculum = (TextView) findViewById(R.id.textView_for_show_curriculum);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        cursor = db.query(DatabaseHelper.TABLE_CURRICULUM,null,null,null,null,null,null,null);

        if (cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DatabaseHelper.KEY_ID_CURRICULUM);
            int specialityNameIndex = cursor.getColumnIndex(DatabaseHelper.KEY_SPECIALITY_NAME);
            int disciplineIndex = cursor.getColumnIndex(DatabaseHelper.KEY_DISCIPLINE);
            int semesterIndex = cursor.getColumnIndex(DatabaseHelper.KEY_SEMESTER);
            int hoursIndex = cursor.getColumnIndex(DatabaseHelper.KEY_HOURS);
            int reportingFormIndex = cursor.getColumnIndex(DatabaseHelper.KEY_REPORTING_FORM);
            String str = "";
            do {
                str = str + "Специальность = " + cursor.getString(specialityNameIndex) +
                        ", Дисциплина = " + cursor.getString(disciplineIndex) +
                        ", Семестр = " + cursor.getString(semesterIndex) +
                        ", Часов = " + cursor.getString(hoursIndex) +
                        ", Форма отчетности = " + cursor.getString(reportingFormIndex) + "\n\n";

                Log.d("mLogs", "ID = " + cursor.getInt(idIndex) +
                        ", Специальность = " + cursor.getString(specialityNameIndex) +
                        ", Дисциплина = " + cursor.getString(disciplineIndex) +
                        ", Семестр = " + cursor.getString(semesterIndex) +
                        ", Часов = " + cursor.getString(hoursIndex) +
                        ", Форма отчетности = " + cursor.getString(reportingFormIndex));
            } while (cursor.moveToNext());
            show_curriculum.setText(str);
        } else {
            Log.d("mLogs", "0 rows");
            show_curriculum.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            show_curriculum.setText("Учебный план пуст!");
        }
        cursor.close();
    }
}