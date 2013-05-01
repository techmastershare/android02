
package ebook.ngocnk.ebookreader.activities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ebook.ngocnk.ebookreader.activities.ViewEbookActivity;
import ebook.ngocnk.ebookreader.R;
import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

	public class EbookActivity extends Activity {
		private ImageView mImage;
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.view_ebook);
		    
		    //load data from assets
		    final ListView lv=(ListView) findViewById(R.id.list_view);
		    final AssetManager asmr = getAssets();
	        try {
			   	String[] filelist = asmr.list("stories");
	        	ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, filelist);
	        	lv.setAdapter(adapter);
	        } catch (IOException e) {
				e.printStackTrace();
	        }
	        //event when click item on listview
	        lv.setOnItemClickListener(
	        new AdapterView.OnItemClickListener() {
	        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	        	//load comic in selected folder
	        	startActivity(new Intent(EbookActivity.this, ViewEbookActivity.class));
	        	}
	        });
	     
	      initUI();
	    }
		
		void initUI() {
			
		}	
	}
