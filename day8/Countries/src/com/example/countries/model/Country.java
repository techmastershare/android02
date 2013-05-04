package com.example.countries.model;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Country implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mName;
	private Bitmap mFlag;

	public Country(String name, Bitmap flag) {
		super();
		this.mName = name;
		this.mFlag = flag;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public Bitmap getFlag() {
		return mFlag;
	}

	public void setFlag(Bitmap flag) {
		this.mFlag = flag;
	}

}
