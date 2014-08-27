package iWish_Activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import com.progect.iwish.R;

/** Raffaella*/

public class FrinedsActivity extends ListActivity{
	private ImageButton bt_search;
	private ImageButton bt_cancel;
	private ImageButton bt_sortByName;
	private ImageButton bt_sortByLatest;
	private ImageButton bt_sortByPoints;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friends);
	}

}
