package com.techmaster.countrytab;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CountryItemView extends LinearLayout {

	static final String ASSETS_DIR = "country/";
	
	Context mContext;
	ImageView mFlagImage;
	TextView mCountryName;
	ImageView mDetailImag;
	int mPosition;
	
	Country mCountry;
	onSeeDetailListener mCountryDetail;
	
	public interface onSeeDetailListener{
		public void onSeeDetail(int position);
	}
	
	public void setOnSeedetailListener(onSeeDetailListener countryDetail){
		mCountryDetail = countryDetail;
	}
	
	public CountryItemView(Context context, int position) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		mPosition = position;
		initUI();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) this.getContext()
				                     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.country_item, this);
		mFlagImage = (ImageView) findViewById(R.id.country_icon);
		mCountryName = (TextView) findViewById(R.id.country_name);
		mDetailImag = (ImageView) findViewById(R.id.details_icon);
		
		mDetailImag.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    mCountryDetail.onSeeDetail(mPosition);	
			}
		});
	}

	public void setCountry(Country c){
		mCountry = c;
	}
	
	public void update(){
		Bitmap bmFlag;
		try {
			bmFlag = BitmapFactory.decodeStream(this.mContext.getAssets()
					.open(ASSETS_DIR + mCountry.getRegion() + "/" + mCountry.getName() + "/" +mCountry.getResourceId()));
			mFlagImage.setImageBitmap(bmFlag);
			mCountryName.setText(mCountry.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
