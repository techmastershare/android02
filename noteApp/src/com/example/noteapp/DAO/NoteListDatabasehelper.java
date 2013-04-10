package com.example.noteapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteListDatabasehelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "noteList.db";
	private static final String TABLE_NAME = "timerecord";
	private static final String NOTE_ID = "_id";
	private static final String NOTE_TIME_TEXT="time";
	private static final String NOTE_NOTE_TEXT="note"; 
	
	private SQLiteDatabase database;
	private NoteOpenHelper openHelper;
	
	public NoteListDatabasehelper(Context context){
		openHelper = new NoteOpenHelper(context);			
	}
	
	public class NoteOpenHelper extends SQLiteOpenHelper{

		NoteOpenHelper(Context context ) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(" CREATE TABLE  " + TABLE_NAME + " ( " 
					    + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					    + NOTE_TIME_TEXT + " TEXT NOT NULL, "
					    + NOTE_NOTE_TEXT + " TEXT NOT NULL )");
					    
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
		
	}
	
	public void open() {
		database = openHelper.getWritableDatabase();
	}
	
	public void close(){
		 openHelper.close();
	 }
	
	
	public Cursor getAllData(){
		return database.rawQuery(" select * from  "+ TABLE_NAME, null);
	}
	
	public void addNote(String time, String note){
		ContentValues content= new ContentValues();
		content.put(NOTE_TIME_TEXT, time);
		content.put(NOTE_NOTE_TEXT, note);
		
		database.insert(TABLE_NAME, null, content);
	}
	
	public void updateNote(int id, String time, String note ){
		ContentValues updateContent= new ContentValues();
		updateContent.put(NOTE_TIME_TEXT, time);
		updateContent.put(NOTE_NOTE_TEXT, note);
		
		database.update(TABLE_NAME, updateContent, NOTE_ID + " = "+ id, null);
	}
	
	public void delete(int id){
		database.delete(TABLE_NAME, NOTE_ID + " = "+ id, null);
	}
}
