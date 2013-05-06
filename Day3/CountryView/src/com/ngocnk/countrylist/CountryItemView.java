package com.ngocnk.countrylist;


import com.ngocnk.countryview.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CountryItemView extends LinearLayout{
	private ImageView mImageView = null;
	private TextView mNameTextView = null;
	private Country mCountry = null;
	
	public Country getCountry() {
		return mCountry;
	}
	
	public void setCountry(Country country) {
		this.mCountry = country;
	}
	
	public CountryItemView(Context context) {
		super(context);
		initUI();
	}
	
	protected void initUI() {
		LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.item_list_view, this);
		mImageView = (ImageView) findViewById(R.id.image_view);
		mNameTextView = (TextView) findViewById(R.id.text_view);
	}
	
	public void updateView() {
		Bitmap bitmap = BitmapFactory.decodeStream(mCountry.getImageStream());
		mImageView.setImageBitmap(bitmap);
		mNameTextView.setText(mCountry.getName()); 
	}
}
