package com.example.countries.activity;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.countries.R;
import com.example.countries.common.Common;
import com.example.countries.model.Country;

public class CountryDetailActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.country_detail_view);

		try {
			initUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initUI() throws IOException {
		TextView nameTextView = (TextView) findViewById(R.id.country_detail_text_view);
		ImageView flagImageView = (ImageView) findViewById(R.id.flag_detail_image_view);

		Bundle bundle = getIntent().getExtras();
		Country country = null;
		String continent = null;
		if (bundle != null) {
			country = bundle.getParcelable(Common.COUNTRY_OBJ);
			continent = bundle.getString(Common.CONTINENT);
			nameTextView.setText(country.getName());
			flagImageView.setImageBitmap(country.getFlag());
		}

		ImageView backButton = (ImageView) findViewById(R.id.back_button);
		backButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		WebView webView = (WebView) findViewById(R.id.web_view);
		webView.setWebChromeClient(new WebChromeClient());

		if (country != null) {
			webView.loadUrl("file:///android_asset/" + continent + "/" + country.getName() + "/info.html");
		}
	}
}
