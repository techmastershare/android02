package com.ngocnk.countryview;

import java.io.IOException;
import java.util.ArrayList;

import com.ngocnk.world.Africa;
import com.ngocnk.world.Antarctica;
import com.ngocnk.world.Asia;
import com.ngocnk.world.Europe;
import com.ngocnk.world.NorthAmerica;
import com.ngocnk.world.Oceania;
import com.ngocnk.world.SouthAmerica;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabLayoutActivity extends TabActivity {
	AssetManager mAssetManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);
		TabHost tabHost = getTabHost();
    
		/*mAssetManager = getAssets();
		
		try {
			String[] ListFile = mAssetManager.list("WorldMap");
			for (String assets : ListFile) {
				TabSpec spec = tabHost.newTabSpec(assets);
				spec.setIndicator(assets, getResources().getDrawable(R.drawable.icon_tab));
				Intent intent = new Intent(this, .class); //have a problem with "...".class
				spec.setContent(intent);
				tabHost.addTab(spec);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
    
	// Tab for Asia
    TabSpec asiaspec = tabHost.newTabSpec("Asia Map");
    asiaspec.setIndicator("Asia Map", getResources().getDrawable(R.drawable.icon_tab));
    Intent asiaIntent = new Intent(this, Asia.class);
    asiaspec.setContent(asiaIntent);
    
    // Tab for Europe
    TabSpec europespec = tabHost.newTabSpec("Europe Map");
    europespec.setIndicator("Europe Map", getResources().getDrawable(R.drawable.icon_tab));
    Intent europeIntent = new Intent(this, Europe.class);
    europespec.setContent(europeIntent);
    
    // Tab for South America
    TabSpec southamericaspec = tabHost.newTabSpec("South America");
    southamericaspec.setIndicator("South America Map", getResources().getDrawable(R.drawable.icon_tab));
    Intent southamericaIntent = new Intent(this, SouthAmerica.class);
    southamericaspec.setContent(southamericaIntent);
    
    // Tab for Africa
    TabSpec africaspec = tabHost.newTabSpec("Africa Map");
    africaspec.setIndicator("Africa Map", getResources().getDrawable(R.drawable.icon_tab));
    Intent africaIntent = new Intent(this, Africa.class);
    africaspec.setContent(africaIntent);
    
    // Tab for Antarctica
    TabSpec antarcticaspec = tabHost.newTabSpec("Antarctica Map");
    antarcticaspec.setIndicator("Antarctica Map", getResources().getDrawable(R.drawable.icon_tab));
    Intent antarcticaIntent = new Intent(this, Antarctica.class);
    antarcticaspec.setContent(antarcticaIntent);
    
    // Tab for NorthAmerica
    TabSpec northamericaspec = tabHost.newTabSpec("North America Map");
    northamericaspec.setIndicator("North America Map", getResources().getDrawable(R.drawable.icon_tab));
    Intent northamericaIntent = new Intent(this, NorthAmerica.class);
    northamericaspec.setContent(northamericaIntent);

    // Tab for America
    TabSpec oceaniaspec = tabHost.newTabSpec("Australia & Oceania Map");
    oceaniaspec.setIndicator("Antarctica Map", getResources().getDrawable(R.drawable.icon_tab));
    Intent oceaniaIntent = new Intent(this, Oceania.class);
    oceaniaspec.setContent(antarcticaIntent);
    
    // Adding all TabSpec to TabHost
    tabHost.addTab(asiaspec); 
    tabHost.addTab(europespec); 
    tabHost.addTab(southamericaspec); 
    tabHost.addTab(africaspec); 	
    tabHost.addTab(antarcticaspec);
    tabHost.addTab(northamericaspec); 	
    tabHost.addTab(oceaniaspec);
    
	
	}
}
