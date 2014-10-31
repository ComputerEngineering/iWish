package iWish_Activity;
/**Antonio  --- Miki*/

import iWish_Activities.Activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

public class GoalActivity extends Activity{
	private Activities mActivities;
	private int Km = 0;
	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goal);
		intent = getIntent();
		mActivities = (Activities) intent.getSerializableExtra("a");

		final ImageButton Button5km = (ImageButton)findViewById(R.id.cinque_km);
		final ImageButton Button10km = (ImageButton)findViewById(R.id.dieci_km);
		final ImageButton ButtonDone = (ImageButton)findViewById(R.id.done);

		ButtonDone.setTag(false);

		Button5km.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Button5km.setImageResource(R.drawable.cinque_km2); 
				Button10km.setImageResource(R.drawable.dieci_km); 
				ButtonDone.setImageResource(R.drawable.botton_done2); 
				ButtonDone.setTag(true);
				Km = 5;
			}    
		}); 
		Button10km.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Button10km.setImageResource(R.drawable.dieci_km2); 
				Button5km.setImageResource(R.drawable.cinque_km); 
				ButtonDone.setImageResource(R.drawable.botton_done2); 
				ButtonDone.setTag(true);
				Km = 10;
			}    
		}); 
		ButtonDone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (ButtonDone.getTag()==(Boolean)true){
					mActivities.setKmObbiettivo(Km);
					Intent intent2 = new Intent(GoalActivity.this, StartingActivity.class);
					intent2.putExtra("a", mActivities);//aggiungiamo questa nuova informazione nel nostro intent
					startActivity(intent2);//facciamo partire l'intent StartingActivity
				}  
			}
		}); 
	}
}

