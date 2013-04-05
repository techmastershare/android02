package com.example.homework1;

import org.w3c.dom.Text;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	int count = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView tv1, tv2;
		
		final ImageView i1, i2, i3, i4;
				
		final int img_list[] ={ 
	            R.drawable.pic1, 
	            R.drawable.pic2, 
	            R.drawable.pic3, 
	            R.drawable.pic4,
	            R.drawable.pic5,
	            R.drawable.pic6
	    };
		
		final String img_description_list[]={ 
	            "Student", 
	            "Doctor", 
	            "Animal", 
	            "School",
	            "Ambulance",
	            "Zoo"
	    };
		
		//final TextView t2 = (TextView)findViewById(R.id.imageView2);
		
		//tv1 = (TextView) findViewById(R.id.text1);
		//tv2 = (TextView) findViewById(R.id.imageView2);
		i1 = (ImageView) findViewById(R.id.imageView1);
		i2 = (ImageView) findViewById(R.id.imageView2);
		i3 = (ImageView) findViewById(R.id.imageView3);
		i4 = (ImageView) findViewById(R.id.imageView4);
		
		i3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				count--;
				if (count == -1) count = 2;
				//tv.setText(img_description_list[count]); 
				i1.setImageResource(img_list[count]);
				i2.setImageResource(img_list[count+3]); 
	        }
		}); 
		
		i4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				count++;	
				if (count == 3) count = 0;
				//tv.setText(img_description_list[count]); 
				i1.setImageResource(img_list[count]);
				i2.setImageResource(img_list[count+3]); 
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
