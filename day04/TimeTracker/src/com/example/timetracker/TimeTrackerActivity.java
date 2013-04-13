package com.example.timetracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class TimeTrackerActivity extends Activity {

	public static final int TIME_ENTRY_REQUEST_CODE = 1;
	TimeTrackerAdapter timeList;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.time_list_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ListView listview = (ListView) findViewById(R.id.times_list);
		timeList = new TimeTrackerAdapter(this);
		listview.setAdapter(timeList);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.add_time_menu_item) {
			Intent intent = new Intent(this, AddTimeActivity.class);
			startActivityForResult(intent, TIME_ENTRY_REQUEST_CODE);

			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (requestCode == TIME_ENTRY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				String notes = data.getStringExtra("notes");
				String time = data.getStringExtra("time");

				boolean edit = data.getBooleanExtra("edit", false);
				if (edit) {
					int id = data.getIntExtra("id", -1);
					TimeRecord timerecord = (TimeRecord) timeList.times.get(id);
					timerecord.setTime(time);
					timerecord.setNotes(notes);
				} else {

					timeList.times.add(new TimeRecord(time, notes));

				}

				timeList.notifyDataSetChanged();
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

}
