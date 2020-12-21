package com.example.database_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Dean's_office.db";
    public static final int DATABASE_VERSION  = 1;
    public static final String TABLE_NAME = "student_table";
    public static final String TABLE_CURRICULUM = "curriculum_table";
    public static final String TABLE_AP = "academic_performance_table";
    //ТАБЛИЦА СТУДЕНТОВ
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "ИМЯ";
    public static final String KEY_SURNAME = "ФАМИЛИЯ";
    public static final String KEY_FATHER_NAME = "ОТЧЕСТВО";
    public static final String KEY_YEAR_ADMISSION = "ГОДПОСТУПЛЕНИЯ";
    public static final String KEY_FORM_EDUCATION = "ФОРМАОБУЧЕНИЯ";
    //ТАБЛИЦА УЧЕБНОГО ПЛАНА
    public static final String KEY_ID_CURRICULUM = "ID";
    public static final String KEY_SPECIALITY_NAME = "НАЗВАНИЕ_СПЕЦИАЛЬНОСТИ";
    public static final String KEY_DISCIPLINE = "ДИСЦИПЛИНА";
    public static final String KEY_SEMESTER = "СЕМЕСТР";
    public static final String KEY_HOURS = "ЧАСОВ";
    public static final String KEY_REPORTING_FORM = "ФОРМА_ОТЧЕТНОСТИ";
    //ТАБЛИЦА ЖУРНАЛА УСПЕВАЕМОСТИ
    public static final String KEY_ID_AP = "ID";
    public static final String KEY_AP_SEMESTER = "СЕМЕСТР";
    public static final String KEY_AP_FULLNAME = "ФИО";
    public static final String KEY_AP_DISCIPLINE = "ДИСЦИПЛИНА";
    public static final String KEY_RATING = "ОЦЕНКА";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student_table(ID INTEGER PRIMARY KEY AUTOINCREMENT, ИМЯ TEXT, ФАМИЛИЯ TEXT, ОТЧЕСТВО TEXT, ГОДПОСТУПЛЕНИЯ TEXT, ФОРМАОБУЧЕНИЯ TEXT)");
        db.execSQL("create table curriculum_table(ID INTEGER PRIMARY KEY AUTOINCREMENT, НАЗВАНИЕ_СПЕЦИАЛЬНОСТИ TEXT, ДИСЦИПЛИНА TEXT, СЕМЕСТР TEXT, ЧАСОВ TEXT, ФОРМА_ОТЧЕТНОСТИ TEXT)");
        db.execSQL("create table academic_performance_table(ID INTEGER PRIMARY KEY AUTOINCREMENT, СЕМЕСТР TEXT, ФИО TEXT, ДИСЦИПЛИНА TEXT, ОЦЕНКА TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURRICULUM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AP);
        onCreate(db);
    }
}
