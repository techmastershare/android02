package com.mrloveqd.discovery;

import java.io.IOException;
import java.io.InputStream;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.mrloveqd.discovery.commons.Commons;
import com.mrloveqd.discovery.controllers.NationAdapter;
import com.mrloveqd.discovery.models.Nation;
import com.mrloveqd.discovery.models.NationModel;
import com.mrloveqd.discovery.views.NationItemView;

public class CountryActivity extends SherlockFragment implements TabListener {

	private Fragment mFragment;
	private ListView mCountryListView = null;
	private NationModel mNationModel = new NationModel();
	private NationAdapter mNationAdapter = new NationAdapter();
	private View mView = null; 
	private static String mContinent = "";
	private static String CONTINENTS = "Continents";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setContentView(R.layout.country_view);
	}



	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mFragment = new CountryActivity();
        // Attach fragment1.xml layout
        ft.add(android.R.id.content, mFragment);
        ft.attach(mFragment);
        
        // Get current Name of Continent 
        mContinent = (String) tab.getText();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(mFragment);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mView = inflater.inflate(R.layout.country_view, container, false);
		mCountryListView = (ListView) mView.findViewById(R.id.country_list_view);
		
		//get file in asset
		AssetManager asssetManager = getActivity().getAssets();
		String[] listFile = null ;
		try {
			listFile = asssetManager.list(Commons.PARENT_DIR + "/" + mContinent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		for (int i = 0; i < listFile.length ; i++) {
//			String name = listFile[i].substring(0, (listFile[i].length() -5) );
			String name = listFile[i];
			try {
				InputStream inputStream = getActivity().getAssets().open(Commons.PARENT_DIR + "/" + mContinent + "/" + name + "/flag.png");
				Drawable drawable = Drawable.createFromStream(inputStream, null);
				Nation nation = new Nation(drawable, name);
				mNationModel.add(nation);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		mNationModel.sortByName();
		mNationAdapter.setContext(getActivity());
		mNationAdapter.setNationModel(mNationModel);
		mCountryListView.setAdapter(mNationAdapter);
		
		mNationAdapter.setOnNationDetailListener(new NationItemView.OnNationDetailListener() {
			
			@Override
			public void onDetailNationClicked(Nation nation) {
				if(nation == null)
					return;
				
				Bundle bundle = new Bundle();
				bundle.putString(Commons.NATION_NAME, nation.getNationName());
				bundle.putString(Commons.CONTINENT, mContinent);
				Intent inten = new Intent(getActivity(), DetailActivity.class);
				inten.putExtras(bundle);
				startActivity(inten);
			}
		});
		
		return mView;
	}

}
