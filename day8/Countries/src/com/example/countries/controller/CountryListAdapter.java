package com.example.countries.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.countries.model.Country;
import com.example.countries.model.CountryModel;
import com.example.countries.view.CountryItemView;
import com.example.countries.view.CountryItemView.OnButtonDetailClick;

public class CountryListAdapter extends BaseAdapter {

	private CountryModel mCountryModel;
	private Context mContext;
	private OnButtonDetailClick mOnButtonDetailClick;

	public void setCountryModel(CountryModel model) {
		mCountryModel = model;
	}

	public void setContext(Context context) {
		this.mContext = context;
	}

	public void setOnButtonDetailClicked(OnButtonDetailClick onButtonDetailClick) {
		this.mOnButtonDetailClick = onButtonDetailClick;
	}

	@Override
	public int getCount() {
		if (mCountryModel != null)
			return mCountryModel.count();

		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (mCountryModel != null)
			return mCountryModel.get(position);

		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CountryItemView view = (CountryItemView) convertView;
		Country country = mCountryModel.get(position);
		if (view == null) {
			view = new CountryItemView(mContext, null);
			view.setOnButtonDetailClick(new OnButtonDetailClick() {

				@Override
				public void onButtonClicked(Country country) {
					if (mOnButtonDetailClick != null) {
						mOnButtonDetailClick.onButtonClicked(country);
					}
				}
			});
		}

		view.setCountry(country);
		view.update();

		return view;
	}
}
