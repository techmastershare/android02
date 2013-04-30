package com.anhvu.activity;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ReaderAdapter extends BaseAdapter {

	ArrayList<ItemReader> mList;
	Context mContext;
	
	public ReaderAdapter(Context context, ArrayList<ItemReader> list ){		
		mList = list;
		mContext = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ReaderItemView view = (ReaderItemView) convertView;
		ItemReader item = mList.get(position);
		
		if(view == null){
			view = new ReaderItemView(mContext);
		}
		view.setItemReader(item);
		view.update();
		return view;
	}

}
