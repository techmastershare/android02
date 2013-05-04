package com.example.countries.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.countries.R;
import com.example.countries.model.TabObject;

public class TabItemView extends LinearLayout {

	TabObject mTabObject;
	ImageView mImageView;
	TextView mTextView;
	Context mContext;

	public TabObject getTabObject() {
		return mTabObject;
	}

	public void setTabObject(TabObject tabObject) {
		this.mTabObject = tabObject;
	}

	public void setContext(Context context) {
		mContext = context;
	}

	public TabItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initUI();
	}

	private void initUI() {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.tab_item_view, this);

		mTextView = (TextView) findViewById(R.id.tab_name_text_view);
		mImageView = (ImageView) findViewById(R.id.tab_image_view);

	}

	public void update() {
		mTextView.setText(mTabObject.getTitle());
		if (mTabObject.isSelected()) {
			mImageView.setImageResource(android.R.drawable.star_big_on);
		} else {
			mImageView.setImageResource(android.R.drawable.star_big_off);
		}
	}

	public void clearSelected() {
		mTabObject.setSelected(false);
	}
}
