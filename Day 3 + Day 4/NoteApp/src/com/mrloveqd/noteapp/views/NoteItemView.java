package com.mrloveqd.noteapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrloveqd.noteapp.R;
import com.mrloveqd.noteapp.models.Note;

public class NoteItemView extends LinearLayout {

	private TextView mContentTextView = null;
	private TextView mDateTextView = null;
	private Note mNote = null;
	private OnNoteDeleteListener mOnNoteDeleteListener = null;
	
	public interface OnNoteDeleteListener{
		public void onDeleteNoteClicked(Note note);
	}
	
	public void setOnNoteDeleteListener(OnNoteDeleteListener callback){
		mOnNoteDeleteListener = callback;
	}
	public Note getNote() {
		return mNote;
	}

	public void setNote(Note note) {
		this.mNote = note;
	}

	public NoteItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
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
		ImageView mDeleteImageView = (ImageView) findViewById(R.id.delete_image_view);
		mDeleteImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mOnNoteDeleteListener != null)
					mOnNoteDeleteListener.onDeleteNoteClicked(mNote);
			}
		});
		
		
		return true;
	}

	public void updateView() {
		mContentTextView.setText(mNote.getContent());
		mDateTextView.setText(mNote.getDate());
	}

}
