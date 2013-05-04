package com.example.countries.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.countries.R;
import com.example.countries.model.Country;

public class CountryDetailActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.country_detail_view);

		initUI();
	}

	private void initUI() {
		TextView nameTextView = (TextView) findViewById(R.id.country_detail_text_view);
		ImageView flagImageView = (ImageView) findViewById(R.id.flag_detail_image_view);

		Country country = (Country) getIntent().getSerializableExtra("country_obj");
		nameTextView.setText(country.getName());
		flagImageView.setImageBitmap(country.getFlag());
	}
}
