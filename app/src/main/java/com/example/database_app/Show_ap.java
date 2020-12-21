package com.example.database_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Show_ap extends AppCompatActivity {

    TextView show_ap;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_ap);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        show_ap = (TextView) findViewById(R.id.textView_for_show_ap);

        cursor = db.query(DatabaseHelper.TABLE_AP, null, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.KEY_ID_AP);
            int fullNameIndex = cursor.getColumnIndex(DatabaseHelper.KEY_AP_FULLNAME);
            int semesterIndex = cursor.getColumnIndex(DatabaseHelper.KEY_AP_SEMESTER);
            int disciplineIndex = cursor.getColumnIndex(DatabaseHelper.KEY_AP_DISCIPLINE);
            int ratingIndex = cursor.getColumnIndex(DatabaseHelper.KEY_RATING);
            String str = "";
            do {
                str = str + "ФИО = " + cursor.getString(fullNameIndex) +
                        ", Дисциплина = " + cursor.getString(disciplineIndex) +
                        ", Семестр = " + cursor.getString(semesterIndex) +
                        ", Оценка = " + cursor.getString(ratingIndex) + "\n\n";

                Log.d("mLogs", "ID = " + cursor.getInt(idIndex) +
                        ", ФИО = " + cursor.getString(fullNameIndex) +
                        ", Дисциплина = " + cursor.getString(disciplineIndex) +
                        ", Семестр = " + cursor.getString(semesterIndex) +
                        ", Оценка = " + cursor.getString(ratingIndex));
            } while (cursor.moveToNext());
            show_ap.setText(str);
        } else {
            Log.d("mLogs", "0 rows");
            show_ap.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            show_ap.setText("Журнал успеваемости пуст!");
        }
        cursor.close();
    }
}