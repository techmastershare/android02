package com.mrloveqd.ebookapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.mrloveqd.ebookapp.R;
import com.mrloveqd.ebookapp.common.Common;

public class EbookActivity extends Activity {

	private ImageView mImageReaderImageView, mWordReaderImageView, mPdfReaderImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);
		initUI();
	}
	
	protected void initUI(){
		mImageReaderImageView = (ImageView) findViewById(R.id.image_reader_image_view);
		mWordReaderImageView = (ImageView) findViewById(R.id.word_reader_image_view);
		mPdfReaderImageView = (ImageView) findViewById(R.id.pdf_reader_image_view);
		
		mImageReaderImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(EbookActivity.this, ImageReaderActivity.class));
			}
		});
		
		mPdfReaderImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				startActivity(new Intent(EbookActivity.this, PdfReaderActivity.class));
			}
		});
		
		mWordReaderImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				startActivity(new Intent(EbookActivity.this, WordReaderActivity.class));
			}
		});
	}

}
