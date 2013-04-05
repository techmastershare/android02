package com.sample.activityswitching1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ButtonPage extends Activity {
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
		   setContentView(R.layout.buttonpage);
		   
		   Button page1 = (Button) findViewById(R.id.Button01);
		   page1.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                Intent myIntent = new Intent(view.getContext(), Page1.class);
	                startActivityForResult(myIntent, 0);
	            }

		   });
		   
		   Button page2 = (Button) findViewById(R.id.Button02);
		   page2.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                Intent myIntent = new Intent(view.getContext(), Page2.class);
	                startActivityForResult(myIntent, 0);
	            }

		   });
		   
		   Button page3 = (Button) findViewById(R.id.Button03);
		   page3.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                Intent myIntent = new Intent(view.getContext(), Page3.class);
	                startActivityForResult(myIntent, 0);
	            }

		   });
		   
		   Button listViewPage = (Button) findViewById(R.id.Button04);
		   listViewPage.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                Intent myIntent = new Intent(view.getContext(), HomeListView.class);
	                startActivityForResult(myIntent, 0);
	            }

		   });
	   }
}
