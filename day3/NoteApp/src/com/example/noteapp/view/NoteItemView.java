package com.example.noteapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.noteapp.R;
import com.example.noteapp.model.Note;

public class NoteItemView extends LinearLayout {

	private TextView mTextView;
	private Note mNote;

	public interface OnNoteDeleteListener {
		public void onDeleteNoteClicked(Note note);
	}

	private OnNoteDeleteListener onNoteDeleteListener;

	public void setOnNoteDeleteListener(OnNoteDeleteListener callback) {
		onNoteDeleteListener = callback;
	}

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

		ImageView deleteImageView = (ImageView) findViewById(R.id.delete_image_view);
		deleteImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				onNoteDeleteListener.onDeleteNoteClicked(mNote);
			}
		});
	}

	public void updateView() {
		mTextView.setText(mNote.getTitle());
	}

}
