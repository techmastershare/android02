package com.mrloveqd.discovery;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.mrloveqd.discovery.commons.Commons;

public class DetailActivity extends SherlockFragmentActivity {

	private WebView mContentWebView = null;
	private ImageView mBackImageView, mFlagImageView = null;
	private TextView mNationNameTextView = null;
	private String mNationName = "";
	private String mContinent = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_view);
		initUI();
	}

	protected void initUI() {
		
		mContentWebView = (WebView) findViewById(R.id.content_webview);
		mNationNameTextView = (TextView) findViewById(R.id.nation_name_text_view);
		mContentWebView = (WebView) findViewById(R.id.content_webview);
		mBackImageView = (ImageView) findViewById(R.id.back_image_view);
		mFlagImageView = (ImageView) findViewById(R.id.flag_image_view);
		
		Bundle bundel = getIntent().getExtras();
		mNationName = bundel.getString(Commons.NATION_NAME);
		mContinent = bundel.getString(Commons.CONTINENT);
		mNationNameTextView.setText(mNationName);
		
		// Get and Set Flag Image
		
		try {
			InputStream inputStream = getAssets().open(Commons.PARENT_DIR + "/" + mContinent + "/" + mNationName + "/flag.png");
			Drawable drawable = Drawable.createFromStream(inputStream, null);
			mFlagImageView.setImageDrawable(drawable);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mContentWebView.loadUrl("file:///android_asset/" + Commons.PARENT_DIR + "/" + mContinent + "/" + mNationName + "/" + "info.html");
		mBackImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DetailActivity.this.finish();
			}
		});
	}
}
