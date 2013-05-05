package com.example.countries.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Country implements Parcelable {

	private String mName;
	private Bitmap mFlag;

	public Country(String name, Bitmap flag) {
		super();
		this.mName = name;
		this.mFlag = flag;
	}

	public Country(Parcel parcel) {
		this.mName = parcel.readString();
		this.mFlag = Bitmap.CREATOR.createFromParcel(parcel);
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mName);
		mFlag.writeToParcel(dest, 0);
	}

	public static final Creator<Country> CREATOR = new Creator<Country>() {

		@Override
		public Country createFromParcel(Parcel source) {
			return new Country(source);
		}

		@Override
		public Country[] newArray(int size) {
			return new Country[size];
		}
	};

}
