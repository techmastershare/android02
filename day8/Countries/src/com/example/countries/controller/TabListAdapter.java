package com.example.countries.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.countries.model.TabModel;
import com.example.countries.model.TabObject;
import com.example.countries.view.TabItemView;

public class TabListAdapter extends BaseAdapter {

	private TabModel mTabModel;
	private Context mContext;

	public void setTabModel(TabModel tabModel) {
		this.mTabModel = tabModel;
	}

	public void setContext(Context context) {
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return mTabModel.count();
	}

	@Override
	public Object getItem(int position) {
		return mTabModel.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TabItemView tabView = (TabItemView) convertView;
		TabObject tab = mTabModel.get(position);

		if (tabView == null) {
			tabView = new TabItemView(mContext, null);
		}

		tabView.setContext(mContext);
		tabView.setTabObject(tab);
		tabView.update();
		return tabView;
	}

}
