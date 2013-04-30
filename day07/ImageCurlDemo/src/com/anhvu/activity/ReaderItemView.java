package com.anhvu.activity;

import com.example.imagecurldemo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ReaderItemView extends LinearLayout {

	private ImageView mImage;
	private TextView mTextView;
	private ItemReader mItem;
	
	public ReaderItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initUI();
	}

	protected void initUI() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.itemreader, this);
		mImage = (ImageView) findViewById(R.id.imageView);
		mTextView = (TextView) findViewById(R.id.textView);
	}

	public void setItemReader(ItemReader item) {
		// TODO Auto-generated method stub
		mItem = item;
	}

	public void update() {
		// TODO Auto-generated method stub
		Bitmap bm = BitmapFactory.decodeStream(mItem.getImagePath());
		mImage.setImageBitmap(bm);
		mTextView.setText(mItem.getReaderName());
	}

}
