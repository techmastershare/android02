package com.sample.activityswitching1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Page2 extends Activity {
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
		   TextView tv = new TextView(this);
		   tv.setText("This is page 2");
		   setContentView(tv);
		   }
}
