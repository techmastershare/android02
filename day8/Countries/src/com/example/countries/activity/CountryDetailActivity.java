package com.example.countries.activity;

import java.io.IOException;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.countries.R;
import com.example.countries.common.Common;
import com.example.countries.model.Country;

public class CountryDetailActivity extends Activity {
	ActionBar mActionBar;

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
		WebView webView = (WebView) findViewById(R.id.web_view);
		Bundle bundle = getIntent().getExtras();
		Country country = null;
		String continent = null;
		if (bundle != null) {
			country = bundle.getParcelable(Common.COUNTRY_OBJ);
			continent = bundle.getString(Common.CONTINENT);
		}

		if (country != null) {
			webView.loadUrl("file:///android_asset/" + continent + "/" + country.getName() + "/info.html");

			mActionBar = getActionBar();
			mActionBar.setTitle(country.getName());
			mActionBar.setIcon(new BitmapDrawable(getResources(), country.getFlag()));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_back:
			finish();
			break;
		}

		return true;
	}
}
