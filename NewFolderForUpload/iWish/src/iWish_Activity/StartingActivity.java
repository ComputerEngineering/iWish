package iWish_Activity;
/**Michela*/

import iWish_Activities.Activities;

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class StartingActivity extends Activity{
	private Activities mActivities;
	private Intent intent;
	private String dataStart;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.starting);
		intent = getIntent();
		mActivities = (Activities) intent.getSerializableExtra("a");

		final ImageButton ButtonReady = (ImageButton)findViewById(R.id.ready);
		final ImageButton ButtonOtherDay = (ImageButton)findViewById(R.id.other_day);
		final ImageButton ButtonDone = (ImageButton)findViewById(R.id.done);

		ButtonDone.setTag(false);

		ButtonReady.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ButtonReady.setImageResource(R.drawable.ready2); 
				ButtonOtherDay.setImageResource(R.drawable.other_day); 
				ButtonDone.setImageResource(R.drawable.botton_done2); 
				ButtonDone.setTag(true);
				dataStart = "ready";
			}    
		}); 
		ButtonOtherDay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ButtonOtherDay.setImageResource(R.drawable.other_day2); 
				ButtonReady.setImageResource(R.drawable.ready); 
				ButtonDone.setImageResource(R.drawable.botton_done2); 
				ButtonDone.setTag(true);
				dataStart = "otherDay";
			}    
		}); 
		ButtonDone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (ButtonDone.getTag()==(Boolean)true){
					Intent intent2 = new Intent(StartingActivity.this, ChallengeActivity.class);
					intent2.putExtra("a", mActivities);//aggiungiamo questa nuova informazione nel nostro intent
					intent2.putExtra("starting", dataStart);
					startActivity(intent2);//facciamo partire l'intent ChallengeActivity
				}  

			}
		}); 
	}
}

