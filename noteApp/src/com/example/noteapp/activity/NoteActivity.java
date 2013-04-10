package com.example.noteapp.activity;

import com.example.noteapp.R;
import com.example.noteapp.DAO.NoteListDatabasehelper;
import com.example.noteapp.controller.NoteAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class NoteActivity extends Activity {

	ListView mNoteListView;
	Button mBtAdd;
	public static final int REQUSET_CODDE = 1;
	NoteListDatabasehelper mOpenHelper;
	NoteAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		init();
	}

	public void init() {
		// TODO Auto-generated method stub
		mNoteListView = (ListView) findViewById(R.id.lvNote);
		mBtAdd = (Button) findViewById(R.id.btAdd);

		mOpenHelper = new NoteListDatabasehelper(this);
		mOpenHelper.open();
		mAdapter = new NoteAdapter(this, mOpenHelper.getAllData(), mOpenHelper,
				this);
		mNoteListView.setAdapter(mAdapter);

		mBtAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("COM.ACTIVITY.ADDOREDIT");
				startActivityForResult(i, REQUSET_CODDE);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUSET_CODDE) {
			if (resultCode == RESULT_OK) {
				String time = data.getStringExtra("time");
				String note = data.getStringExtra("note");
				
				if (data.getBooleanExtra("edit", false)) {
					int id = data.getIntExtra("id", -1);
                    mOpenHelper.updateNote(id, time, note);
                    
				} else {
					
					mOpenHelper.addNote(time , note);
					
				}
				mAdapter.changeCursor(mOpenHelper.getAllData());
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.note, menu);
		return true;
	}

}
