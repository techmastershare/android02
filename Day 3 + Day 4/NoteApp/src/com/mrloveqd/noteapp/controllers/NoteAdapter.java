package com.mrloveqd.noteapp.controllers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mrloveqd.noteapp.models.Note;
import com.mrloveqd.noteapp.models.NoteModel;
import com.mrloveqd.noteapp.views.NoteItemView;

public class NoteAdapter extends BaseAdapter {
	private NoteModel mNoteModel = null;
	private Context mContext = null;
	private NoteItemView.OnNoteDeleteListener mOnNoteDeleteListener = null;
	
	public void setOnNoteDeleteListener(NoteItemView.OnNoteDeleteListener callback){
		mOnNoteDeleteListener = callback;
	}

	public void setNoteModel(NoteModel noteModel) {
		mNoteModel = noteModel;
	}

	public NoteModel getContactModel() {
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NoteItemView view = (NoteItemView) convertView;
		Note note = mNoteModel.get(position);

		if (convertView == null){
			view = new NoteItemView(mContext, null);
			view.setOnNoteDeleteListener(new NoteItemView.OnNoteDeleteListener() {
				@Override
				public void onDeleteNoteClicked(Note note) {
					if(mOnNoteDeleteListener != null)
						mOnNoteDeleteListener.onDeleteNoteClicked(note);
				}
			});
		}
		
		view.setNote(note);

		// Update item view
		view.updateView();

		return view;
	}

}
