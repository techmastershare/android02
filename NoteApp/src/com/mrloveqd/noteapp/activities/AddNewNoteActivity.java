package com.mrloveqd.noteapp.activities;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrloveqd.noteapp.R;
import com.mrloveqd.noteapp.models.NoteModel;

public class AddNewNoteActivity extends Activity {

	EditText mContentEditText;
	Button mCancelButton, mSaveButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_note);
		mCancelButton = (Button) findViewById(R.id.cancel_button);
		mSaveButton = (Button) findViewById(R.id.save_button);

		mCancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ConfirmCancel();
			}
		});

		mSaveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				saveNote();
			}
		});

	}

	protected void saveNote() {
		mContentEditText = (EditText) findViewById(R.id.content_edittext);
		final Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
	    int mMonth = c.get(Calendar.MONTH) + 1;
	    int mDay = c.get(Calendar.DAY_OF_MONTH);
		
		String mDate = mYear + "-" + mMonth + "-" + mDay;
		
		NoteModel noteModel = new NoteModel(getApplicationContext());
		long flag = noteModel.addNote(mContentEditText.getText().toString()
				.trim());
		noteModel.closeDb();
		if (flag != -1) {
			Toast.makeText(getApplicationContext(), "Add new Note successful!",
					Toast.LENGTH_SHORT).show();
			AddNewNoteActivity.this.finish();
			startActivity(new Intent(getApplicationContext(),
					NoteActivity.class));
		} else {
			Toast.makeText(getApplicationContext(), "Add new Note fail!",
					Toast.LENGTH_SHORT).show();
		}

	}

	
	protected void ConfirmCancel(){
		AlertDialog.Builder confirmCancel = new AlertDialog.Builder(AddNewNoteActivity.this);
		confirmCancel.setTitle("Confirm!");
		confirmCancel.setMessage("Do you want cancel ?");
		confirmCancel.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				AddNewNoteActivity.this.finish();
			}
		});
		confirmCancel.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		confirmCancel.show();
	}

}
