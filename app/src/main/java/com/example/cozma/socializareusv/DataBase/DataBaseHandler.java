package com.example.cozma.socializareusv.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cozma on 19.11.2016.
 */

public class DataBaseHandler extends SQLiteOpenHelper {
    //Data abse version
    private static final int Database_Version = 1;

    //Data Base name
    private static final String DATABASE_NAME = "studentsManager";

    //TablesName
    private static final String TABLE_STUDENTS = "students";

    //Colons for table
    private static final String KEY_ID = "id";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_UNIVERSITY = "university";
    private static final String KEY_PHONE = "phone";
//    private static final String KEY_PATH = "key_path";


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_FIRST_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_UNIVERSITY + " TEXT"
                + ")";


        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * CRUD
     */
    public void addStudnet(Client client) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_ID, client.get_id());
        contentValues.put(KEY_FIRST_NAME, client.get_firstname());
        contentValues.put(KEY_LAST_NAME, client.get_lastName());
        contentValues.put(KEY_PHONE, client.get_phone());
        contentValues.put(KEY_UNIVERSITY, client.get_university());
//        contentValues.put(KEY_PATH,client.get_imagePath());

        database.insert(TABLE_STUDENTS, null, contentValues);
        database.close();
    }

    Client getClient(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENTS, new String[]{KEY_ID,
                        KEY_FIRST_NAME, KEY_LAST_NAME, KEY_PHONE, KEY_UNIVERSITY}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Client client = new Client(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        // return contact
        return client;
    }

    public List<Client> getAllStudents() {
        List<Client> clientList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Client client = new Client();
                client.set_id(Integer.parseInt(cursor.getString(0)));
                client.set_firstname(cursor.getString(1));
                client.set_lastName(cursor.getString(2));
                client.set_phone(cursor.getString(3));
                client.set_university(cursor.getString(4));
            }while (cursor.moveToNext());
        }
        return clientList;
    }
}
