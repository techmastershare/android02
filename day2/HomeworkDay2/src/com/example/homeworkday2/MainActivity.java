package com.example.homeworkday2;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	Map<Integer, int[]> map;

	int index = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		map = new HashMap<Integer, int[]>();
		map.put(0, new int[] { R.drawable.u_attendant, R.drawable.v_attendant });
		map.put(1, new int[] { R.drawable.u_docter, R.drawable.v_docter });
		map.put(2, new int[] { R.drawable.u_driver, R.drawable.v_driver });
		map.put(3, new int[] { R.drawable.u_police, R.drawable.v_police });
		map.put(4, new int[] { R.drawable.u_worker, R.drawable.v_worker });

		final ImageView ivUniform = (ImageView) findViewById(R.id.ImageViewer_Uniform);
		final ImageView ivVehicle = (ImageView) findViewById(R.id.ImageViewer_Vehicle);
		ImageView ivLeftButton = (ImageView) findViewById(R.id.ImageViewer_LeftButton);
		ImageView ivRightButton = (ImageView) findViewById(R.id.ImageViewer_RightButton);

		ivRightButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (index < map.size() - 1) {
					index++;
				} else {
					index = 0;
				}

				ivUniform.setImageResource(map.get(index)[0]);
				ivVehicle.setImageResource(map.get(index)[1]);
			}
		});

		ivLeftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (index > 0)
					index--;
				else
					index = map.size() - 1;

				ivUniform.setImageResource(map.get(index)[0]);
				ivVehicle.setImageResource(map.get(index)[1]);
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
