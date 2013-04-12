package com.techmaster.listphonebook;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView lvPhone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPhone = (ListView)findViewById(R.id.ListView01);
        lvPhone.setClickable(true);
        
        final List<PhoneBook> listPhoneBook = new ArrayList<PhoneBook>();
        
        listPhoneBook.add(new PhoneBook(BitmapFactory.decodeResource(getResources(), 
        R.drawable.ic_launcher),"ABC", "0947-896-668", "Umba_a@gmail.com"));
        
        listPhoneBook.add(new PhoneBook(BitmapFactory.decodeResource(getResources(), 
        R.drawable.ic_launcher),"DEF", "0986-686-868", "Eln@gmail.com"));
        
        listPhoneBook.add(new PhoneBook(BitmapFactory.decodeResource(getResources(), 
        R.drawable.ic_launcher),"GHI", "0902-001-002", "ThanhThuy@gmail.com"));
        
        listPhoneBook.add(new PhoneBook(BitmapFactory.decodeResource(getResources(), 
        R.drawable.ic_launcher),"KLM", "0986-999-999", "ThuHuong9x@gmail.com"));
        
        listPhoneBook.add(new PhoneBook(BitmapFactory.decodeResource(getResources(), 
        R.drawable.ic_launcher),"NOP", "0986-006-228", "hotx@gmail.com"));
        
        /*listPhoneBook.add(new PhoneBook(BitmapFactory.decodeResource(getResources(), 
        R.drawable.ic_launcher),"QRS", "0986-677-028", "bagbay@gmail.com"));
        ///////
        listPhoneBook.add(new PhoneBook(BitmapFactory.decodeResource(getResources(), 
        R.drawable.ic_launcher),"ABC", "0947-896-668", "Umbana@gmail.com"));
        
        listPhoneBook.add(new PhoneBook(BitmapFactory.decodeResource(getResources(), 
        R.drawable.ic_launcher),"DEF", "0986-686-868", "ran@gmail.com"));
        
        listPhoneBook.add(new PhoneBook(BitmapFactory.decodeResource(getResources(), 
        R.drawable.ic_launcher),"GHI", "0902-001-002", "Thuy@gmail.com"));
        
        listPhoneBook.add(new PhoneBook(BitmapFactory.decodeResource(getResources(), 
        R.drawable.ic_launcher),"KLM", "0986-999-999", "ThuHuong9x@gmail.com"));
        
        listPhoneBook.add(new PhoneBook(BitmapFactory.decodeResource(getResources(), 
        R.drawable.ic_launcher),"NOP", "0986-006-228", "hox@gmail.com"));
        
        listPhoneBook.add(new PhoneBook(BitmapFactory.decodeResource(getResources(), 
        R.drawable.ic_launcher),"QRS", "0986-677-028", "aybay@gmail.com"));*/
        
        PhoneBookAdapter adapter = new PhoneBookAdapter(this, listPhoneBook);
        lvPhone.setAdapter(adapter);

        lvPhone.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//Se lam mot viec j do khi click vao row
                               Toast toast = Toast.makeText(getApplicationContext(), "click row: " + position,Toast.LENGTH_SHORT);
                               toast.show();
        			}
        	});
    }
}
