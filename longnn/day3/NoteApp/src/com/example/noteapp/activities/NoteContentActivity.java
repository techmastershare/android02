package com.example.noteapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.noteapp.R;
import com.example.noteapp.model.Note;

public class NoteContentActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_content);

		TextView titleTextView = (TextView) findViewById(R.id.note_title_text_view);
		TextView contentTextView = (TextView) findViewById(R.id.note_content_text_view);

		final Note note = (Note) getIntent().getSerializableExtra("note_object");
		Button editButton = (Button) findViewById(R.id.edit_button);
		if (note != null) {
			// Note note = (Note) extras.get("note");
			titleTextView.setText(note.getTitle());
			contentTextView.setText(note.getContent());
			// editButton.setText("Edit");
		}

		editButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent editIntent = new Intent(getApplicationContext(), NoteEditActivity.class);
				if (note != null)
					editIntent.putExtra("note_object", note);
				startActivity(editIntent);
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(NoteContentActivity.this, NoteListActivity.class));
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

}
