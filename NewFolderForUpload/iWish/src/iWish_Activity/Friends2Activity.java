package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.progect.iwish.R;

/**Antonio*/

public class Friends2Activity extends Activity {

	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.friends2);
	    
		final TextView ButtonByName = (TextView)findViewById(R.id.byname);
		final TextView ButtonByLatest = (TextView)findViewById(R.id.bylatest);
		final TextView ButtonByPoints = (TextView)findViewById(R.id.bypoints);
	    
	    
		ButtonByName.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	        	ButtonByName.setTextColor(getResources().getColor(R.color.verde_scuro));
	        	ButtonByLatest.setTextColor(getResources().getColor(R.color.placeholder));
	        	ButtonByPoints.setTextColor(getResources().getColor(R.color.placeholder));
	        }    
	    }); 
		
		ButtonByLatest.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	        	ButtonByName.setTextColor(getResources().getColor(R.color.placeholder));
	        	ButtonByLatest.setTextColor(getResources().getColor(R.color.verde_scuro));
	        	ButtonByPoints.setTextColor(getResources().getColor(R.color.placeholder));

	         }    
	    }); 
		
		ButtonByPoints.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	        	
	        	ButtonByName.setTextColor(getResources().getColor(R.color.placeholder));
	        	ButtonByLatest.setTextColor(getResources().getColor(R.color.placeholder));
	        	ButtonByPoints.setTextColor(getResources().getColor(R.color.verde_scuro));
	        	
	          }
	        }); 
				
		ImageButton avanti = (ImageButton)findViewById(R.id.bott_omino);
		avanti.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Friends2Activity.this,ProfileActivity.class ));
			}
		});
	    
	
	}
	
}
