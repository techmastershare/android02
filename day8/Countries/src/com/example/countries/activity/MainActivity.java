package com.example.countries.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.countries.R;
import com.example.countries.common.Common;
import com.example.countries.controller.CountryListAdapter;
import com.example.countries.controller.TabListAdapter;
import com.example.countries.model.Country;
import com.example.countries.model.CountryModel;
import com.example.countries.model.TabModel;
import com.example.countries.model.TabObject;
import com.example.countries.view.CountryItemView;
import com.example.countries.view.HorizontalListView;
import com.example.countries.view.TabItemView;

public class MainActivity extends Activity {

	private AssetManager mAssMgr;
	private CountryModel mCountryModel;
	private CountryListAdapter mCountryAdapter;
	private ListView mCountryListView;
	private String mContinent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);

		initUI();
	}

	private void initUI() {
		HorizontalListView tabListView = (HorizontalListView) findViewById(R.id.tab_list_view);
		mCountryListView = (ListView) findViewById(R.id.country_list_view);
		mCountryModel = new CountryModel();
		mCountryAdapter = new CountryListAdapter();
		mCountryAdapter.setContext(this);

		mAssMgr = getAssets();
		ArrayList<String> continentList = new ArrayList<String>();
		try {
			// String[] folderList = am.list("");
			continentList = new ArrayList<String>(Arrays.asList(mAssMgr.list("")));
			if (continentList != null) {
				if (continentList.contains("webkit"))
					continentList.remove("webkit");

				if (continentList.contains("images"))
					continentList.remove("images");

				if (continentList.contains("sounds"))
					continentList.remove("sounds");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final TabModel tabModel = new TabModel();
		for (String continent : continentList) {
			TabObject tab = new TabObject(continent, false);
			tabModel.add(tab);
		}

		if (tabModel.count() > 0) {
			TabObject tabObj = tabModel.get(0);
			tabObj.setSelected(true);
			fillDetaiToListView(tabObj);
		}

		TabListAdapter tabAdapter = new TabListAdapter();
		tabAdapter.setContext(getApplicationContext());
		tabAdapter.setTabModel(tabModel);
		tabListView.setAdapter(tabAdapter);

		mCountryAdapter.setOnButtonDetailClicked(new CountryItemView.OnButtonDetailClick() {

			@Override
			public void onButtonClicked(Country country) {
				if (country != null) {
					Intent intent = new Intent(getApplicationContext(), CountryDetailActivity.class);
					intent.putExtra(Common.COUNTRY_OBJ, country);
					intent.putExtra(Common.CONTINENT, mContinent);
					startActivity(intent);
				}
			}
		});

		tabListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int index, long arg3) {

				TabItemView view;
				for (int i = 0; i < parent.getChildCount(); i++) {
					view = (TabItemView) parent.getChildAt(i);
					view.clearSelected();
					view.update();
				}

				view = (TabItemView) arg1;
				TabObject tabObject = view.getTabObject();
				tabObject.setSelected(true);
				view.update();

				fillDetaiToListView(tabObject);
			}
		});

		// mCountryListView.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View view, int arg2,
		// long arg3) {
		// CountryItemView countryView = (CountryItemView) view;
		// Intent intent = new Intent(getApplicationContext(),
		// CountryDetailActivity.class);
		// Country country = countryView.getCountry();
		// intent.putExtra("country_obj", country);
		// startActivity(intent);
		// }
		// });
	}

	private void fillDetaiToListView(TabObject tab) {
		try {
			mContinent = tab.getTitle();
			mCountryModel.clear();
			String[] countryList = mAssMgr.list(mContinent);
			for (String countryName : countryList) {
				Bitmap flag = BitmapFactory.decodeStream(mAssMgr.open(mContinent + "/" + countryName + "/flag.png"));
				Country country = new Country(countryName, flag);
				mCountryModel.add(country);
			}

			mCountryAdapter.setCountryModel(mCountryModel);
			mCountryListView.setAdapter(mCountryAdapter);
		} catch (IOException e) {
			Log.e("ERRORRRRRRRRR", "");
		}
	}
}
