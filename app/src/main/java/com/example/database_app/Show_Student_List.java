package com.example.database_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Show_Student_List extends AppCompatActivity {

    TextView show_studentList;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show__student__list);

        show_studentList = (TextView) findViewById(R.id.textView_for_show_studentList);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        cursor = db.query(DatabaseHelper.TABLE_NAME,null,null,null,null,null,null,null);

        if (cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DatabaseHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DatabaseHelper.KEY_NAME);
            int surnameIndex = cursor.getColumnIndex(DatabaseHelper.KEY_SURNAME);
            int fatherNameIndex = cursor.getColumnIndex(DatabaseHelper.KEY_FATHER_NAME);
            int yearAdmissionIndex = cursor.getColumnIndex(DatabaseHelper.KEY_YEAR_ADMISSION);
            int formEducationIndex = cursor.getColumnIndex(DatabaseHelper.KEY_FORM_EDUCATION);
            String str = "";
            do {
                str = str + "Имя = " + cursor.getString(nameIndex) +
                        ", Фамилия = " + cursor.getString(surnameIndex) +
                        ", Отчество = " + cursor.getString(fatherNameIndex) +
                        ", Год поступления = " + cursor.getString(yearAdmissionIndex) +
                        ", Форма обучения = " + cursor.getString(formEducationIndex) + "\n\n";

                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", surname = " + cursor.getString(surnameIndex) +
                        ", fatherName = " + cursor.getString(fatherNameIndex) +
                        ", yearAdmission = " + cursor.getString(yearAdmissionIndex) +
                        ", formEducation = " + cursor.getString(formEducationIndex));
            } while (cursor.moveToNext());
            show_studentList.setText(str);
        } else {
            Log.d("mLog", "0 rows");
            show_studentList.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            show_studentList.setText("Список студентов пуст!");
        }
        cursor.close();

    }
}