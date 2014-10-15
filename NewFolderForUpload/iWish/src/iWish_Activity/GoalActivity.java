package iWish_Activity;
/**Antonio  --- Miki*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

public class GoalActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goal);

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
			}    
		}); 
		Button10km.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Button10km.setImageResource(R.drawable.dieci_km2); 
				Button5km.setImageResource(R.drawable.cinque_km); 
				ButtonDone.setImageResource(R.drawable.botton_done2); 
				ButtonDone.setTag(true);
			}    
		}); 
		ButtonDone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (ButtonDone.getTag()==(Boolean)true){
					startActivity(new Intent(GoalActivity.this,StartingActivity.class ));
				}  
			}
		}); 
	}
}

