package com.example.food;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UsersFood.db";
    private static final String TABLE_NAME = "user_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "PASSWORD";
    private static final String COL_5 = "PHONE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT, PHONE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String username, String email, String password, int phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, password);
        contentValues.put(COL_5, phoneNumber);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result != -1) {
            Log.d("DatabaseHelper", "Data inserted successfully");
        } else {
            Log.d("DatabaseHelper", "Failed to insert data");
        }
        return result != -1;
    }
    public boolean updateUserData(String email, String newUsername, String newEmail, int newPhoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, newUsername);
        contentValues.put(COL_3, newEmail);
        contentValues.put(COL_5, newPhoneNumber);

        int rowsAffected = db.update(TABLE_NAME, contentValues, COL_3 + "=?", new String[]{email});

        if (rowsAffected > 0) {
            Log.d("DatabaseHelper", "Données de l'utilisateur mises à jour avec succès");
        } else {
            Log.d("DatabaseHelper", "Échec de la mise à jour des données de l'utilisateur");
        }

        return rowsAffected > 0;
    }


    public Cursor getUserData(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_3 + "=? AND " + COL_4 + "=?", new String[]{email, password});
    }
    public boolean emailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {

            String query = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COL_3 + "=?";
            cursor = db.rawQuery(query, new String[]{email});

            if (cursor != null && cursor.moveToFirst()) {
                int count = cursor.getInt(0);
                return count > 0;
            }

            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }
}