package iWish_Activity;
/** Raffaella  - Miki*/

import iWish_Activities.Activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

public class ChallengeActivity extends Activity{
	private ImageButton ButtonVsMyself;
	private ImageButton ButtonVsFriends;
	private ImageButton ButtonDone;
	private boolean daSolo = true;
	private String dataStart;
	
	private Activities mActivities;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.challenge);
		setUpViews();

		ButtonDone.setTag(false);
		
		Intent intent = getIntent();
		mActivities = (Activities) intent.getSerializableExtra("a");
		dataStart = (String) intent.getSerializableExtra("starting");

		ButtonVsMyself.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ButtonVsMyself.setImageResource(R.drawable.vs_myself2); 
				ButtonVsFriends.setImageResource(R.drawable.vs_friends); 
				ButtonDone.setImageResource(R.drawable.botton_done2); 
				ButtonDone.setTag(true);
				daSolo = true;
			}    
		});
		ButtonVsFriends.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ButtonVsFriends.setImageResource(R.drawable.vs_friends2); 
				ButtonVsMyself.setImageResource(R.drawable.vs_myself); 
				ButtonDone.setImageResource(R.drawable.botton_done2); 
				ButtonDone.setTag(true);
				daSolo = false;
			}    
		}); 
		//mActivities = new Activities();
		//mActivities.setKmObbiettivo(7); //momentaneo per provare l'activity successiva
		//mActivities.setTipoAttivita("ride"); //momentaneo per provare l'activity successiva
		ButtonDone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (ButtonDone.getTag()==(Boolean)true){
					Intent intent2;
					if(daSolo == true){
						intent2 = new Intent(ChallengeActivity.this, NewWishActivity.class);
						intent2.putExtra("a", mActivities);
						intent2.putExtra("Activity", "ChallengeActivity");
						intent2.putExtra("starting", dataStart);
						startActivity(intent2);
					}
					else{
						intent2 = new Intent(ChallengeActivity.this, Friends2Activity.class);
						intent2.putExtra("a", mActivities);
						intent2.putExtra("Activity", "ChallengeActivity");
						intent2.putExtra("starting", dataStart);
						startActivity(intent2);
					}
				}  
			}
		}); 
	}
	private void setUpViews() {
		ButtonVsMyself = (ImageButton)findViewById(R.id.vs_myself);
		ButtonVsFriends = (ImageButton)findViewById(R.id.vs_friends);
		ButtonDone = (ImageButton)findViewById(R.id.done);
	}
}

