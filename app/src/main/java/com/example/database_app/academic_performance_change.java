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

public class academic_performance_change extends AppCompatActivity {

    private Button change_academic_performance_information_btn, delete_academic_performance_information_btn,
            save_academic_performance_information_btn;
    TextView academic_performance_info_tv;
    EditText et_new_surname_in_journal, et_new_name_in_journal, et_new_fatherName_in_journal, et_new_discipline_in_journal,
            et_new_semester_in_journal, et_new_rating_in_journal;
    DatabaseHelper dbHelper;
    String ap_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_performance_change);

        ap_id = getIntent().getExtras().get("JOURNAL_ID").toString();
        String ap_info = getIntent().getExtras().get("JOURNAL_INFO").toString();
        academic_performance_info_tv = (TextView) findViewById(R.id.academic_performance_info_tv);
        academic_performance_info_tv.setText(ap_info);

        addListenerOnClick();
    }

    public void addListenerOnClick() {
        change_academic_performance_information_btn = (Button) findViewById(R.id.change_academic_performance_information_btn);
        delete_academic_performance_information_btn = (Button) findViewById(R.id.delete_academic_performance_information_btn);
        save_academic_performance_information_btn = (Button) findViewById(R.id.save_new_ap_info_btn);

        et_new_surname_in_journal = (EditText) findViewById(R.id.et_new_surname_in_journal);
        et_new_name_in_journal = (EditText) findViewById(R.id.et_new_name_in_journal);
        et_new_fatherName_in_journal = (EditText) findViewById(R.id.et_new_fatherName_in_journal);
        et_new_discipline_in_journal = (EditText) findViewById(R.id.et_new_discipline_in_journal);
        et_new_semester_in_journal = (EditText) findViewById(R.id.et_new_semester_in_journal);
        et_new_rating_in_journal = (EditText) findViewById(R.id.et_new_rating_in_journal);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout_for_new_info_academic_performance);

        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        change_academic_performance_information_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });

        save_academic_performance_information_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_fullName = et_new_surname_in_journal.getText().toString() + " " +
                        et_new_name_in_journal.getText().toString() + " " +
                        et_new_fatherName_in_journal.getText().toString();
                String new_discipline = et_new_discipline_in_journal.getText().toString();
                String new_semester = et_new_semester_in_journal.getText().toString();
                String new_rating = et_new_rating_in_journal.getText().toString();


                if (!ap_id.equalsIgnoreCase("")) {
                    contentValues.put(DatabaseHelper.KEY_AP_FULLNAME, new_fullName);
                    contentValues.put(DatabaseHelper.KEY_AP_DISCIPLINE, new_discipline);
                    contentValues.put(DatabaseHelper.KEY_AP_SEMESTER, new_semester);
                    contentValues.put(DatabaseHelper.KEY_RATING, new_rating);

                    int updCount = database.update(DatabaseHelper.TABLE_AP, contentValues, DatabaseHelper.KEY_ID_AP + " = ?", new String[]{ap_id});
                    Log.d("mLogs", "количество обновленных строк = " + updCount);
                }

                linearLayout.setVisibility(View.INVISIBLE);
            }
        });
        delete_academic_performance_information_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delCount = database.delete(DatabaseHelper.TABLE_AP, DatabaseHelper.KEY_ID_AP + "=" + ap_id, null);

                Log.d("mLog", "количество удаленных строк = " + delCount);
            }
        });
    }
}