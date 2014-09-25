package iWish_Activity;
//Raffaella

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
		bt_statics = (ImageButton)findViewById(R.id.bot_statistics);
		bt_friends = (ImageButton)findViewById(R.id.bot_friends);
		bt_wishlist = (ImageButton)findViewById(R.id.bot_wishlist);
		bt_calendar= (ImageButton)findViewById(R.id.bot_calendar);
		bt_option = (ImageButton)findViewById(R.id.bot_ruota);
		bt_allarme= (ImageButton)findViewById(R.id.bot_campana);
		bt_make_new_wish_profile = (ImageButton)findViewById(R.id.imageUser_profile);
		tv_userName = (TextView)findViewById(R.id.textView_name);
		tv_wishes = (TextView)findViewById(R.id.textView_wishes_profile);
		tv_points = (TextView)findViewById(R.id.textView_points_profile);
		tv_level = (TextView)findViewById(R.id.textView_level_profile);
		
		
		bt_make_new_wish_profile.setOnClickListener(new View.OnClickListener() {

		      @Override
		      public void onClick(View v) {
		        // request your webservice here. Possible use of AsyncTask and ProgressDialog
		        // show the result here - dialog or Toast
		    	  startActivity(new Intent(ProfileActivity.this,WantToDoActivity.class ));
		      }

		    });	
		
		bt_calendar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Stub di metodo generato automaticamente
				startActivity(new Intent(ProfileActivity.this, CalendarActivity.class ));
			}
		});
		
		bt_wishlist.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Stub di metodo generato automaticamente
				startActivity(new Intent(ProfileActivity.this, WishListActivity.class ));
			}
		});
		
		bt_statics.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Stub di metodo generato automaticamente
				startActivity(new Intent(ProfileActivity.this, StatisticsActivity.class ));
			}
		});

		bt_friends.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Stub di metodo generato automaticamente
				startActivity(new Intent(ProfileActivity.this, Friends2Activity.class ));
			}
		});
		
		
		
	}
}

