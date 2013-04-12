package com.techmaster.listphonebook;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PhoneBookAdapter extends BaseAdapter {
	private Context mContext;
	private List<PhoneBook> mListPhoneBook;

	public PhoneBookAdapter(Context context, List<PhoneBook> list) {
		mContext = context;
		mListPhoneBook = list;

	}

	@Override
	public int getCount() {
		return mListPhoneBook.size();
	}

	@Override
	public Object getItem(int pos) {
		return mListPhoneBook.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		// get selected entry
		PhoneBook entry = mListPhoneBook.get(pos);

		// inflating list view layout if null
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.phonebook, null);
		}

		// set avatar
		ImageView ivAvatar = (ImageView)convertView.findViewById(R.id.imgAvatar);
		ivAvatar.setImageBitmap(entry.getAvatar());

		// set name
		TextView tvName = (TextView)convertView.findViewById(R.id.tvName);
		tvName.setText(entry.getName());

		return convertView;
	}

}
