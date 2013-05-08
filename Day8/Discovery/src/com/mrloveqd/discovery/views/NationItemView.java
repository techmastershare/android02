package com.mrloveqd.discovery.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrloveqd.discovery.R;
import com.mrloveqd.discovery.models.Nation;

public class NationItemView extends LinearLayout {

	private ImageView mFlagImageView = null;
	private TextView mNationNameTextView = null;
	private Nation mNation = null;
	private OnNationDetailListener mOnNationDetailListener = null;
	
	public interface OnNationDetailListener{
		public void onDetailNationClicked(Nation nation);
	}
	
	public void setOnNationDetailListener(OnNationDetailListener callback){
		mOnNationDetailListener = callback;
	}
	public Nation getNation() {
		return mNation;
	}

	public void setNation (Nation nation) {
		this.mNation = nation;
	}

	public NationItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	protected boolean initialize() {
		initUI();
		return true;
	}

	protected boolean initUI() {
		LayoutInflater layoutInflater = (LayoutInflater) this.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.nation_item_view, this);

		// Initialize controls
		this.mFlagImageView = (ImageView) findViewById(R.id.flag_image_view);
		this.mNationNameTextView = (TextView) findViewById(R.id.name_text_view);
		ImageView mDetailImageView = (ImageView) findViewById(R.id.detail_image_view);
		mDetailImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mOnNationDetailListener != null)
					mOnNationDetailListener.onDetailNationClicked(mNation);
			}
		});
		
		
		return true;
	}

	public void updateView() {
		mNationNameTextView.setText(mNation.getNationName());
		mFlagImageView.setImageDrawable(mNation.getFlag());
	}

}
