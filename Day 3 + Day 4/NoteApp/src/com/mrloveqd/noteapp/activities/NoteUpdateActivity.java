package com.mrloveqd.noteapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mrloveqd.noteapp.R;
import com.mrloveqd.noteapp.models.NoteModel;

public class NoteUpdateActivity extends Activity {

	EditText mContentEditText;
	Button mSaveButton, mCancelButton;
	TextView mTitleTextView;
	int mId;
	String mContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_note_view);
		initUI();
		mSaveButton = (Button) findViewById(R.id.save_button);
		mCancelButton = (Button) findViewById(R.id.cancel_button);
		mSaveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				saveButton(mId);
			}
		});
		
		mCancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!mContent.equals(mContentEditText.getText().toString().trim())){
					cancelButton();
				}else{
					NoteUpdateActivity.this.finish();
				}
			}
		});
	}

	protected void initUI() {
		mTitleTextView = (TextView) findViewById(R.id.title_textview);
		mContentEditText = (EditText) findViewById(R.id.content_edittext);

		Bundle bundle = getIntent().getExtras();
		mId = bundle.getInt("ID");
		mContent = bundle.getString("CONTENT");
			mTitleTextView.setText("Update Note");
			mContentEditText.setText(mContent);
	}

	protected void saveButton(int id) {
		mContentEditText = (EditText) findViewById(R.id.content_edittext);
		NoteModel noteModel = new NoteModel(getApplicationContext());
		int count = noteModel.UpdateNote(id, mContentEditText.getText().toString().trim());
		noteModel.closeDb();
		if (count > 0) {
			Toast.makeText(getApplicationContext(), "Update successfull",
					Toast.LENGTH_SHORT).show();
			NoteUpdateActivity.this.finish();
			startActivity(new Intent(getApplicationContext(),
					NoteActivity.class));
		} else {
			Toast.makeText(getApplicationContext(), "Update fail",
					Toast.LENGTH_SHORT).show();
		}
	}

	protected void cancelButton() {
		AlertDialog.Builder confirmCancel = new AlertDialog.Builder(
				NoteUpdateActivity.this);
		confirmCancel.setTitle("Confirm!");
		confirmCancel.setMessage("Do you want cancel ?");
		confirmCancel.setPositiveButton("Ok",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						NoteUpdateActivity.this.finish();
					}
				});
		confirmCancel.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				});
		confirmCancel.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_note, menu);
		return true;
	}

}
