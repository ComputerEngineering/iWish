package iWish_Activity;
/**Michela*/

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class CalendarActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);

		final TextView ButtonToday = (TextView)findViewById(R.id.today);
		final TextView ButtonWeek = (TextView)findViewById(R.id.week);
		final TextView ButtonMonth = (TextView)findViewById(R.id.month);

		ButtonToday.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ButtonToday.setTextColor(getResources().getColor(R.color.verde_scuro));
				ButtonWeek.setTextColor(getResources().getColor(R.color.placeholder));
				ButtonMonth.setTextColor(getResources().getColor(R.color.placeholder));
			}    
		}); 
		ButtonWeek.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ButtonToday.setTextColor(getResources().getColor(R.color.placeholder));
				ButtonWeek.setTextColor(getResources().getColor(R.color.verde_scuro));
				ButtonMonth.setTextColor(getResources().getColor(R.color.placeholder));
			}    
		}); 
		ButtonMonth.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ButtonToday.setTextColor(getResources().getColor(R.color.placeholder));
				ButtonWeek.setTextColor(getResources().getColor(R.color.placeholder));
				ButtonMonth.setTextColor(getResources().getColor(R.color.verde_scuro));
			}
		}); 
		ImageButton avanti = (ImageButton)findViewById(R.id.bott_omino);
		avanti.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(CalendarActivity.this,ProfileActivity.class ));
			}
		});
	}
}
