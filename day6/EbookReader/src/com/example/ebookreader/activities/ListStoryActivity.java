package com.example.ebookreader.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ebookreader.R;
import com.example.ebookreader.common.Common;

public class ListStoryActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_story);

		ListView storyListView = (ListView) findViewById(R.id.stories_list_view);
		AssetManager am = getAssets();
		List<String> folderList = new ArrayList<String>();
		try {
			// String[] folderList = am.list("");
			folderList = new ArrayList<String>(Arrays.asList(am.list("")));
			if (folderList != null) {
				if (folderList.contains("webkit"))
					folderList.remove("webkit");

				if (folderList.contains("images"))
					folderList.remove("images");

				if (folderList.contains("sounds"))
					folderList.remove("sounds");
				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
						android.R.layout.simple_list_item_1, folderList.toArray(new String[folderList.size()]));
				storyListView.setAdapter(arrayAdapter);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		storyListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
				String storyName = ((TextView) view).getText().toString();
				Intent intent = new Intent(ListStoryActivity.this, ImageReaderActivity.class);
				intent.putExtra(Common.STORY_NAME, storyName);
				startActivity(intent);
			}
		});
	}
}
