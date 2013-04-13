package com.example.noteapp.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NoteModel extends SQLiteOpenHelper {
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "NoteDb";
	public static final String TABLE_NAME = "Notes";

	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";

	List<Note> mNoteList;

	public void setNoteList(List<Note> nodeList) {
		mNoteList = nodeList;
	}

	public NoteModel(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT , %s TEXT, %s TEXT)", TABLE_NAME, ID,
				TITLE, CONTENT);

		Log.d("SQL", sql);
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	public void add(Note note) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TITLE, note.getTitle());
		values.put(CONTENT, note.getContent());

		db.insert(TABLE_NAME, null, values);
		db.close();
	}

	public Note get(int index) {
		// SQLiteDatabase db = this.getReadableDatabase();
		// Cursor cursor = db.query(TABLE_NAME, new String[] { ID, TITLE,
		// CONTENT }, ID + "=?",
		// new String[] { String.valueOf(++index) }, null, null, null);
		// if (cursor.getCount() > 0) {
		// cursor.moveToFirst();
		// Note note = new Note(cursor.getInt(0), cursor.getString(1),
		// cursor.getString(2));
		// db.close();
		// return note;
		// }

		return mNoteList.get(index);
	}

	public Note getNote(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Note note = null;
		Cursor cursor = db.query(TABLE_NAME, new String[] { ID, TITLE, CONTENT }, ID + "=?", new String[] { String.valueOf(id) },
				null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			note = new Note(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
		}

		db.close();
		return note;
	}

	public List<Note> getAllNote() {
		List<Note> noteList = new ArrayList<Note>();
		SQLiteDatabase db = getReadableDatabase();
		String sql = "SELECT * FROM " + TABLE_NAME;
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor != null) {
			cursor.moveToFirst();
			do {
				noteList.add(new Note(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
			} while (cursor.moveToNext());

			db.close();
			return noteList;
		}

		return null;
	}

	public List<Note> searchNote(String title) {
		List<Note> noteList = new ArrayList<Note>();
		for (Note note : mNoteList) {
			if (note.getTitle().contains(title)) {
				noteList.add(note);
			}
		}
		return noteList;
	}

	public void deleteNote(Note note) {
		SQLiteDatabase db = getWritableDatabase();
		db.delete(TABLE_NAME, ID + "=?", new String[] { String.valueOf(note.getId()) });
		db.close();
	}

	public void updateNote(Note note) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ID, note.getId());
		values.put(TITLE, note.getTitle());
		values.put(CONTENT, note.getContent());

		db.update(TABLE_NAME, values, ID + "=?", new String[] { String.valueOf(note.getId()) });
		db.close();
	}

	public int size() {
		// TODO Auto-generated method stub
		return mNoteList.size();
	}
}
