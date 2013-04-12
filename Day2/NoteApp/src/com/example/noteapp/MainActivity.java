package com.example.noteapp;


import com.example.controllers.NoteAdapter;
import com.example.models.Note;
import com.example.models.NoteModel;
import com.example.views.NoteItemView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView mNoteListView = null;
	private NoteAdapter mNoteAdapter = new NoteAdapter();
	private NoteModel mNoteModel = new NoteModel();
	final Context context = this;
	private ImageView ImageViewButton;
	private String result1, result2;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageViewButton = (ImageView) findViewById(R.id.add_note_image);
        
		ImageViewButton.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            showDialog(0);

	        }
	    });
	
		initUI();
	}
	
	//Dialog for Create Note
	protected Dialog onCreateDialog(int id) {
    
	    final AlertDialog.Builder alert = new AlertDialog.Builder(this);    
	    	    
	    LinearLayout linear = new LinearLayout(this);
	    linear.setOrientation(1); 
	    final EditText input1 = new EditText(this); 
	    final EditText input2 = new EditText(this);
	    
	    alert.setMessage("Enter Name & Content");
	    linear.addView(input1);
	    linear.addView(input2);
	    alert.setView(linear);
	
	    alert.setIcon(R.drawable.pic4);
	    alert.setTitle("Create New Note");
	    
	    
	    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {    
	    	//@Override
	    	public void onClick(DialogInterface dialog, int whichButton) {              
	    		String value = input1.getText().toString().trim();                       
	    		Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
	        }
	    });                 
	    
	    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {                           
	        public void onClick(DialogInterface dialog, int whichButton) {          
	            dialog.cancel();
	        }
	    });         
	    return alert.create();      
    } 
    	
	protected void initUI() {
		mNoteListView = (ListView) findViewById(R.id.note_view);
				
		//Test add note
		mNoteAdapter.setContext(this);
		
					
						String name = String.format("NAME: Note %d", result1);
						Note note = new Note(result1, result2, -1);
						
						// Add Note in of the model
						mNoteModel.add(note);
						//Notify the adapter changing data
						mNoteAdapter.notifyDataSetChanged();
						
						String text = String.format("Adding %s ...", note.getName());
						Toast toast = Toast.makeText(getApplicationContext(), text, text.length());
						toast.show();
					
			
				
		// Set data for the adapter
		mNoteAdapter.setContext(this);
		mNoteAdapter.setOnNoteDeleteListener(new NoteItemView.OnNoteDeleteListener() {
			
			@Override
			public void OnDeleteNoteClicked(Note note) {
				// TODO Auto-generated method stub
				if (note == null)
					return;
				
				// Delete Note out of the model
				mNoteModel.remove(note);
				// Notify the adapter changing data
				mNoteAdapter.notifyDataSetChanged();
				
				String text = String.format("Deleting %s ...", note.getName());
				Toast toast = Toast.makeText(getApplicationContext(), text, text.length());
				toast.show();
			}
		}); 
			
		
		mNoteListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// Get Note at the clicked position
				Note note = (Note) mNoteAdapter.getItem(arg2);
				if (note == null)
					return;
				
				String text = String.format("Showing Content of the Note %s ...", note.getContent());
				Toast toast = Toast.makeText(getApplicationContext(), text, text.length());
				toast.show();
			}
		});
		
		mNoteAdapter.setNoteModel(mNoteModel);
		
		// Set adapter for the list view
		mNoteListView.setAdapter(mNoteAdapter);
	}
}
