package com.anhvu.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.example.imagecurldemo.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView mListReader;
	private static final String TAG = "ImageCurlDemo";
	ArrayList<ItemReader> mFolderName;
	AssetManager mAssetMgr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);
		initUI();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		mListReader = (ListView) findViewById(R.id.lvReader);
		mFolderName = new ArrayList<ItemReader>();
		mAssetMgr = getAssets();
		InputStream path;
		try {
			String[] folderName = mAssetMgr.list(Common.ASSET_DIR_NAME);

			for (String asset : folderName) {
			//	Log.i(TAG, asset + "/thumb.png");
				/*if (asset.equals("imgReader")) {
					path = mAssetMgr.open(asset + "/thumb.png");
					mFolderName.add(new ItemReader("Image Reader", path));					
				} else if (asset.equals("docReader")) {
					path = mAssetMgr.open(asset + "/thumb.png");
					mFolderName.add(new ItemReader("Document Reader", path));					
				} else if (asset.equals("pdfReader")) {
					path = mAssetMgr.open(asset + "/thumb.png");
					mFolderName.add(new ItemReader("PDF Reader", path));				
				}*/
				path = mAssetMgr.open(Common.ASSET_DIR_PATH + asset + "/thumb.png");
				mFolderName.add(new ItemReader(asset, path));
			}
			
			mListReader.setAdapter(new ReaderAdapter(this, mFolderName));
			mListReader.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> a, View v, int position, long id) {
					// TODO Auto-generated method stub
					String readerName = mFolderName.get(position).getReaderName();					
						Intent intent = new Intent("COM.ANHVU.IMAGECURL");
						try {
							String[] imageArray = mAssetMgr.list(Common.ASSET_DIR_PATH  + readerName);
							ArrayList<String> imageList = new ArrayList<String>();
							for(int i = 0; i < imageArray.length; i++){
								if(!imageArray[i].equals("thumb.png")){
									//Log.i(TAG, imageArray[i]);
									imageList.add(Common.ASSET_DIR_PATH  + readerName + "/"  + imageArray[i]);
								}
							}
							
							intent.putStringArrayListExtra("images", imageList);
							startActivity(intent);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					

				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
