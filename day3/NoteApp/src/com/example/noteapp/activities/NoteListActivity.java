package com.example.noteapp.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.noteapp.R;
import com.example.noteapp.controller.NoteAdapter;
import com.example.noteapp.model.Note;
import com.example.noteapp.model.NoteModel;
import com.example.noteapp.view.NoteItemView;

public class NoteListActivity extends Activity {

	private ListView mListView;
	private NoteAdapter mNoteAdapter;
	private NoteModel mNoteModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_list);

		initUI();
	}

	protected void initUI() {
		mListView = (ListView) findViewById(R.id.note_list_view);
		registerForContextMenu(mListView);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parents, View itemClicked, int position, long id) {
				Note note = ((NoteItemView) itemClicked).getNote();
				Intent intent = new Intent(getApplicationContext(), NoteContentActivity.class);
				intent.putExtra("note_object", note);
				startActivity(intent);
			}
		});

		Button addButton = (Button) findViewById(R.id.add_button);
		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), NoteEditActivity.class));
			}
		});

		mNoteModel = new NoteModel(getApplicationContext());
		mNoteAdapter = new NoteAdapter();

		final List<Note> noteList = mNoteModel.getAllNote();
		mNoteModel.setNoteList(noteList);
		mNoteAdapter.setContext(this);
		mNoteAdapter.setNoteModel(mNoteModel);
		mNoteAdapter.setOnNoteDeleteListener(new NoteItemView.OnNoteDeleteListener() {

			@Override
			public void onDeleteNoteClicked(Note note) {
				if (note != null) {
					mNoteModel.deleteNote(note);
					fillDataToListView(mNoteModel.getAllNote());
				}
			}
		});

		mListView.setAdapter(mNoteAdapter);

		final EditText searchEditText = (EditText) findViewById(R.id.search_edit_text);
		searchEditText.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				String text = searchEditText.getText().toString();
				if (text.length() > 0) {
					fillDataToListView(mNoteModel.searchNote(text));
				} else {
					fillDataToListView(mNoteModel.getAllNote());
				}
				return false;
			}
		});
	}

	private void fillDataToListView(List<Note> noteList) {
		mNoteModel.setNoteList(noteList);
		mNoteAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		fillDataToListView(mNoteModel.getAllNote());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);

		menu.setHeaderTitle("Action");
		menu.add(0, v.getId(), 0, "Delete");
		menu.add(0, v.getId(), 0, "Edit");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle().equals("Delete")) {
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
			Note note = mNoteModel.get(info.position);
			mNoteModel.deleteNote(note);
			fillDataToListView(mNoteModel.getAllNote());
		} else if (item.getTitle().equals("Edit")) {
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
			Note note = mNoteModel.get(info.position);
			Intent intent = new Intent(getApplicationContext(), NoteEditActivity.class);
			intent.putExtra("note_object", note);
			startActivity(intent);
		} else {
			return false;
		}
		return true;
	}

}
