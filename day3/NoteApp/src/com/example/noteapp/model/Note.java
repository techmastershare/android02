package com.example.noteapp.model;

import java.io.Serializable;

public class Note implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mId;
	private String mTitle;
	private String mContent;

	public Note(int id, String title, String content) {
		super();
		this.mId = id;
		this.mTitle = title;
		this.mContent = content;
	}

	public Note(String title, String content) {
		super();
		this.mTitle = title;
		this.mContent = content;
	}

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		this.mId = id;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public String getContent() {
		return mContent;
	}

	public void setContent(String content) {
		this.mContent = content;
	}

}
