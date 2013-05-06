package com.ngocnk.world;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.ngocnk.countrylist.Country;
import com.ngocnk.countrylist.CountryAdapter;
import com.ngocnk.countryview.DetailActivity;
import com.ngocnk.countryview.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Antarctica extends Activity {
	public ListView mCountryListView = null;
	public ArrayList<Country> mArray = new ArrayList<Country>();
 	AssetManager mAssetManager;
 	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.country_layout);
	    initUI();    
	}

	protected void initUI() {
		mCountryListView = (ListView) findViewById(R.id.country_list_view);
		InputStream mImageInput;
		mAssetManager = getAssets();
		
		try {
			String[] ListFile = mAssetManager.list("Antarctica");
			for (String assets : ListFile) {
					mImageInput = mAssetManager.open("Antarctica/" + assets + "/flag.png");
					mArray.add(new Country(assets,mImageInput));
					mCountryListView.setAdapter(new CountryAdapter(this, mArray));
				}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		mCountryListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				//Get country at the clicked position
				Intent intent = new Intent(Antarctica.this, DetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("FolderExtra","Asia");
				bundle.putInt("CountryPosition", position);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

}
