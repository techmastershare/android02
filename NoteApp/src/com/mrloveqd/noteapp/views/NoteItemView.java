package com.mrloveqd.noteapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrloveqd.noteapp.R;
import com.mrloveqd.noteapp.models.Note;

public class NoteItemView extends LinearLayout {

	public NoteItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	private TextView mContentTextView = null;
	private TextView mDateTextView = null;
	private Note mNote = null;

	public Note getNote() {
		return mNote;
	}

	public void setNote(Note note) {
		this.mNote = note;
	}

	protected boolean initialize() {
		initUI();

		return true;
	}

	protected boolean initUI() {
		LayoutInflater layoutInflater = (LayoutInflater) this.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.note_item_view, this);

		// Initialize controls
		this.mContentTextView = (TextView) findViewById(R.id.content_text_view);
		this.mDateTextView = (TextView) findViewById(R.id.date_text_view);

		return true;
	}

	public void updateView() {
		mContentTextView.setText(mNote.getContent());
	}

}
