package com.example.timetracker;

import java.io.ObjectOutputStream.PutField;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddTimeActivity extends Activity {
	Intent intent;
boolean edit = false;
EditText timeView;
EditText notesView;
int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_time);
		 timeView = (EditText) findViewById(R.id.time_view);
		 notesView = (EditText) findViewById(R.id.notes_view);
		intent = getIntent();
		edit = intent.getBooleanExtra("edit", false);
		id = intent.getIntExtra("id", -1);
		if (edit){
			timeView.setText(intent.getStringExtra("time"));
			notesView.setText(intent.getStringExtra("note"));
			

		}
		
	}

	public void onSave(View view) {

		
		intent.putExtra("time", timeView.getText().toString());
		if(id != -1) intent.putExtra("id", id);
		intent.putExtra("notes", notesView.getText().toString());
		intent.putExtra("edit", edit);
		this.setResult(RESULT_OK, intent);
		finish();
	}

	public void onCancel(View view) {
		finish();
	}

}
