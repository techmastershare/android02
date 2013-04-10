package com.example.noteapp.view;

import com.example.noteapp.R;
import com.example.noteapp.model.Note;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NoteItemView extends LinearLayout {

	private TextView mTextView;
	private Note mNote;

	public Note getNote() {
		return mNote;
	}

	public void setNote(Note note) {
		mNote = note;
	}

	public NoteItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initUI();
	}

	private void initUI() {
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.note_row, this);
		mTextView = (TextView) findViewById(R.id.note);
	}

	public void updateView() {
		mTextView.setText(mNote.getTitle());
	}

}
