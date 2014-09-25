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

public class WishListActivity extends Activity{
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.wishlist);
	    
		final TextView ButtonOngoing = (TextView)findViewById(R.id.ongoing);
		final TextView ButtonFinished = (TextView)findViewById(R.id.finished);
		final TextView ButtonChallenges = (TextView)findViewById(R.id.challenges);

		final TextView TextOngoing = (TextView)findViewById(R.id.textView_ongoing);
		final TextView TextFinished = (TextView)findViewById(R.id.textView_finished);
		final TextView TextChallenges = (TextView)findViewById(R.id.textView_challenges);		
		
		ButtonOngoing.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	        	ButtonOngoing.setTextColor(getResources().getColor(R.color.verde_scuro));	
	        	ButtonFinished.setTextColor(getResources().getColor(R.color.placeholder));
	        	ButtonChallenges.setTextColor(getResources().getColor(R.color.placeholder));
        	
	        	TextOngoing.setTextColor(getResources().getColor(R.color.verde_scuro));	   
	        	TextFinished.setTextColor(getResources().getColor(R.color.placeholder));	        
	        	TextChallenges.setTextColor(getResources().getColor(R.color.placeholder));	 
	        	
	        }    
	    }); 
		
		ButtonFinished.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	        	ButtonOngoing.setTextColor(getResources().getColor(R.color.placeholder));	
	        	ButtonFinished.setTextColor(getResources().getColor(R.color.verde_scuro));
	        	ButtonChallenges.setTextColor(getResources().getColor(R.color.placeholder));
        	
	        	TextOngoing.setTextColor(getResources().getColor(R.color.placeholder));	   
	        	TextFinished.setTextColor(getResources().getColor(R.color.verde_scuro));	        
	        	TextChallenges.setTextColor(getResources().getColor(R.color.placeholder));	 

	         }    
	    }); 
		
		ButtonChallenges.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	        	
	        	ButtonOngoing.setTextColor(getResources().getColor(R.color.placeholder));	
	        	ButtonFinished.setTextColor(getResources().getColor(R.color.placeholder));
	        	ButtonChallenges.setTextColor(getResources().getColor(R.color.verde_scuro));
        	
	        	TextOngoing.setTextColor(getResources().getColor(R.color.placeholder));	   
	        	TextFinished.setTextColor(getResources().getColor(R.color.placeholder));	        
	        	TextChallenges.setTextColor(getResources().getColor(R.color.verde_scuro));	 
	        	
	          }
	        }); 		
    		
		ImageButton menu = (ImageButton)findViewById(R.id.bott_omino);
		menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Stub di metodo generato automaticamente
				startActivity(new Intent(WishListActivity.this, ProfileActivity.class ));			
			}
		});
		    
	}
}