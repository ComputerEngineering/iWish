package iWish_Activity;
/**Michela*/

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class CalendarActivity extends Activity {

	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.calendar);
	    
		final ImageButton ButtonToday = (ImageButton)findViewById(R.id.today);
		final ImageButton ButtonWeek = (ImageButton)findViewById(R.id.week);
		final ImageButton ButtonMonth = (ImageButton)findViewById(R.id.month);
	    
	    
		ButtonToday.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	        	ButtonToday.setImageResource(R.drawable.bot_today2); 
	        	ButtonWeek.setImageResource(R.drawable.bot_week); 
	        	ButtonMonth.setImageResource(R.drawable.bott_month); 
	        }    
	    }); 
		
		ButtonWeek.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	        	ButtonToday.setImageResource(R.drawable.bot_today); 
	        	ButtonWeek.setImageResource(R.drawable.bot_week2); 
	        	ButtonMonth.setImageResource(R.drawable.bott_month); 

	         }    
	    }); 
		
		ButtonMonth.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	        	
	        	ButtonToday.setImageResource(R.drawable.bot_today); 
	        	ButtonWeek.setImageResource(R.drawable.bot_week); 
	        	ButtonMonth.setImageResource(R.drawable.bott_month2); 

	        	
	          }
	        }); 
		
		
		ImageButton avanti = (ImageButton)findViewById(R.id.bott_omino);
		avanti.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(CalendarActivity.this,StatisticsActivity.class ));
			}
		});
	    
	
	}
	
}
