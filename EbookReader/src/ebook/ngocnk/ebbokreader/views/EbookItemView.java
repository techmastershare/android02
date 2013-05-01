package ebook.ngocnk.ebbokreader.views;

import ebook.ngocnk.ebookreader.R;
import ebook.ngocnk.ebookreader.models.Ebook;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EbookItemView extends LinearLayout {

	private ImageView mImage;
	private TextView mTextView;
	private Ebook mItem;
	
	public EbookItemView(Context context) {
		super(context);
		initUI();
	}

	protected void initUI() {
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.item_view_ebook, this);
		mImage = (ImageView) findViewById(R.id.thumble);
		mTextView = (TextView) findViewById(R.id.name_ebook);
	}

	public void setItemReader(Ebook item) {
		mItem = item;
	}

	public void update() {
		Bitmap bm = BitmapFactory.decodeStream(mItem.getImagePath());
		mImage.setImageBitmap(bm);
		mTextView.setText(mItem.getReaderName());
	}

}
