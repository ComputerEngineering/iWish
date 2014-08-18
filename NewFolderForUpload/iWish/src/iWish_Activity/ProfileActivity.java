package iWish_Activity;
//Raffaella

import com.progect.iwish.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class ProfileActivity extends Activity{
	private ImageButton bt_option;
	private ImageButton bt_allarme;
	private ImageButton bt_calendar;
	private ImageButton bt_wishlist;
	private ImageButton bt_statics;
	private ImageButton bt_friends;
	private Button bt_make_new_wish_profile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
	}

}
