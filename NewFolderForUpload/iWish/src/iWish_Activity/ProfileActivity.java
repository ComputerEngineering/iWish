package iWish_Activity;
/**Raffaella**/

//import iWish_Control.ControlActivities;
//import iWish_Control.ControlConnection;
import java.util.List;

import iWish_Control.ControlActivities;
import iWish_Control.ControlConnection;
import iWish_Control.ControlSession;
import iWish_Session.Session;
import iWish_Utente.UserIstance;
import iWish_Utente.Utente;

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileActivity extends Activity{
	//private ImageButton bt_option;
	//private ImageButton bt_allarme;
	private ImageButton bt_calendar;
	private ImageButton bt_wishlist;
	private ImageButton bt_statics;
	private ImageButton bt_friends;
	private ImageButton bt_make_new_wish_profile;
	private TextView tv_wishes;
	private TextView tv_points;
	private TextView tv_level;
	private TextView tv_userName;
	private Utente mUser;
	//private UserIstance mUserIstance;
	private Intent intent;
	private int nWish =-1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		setUpViews();
		intent = getIntent();
		mUser = (Utente) intent.getSerializableExtra("u");

		if(UserIstance.getIstanceUserIstance().getEmailUser()==null && mUser != null){
			setIstanceSession();
			nWish = ControlActivities.getIstanceControlActivities().getOnDbHowManyActvitiesUser(
					UserIstance.getIstanceUserIstance().getEmailUser(), getApplicationContext());
			if(nWish>0){
				returnActivityAndSessionOnLine();
			}
		}

		tv_points.setText(Integer.toString(UserIstance.getIstanceUserIstance().getPoint()));
		tv_level.setText(Integer.toString(UserIstance.getIstanceUserIstance().getPoint()/100));
		if(nWish!=-1){
			tv_wishes.setText(
					Integer.toString(nWish));
		}
		else{
			tv_wishes.setText(
					Integer.toString(ControlActivities.getIstanceControlActivities().getOnDbHowManyActvitiesUser(
							UserIstance.getIstanceUserIstance().getEmailUser(), getApplicationContext())));
		}
		//utente fittizzio
		/*
		UserIstance.getIstanceUserIstance().setTypeUser("active");
		UserIstance.getIstanceUserIstance().setEmailUser("alexxsera@hotmail.com");
		UserIstance.getIstanceUserIstance().setName("Alessandro");
		UserIstance.getIstanceUserIstance().setCity("Velletri");
		UserIstance.getIstanceUserIstance().setSex("m");
		UserIstance.getIstanceUserIstance().setAnswer("Matrix");
		UserIstance.getIstanceUserIstance().setHeight(173);
		UserIstance.getIstanceUserIstance().setBirthday("19021986");
		UserIstance.getIstanceUserIstance().setSurname("Serafini");
		UserIstance.getIstanceUserIstance().setPassword("provain");
		UserIstance.getIstanceUserIstance().setQuestion("Favorite Movies");
		//fine utente fittizzio
		 */
		tv_userName.setText(UserIstance.getIstanceUserIstance().getName());

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

	public void onBackPressed() {
		// your code.
		//Intent intent3 = new Intent(ProgressActivity.this, ProfileActivity.class );
		//eventuale uso adesso non serve
		//intent3.putExtra("a", mActivities);
		//startActivity(intent3);
		//finish();
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
	}

	private void setUpViews() {
		bt_statics = (ImageButton)findViewById(R.id.bot_statistics);
		bt_friends = (ImageButton)findViewById(R.id.bot_friends);
		bt_wishlist = (ImageButton)findViewById(R.id.bot_wishlist);
		bt_calendar= (ImageButton)findViewById(R.id.bot_calendar);
		//bt_option = (ImageButton)findViewById(R.id.bot_ruota);
		//bt_allarme= (ImageButton)findViewById(R.id.bot_campana);
		bt_make_new_wish_profile = (ImageButton)findViewById(R.id.imageUser_profile);
		tv_userName = (TextView)findViewById(R.id.textView_name);
		tv_wishes = (TextView)findViewById(R.id.textView_wishes_profile);
		tv_points = (TextView)findViewById(R.id.textView_points_profile);
		tv_level = (TextView)findViewById(R.id.textView_level_profile);
	}

	//mantiene i dati(un'istanza della classe) dell'utente per accedervi da altre activity
	private void setIstanceSession(){
		UserIstance.getIstanceUserIstance().setTypeUser(mUser.getTypeUser());
		UserIstance.getIstanceUserIstance().setEmailUser(mUser.getEmail());
		UserIstance.getIstanceUserIstance().setName(mUser.getName());
		UserIstance.getIstanceUserIstance().setCity(mUser.getCity());
		UserIstance.getIstanceUserIstance().setSex(mUser.getSex());
		UserIstance.getIstanceUserIstance().setAnswer(mUser.getAnswer());
		UserIstance.getIstanceUserIstance().setHeight(mUser.getHeight());
		UserIstance.getIstanceUserIstance().setBirthday(mUser.getBirthday());
		UserIstance.getIstanceUserIstance().setSurname(mUser.getSurname());
		UserIstance.getIstanceUserIstance().setPassword(mUser.getPassword());
		UserIstance.getIstanceUserIstance().setQuestion(mUser.getQuestion());
	}

	private void returnActivityAndSessionOnLine(){
		if(isOnLine()){
			//reinvio ultima activity
			try {
				ControlConnection.getIstanceControlConnection().onInsertActivities();
				Log.i("ProfileActivity", "DOPO L'INSERIMENTO online");
			} catch (Exception e) {
				Log.i("ProfileActivity", "errore INSERIMENTO NEL DB ACTIVITIES");
				e.printStackTrace();
			}
			Log.i("ProfileActivity", "SALVATAGGIO SUL DB ANDATO A BUON FINE");

			//reinvio ultima session
			try {
				List<Session> mSession = ControlSession.getIstanceControlSession().getOnDbAllSession(getApplicationContext());
				if(mSession!=null){
					Session aSession = mSession.get(mSession.size()-1);
					String battiti = aSession.getBattiti();
					ControlConnection.getIstanceControlConnection().onInsertSession(battiti);
					Log.i("ProfileActivity", "DOPO L'INSERIMENTO online");
				}
			} catch (Exception e) {
				Log.i("ProfileActivity", "errore INSERIMENTO NEL DB SESSION");
				e.printStackTrace();
			}
			Log.i("ProfileActivity", "SALVATAGGIO SUL DB ANDATO A BUON FINE");
		}
	}
	private boolean isOnLine() {  
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);  
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();  
		return activeNetworkInfo != null;  
	}
}

