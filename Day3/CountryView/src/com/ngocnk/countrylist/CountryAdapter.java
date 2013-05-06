package com.ngocnk.countrylist;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CountryAdapter extends BaseAdapter{
	ArrayList<Country> mCountryList;
	private Context mContext = null;
		
	public CountryAdapter(Context context, ArrayList<Country> countrylist ) {
		mCountryList = countrylist;
		mContext = context;
	}
	
	public void setContext(Context context) {
		mContext = context;
	}
	public Context getContext() {
		return mContext;
	}
	
	@Override
	public int getCount() {
		if (mCountryList == null)
			return 0;
		return mCountryList.size();
	}

	@Override
	public Object getItem(int position) {
		if (mCountryList == null)
			return null;
		return mCountryList.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CountryItemView view = (CountryItemView) convertView;
		Country country = mCountryList.get(position);
		
		if(convertView == null) {
            view = new CountryItemView(mContext);
		}
            
		view.setCountry(country);
		view.updateView();
		return view;
	}

}
