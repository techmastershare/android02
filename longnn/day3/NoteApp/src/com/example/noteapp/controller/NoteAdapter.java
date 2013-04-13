package com.example.noteapp.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.noteapp.model.Note;
import com.example.noteapp.model.NoteModel;
import com.example.noteapp.view.NoteItemView;

public class NoteAdapter extends BaseAdapter {

	private NoteModel mNoteModel;
	private Context mContext;

	public NoteItemView.OnNoteDeleteListener onNoteDeleteListener;

	public void setOnNoteDeleteListener(NoteItemView.OnNoteDeleteListener callback) {
		onNoteDeleteListener = callback;
	}

	public NoteModel getNoteModel() {
		return mNoteModel;
	}

	public void setNoteModel(NoteModel noteModel) {
		this.mNoteModel = noteModel;
	}

	public Context getContext() {
		return mContext;
	}

	public void setContext(Context context) {
		this.mContext = context;
	}

	@Override
	public int getCount() {
		if (mNoteModel != null)
			return mNoteModel.size();

		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (mNoteModel != null) {
			return mNoteModel.get(position);
		}
		return null;
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

		if (view == null) {
			view = new NoteItemView(mContext, null);
			view.setOnNoteDeleteListener(new NoteItemView.OnNoteDeleteListener() {

				@Override
				public void onDeleteNoteClicked(Note note) {
					if (onNoteDeleteListener != null) {
						onNoteDeleteListener.onDeleteNoteClicked(note);
					}
				}
			});
		}

		view.setNote(note);
		view.updateView();

		return view;
	}

}
