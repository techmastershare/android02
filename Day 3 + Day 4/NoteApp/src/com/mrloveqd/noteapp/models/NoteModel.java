package com.mrloveqd.noteapp.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NoteModel {
	private static final String DATABASE_NAME = "NoteApp";
	private static final String DATABASE_TABLE = "Note";
	private static final int DATABASE_VERSION = 1;

	Context mContext;
	SQLiteOpenHelper mHelper;
	SQLiteDatabase mDatabase;
	ArrayList<Note> mNoteList = new ArrayList<Note>();
	

	class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = "CREATE TABLE " + DATABASE_TABLE
					+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "Content TEXT NOT NULL, " + "Date TEXT NOT NULL );";
			Log.d("CREATE TABLE", sql);
			db.execSQL(sql);

			for (int i = 1; i <= 15; i++) {
				sql = "INSERT INTO " + DATABASE_TABLE
						+ " VALUES (NULL, 'Content " + i + "', '2013-03-" + i + "');";
				db.execSQL(sql);
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("CREATE TABLE IF EXIST " + DATABASE_TABLE);
			onCreate(db);
		}
	}

	public NoteModel(Context context) {
		mContext = context;
	}

	public NoteModel openDb() throws SQLException {
		mHelper = new DbHelper(mContext);
		mDatabase = mHelper.getWritableDatabase();
		return this;
	}

	public void closeDb() {
		mDatabase.close();
	}


	public ArrayList<Note> listNote() {
		try {
			openDb();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mNoteList = new ArrayList<Note>();

		String sql = "SELECT COUNT(*) FROM " + DATABASE_TABLE;
		Cursor cursor = mDatabase.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			sql = "SELECT * FROM " + DATABASE_TABLE + " ORDER BY _id DESC";
			Log.d("SELECT ALL", sql);
			cursor = mDatabase.rawQuery(sql, null);
			if (cursor.moveToFirst()) {
				do {
					Note note = new Note(cursor.getInt(0), cursor.getString(1),
							cursor.getString(2));
					mNoteList.add(note);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		return mNoteList;
	}
	
	public long addNote(String content){
		try {
			openDb();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
	    int mMonth = c.get(Calendar.MONTH) + 1;
	    int mDay = c.get(Calendar.DAY_OF_MONTH);
		String date = mYear + "-" + mMonth + "-" + mDay;
		
		ContentValues cv = new ContentValues();
		cv.put("Content", content);
		cv.put("Date", date);
		return mDatabase.insert(DATABASE_TABLE, null, cv); // !=1 inser succesfull, =-1 fail
	}

	public int deleteNote(int id){
		try {
			openDb();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mDatabase.delete(DATABASE_TABLE, " _id = " + id, null);
	}
	
	
	public int UpdateNote(int id, String content){
		try {
			openDb();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
	    int mMonth = c.get(Calendar.MONTH) + 1;
	    int mDay = c.get(Calendar.DAY_OF_MONTH);
		String date = mYear + "-" + mMonth + "-" + mDay;
		
		ContentValues cv = new ContentValues();
		cv.put("Content", content);
		cv.put("Date", date);
		return mDatabase.update(DATABASE_TABLE, cv, " _id = " + id, null); // 
	}
	
	public int size() {
//		try {
//			openDb();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String sql = "SELECT COUNT(*) FROM " + DATABASE_TABLE;
//		Cursor cursor = mDatabase.rawQuery(sql, null);
//		return cursor.getInt(0);
		return mNoteList.size();
	}

	public Note get(final int index) {
		if (index < 0)
			return null;
		return mNoteList.get(index);
	}
	
	public boolean remove(final Note note) {
		return mNoteList.remove(note);
	}
	
	public Cursor listAll() {
		try {
			openDb();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT * FROM " + DATABASE_TABLE + " ORDER BY _id DESC";
		Log.d("SELECT ALL", sql);
		Cursor mcursor = mDatabase.rawQuery(sql, null);
		if(mcursor != null){
			mcursor.moveToFirst();
		}
		return mcursor;
	}
}
