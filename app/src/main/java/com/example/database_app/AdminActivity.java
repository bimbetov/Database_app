package com.example.database_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    private Button editing_students_btn, academic_plan_btn, grade_book_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        addListenerOnClick();
    }

    public void addListenerOnClick() {
        editing_students_btn = (Button) findViewById(R.id.editing_students_btn);
        academic_plan_btn = (Button)findViewById(R.id.academic_plan_btn);
        grade_book_btn = (Button)findViewById(R.id.grade_book_btn);

        editing_students_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), search_add_student.class);
                startActivity(intent);
            }
        });

        academic_plan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), academic_plan.class);
                startActivity(intent);
            }
        });

        grade_book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), grade_book.class);
                startActivity(intent);
            }
        });
    }
}