package com.ngocnk.countryview;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {
	private WebView mWebView;
	private TextView mText;
	private ImageView mFlag;
	AssetManager mAssetManager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.country_detail_view);
	    
	    ImageView back = (ImageView) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        initUI();
	}
	
    protected void initUI() {
        //Get Bundle
        Bundle extra = getIntent().getExtras();
       		String folder = extra.getString("FolderExtra");
       		int position = extra.getInt("CountryPosition");
       		
       	mAssetManager = getAssets();
        try {
        	String[] List = mAssetManager.list(folder);
        	String mNameCountry = List[position];
        		
        	//Set country name
        	mText = (TextView) findViewById(R.id.name_country_detail);	
        	mText.setText(mNameCountry);
               	
        	//Set country flag
        	InputStream flag;  
            flag = mAssetManager.open(folder + "/" + mNameCountry + "/flag.png");
        	Bitmap bitmap = BitmapFactory.decodeStream(flag);
        	ImageView flag_view = (ImageView) findViewById(R.id.flag_view);
        	flag_view.setImageBitmap(bitmap);
        	flag.close();
        	
        	//WebView for country detail
            mWebView = (WebView) findViewById(R.id.web_view);
    		mWebView.setWebChromeClient(new WebChromeClient());
    		WebSettings webSettings = mWebView.getSettings();
    		webSettings.setJavaScriptEnabled(true);
    		mWebView.loadUrl("file:///android_asset/" + folder + "/"+ mNameCountry + "/info.html");
        } catch (IOException e) {
        	e.printStackTrace();
        }
  	}
}
