package com.example.bt_android6;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	Integer[] docImage = { R.raw.doctor, R.raw.ambulance };

	Integer[] workerImager = { R.raw.worker, R.raw.mayxuc };

	Integer[] ffImage = { R.raw.firefighter, R.raw.firetruck };

	int position = 0;

	ArrayList<Integer[]> imageContain = new ArrayList<Integer[]>();

	ImageView mLeft = (ImageView) findViewById(R.id.leftImage);
	ImageView mRight = (ImageView) findViewById(R.id.rightImage);
	ImageView nextImage = (ImageView) findViewById(R.id.next);
	ImageView backImage = (ImageView) findViewById(R.id.back);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imageContain.add(docImage);
		imageContain.add(workerImager);
		imageContain.add(ffImage);

		nextImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (position < imageContain.size() - 1) {
					mLeft.setImageResource(imageContain.get(position + 1)[0]);
					mRight.setImageResource(imageContain.get(position + 1)[1]);
					position++;
				} else if (position == imageContain.size() - 1) {
					position = 0;
					mLeft.setImageResource(imageContain.get(position)[0]);
					mRight.setImageResource(imageContain.get(position)[1]);
				}
			}
		});

		backImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (position > 0) {
					mLeft.setImageResource(imageContain.get(position - 1)[0]);
					mRight.setImageResource(imageContain.get(position - 1)[1]);
					position--;
				} else if (position == 0) {
					position = imageContain.size() - 1;
					mLeft.setImageResource(imageContain.get(position)[0]);
					mRight.setImageResource(imageContain.get(position)[1]);
				}
			}
		});
	}

	
}
