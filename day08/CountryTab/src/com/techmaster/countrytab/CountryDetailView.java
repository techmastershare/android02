package com.techmaster.countrytab;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryDetailView extends Activity {

	WebView mWebCountry;
	ImageView mBackImg;
	ImageView mFlagImg;
	TextView mCountryName;
	String mName;
	String mImagePath;
	String mHtml;
	static final String ASSETS_DIR = "flag_images/";
	int tabIndex;
	Intent mIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.countrydetail);
	    mIntent = getIntent();
		mName = mIntent.getStringExtra("countryName");
		mImagePath = ASSETS_DIR + mIntent.getStringExtra("flagImage");
		tabIndex = mIntent.getIntExtra("tabIndex", -1);
		mHtml = mIntent.getStringExtra("abbreviation") + ".html";
		initUI();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		mWebCountry = (WebView) findViewById(R.id.webView1);
		mBackImg = (ImageView) findViewById(R.id.backImage);
		mFlagImg = (ImageView) findViewById(R.id.flagImage);
		mCountryName = (TextView) findViewById(R.id.textView1);
		try {
			Bitmap bm = BitmapFactory.decodeStream(getAssets().open(mImagePath));
			mFlagImg.setImageBitmap(bm);
			mCountryName.setText(mName);
			
			mWebCountry.setWebChromeClient(new WebChromeClient());
			mWebCountry.setWebViewClient(new WebViewClient());
			WebSettings setting = mWebCountry.getSettings();
			setting.setJavaScriptEnabled(true);
			
			mWebCountry.loadUrl("file:///android_asset/www/" + mHtml);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mBackImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mIntent.putExtra("tabIndex", tabIndex);
				CountryDetailView.this.setResult(RESULT_OK,mIntent);
				finish();
			}
		});
	}
}
