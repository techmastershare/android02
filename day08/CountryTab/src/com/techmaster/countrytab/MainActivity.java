package com.techmaster.countrytab;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.techmaster.countrytab.CountryItemView.onSeeDetailListener;



import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
	
	TabHost mTabhost;
	HashMap<String, List<Country>> map;
	static final int REQUEST_CODE = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		initUI();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		mTabhost = getTabHost();
		mTabhost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
		CountryParser countryParse = new CountryParser(this);
		InputStream is = getResources().openRawResource(R.raw.countries);
		countryParse.parse(is);
		map = countryParse.getMap();

		Set<String> tabName = map.keySet();
		for (String name : tabName){
			TabSpec tab = mTabhost.newTabSpec(name);
			tab.setIndicator(createTabView(getApplicationContext(), name));
			final ListView tabContent = new ListView(this);
			CountryAdapter adapter = new CountryAdapter(mTabhost.getContext(), (List)map.get(name));
			adapter.setOnSeeDetailListener(new onSeeDetailListener() {
				@Override
				public void onSeeDetail(Country country) {
					// TODO Auto-generated method stub
					Intent i = new Intent(MainActivity.this, CountryDetailView.class);
					i.putExtra("countryName", country.getName());
					i.putExtra("flagImage", country.getResourceId());
					i.putExtra("tabIndex", mTabhost.getCurrentTab());
					i.putExtra("abbreviation", country.getAbbreviation());
					startActivityForResult(i, REQUEST_CODE);
				}
			});
			
			tabContent.setAdapter(adapter);
			tab.setContent(new TabHost.TabContentFactory() {				
				@Override
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					return tabContent;
				}
			});
		    mTabhost.addTab(tab);
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_CODE){
			if(resultCode == RESULT_OK){
				int tabIndex = data.getIntExtra("tabIndex", -1);
				Log.i("MAINACTIVITY", " "+ tabIndex);
				if(tabIndex != -1){
					mTabhost.setCurrentTab(tabIndex);
				}
			}
		}
	}
	
	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}

}
