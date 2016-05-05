package com.arte.quicknotes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.arte.quicknotes.database.NotesStorage;
import com.arte.quicknotes.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesDataSource implements NotesStorage {

    private static NotesDataSource mInstance;
    private NotesDbHelper dbHelper;
    private List<Note> mNotes = new ArrayList<>();
    private boolean dbDirty = true;

    public static synchronized NotesDataSource getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NotesDataSource(context);
        }
        return mInstance;
    }

    private NotesDataSource(Context context) {
        dbHelper = new NotesDbHelper(context);
    }

    public void add(Note note) {
        ContentValues values = getContentValues(note.getTitle(), note.getContent());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(NotesDbHelper.NoteEntry.TABLE_NAME, null, values);

        dbDirty = true;
    }

    public void update(Note note) {
        ContentValues values = getContentValues(note.getTitle(), note.getContent());
        String whereClause = NotesDbHelper.NoteEntry._ID + " = ?";
        String[] args = { String.valueOf(note.getId()) };

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(NotesDbHelper.NoteEntry.TABLE_NAME, values, whereClause, args);

        dbDirty = true;
    }

    public void delete(Note note) {
        long deleteId = note.getId();
        String[] args = { String.valueOf(deleteId) };

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(NotesDbHelper.NoteEntry.TABLE_NAME,
                NotesDbHelper.NoteEntry._ID + " = ?",
                args);

        dbDirty = true;
    }

    public  Note get(int id) {
        String[] projection = {
                NotesDbHelper.NoteEntry._ID,
                NotesDbHelper.NoteEntry.COLUMN_NAME_TITLE,
                NotesDbHelper.NoteEntry.COLUMN_NAME_CONTENT
        };
        String whereClause = NotesDbHelper.NoteEntry._ID + " = ?";
        String[] args = { String.valueOf(id) };

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(NotesDbHelper.NoteEntry.TABLE_NAME, projection, whereClause, args, null, null, null);
        cursor.moveToFirst();
        Note note = toNote(cursor);
        return note;
    }

    public  List<Note> getAll() {
        if (dbDirty) {
            refreshNotes();
            dbDirty = false;
        }
        return mNotes;
    }

    private ContentValues getContentValues(String title, String content) {
        ContentValues values = new ContentValues();
        values.put(NotesDbHelper.NoteEntry.COLUMN_NAME_TITLE, title);
        values.put(NotesDbHelper.NoteEntry.COLUMN_NAME_CONTENT, content);
        return values;
    }

    private Note toNote(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(NotesDbHelper.NoteEntry._ID)));
        note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(NotesDbHelper.NoteEntry.COLUMN_NAME_TITLE)));
        note.setContent(cursor.getString(cursor.getColumnIndexOrThrow(NotesDbHelper.NoteEntry.COLUMN_NAME_CONTENT)));
        return note;
    }


    private void refreshNotes() {
        String[] projection = {
                NotesDbHelper.NoteEntry._ID,
                NotesDbHelper.NoteEntry.COLUMN_NAME_TITLE,
                NotesDbHelper.NoteEntry.COLUMN_NAME_CONTENT
        };
        String sortOrder =
                NotesDbHelper.NoteEntry._ID + " DESC";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                NotesDbHelper.NoteEntry.TABLE_NAME,     // The table to query
                projection,                             // The columns to return
                null,                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                sortOrder                               // The sort order
        );
        mNotes.clear();
        while(cursor.moveToNext()) {
            Note note = toNote(cursor);
            mNotes.add(note);
        }
        cursor.close();
    }
}
