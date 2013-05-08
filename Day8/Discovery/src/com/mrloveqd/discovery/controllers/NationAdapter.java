package com.mrloveqd.discovery.controllers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mrloveqd.discovery.models.Nation;
import com.mrloveqd.discovery.models.NationModel;
import com.mrloveqd.discovery.views.NationItemView;

public class NationAdapter extends BaseAdapter {
	
	private NationModel mNationModel = null;
	private Context mContext = null;
	private NationItemView.OnNationDetailListener mOnNationDetailListener = null;
	
	public void setOnNationDetailListener(NationItemView.OnNationDetailListener callback) {
		mOnNationDetailListener = callback;
	}
	
	public void setNationModel(NationModel nationModel) {
		mNationModel = nationModel;
	}
	public NationModel getNationModel() {
		return mNationModel;
	}
	
	public void setContext(Context context) {
		mContext = context;
	}
	public Context getContext() {
		return mContext;
	}
	
	@Override
	public int getCount() {
		if (mNationModel == null)
			return 0;
		return mNationModel.size();
	}

	@Override
	public Object getItem(int position) {
		if (mNationModel == null)
			return null;
		return mNationModel.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NationItemView view = (NationItemView) convertView;
        Nation nation = mNationModel.get(position);
        
        if(convertView == null) {
            view = new NationItemView(mContext, null);
            view.setOnNationDetailListener(new NationItemView.OnNationDetailListener() {

				@Override
				public void onDetailNationClicked(Nation nation) {
					if (mOnNationDetailListener != null) 
						mOnNationDetailListener.onDetailNationClicked(nation);
				}
			});
        }
        
        view.setNation(nation);
        
        // Update item view
        view.updateView();
        
        return view;
	}
}
