package com.example.noteapp.activity;

import com.example.noteapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddNote extends Activity {

	EditText mTime;
	EditText mNote;
	Button saveBt;
	Button cancelBt;
	Intent mIntent;
	Boolean mEditNote;
	int mId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editnote);
		init();
	}

	public void init() {
		mTime = (EditText) findViewById(R.id.etTime);
		mNote = (EditText) findViewById(R.id.etNote);
		saveBt = (Button) findViewById(R.id.btSave);
		cancelBt = (Button) findViewById(R.id.btCancel);
		mIntent = getIntent();
		
		mEditNote = mIntent.getBooleanExtra("edit", false);

		if (mEditNote) {
			mId = mIntent.getIntExtra("id", -1);
			mTime.setText(mIntent.getStringExtra("time"));
			mNote.setText(mIntent.getStringExtra("note"));
		}

		saveBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(mEditNote){
					mIntent.putExtra("edit", mEditNote);
					mIntent.putExtra("id", mId);
				}
				mIntent.putExtra("time", mTime.getText().toString());
				mIntent.putExtra("note", mNote.getText().toString());
				AddNote.this.setResult(RESULT_OK , mIntent);

				finish();
			}
		});

		cancelBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
