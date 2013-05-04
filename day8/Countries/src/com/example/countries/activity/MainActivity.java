package com.example.countries.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.countries.R;
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

	int mCurrentIndex = 0;
	AssetManager mAssMgr;
	CountryModel mCountryModel;
	CountryListAdapter mContryAdapter;
	ListView mCountryListView;

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
		mContryAdapter = new CountryListAdapter();
		mContryAdapter.setContext(this);
		final TabModel model = new TabModel();

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

		for (String continent : continentList) {
			TabObject tab = new TabObject(continent, false);

			model.add(tab);
		}

		if (model.count() > 0) {
			TabObject tabObj = model.get(0);
			tabObj.setSelected(true);
			fillDetaiToListView(tabObj);
		}

		TabListAdapter adapter = new TabListAdapter();
		adapter.setContext(getApplicationContext());
		adapter.setTabModel(model);
		tabListView.setAdapter(adapter);

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

		mCountryListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
				CountryItemView countryView = (CountryItemView) view;
				Intent intent = new Intent(getApplicationContext(), CountryDetailActivity.class);
				Country country = countryView.getCountry();
				intent.putExtra("country_obj", country);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void fillDetaiToListView(TabObject tab) {
		try {
			String[] strList = mAssMgr.list(tab.getTitle());
			List<String> countryNameList = new ArrayList<String>();
			List<String> countryFlagList = new ArrayList<String>();

			for (String str : strList) {
				if (str.endsWith(".html")) {
					countryNameList.add(str);
				} else if (str.endsWith(".png")) {
					countryFlagList.add(str);
				}
			}

			MyCompare compare = new MyCompare();
			Collections.sort(countryNameList, compare);
			Collections.sort(countryFlagList, compare);

			mCountryModel.clear();
			for (int i = 0; i < countryFlagList.size(); i++) {
				String name = countryNameList.get(i).substring(0, countryNameList.get(i).lastIndexOf("."));
				Bitmap flag = BitmapFactory.decodeStream(mAssMgr.open(tab.getTitle() + "/" + countryFlagList.get(i)));
				Country country = new Country(name, flag);
				mCountryModel.add(country);
			}

			mContryAdapter.setCountryModel(mCountryModel);
			mCountryListView.setAdapter(mContryAdapter);
		} catch (IOException e) {
			Log.e("ERRORRRRRRRRR", "");
		}
	}

	private class MyCompare implements Comparator<String> {

		@Override
		public int compare(String str1, String str2) {
			return str1.compareToIgnoreCase(str2);
		}

	}
}
