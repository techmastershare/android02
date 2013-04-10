package com.example.noteapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noteapp.R;
import com.example.noteapp.model.Note;
import com.example.noteapp.model.NoteModel;

public class NoteEditActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_edit);

		final EditText titleEditText = (EditText) findViewById(R.id.note_title_edit_text);
		final EditText contentEditText = (EditText) findViewById(R.id.note_content_edit_text);

		Note note = (Note) getIntent().getSerializableExtra("note_object");
		if (note != null) {
			titleEditText.setText(note.getTitle());
			contentEditText.setText(note.getContent());
		}

		Button saveButton = (Button) findViewById(R.id.save_button);
		saveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Note note = (Note) getIntent().getSerializableExtra("note_object");
				if (note == null) {
					note = new Note(0, titleEditText.getText().toString(), contentEditText.getText().toString());
				} else {
					note.setTitle(titleEditText.getText().toString());
					note.setContent(contentEditText.getText().toString());
				}
				saveNote(note);
				Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
				NoteEditActivity.this.finish();
			}
		});
	}

	private void saveNote(Note note) {

		NoteModel noteModel = new NoteModel(getApplicationContext());
		if (note.getId() == 0)
			noteModel.add(note);
		else
			noteModel.updateNote(note);
	}
}
