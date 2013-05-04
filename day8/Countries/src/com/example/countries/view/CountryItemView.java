package com.example.countries.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.countries.R;
import com.example.countries.model.Country;

public class CountryItemView extends LinearLayout {

	private Country mCountry;
	private TextView mTextView;
	private ImageView mImageView;

	public Country getCountry() {
		return mCountry;
	}

	public void setCountry(Country country) {
		this.mCountry = country;
	}

	public CountryItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initUI();
	}

	private void initUI() {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.country_item_view, this);
		mTextView = (TextView) findViewById(R.id.country_text_view);
		mImageView = (ImageView) findViewById(R.id.flag_image_view);
	}

	public void update() {
		mTextView.setText(mCountry.getName());
		mImageView.setImageBitmap(mCountry.getFlag());
	}
}
