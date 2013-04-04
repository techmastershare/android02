package com.mrloveqd.day2;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ImageView iv_avatar, iv_desc, iv_pre, iv_next;
	boolean flag = true;
	int leng = 0;
	MediaPlayer player;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		iv_avatar = (ImageView) findViewById(R.id.iv_avatar);
		iv_desc = (ImageView) findViewById(R.id.iv_desc);
		iv_pre = (ImageView) findViewById(R.id.iv_pre);
		iv_next = (ImageView) findViewById(R.id.iv_next);

		player = new MediaPlayer();
		final Integer[] avatarId = new Integer[] { R.drawable.ic_doctor, R.drawable.ic_fire_man, R.drawable.ic_worker };
		final Integer[] descId = new Integer[] { R.drawable.ic_ambulance, R.drawable.ic_fire_engine, R.drawable.ic_work };
		final Integer[] soundId = new Integer[]{ R.drawable.ambulance, R.drawable.fire_engine, R.drawable.work};

//		init default image
		iv_avatar.setImageResource(avatarId[leng]);
		iv_desc.setImageResource(descId[leng]);
		
//		 event clicked Next
		iv_next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (leng >= avatarId.length - 1) {
					toastMessage("You can not watch more because this is the last photo!");
				} else {
					player.stop();
					flag = true;
					leng++;
					iv_avatar.setImageResource(avatarId[leng]);
					iv_desc.setImageResource(descId[leng]);
					
				}
			}
		});

//		 event clicked Previous
		iv_pre.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (leng >= 1) {
					player.stop();
					flag = true;
					leng--;
					iv_avatar.setImageResource(avatarId[leng]);
					iv_desc.setImageResource(descId[leng]);
				} else {
					toastMessage("You can not watch more because this is the first photo!");
				}
			}
		});
		
//		event touch image description
		iv_desc.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(flag == true){
					player = MediaPlayer.create(getApplicationContext(), soundId[leng]);
					player.start();
					flag = false;
				}else{
					player.stop();
					flag = true;
				}
			}
		});

	}

	protected void toastMessage(String text) {
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		player.stop();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
