package ebook.ngocnk.ebookreader.controllers;

import java.util.ArrayList;

import ebook.ngocnk.ebbokreader.views.EbookItemView;
import ebook.ngocnk.ebookreader.models.Ebook;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class EbookAdapter extends BaseAdapter {

	ArrayList<Ebook> mList;
	Context mContext;
	
	public EbookAdapter(Context context, ArrayList<Ebook> list ){		
		mList = list;
		mContext = context;
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EbookItemView view = (EbookItemView) convertView;
		Ebook item = mList.get(position);
		
		if(view == null){
			view = new EbookItemView(mContext);
		}
		view.setItemReader(item);
		view.update();
		return view;
	}

}
