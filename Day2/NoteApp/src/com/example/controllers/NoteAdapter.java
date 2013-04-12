package com.example.controllers;

import com.example.models.Note;
import com.example.models.NoteModel;
import com.example.views.NoteItemView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class NoteAdapter extends BaseAdapter {
	private NoteModel mNoteModel = null;
	private Context mContext = null;
	private NoteItemView.OnNoteDeleteListener mOnNoteDeleteListener = null;
	private NoteItemView.OnNoteAddListener mOnNoteAddListener = null;
	
	
	public void setOnNoteDeleteListener(NoteItemView.OnNoteDeleteListener callback) {
		mOnNoteDeleteListener = callback;
	}
	
	public void SetOnNoteAddListener(NoteItemView.OnNoteAddListener callback1) {
		mOnNoteAddListener = callback1;
	}
	
	public void setNoteModel(NoteModel NoteModel) {
		mNoteModel = NoteModel;
	}
	public NoteModel getNoteModel() {
		return mNoteModel;
	}
	
	public void setContext(Context context) {
		mContext = context;
	}
	public Context getContext() {
		return mContext;
	}
	
	@Override
	public int getCount() {
		if (mNoteModel == null)
			return 0;
		return mNoteModel.size();
	}

	@Override
	public Object getItem(int position) {
		if (mNoteModel == null)
			return null;
		return mNoteModel.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NoteItemView view = (NoteItemView) convertView;
        Note note = mNoteModel.get(position);
        
        if(convertView == null) {
            view = new NoteItemView(mContext, null);
            view.setOnNoteDeleteListener(new NoteItemView.OnNoteDeleteListener(){
				
				@Override
				public void OnDeleteNoteClicked(Note note) {
					// TODO Auto-generated method stub
					if (mOnNoteDeleteListener != null) 
						mOnNoteDeleteListener.OnDeleteNoteClicked(note);
				}
			}); 
        }
        
        view.setNote(note);
        
        // Update item view
        view.updateView();
        
        return view;
	}

}
