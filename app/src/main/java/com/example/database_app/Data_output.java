package com.example.database_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Data_output extends AppCompatActivity {

    TextView data_output;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_output);

        data_output = (TextView) findViewById(R.id.textView_for_output_data);

        String fullName = getIntent().getExtras().get("FULL_NAME").toString();
        String semester = getIntent().getExtras().get("SEMESTER").toString();

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_AP + " where " +
                DatabaseHelper.KEY_AP_FULLNAME + " = ? AND " +
                DatabaseHelper.KEY_AP_SEMESTER + " = ?", new String[]{fullName, semester});

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.KEY_ID_AP);
            int fullNameIndex = cursor.getColumnIndex(DatabaseHelper.KEY_AP_FULLNAME);
            int semesterIndex = cursor.getColumnIndex(DatabaseHelper.KEY_AP_SEMESTER);
            int disciplineIndex = cursor.getColumnIndex(DatabaseHelper.KEY_AP_DISCIPLINE);
            int ratingIndex = cursor.getColumnIndex(DatabaseHelper.KEY_RATING);
            Log.d("mLogs", "ФИО = " + cursor.getString(fullNameIndex) +
                    ", Семестр = " + cursor.getString(semesterIndex));
            String info_about_ap_student = cursor.getString(fullNameIndex) +
                    ",\nСеместр = " + cursor.getString(semesterIndex) + "\n";
            String currStr = "";
            do {
                Log.d("mLogs", "Дисциплина = " + cursor.getString(disciplineIndex) +
                        ", Оценка = " + cursor.getString(ratingIndex));
                currStr = currStr + cursor.getString(disciplineIndex) +
                        "  " + cursor.getString(ratingIndex) + "\n";
            } while (cursor.moveToNext());
            data_output.setText(info_about_ap_student + currStr);
        } else {
            Log.d("mLogs", "0 rows");
            data_output.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            data_output.setText("ИНФОРМАЦИЯ ОТСУТСТВУЕТ");
        }
        cursor.close();

    }
}