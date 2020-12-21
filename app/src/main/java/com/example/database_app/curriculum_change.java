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

public class curriculum_change extends AppCompatActivity {

    private Button change_curriculum_information_btn, save_new_curriculum_info_btn, delete_curriculum_information_btn;
    TextView curriculum_info_tv;
    EditText et_new_speciality_name, et_new_discipline, et_new_semester, et_new_hours, et_new_reporting_form;
    DatabaseHelper dbHelper;
    String discipline_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum_change);

        discipline_id = getIntent().getExtras().get("DISCIPLINE_ID").toString();
        String discipline_info = getIntent().getExtras().get("DISCIPLINE_INFO").toString();
        curriculum_info_tv = (TextView) findViewById(R.id.curriculum_info_tv);
        curriculum_info_tv.setText(discipline_info);

        addListenerOnClick();
    }

    public void addListenerOnClick() {
        change_curriculum_information_btn = (Button) findViewById(R.id.change_curriculum_information_btn);
        delete_curriculum_information_btn = (Button) findViewById(R.id.delete_curriculum_information_btn);
        save_new_curriculum_info_btn = (Button) findViewById(R.id.save_new_curriculum_info_btn);

        et_new_speciality_name = (EditText) findViewById(R.id.et_new_speciality_name);
        et_new_discipline = (EditText) findViewById(R.id.et_new_discipline);
        et_new_semester = (EditText) findViewById(R.id.et_new_semester);
        et_new_hours = (EditText) findViewById(R.id.et_new_hours);
        et_new_reporting_form = (EditText) findViewById(R.id.et_new_reporting_form);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout_for_new_info_curriculum);

        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        change_curriculum_information_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });

        save_new_curriculum_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_specialityName = et_new_speciality_name.getText().toString();
                String new_discipline = et_new_discipline.getText().toString();
                String new_semester = et_new_semester.getText().toString();
                String new_hours = et_new_hours.getText().toString();
                String new_reporting_form = et_new_reporting_form.getText().toString();

                if (!discipline_id.equalsIgnoreCase("")) {
                    contentValues.put(DatabaseHelper.KEY_SPECIALITY_NAME, new_specialityName);
                    contentValues.put(DatabaseHelper.KEY_DISCIPLINE, new_discipline);
                    contentValues.put(DatabaseHelper.KEY_SEMESTER, new_semester);
                    contentValues.put(DatabaseHelper.KEY_HOURS, new_hours);
                    contentValues.put(DatabaseHelper.KEY_REPORTING_FORM, new_reporting_form);

                    int updCount = database.update(DatabaseHelper.TABLE_CURRICULUM, contentValues, DatabaseHelper.KEY_ID_CURRICULUM + " = ?", new String[]{discipline_id});
                    Log.d("mLogs", "количество обновленных строк = " + updCount);
                }

                linearLayout.setVisibility(View.INVISIBLE);
            }
        });
        delete_curriculum_information_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delCount = database.delete(DatabaseHelper.TABLE_CURRICULUM, DatabaseHelper.KEY_ID_CURRICULUM + "=" + discipline_id, null);

                Log.d("mLog", "количество удаленных строк = " + delCount);
            }
        });


    }
}