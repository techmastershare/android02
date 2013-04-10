package com.example.noteapp.controller;

import com.example.noteapp.R;
import com.example.noteapp.DAO.NoteListDatabasehelper;
import com.example.noteapp.activity.NoteActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class NoteAdapter extends CursorAdapter {

	Context mContext;
	NoteListDatabasehelper mDatabase;
	TextView timeText;
	TextView noteText;
	int id;
	NoteActivity mainActivity;
	
	public NoteAdapter(Context context, Cursor c, NoteListDatabasehelper database, NoteActivity mainActivity) {
		super(context, c);
		
		// TODO Auto-generated constructor stub
		
		mContext = context;
		mDatabase = database;	
		mDatabase.open();
		this.mainActivity = mainActivity;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		
	    id = cursor.getInt(0);
	    timeText = (TextView) view.findViewById(R.id.tvTime);
	    noteText = (TextView) view.findViewById(R.id.tvNote);
		Button removeBt = (Button) view.findViewById(R.id.btRemove);
		Button editBt = (Button) view.findViewById(R.id.editBt);
		
		timeText.setText(cursor.getString(1));
		noteText.setText(cursor.getString(2));
		
		
		
		removeBt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			      mDatabase.delete(id);			      
			      changeCursor(mDatabase.getAllData());
			}
		});
		
		editBt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			   	Bundle bundle= new Bundle();
			   	
				Intent i = new Intent("COM.ACTIVITY.ADDOREDIT");
			   	i.putExtra("time", timeText.getText().toString());
			   	i.putExtra("note", noteText.getText().toString());
			   	i.putExtra("id", id);
			   	i.putExtra("edit", true);
			   	mainActivity.startActivityForResult(i , NoteActivity.REQUSET_CODDE );
			}
		});
		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.note_item_view, null);
		return view;
	}
		
}
