package com.example.regformwithdb.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.regformwithdb.ModelCLass.ModelClass;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_Name = "coursedb";
    private static final int DB_Version = 1;
    private static final String Table_Name = "mycourses";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String Email_Col = "email";
    private static final String Pass_COL = "pass";

    public DBHandler(@Nullable Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = " CREATE TABLE " + Table_Name + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + Email_Col + " TEXT, "
                + Pass_COL + " TEXT) ";

        db.execSQL(query);

    }

    public void addRegData(String personName, String personEmail, String personPass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, personName);
        values.put(Email_Col, personEmail);
        values.put(Pass_COL, personPass);

        db.insert(Table_Name, null, values);
        db.close();
    }

    public ArrayList<ModelClass> readRegData() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(" SELECT * FROM " + Table_Name, null);

        ArrayList<ModelClass> modelClassArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                modelClassArrayList.add(new ModelClass(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return modelClassArrayList;
    }

    public void updateCourse(String originalPersonName,String personName,String personEmail,String personPass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME_COL,personName);
        contentValues.put(Email_Col,personEmail);
        contentValues.put(Pass_COL,personPass);

        db.update(Table_Name,contentValues,"name=?",new String[]{originalPersonName});
        db.close();
    }

    public void delete(String personName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Table_Name,"name=?",new String[]{personName});
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);

    }
}
