/*
   Copyright 2012 Harri Smatt

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package fi.harism.curl;

import java.io.IOException;
import java.util.ArrayList;

import com.example.imagecurldemo.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * Simple Activity for curl testing.
 * 
 * @author harism
 */
public class CurlActivity extends Activity {

	private CurlView mCurlView;
	ArrayList<String> mImage;
	TextView mCurrPageTextView;
	public Handler mHandle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			mCurrPageTextView.setText(msg.getData().getString("numPage"));
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_view);
		mCurrPageTextView = (TextView) findViewById(R.id.currentPage);

		int index = 0;
		if (getLastNonConfigurationInstance() != null) {
			index = (Integer) getLastNonConfigurationInstance();
		}

		mImage = new ArrayList<String>();
		Intent i = getIntent();
		mImage = i.getStringArrayListExtra("images");

		mCurlView = (CurlView) findViewById(R.id.curl);
		mCurlView.setPageProvider(new PageProvider(mImage));
		mCurlView.setSizeChangedObserver(new SizeChangedObserver());
		mCurlView.setCurrentIndex(index);
		mCurlView.setBackgroundColor(0xFF202830);

		// This is something somewhat experimental. Before uncommenting next
		// line, please see method comments in CurlView.
		// mCurlView.setEnableTouchPressure(true);
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
		private ArrayList<String> mBitmapList;

		public PageProvider(ArrayList<String> images) {
			// TODO Auto-generated constructor stub
			mBitmapList = images;
		}

		@Override
		public int getPageCount() {
			return mBitmapList.size();
		}

		private Bitmap loadBitmap(int width, int height, int index) {
			Bitmap b = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			b.eraseColor(0xFFFFFFFF);
			Canvas c = new Canvas(b);
			Drawable d;
			try {
				d = Drawable.createFromStream(
						getAssets().open(mBitmapList.get(index)), null);

				/* getResources().getDrawable(mBitmapList[index]); */

				int margin = 7;
				int border = 3;
				Rect r = new Rect(margin, margin, width - margin, height
						- margin);

				int imageWidth = r.width() - (border * 2);
				int imageHeight = imageWidth * d.getIntrinsicHeight()
						/ d.getIntrinsicWidth();
				if (imageHeight > r.height() - (border * 2)) {
					imageHeight = r.height() - (border * 2);
					imageWidth = imageHeight * d.getIntrinsicWidth()
							/ d.getIntrinsicHeight();
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

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return b;
		}

		// sua lai method nay
		@Override
		public void updatePage(CurlPage page, int width, int height, int index) {

			if (index == 0) {
				Bitmap front = loadBitmap(width, height, index);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK);
				// setNumberPageView("");
			} else if (index == getPageCount() - 1) {
				Bitmap front = loadBitmap(width, height, index);
				page.setTexture(front, CurlPage.SIDE_BOTH);
				page.setColor(Color.argb(127, 255, 255, 255),
						CurlPage.SIDE_BACK);
				// setNumberPageView("Page "+ index + " / " + (getPageCount() -
				// 1));
			} else if (index < (getPageCount() - 1) && index > 0) {
				Bitmap front = loadBitmap(width, height, index);
				Bitmap back = loadBitmap(width, height, index);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setTexture(back, CurlPage.SIDE_BACK);
				page.setColor(Color.argb(127, 255, 255, 255),
						CurlPage.SIDE_BACK);
				
			}

			/*switch (index) { // First case is image on front side, solid colored
								// back.
			case 0: {
				Bitmap front = loadBitmap(width, height, 0);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK);
				break;
			} // Second case is image on back side, solid coloredfront.
			case 1: {
				Bitmap back = loadBitmap(width, height, 2);
				page.setTexture(back, CurlPage.SIDE_BACK);
				page.setColor(Color.rgb(127, 140, 180), CurlPage.SIDE_FRONT);
				break;
			} // Third case is images on both sides.
			case 2: {
				Bitmap front = loadBitmap(width, height, 1);
				Bitmap back = loadBitmap(width, height, 3);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setTexture(back, CurlPage.SIDE_BACK);
				break;
			} // Fourth case is images on both sides - plus they are blend
				// against // separate colors.
			case 3: {
				Bitmap front = loadBitmap(width, height, 2);
				Bitmap back = loadBitmap(width, height, 1);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setTexture(back, CurlPage.SIDE_BACK);
				page.setColor(Color.argb(127, 170, 130, 255),
						CurlPage.SIDE_FRONT);
				page.setColor(Color.rgb(255, 190, 150), CurlPage.SIDE_BACK);
				break;
			}
			// Fifth case is same image is assigned to front and back. In this
				// // scenario only onetexture is used and shared for both
				// sides.
			case 4:
				Bitmap front = loadBitmap(width, height, 0);
				page.setTexture(front, CurlPage.SIDE_BOTH);
				page.setColor(Color.argb(127, 255, 255, 255),
						CurlPage.SIDE_BACK);
				break;
			}
*/
		}

		@Override
		public void setCurrentPageView(int currentPage) {
			// TODO Auto-generated method stub
			int totalPage = getPageCount() - 1;
			if (currentPage > 0 && currentPage <= totalPage)
				setNumberPageView("Page " + currentPage + " / " + totalPage);
			else
				setNumberPageView("");
		}
	}

	public void setNumberPageView(final String pageString) {
		// TODO Auto-generated method stub
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = Message.obtain();
				Bundle b = new Bundle();
				b.putString("numPage", pageString);
				msg.setData(b);
				mHandle.sendMessage(msg);
			}
		});
		t.start();
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