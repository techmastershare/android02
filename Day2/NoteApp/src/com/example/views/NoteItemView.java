package com.example.views;

import com.example.models.Note;
import com.example.noteapp.R;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NoteItemView extends LinearLayout{
	private ImageView mAvatarImageView = null;
	private TextView mTextView = null;
	private Note mNote = null;
	private OnNoteDeleteListener mOnNoteDeleteListener = null;
	private OnNoteAddListener mOnNoteAddListener = null;
		
	public interface OnNoteDeleteListener {
		public void OnDeleteNoteClicked(Note note);
	}
	
	public interface OnNoteAddListener {
		public void OnAddNoteClicked(Note note);
	}
	
	public void setOnNoteDeleteListener(OnNoteDeleteListener callback) {
		mOnNoteDeleteListener = callback;
	}
	
	public void setOnNoteAddListener(OnNoteAddListener callback1) {
		mOnNoteAddListener = callback1;
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
		LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.item_note_view, this);

		// Initialize controls
		this.mAvatarImageView = (ImageView) findViewById(R.id.avatar_image_view);
		this.mTextView = (TextView) findViewById(R.id.text_view);
		
		ImageView deleteImageView = (ImageView) findViewById(R.id.delete_note);
		deleteImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mOnNoteDeleteListener != null)
					mOnNoteDeleteListener.OnDeleteNoteClicked(mNote);
			}
		});
		
		/*ImageView addImageView = (ImageView) findViewById(R.id.add_note_image);
		addImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOnNoteAddListener.OnAddNoteClicked(mNote);
			}
		});*/
		
		return true;
	}
	
	

	public void updateView() {
		mTextView.setText(mNote.getName());
	}
}

