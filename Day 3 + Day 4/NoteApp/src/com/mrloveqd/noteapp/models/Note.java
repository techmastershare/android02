package com.mrloveqd.noteapp.models;

public class Note {
	private int mId;
	private String mContent;
	private String mDate;

	public Note(int id, String content, String date) {
		mId = id;
		mContent = content;
		mDate = date;
	}

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		this.mId = id;
	}

	public String getContent() {
		return mContent;
	}

	public void setContent(String content) {
		this.mContent = content;
	}

	public String getDate() {
		return mDate;
	}

	public void setDate(String date) {
		this.mDate = date;
	}
}
