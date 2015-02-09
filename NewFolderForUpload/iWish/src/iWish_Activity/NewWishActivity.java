package iWish_Activity;
/**Alessandro*/

import iWish_Activities.Activities;
import iWish_Control.ControlActivities;
import iWish_Control.ControlConnection;
import iWish_Control.ControlUser;
import iWish_Utente.UserIstance;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.progect.iwish.R;

public class NewWishActivity extends Activity{
	private ImageButton ok;
	private ImageView ImageOminoAttivita;
	private TextView TextKmAttivita;
	private TextView TextNomeFriend;
	private TextView TextScrittaPunti;
	private TextView TextPunti;
	private TextView TextScrittaWinner;
	private TextView TextWinner;
	private TextView TextScrittaLoser;
	private TextView TextLoser;
	private CircularImageView CircularImmagineFriend;
	private String dataStart;
	private int Km =0;
	private Intent intent2;
	
	private long tempo = 0; 
	private int timeStart = 1414752545;//data 31/10/14
	private int timeEnd = 1414925345;//data 2/11/14
	
	private Activities mActivities;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_wish);
		setUpViews();
		Intent intent = getIntent();
		mActivities = (Activities) intent.getSerializableExtra("a");
		mActivities.setEmailChallenger(UserIstance.getIstanceUserIstance().getEmailUser());
		dataStart = (String) intent.getSerializableExtra("starting");
		if(dataStart.equals("otherDay")){
			ok.setImageResource(R.drawable.botton_done2);
		}
		Km = mActivities.getKmObbiettivo();
		setTime();
		try {
			Log.i("NewWishActivity", "PRIMA DI INSERIRE NEL DB ACTIVITIES");
			ControlActivities.getIstanceControlActivities().saveOnDbActivities(mActivities, getApplicationContext());
			Log.i("NewWishActivity", "DOPO L'INSERIMENTO NEL DB ACTIVITIES");
			ControlConnection.getIstanceControlConnection().onInsertActivities();
			Log.i("NewWishActivity", "DOPO L'INSERIMENTO online");
		} catch (Exception e) {
			//Toast.makeText(c ,"errore di salvataggio", Toast.LENGTH_LONG).show();
			Log.i("WeightActivity", "errore INSERIMENTO NEL DB ACTIVITIES");
			e.printStackTrace();
		}
		Log.i("WeightActivity", "SALVATAGGIO SUL DB ANDATO A BUON FINE");

		TextKmAttivita.setText("Running " + Km + "Km");
		if(mActivities.getTipoAttivita().equals("run")){
			ImageOminoAttivita.setImageResource(R.drawable.omino_run);
		}
		if(mActivities.getTipoAttivita().equals("ride")){
			ImageOminoAttivita.setImageResource(R.drawable.omino_ride);
		}
		if(mActivities.getTipoAttivita().equals("gym")){
			ImageOminoAttivita.setImageResource(R.drawable.omino_avatar_attivo);
			TextKmAttivita.setText("Weights Gym");
		}

		if(intent.getSerializableExtra("Activity").equals("ChallengeActivity")){
			CircularImmagineFriend.setVisibility(View.INVISIBLE);
			TextNomeFriend.setVisibility(View.INVISIBLE);
			TextScrittaPunti.setVisibility(View.INVISIBLE);
			TextPunti.setVisibility(View.INVISIBLE);
			TextScrittaWinner.setVisibility(View.INVISIBLE);
			TextWinner.setVisibility(View.INVISIBLE);
			TextScrittaLoser.setVisibility(View.INVISIBLE);
			TextLoser.setVisibility(View.INVISIBLE);
		}  

		
		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(dataStart.equals("otherDay")){
					startActivity(new Intent(NewWishActivity.this, ProfileActivity.class ));
				}
				else{
					intent2 = new Intent(NewWishActivity.this, ProgressActivity.class );
					intent2.putExtra("a", mActivities);
					startActivity(intent2);
				}
			}
		});  
	}

	private void setUpViews(){
		ImageOminoAttivita = (ImageView) findViewById(R.id.omino_attivita);
		TextKmAttivita = (TextView) findViewById(R.id.KmAttivita);
		TextNomeFriend = (TextView) findViewById(R.id.nomeFriend);
		TextScrittaPunti = (TextView) findViewById(R.id.scrittaPunti);
		TextPunti = (TextView) findViewById(R.id.punti);
		TextScrittaWinner = (TextView) findViewById(R.id.scrittaWinner);
		TextWinner = (TextView) findViewById(R.id.winner);
		TextScrittaLoser = (TextView) findViewById(R.id.scrittaLoser);
		TextLoser = (TextView) findViewById(R.id.loser);
		CircularImmagineFriend = (CircularImageView) findViewById(R.id.ImmagineFriend);
		ok = (ImageButton)findViewById(R.id.m_letsgo);
	}

	private void setTime(){
		//Scrivo sul nuovo standard output:
		// Tempo:
		tempo = System.currentTimeMillis();
		//System.out.println("Tempo in millisecondi: "+tempo);

		timeStart=(int) (tempo/1000); 
		mActivities.setStartDate(timeStart);
		String tipoUser = UserIstance.getIstanceUserIstance().getTypeUser();
		if(tipoUser.equals("active")){
			if(mActivities.getKmObbiettivo() == 5){
				timeEnd = timeStart + 86400;
				mActivities.setEndDate(timeEnd);//2gg
			}
			else{
				timeEnd = timeStart + 172800; //4gg
				mActivities.setEndDate(timeEnd);
			}
		}
		else{
			if(mActivities.getKmObbiettivo() == 5){
				timeEnd = timeStart + 259200; //6gg
				mActivities.setEndDate(timeEnd);
			}
			else{
				timeEnd = timeStart + 518400; //12gg
				mActivities.setEndDate(timeEnd);
			}
		}
		//System.out.println("Tempo in secondi: "+t1);

		//long sec = t1%60; 
		//long t3  = t1/60;
		//long min = t3%60;
		//long t4  = t3/60;
		//System.out.println("Tempo in ore h"+t4+" m"+min+" s"+sec);

		//System.out.println("n» il tempo passato dal 1/1/1970 ad oran");
	}
}
