package com.example.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.project1.R.layout.main);
		
//		ImageView iv1 = (ImageView)findViewById(com.example.project1.R.id.image1);
//		ImageView iv2 = (ImageView)findViewById(com.example.project1.R.id.image2);
		Button bt = (Button)findViewById(com.example.project1.R.id.button1);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,Main2.class);
				startActivity(i);
				finish();
			}
		});
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
}
