package com.techmaster.countrytab;

import java.util.List;

import com.techmaster.countrytab.CountryItemView.onSeeDetailListener;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CountryAdapter extends BaseAdapter {

	List<Country> mListCountry;
	Context mContext;
	private onSeeDetailListener mOnSeeDetailListener; 
	
	public CountryAdapter(Context context, List<Country> list) {
		mContext = context;
		mListCountry = list;
	}
	
	public void setOnSeeDetailListener(onSeeDetailListener callBack){
		mOnSeeDetailListener = callBack;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListCountry.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mListCountry.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		CountryItemView view = (CountryItemView) convertView;
		Country country = mListCountry.get(position);
		if(convertView == null){
			view = new CountryItemView(mContext, position);
		}		
		view.setCountry(country);
		view.setOnSeedetailListener(new onSeeDetailListener() {			
			@Override
			public void onSeeDetail(int position) {
				// TODO Auto-generated method stub
			    mOnSeeDetailListener.onSeeDetail(position);	
			}
		});
		view.update();		
		return view;
	}

}
