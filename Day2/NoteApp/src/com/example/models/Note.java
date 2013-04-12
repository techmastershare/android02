package com.example.models;

public class Note {
	private String mName = "";
	private String mContent = "";
	private int mAvatarID = -1;
	
	public Note(String Name, String Content, int AvatarID) {
		this.mContent = Content;
		this.mName = Name;
		this.mAvatarID = AvatarID;
	}
	
	public void setName(String name) {
		this.mName = name;
	}
	
	public String getName() {
		return mName;
	}
	
	public void setDate(String content) {
		this.mContent = content;
	}
	
	public String getContent() {
		return mContent;
	}
	
	public void setAvatarID(int id) {
		mAvatarID = id;
	}
	
	public int getAvatarID() {
		return mAvatarID;
	}
}
