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
import android.widget.TextView;
import android.widget.Toast;

public class search_add_student extends AppCompatActivity {

    private Button add_student_btn, search_student_btn, show_studentList_btn;
    private EditText et_search_name, et_search_surname, et_search_fatherName;

    String STUDENT_INFO = "STUDENT_INFO";
    String STUDENT_ID = "STUDENT_ID";

    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_add_student);

        dbHelper = new DatabaseHelper(this);

        addListenerOnClick();
    }

    public void addListenerOnClick() {
        search_student_btn = (Button) findViewById(R.id.search_student_btn);
        add_student_btn = (Button) findViewById(R.id.add_student_btn);
        show_studentList_btn = (Button) findViewById(R.id.show_student_btn);

        et_search_surname = (EditText) findViewById(R.id.et_search_surname);
        et_search_name = (EditText) findViewById(R.id.et_search_name);
        et_search_fatherName = (EditText) findViewById(R.id.et_search_fatherName);

        db = dbHelper.getWritableDatabase();

        search_student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_search_name.getText().toString();
                String surname = et_search_surname.getText().toString();
                String fatherName = et_search_fatherName.getText().toString();

                Intent intent = new Intent(getApplicationContext(), student_info.class);

                Log.d("mLogs", "---ПОИСК---");

                cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_NAME + " where " +
                        DatabaseHelper.KEY_SURNAME + " = ? AND " +
                        DatabaseHelper.KEY_NAME + " = ? AND " +
                        DatabaseHelper.KEY_FATHER_NAME + " = ?", new String[]{surname, name, fatherName});

                String currentStudentId = "";

                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        String str;
                        do {
                            str = "";
                            for (String cn : cursor.getColumnNames()) {
                                if (cn.equals("ID")) {
                                    currentStudentId = cursor.getString(cursor.getColumnIndex(cn));
                                }
                                str = str.concat(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                            }
                            Log.d("mLogs", str);
                        } while (cursor.moveToNext());
                        if (!str.equals("")) {
                            intent.putExtra(STUDENT_INFO, str);
                            intent.putExtra(STUDENT_ID, currentStudentId);
                            startActivity(intent);
                        }
                    }
                    cursor.close();
                } else {
                    Log.d("mLogs", "Cursor is null");
                }
            }
        });

        show_studentList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Show_Student_List.class);
                startActivity(intent);

                /*Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,null,null,null,null,null,null,null);

                if (cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DatabaseHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DatabaseHelper.KEY_NAME);
                    int surnameIndex = cursor.getColumnIndex(DatabaseHelper.KEY_SURNAME);
                    int fatherNameIndex = cursor.getColumnIndex(DatabaseHelper.KEY_FATHER_NAME);
                    int yearAdmissionIndex = cursor.getColumnIndex(DatabaseHelper.KEY_YEAR_ADMISSION);
                    int formEducationIndex = cursor.getColumnIndex(DatabaseHelper.KEY_FORM_EDUCATION);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", surname = " + cursor.getString(surnameIndex) +
                                ", fatherName = " + cursor.getString(fatherNameIndex) +
                                ", yearAdmission = " + cursor.getString(yearAdmissionIndex) +
                                ", formEducation = " + cursor.getString(formEducationIndex));
                    } while (cursor.moveToNext());

                } else {
                    Log.d("mLog", "0 rows");
                }
                cursor.close();*/
            }
        });

        add_student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), adding_student.class);
                startActivity(intent);
            }
        });

    }
}