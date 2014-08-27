package iWish_Activity;
//Raffaella

import com.progect.iwish.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileActivity extends Activity{
	private ImageButton bt_option;
	private ImageButton bt_allarme;
	private ImageButton bt_calendar;
	private ImageButton bt_wishlist;
	private ImageButton bt_statics;
	private ImageButton bt_friends;
	private ImageButton bt_make_new_wish_profile;
	private TextView tv_wishes;
	private TextView tv_points;
	private TextView tv_level;
	private TextView tv_userName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		bt_statics = (ImageButton)findViewById(R.id.imageButton1);
		bt_friends = (ImageButton)findViewById(R.id.imageButton2);
		bt_wishlist = (ImageButton)findViewById(R.id.imageButton3);
		bt_calendar= (ImageButton)findViewById(R.id.imageButton4);
		bt_option = (ImageButton)findViewById(R.id.imageButton5);
		bt_allarme= (ImageButton)findViewById(R.id.imageButton6);
		bt_make_new_wish_profile = (ImageButton)findViewById(R.id.imageUser_profile);
		tv_userName = (TextView)findViewById(R.id.textView_name);
		tv_wishes = (TextView)findViewById(R.id.textView_wishes_profile);
		tv_points = (TextView)findViewById(R.id.textView_points_profile);
		tv_level = (TextView)findViewById(R.id.textView_level_profile);
	}
}
