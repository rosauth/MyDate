package com.android.mydate.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.mydate.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "UserManager.db";

    // User Table
    //TODO save user photo in database
    public static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_INTEREST = "user_interest";
    public static final String COLUMN_USER_GENDER = "user_gender";
    public static final String COLUMN_USER_ADDRESS = "user_address";
    public static final String COLUMN_USER_AGE = "user_age";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    public static final String COLUMN_TOTAL_POKE = "poke_total";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT NOT NULL, " +
            COLUMN_USER_EMAIL + " TEXT NOT NULL, " + COLUMN_USER_ADDRESS + " TEXT NOT NULL, " +
            COLUMN_USER_INTEREST + " TEXT NOT NULL, " + COLUMN_USER_GENDER + " TEXT NOT NULL, " +
            COLUMN_USER_AGE + " INTEGER NOT NULL, " + COLUMN_USER_PASSWORD + " TEXT NOT NULL, " +
            COLUMN_TOTAL_POKE + " INTEGER NOT NULL);";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    //Chat Table
    private static final String TABLE_CHAT = "chats";
    private static final String COLUMN_CHAT_ID = "id_chat";
    private static final String COLUMN_ID_SENDER = "id_sender";
    private static final String COLUMN_ID_RECEIVER = "id_recevier";
    private static final String COLUMN_CONTENT = "chat_content";
    private static final String COLUMN_CHAT_TIME = "chat_time";

    private String CREATE_TABLE_CHAT = "CREATE TABLE " + TABLE_CHAT + " ( " +
            COLUMN_CHAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

            // Foreign key
            COLUMN_ID_SENDER + " INTEGER NOT NULL, " + " FOREIGN KEY (" +
            COLUMN_ID_SENDER + ") REFERENCES " + TABLE_USER + " (" + COLUMN_USER_ID + ")" +

            // Foreign key
            COLUMN_ID_RECEIVER + " INTEGER NOT NULL, " + " FOREIGN KEY (" +
            COLUMN_ID_RECEIVER + ") REFERENCES " + TABLE_USER  + " (" + COLUMN_USER_ID + ")" +

            COLUMN_CONTENT + " TEXT NOT NULL, " + COLUMN_CHAT_TIME + " TIME NOT NULL);";

    private String DROP_CHAT_TABLE = "DROP TABLE IF EXISTS " + TABLE_CHAT;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TABLE_CHAT);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_CHAT_TABLE);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_AGE, user.getAge());
        values.put(COLUMN_USER_INTEREST, user.getInterest());
        values.put(COLUMN_USER_GENDER, user.getSex());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean checkUser(String email){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " =?";
        String[] selectionArgs = { email, password };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }
}
