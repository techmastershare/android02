package com.mrloveqd.discovery.models;

import android.graphics.drawable.Drawable;

public class Nation {
	private Drawable mFlag;
	private String mNationName = "";

	public Drawable getFlag() {
		return mFlag;
	}

	public void setFlag(Drawable flag) {
		this.mFlag = flag;
	}

	public String getNationName() {
		return mNationName;
	}

	public void setNationName(String nationName) {
		this.mNationName = nationName;
	}

	public Nation(Drawable flag, String nationName) {
		this.mFlag = flag;
		this.mNationName = nationName;
	}

}
