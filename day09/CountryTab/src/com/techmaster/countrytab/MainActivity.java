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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
	
	TabHost mTabhost;
	HashMap<String, List<Country>> map;
	static final int REQUEST_CODE = 0;
	private CountriesModel mCountryiesModel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mCountryiesModel = CountriesModel.getInstance();
		initUI();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		mTabhost = getTabHost();
		mTabhost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
		CountryParser countryParse = new CountryParser(this);
		//InputStream is = getResources().openRawResource(R.raw.countries);
		countryParse.parse();
		map = mCountryiesModel.getMap();

		Set<String> tabName = map.keySet();
		for (final String name : tabName){
			String tmpName = name.replace("_", " ");
			TabSpec tab = mTabhost.newTabSpec(tmpName);
			tab.setIndicator(createTabView(getApplicationContext(), tmpName));
			final ListView tabContent = new ListView(this);
			CountryAdapter adapter = 
					new CountryAdapter(mTabhost.getContext(), mCountryiesModel.getAbbreviation(name));
			adapter.setOnSeeDetailListener(new onSeeDetailListener() {
				@Override
				public void onSeeDetail(int position) {
					// TODO Auto-generated method stub
					Intent i = new Intent(MainActivity.this, CountryDetailView.class);
					i.putExtra(Common.TAB_INDEX, mTabhost.getCurrentTab());
					i.putExtra(Common.ITEM_POSITION, position);
					i.putExtra(Common.CONT_NAME, name);
					startActivityForResult(i, REQUEST_CODE);
				}
			});
			/*tabContent.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long id) {
					// TODO Auto-generated method stub
					Intent i = new Intent(MainActivity.this, CountryDetailView.class);
					i.putExtra(Common.TAB_INDEX, mTabhost.getCurrentTab());
					i.putExtra(Common.ITEM_POSITION, position);
					i.putExtra(Common.CONT_NAME, name);
					startActivityForResult(i, REQUEST_CODE);
					
				}
			
			});
*/			
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
				//Log.i("MAINACTIVITY", " "+ tabIndex);
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
