package com.mrloveqd.noteapp.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mrloveqd.noteapp.R;
import com.mrloveqd.noteapp.controllers.NoteAdapter;
import com.mrloveqd.noteapp.models.Note;
import com.mrloveqd.noteapp.models.NoteModel;
import com.mrloveqd.noteapp.views.NoteItemView;

public class NoteActivity extends Activity {

	ListView mNoteListView = null;
	NoteAdapter mNoteAdapter = new NoteAdapter();
	ArrayList<Note> mNoteList = null;
	SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);
		addButtonClicked();
		initUI();
	}

	protected void initUI() {
		mNoteListView = (ListView) findViewById(R.id.note_listview);
		final NoteModel mNoteModel = new NoteModel(getApplicationContext());
		mNoteList = new ArrayList<Note>();
		mNoteList = mNoteModel.listNote();
		mNoteModel.closeDb();
		
		mNoteAdapter.setContext(this);
		mNoteAdapter.setOnNoteDeleteListener(new NoteItemView.OnNoteDeleteListener() {
			@Override
			public void onDeleteNoteClicked(Note note) {
				if (note == null)
					return;
				int count = mNoteModel.deleteNote(note.getId());
				if (count > 0){
					mNoteModel.closeDb();
					mNoteList.remove(note);
					mNoteAdapter.notifyDataSetChanged();
					
					String text = String.format("Deleted %s", note.getContent());
					Toast.makeText(getApplicationContext(), text, text.length()).show();
				}else{
					Toast.makeText(getApplicationContext(), "Detele fail!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		mNoteListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Note note = (Note) mNoteAdapter.getItem(arg2);
				if(note == null)
					return;
				
				Bundle bundle = new Bundle();
				bundle.putInt("ID", note.getId());
				bundle.putString("CONTENT", note.getContent());
				Intent mDetailIntent = new Intent(getApplicationContext(), NoteUpdateActivity.class);
				mDetailIntent.putExtras(bundle);
				startActivity(mDetailIntent);
			}
		});
		
		mNoteAdapter.setNoteModel(mNoteModel);
		mNoteListView.setAdapter(mNoteAdapter);
	}

	protected void addButtonClicked(){
		Button mAddButton = (Button) findViewById(R.id.add_button);
		mAddButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						NoteAddNewActivity.class));
			}
		});
	}
}
