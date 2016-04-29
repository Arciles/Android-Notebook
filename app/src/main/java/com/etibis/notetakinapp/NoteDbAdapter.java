package com.etibis.notetakinapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by esattahaibis on 2016-04-29.
 */

public class NoteDbAdapter {
    private static final String DATABASE_NAME = "notebook.db";
    private static final int DATABASE_VERSION = 1;

    public static final String NOTE_TABLE = "notes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "_title";
    public static final String COLUMN_MESSAGE = "_message";
    public static final String COLUMN_CATEGORY = "_category";
    public static final String COLUMN_DATE = "_date";

    private String[] allColumns = {COLUMN_ID, COLUMN_TITLE, COLUMN_MESSAGE, COLUMN_CATEGORY, COLUMN_DATE};

    public static final String CREATE_TABLE_NOTE = "CREATE TABLE " + NOTE_TABLE + " ( " +
            COLUMN_ID + " INT PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE + " TEXT NOT NULL, " +
            COLUMN_MESSAGE + " TEXT NOT NULL, " +
            COLUMN_CATEGORY + " INT NOT NULL, " +
            COLUMN_DATE + " INT );";

    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    private NoteDBHelper noteDBHelper;

    public NoteDbAdapter(Context context) {
        this.context = context;
    }

    public NoteDbAdapter open() throws android.database.SQLException {
        noteDBHelper = new NoteDBHelper(context);
        sqLiteDatabase = noteDBHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        noteDBHelper.close();
    }
    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<Note>();

        // Grab all of the information in our database for the notes in it
        Cursor cursor = sqLiteDatabase.query(NOTE_TABLE, allColumns, null, null, null, null, null);

        for (cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
            Note note = cursorToNote(cursor);
            notes.add(note);
        }
        cursor.close();
        return notes;
    }

    private Note cursorToNote(Cursor cursor) {

        Note newNote = new Note( cursor.getString(1), cursor.getString(2), Note.Category.valueOf(cursor.getString(3)), cursor.getLong(0), cursor.getLong(4));
        return newNote;
    }


    private static class NoteDBHelper extends SQLiteOpenHelper {

        NoteDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Create Note Table
            db.execSQL(CREATE_TABLE_NOTE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(NoteDBHelper.class.getName(),
                    "Upgrading Database from version" + oldVersion + " to " + newVersion + " which will destrol all old data");
            db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE);
            onCreate(db);
        }
    }
}
