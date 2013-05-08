package com.mrloveqd.discovery;

import java.io.IOException;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class DiscoveryActivity extends SherlockFragmentActivity {

	Tab mTab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main_view);
		initUI();
	}

	private void initUI() {
		ActionBar actionBar = getSupportActionBar();

		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(false);

		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(false);

		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		AssetManager assManager = getAssets();
		try {
			String[] listContinents = assManager.list("Continents");
			for (int i = 0; i < listContinents.length; i++) {
				mTab = actionBar.newTab().setTabListener(new CountryActivity());
				mTab.setText(listContinents[i]);
				actionBar.addTab(mTab);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
