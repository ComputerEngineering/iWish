package iWish_Activity;
/**Michela*/



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


import com.progect.iwish.R;

public class StatisticsActivity extends Activity {
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistics);
		
		final ImageButton ButtonBody = (ImageButton)findViewById(R.id.bot_body);
		final ImageButton ButtonActivity = (ImageButton)findViewById(R.id.bot_activity);
		final ImageButton ButtonBadge = (ImageButton)findViewById(R.id.bot_badges);

		/**click su BODY*/
		ButtonBody.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ButtonBody.setImageResource(R.drawable.bot_body2); 
				ButtonActivity.setImageResource(R.drawable.bot_activity); 
				ButtonBadge.setImageResource(R.drawable.bot_badges); 

			}    
		}); 
		/**click su ACTIVITY*/
		ButtonActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ButtonBody.setImageResource(R.drawable.bot_body); 
				ButtonActivity.setImageResource(R.drawable.bot_activity2); 
				ButtonBadge.setImageResource(R.drawable.bot_badges); 

			}	
		}); 
		/**click su BADGE*/
		ButtonBadge.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ButtonBody.setImageResource(R.drawable.bot_body); 
				ButtonActivity.setImageResource(R.drawable.bot_activity); 
				ButtonBadge.setImageResource(R.drawable.bot_badges2); 

			}
		}); 
		ImageButton avanti = (ImageButton)findViewById(R.id.bott_omino);
		avanti.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(StatisticsActivity.this, ProfileActivity.class ));
			}
		});
		
	}
}

//fine list Badge	



