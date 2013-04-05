package com.example.bai2_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity{

  int a=-1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final int[] imagearray1 = { R.drawable.nguoi1,R.drawable.nguoi2,R.drawable.nguoi3 };
		final int[] imagearray2 = { R.drawable.xe1,R.drawable.xe2,R.drawable.xe3 };
		final ImageView imageView1 = (ImageView) findViewById( R.id.imageView1 );
		final ImageView imageView2 = (ImageView) findViewById( R.id.imageView2 );

		Button button2 = (Button) findViewById( R.id.button2 );
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if ( a < imagearray1.length-1 ) {
					++a;
					imageView1.setImageResource( imagearray1[a] );
					imageView2.setImageResource( imagearray2[a] );
				}
				else {
					a = 0;
					imageView1.setImageResource( imagearray1[a] );
					imageView2.setImageResource( imagearray2[a] );
				}

			}
		});
		
		Button button1 = (Button) findViewById( R.id.button1 );
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(a>0){
					--a;
					imageView1.setImageResource( imagearray1[a] );
					imageView2.setImageResource( imagearray2[a] );
				}else{
					a = imagearray1.length-1;
					imageView1.setImageResource( imagearray1[a] );
					imageView2.setImageResource( imagearray2[a] );

				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true; 
	}
}
