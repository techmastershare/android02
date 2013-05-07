package com.example.ebookreader.activities;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;

import com.example.ebookreader.R;
import com.example.ebookreader.common.Common;
import com.example.ebookreader.view.CurlPage;
import com.example.ebookreader.view.CurlView;

public class ImageReaderActivity extends Activity {

	private CurlView mCurlView;
	AssetManager mAssetMgr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_reader);

		int index = 0;
		if (getLastNonConfigurationInstance() != null) {
			index = (Integer) getLastNonConfigurationInstance();
		}

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String storyName = bundle.getString(Common.STORY_NAME);
			mAssetMgr = getAssets();
			try {
				String[] imageList = mAssetMgr.list(storyName);
				if (imageList != null) {
					for (int i = 0; i < imageList.length; i++) {
						imageList[i] = storyName + "/" + imageList[i];
					}
					mCurlView = (CurlView) findViewById(R.id.curl);
					PageProvider provider = new PageProvider();
					provider.setImageNames(imageList);

					mCurlView.setPageProvider(provider);
					mCurlView.setSizeChangedObserver(new SizeChangedObserver());
					mCurlView.setCurrentIndex(index);
					mCurlView.setBackgroundColor(0xFFC0C0C0);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onPause() {
		super.onPause();
		mCurlView.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		mCurlView.onResume();
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		return mCurlView.getCurrentIndex();
	}

	/**
	 * Bitmap provider.
	 */
	private class PageProvider implements CurlView.PageProvider {

		// Bitmap resources.
		// private int[] mBitmapIds = { R.drawable.obama, R.drawable.road_rage,
		// R.drawable.taipei_101, R.drawable.world };

		private String[] mImageNames;

		public void setImageNames(String[] paths) {
			mImageNames = paths;
		}

		@Override
		public int getPageCount() {
			if (mImageNames == null)
				return 0;

			return mImageNames.length;
		}

		private Bitmap loadBitmap(int width, int height, int index) {
			Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			b.eraseColor(0xFFFFFFFF);
			Canvas c = new Canvas(b);
			Drawable d = null;
			try {
				d = Drawable.createFromStream(mAssetMgr.open(mImageNames[index]), null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int margin = 7;
			int border = 3;
			Rect r = new Rect(margin, margin, width - margin, height - margin);

			int imageWidth = r.width() - (border * 2);
			int imageHeight = imageWidth * d.getIntrinsicHeight() / d.getIntrinsicWidth();
			if (imageHeight > r.height() - (border * 2)) {
				imageHeight = r.height() - (border * 2);
				imageWidth = imageHeight * d.getIntrinsicWidth() / d.getIntrinsicHeight();
			}

			r.left += ((r.width() - imageWidth) / 2) - border;
			r.right = r.left + imageWidth + border + border;
			r.top += ((r.height() - imageHeight) / 2) - border;
			r.bottom = r.top + imageHeight + border + border;

			Paint p = new Paint();
			p.setColor(0xFFC0C0C0);
			c.drawRect(r, p);
			r.left += border;
			r.right -= border;
			r.top += border;
			r.bottom -= border;

			d.setBounds(r);
			d.draw(c);

			return b;
		}

		@Override
		public void updatePage(CurlPage page, int width, int height, int index) {
			Bitmap front = loadBitmap(width, height, index);
			Bitmap back = loadBitmap(width, height, index);
			page.setBitmap(front, CurlPage.SIDE_FRONT);
			page.setBitmap(back, CurlPage.SIDE_BACK);
		}

	}

	/**
	 * CurlView size changed observer.
	 */
	private class SizeChangedObserver implements CurlView.SizeChangedObserver {
		@Override
		public void onSizeChanged(int w, int h) {
			if (w > h) {
				mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
				mCurlView.setMargins(.1f, .05f, .1f, .05f);
			} else {
				mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
				mCurlView.setMargins(.1f, .1f, .1f, .1f);
			}
		}
	}

}