package com.example.finance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBAdapter extends SQLiteOpenHelper {
    private static final String MY_TABLE = "MY_TABLE";
    private static final String COLUMN_BALANCE = "COLUMN_BALANCE";
    private static final String COLUMN_SUM = "COLUMN_SUM";
    private static final String COLUMN_REASON = "COLUMN_REASON";

    public DBAdapter(@Nullable Context context) {
        super(context, "new.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MY_TABLE + " (" + COLUMN_BALANCE + " INTEGER, " + COLUMN_SUM + " INTEGER, " + COLUMN_REASON + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void DeleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MY_TABLE, null, null);
        Operation.getOperationHistory().clear();
        db.close();
    }
    public void addOne(int balance, int sum, String reason){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BALANCE, balance);
        cv.put(COLUMN_SUM, sum);
        cv.put(COLUMN_REASON, reason);
        Operation.addOperationHistory(balance, sum, reason);
        db.insert(MY_TABLE, null, cv);
        db.close();
    }

    public void getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(MY_TABLE, null, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                int id_b = cursor.getColumnIndex(COLUMN_BALANCE);
                int id_s = cursor.getColumnIndex(COLUMN_SUM);
                int id_r = cursor.getColumnIndex(COLUMN_REASON);
                Operation.addOperationHistory(cursor.getInt(id_b), cursor.getInt(id_s), cursor.getString(id_r));
            }while (cursor.moveToNext());
        }
        db.close();
    }
}
