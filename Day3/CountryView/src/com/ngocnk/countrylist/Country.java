package com.ngocnk.countrylist;

import java.io.InputStream;
import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Country {
	private String mName;
	private InputStream mImageStream;;
	
	public Country (String name, InputStream image) {
		mName = name;
		mImageStream = image;
	}
	
	public void setName(String name) {
		mName = name;
	}
	
	public String getName() {
		return mName;
	}
	
	public InputStream getImageStream() {
		return mImageStream;
	}

	public void setImagePath(InputStream image) {
		this.mImageStream = image;
	}     
}
