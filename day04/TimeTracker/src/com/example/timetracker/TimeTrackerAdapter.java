package com.example.timetracker;

import java.util.ArrayList;



import android.app.Activity;
import android.content.Intent;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class TimeTrackerAdapter extends BaseAdapter {
	public ArrayList<TimeRecord> times = new ArrayList<TimeRecord>();
public Activity mainActivity;

	public TimeTrackerAdapter(Activity edit) {
		times = new ArrayList<TimeRecord>();
		times.add(new TimeRecord("03:23", "Felling good!"));
		times.add(new TimeRecord("08:03", "Felling bad!"));
	mainActivity  = edit;
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return times.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return times.get(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	public View getView(int index, View view, ViewGroup parent){
		final int position = index;
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.time_list_item, parent, false);
		}
		final TimeRecord time = times.get(index);
		
		Button edit_button = (Button) view.findViewById(R.id.edit_button);
		
		edit_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("com.example.timetracker.AddtimeActivity");
				intent.putExtra("note", time.getNotes());
				intent.putExtra("time", time.getTime());
				intent.putExtra("edit", true);
				intent.putExtra("id", position);
				mainActivity.startActivityForResult(intent, TimeTrackerActivity.TIME_ENTRY_REQUEST_CODE );
				
			}
		});
		
		
		
		Button delete_button = (Button) view.findViewById(R.id.delete_button);
		delete_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				times.remove(getItem(position));
				TimeTrackerAdapter.this.notifyDataSetChanged();
				
			}
		});
		
		
		TextView timeTextView = (TextView) view.findViewById(R.id.text_view);
		timeTextView.setText(time.getTime());
		TextView notesTextView = (TextView) view.findViewById(R.id.notes_view);
		notesTextView.setText(time.getNotes());
		return view;

	}
	

	

}
