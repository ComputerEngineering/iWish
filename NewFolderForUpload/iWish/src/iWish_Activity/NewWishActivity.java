package iWish_Activity;
/**Alessandro*/

import iWish_Activities.Activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.progect.iwish.R;

public class NewWishActivity extends Activity{
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
	
	private Activities mActivities;
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.new_wish);
	    setUpViews();
	    
	    Intent intent = getIntent();
	    mActivities = (Activities) intent.getSerializableExtra("a");
	    TextKmAttivita.setText("Running "+mActivities.getKmObbiettivo()+"Km");
	    if(mActivities.getTipoAttivita().equals("run")){
	    	ImageOminoAttivita.setImageResource(R.drawable.omino_run);
	    }
	    if(mActivities.getTipoAttivita().equals("ride")){
	    	ImageOminoAttivita.setImageResource(R.drawable.omino_ride);
	    }
	    if(mActivities.getTipoAttivita().equals("Gym")){
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
	    
		ImageButton ok = (ImageButton)findViewById(R.id.m_letsgo);
		ok.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(NewWishActivity.this, ProgressActivity.class ));
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
				
	}
}
