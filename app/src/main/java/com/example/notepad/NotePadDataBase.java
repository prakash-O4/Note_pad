package com.example.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NotePadDataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "NotePad";

    //column name
    private static final String ID = "id";
    private static final String TABLE_NAME = "NotePadData";
    private static final String DATE = "Date";
    private static final String TIME = "Time";
    private static final String TITLE = "Title";
    private static final String CONTENT = "Content";

    public NotePadDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE " + TABLE_NAME + "(" + ID + "INT PRIMARY KEY,"
                + DATE + "TEXT,"
                + TIME + "TEXT,"
                + TITLE + "TEXT,"
                + CONTENT + "TEXT)";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addData(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, data.getTitle());
        cv.put(CONTENT, data.getContent());
        cv.put(DATE, data.getDate());
        cv.put(TIME, data.getTime());

        //inserting data into database
        long ID = db.insert(TABLE_NAME, null, cv);
        Log.d("Insert", "Id is>>>>" + ID);
        return ID;
    }

    //getNote helps to get single data from database
    public Data getData(long id) {
        //command to get single data from database
        //select * from databaseName where id = id
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{ID, TITLE, TIME, DATE, CONTENT}, ID + "=?"
                , new String[]{String.valueOf(id)}, null, null, null);

        //retrieving data from database and passing to the data class to set the data
        return new Data(cursor.getLong(0), cursor.getString(3), cursor.getString(2),
                cursor.getString(1), cursor.getString(4));

    }

    public List<Data> getDatas() {
        SQLiteDatabase database = this.getReadableDatabase();
        List<Data> allDatas = new ArrayList<>();
        //get all data from database
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Data data = new Data();
                data.setId(cursor.getLong(0));
                data.setDate(cursor.getString(3));
                data.setTime(cursor.getString(2));
                data.setTitle(cursor.getString(1));
                data.setContent(cursor.getString(4));

                allDatas.add(data);
            }
            while (cursor.moveToNext());
        }
        return allDatas;
    }
}
