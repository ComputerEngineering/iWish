package iWish_Activity;
/** Raffaella  - Miki*/


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

public class ChallengeActivity extends Activity{
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.challenge);
	
	final ImageButton ButtonVsMyself = (ImageButton)findViewById(R.id.vs_myself);
	final ImageButton ButtonVsFriends = (ImageButton)findViewById(R.id.vs_friends);
	final ImageButton ButtonDone = (ImageButton)findViewById(R.id.done);
	
	ButtonDone.setTag(false);
		
	ButtonVsMyself.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
        	ButtonVsMyself.setImageResource(R.drawable.vs_myself2); 
        	ButtonVsFriends.setImageResource(R.drawable.vs_friends); 
        	ButtonDone.setImageResource(R.drawable.botton_done2); 
        	ButtonDone.setTag(true);
        }    
    }); 
	
	ButtonVsFriends.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
        	ButtonVsFriends.setImageResource(R.drawable.vs_friends2); 
        	ButtonVsMyself.setImageResource(R.drawable.vs_myself); 
        	ButtonDone.setImageResource(R.drawable.botton_done2); 
        	ButtonDone.setTag(true);
         }    
    }); 
	
	ButtonDone.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
        	
        	if (ButtonDone.getTag()==(Boolean)true){
        	startActivity(new Intent(ChallengeActivity.this,NewWishActivity.class ));
        	}  
        	
          }
        }); 
	
	}
	
}

