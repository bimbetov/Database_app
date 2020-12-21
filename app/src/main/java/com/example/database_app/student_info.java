package com.example.database_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class student_info extends AppCompatActivity {

    private Button change_student_information_btn, delete_student_information_btn, save_new_student_info_btn;
    private EditText et_new_surname, et_new_name, et_new_fatherName, et_new_yearAdmission, et_new_formEducation;
    TextView student_info_tv;
    String student_id;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        String student_info = getIntent().getExtras().get("STUDENT_INFO").toString();
        student_id = getIntent().getExtras().get("STUDENT_ID").toString();
        student_info_tv = (TextView)findViewById(R.id.student_info_tv);

        student_info_tv.setText(student_info);

        addListenerOnClick();
    }

    public void addListenerOnClick() {
        change_student_information_btn = (Button)findViewById(R.id.change_student_information_btn);
        save_new_student_info_btn = (Button)findViewById(R.id.save_new_student_info_btn);
        delete_student_information_btn =(Button)findViewById(R.id.delete_student_information_btn);

        et_new_surname = (EditText)findViewById(R.id.et_new_surname);
        et_new_name = (EditText)findViewById(R.id.et_new_name);
        et_new_fatherName = (EditText)findViewById(R.id.et_new_fatherName);
        et_new_yearAdmission = (EditText)findViewById(R.id.et_new_yearAdmission);
        et_new_formEducation = (EditText)findViewById(R.id.et_new_formEducation);

        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear_layout_for_new_info);

        change_student_information_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);

            }
        });

        save_new_student_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_name = et_new_name.getText().toString();
                String new_surname = et_new_surname.getText().toString();
                String new_fatherName = et_new_fatherName.getText().toString();
                String new_yearAdmission = et_new_yearAdmission.getText().toString();
                String new_formEducation = et_new_formEducation.getText().toString();

                if (!student_id.equalsIgnoreCase("")){
                    contentValues.put(DatabaseHelper.KEY_SURNAME, new_surname);
                    contentValues.put(DatabaseHelper.KEY_NAME, new_name);
                    contentValues.put(DatabaseHelper.KEY_FATHER_NAME, new_fatherName);
                    contentValues.put(DatabaseHelper.KEY_YEAR_ADMISSION, new_yearAdmission);
                    contentValues.put(DatabaseHelper.KEY_FORM_EDUCATION, new_formEducation);

                    int updCount = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.KEY_ID + " = ?", new String[]{student_id});
                    Log.d("mLogs", "количество обновленных строк = " + updCount);
                }

                linearLayout.setVisibility(View.INVISIBLE);
            }
        });

        delete_student_information_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delCount = database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.KEY_ID + "=" + student_id,null);

                Log.d("mLog", "количество удаленных строк = " + delCount);
            }
        });

    }
}