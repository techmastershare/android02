package com.mrloveqd.noteapp.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mrloveqd.noteapp.R;
import com.mrloveqd.noteapp.controllers.NoteAdapter;
import com.mrloveqd.noteapp.models.Note;
import com.mrloveqd.noteapp.models.NoteModel;

public class NoteActivity extends Activity {

	TextView mContentTextView, mDateTextView;
	Button mAddButton;
	ListView mNoteListView = null;
	NoteAdapter mNoteAdapter = new NoteAdapter();
	ArrayList<Note> mNoteList = null;
	SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);
		initUI();

		mAddButton = (Button) findViewById(R.id.add_button);
		mAddButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						AddNewNoteActivity.class));
			}
		});

	}

	protected void initUI() {
		mContentTextView = (TextView) findViewById(R.id.content_text_view);
		mDateTextView = (TextView) findViewById(R.id.date_text_view);
		mNoteListView = (ListView) findViewById(R.id.note_listview);

		NoteModel noteModel = new NoteModel(getApplicationContext());
		// mNoteList = noteModel.listNote();
		// noteModel.closeDb();
		//
		// // Set data for the adapter
		// mNoteAdapter.setContext(this);
		// mNoteAdapter.setNoteModel(noteModel);

		// Set adapter for the list view
		// mNoteListView.setAdapter(mNoteAdapter);

		final Cursor mCursor = noteModel.listAll();
		noteModel.closeDb();
		String[] columns = new String[] { "Content", "Date" };
		int[] to = new int[] { R.id.content_text_view, R.id.date_text_view };
		adapter = new SimpleCursorAdapter(getApplicationContext(),
				R.layout.note_item_view, mCursor, columns, to, 0);
		mNoteListView.setAdapter(adapter);

		mNoteListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final int id = mCursor.getInt(mCursor.getColumnIndexOrThrow("_id"));
				final String content = mCursor.getString(mCursor.getColumnIndexOrThrow("Content"));
				Bundle bundle = new Bundle();
				bundle.putInt("ID", id);
				bundle.putString("CONTENT", content);
				bundle.putInt("STATUS", 1);
				Intent mDetailIntent = new Intent(getApplicationContext(), UpdateNoteActivity.class);
				mDetailIntent.putExtras(bundle);
				startActivity(mDetailIntent);
			}
		});
		
		mNoteListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				contextMenu(mCursor);
				return true;
			}
		});

//		mCursor.close();
	}

	protected void contextMenu(Cursor mCursor) {
		final int id = mCursor.getInt(mCursor.getColumnIndexOrThrow("_id"));
//		final String content = mCursor.getString(mCursor.getColumnIndexOrThrow("Content"));
		
		final CharSequence[] items = { "Delete" };

		AlertDialog.Builder builder = new AlertDialog.Builder(NoteActivity.this);
		builder.setTitle("Option");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				// Toast.makeText(getApplicationContext(), items[item],
				// Toast.LENGTH_SHORT).show();
				if (item == 0) { 
					confirmDelete(id);
				}
			}
		}).show();
	}
	
	protected void confirmDelete(final int id) {
			AlertDialog.Builder confirmDelete = new AlertDialog.Builder(NoteActivity.this);
			confirmDelete.setTitle("Confirm!");
			confirmDelete.setMessage("Do you want delete this note?");
			confirmDelete.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					NoteModel noteModel = new NoteModel(getApplicationContext());
					int count = noteModel.deleteNote(id);
					noteModel.closeDb();
					if(count > 0){
						Toast.makeText(getApplicationContext(), "Delete successful!", Toast.LENGTH_SHORT).show();
//						adapter.notifyDataSetChanged();
						initUI();
					}else{
						Toast.makeText(getApplicationContext(), "Delete fail!", Toast.LENGTH_SHORT).show();
					}
				}
			});
			confirmDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			});
			confirmDelete.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
