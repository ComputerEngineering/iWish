package iWish_Activity;
/**Antonio ---  Miki */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

public class GenderActivity extends Activity{
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.gender);
	
	final ImageButton ButtonMaschio = (ImageButton)findViewById(R.id.maschio);
	final ImageButton ButtonFemmina = (ImageButton)findViewById(R.id.femmina);
	final ImageButton ButtonDone = (ImageButton)findViewById(R.id.done);
	
	ButtonDone.setTag(false);
		
	ButtonMaschio.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
        	ButtonMaschio.setImageResource(R.drawable.maschio2); 
        	ButtonFemmina.setImageResource(R.drawable.femmina); 
        	ButtonDone.setImageResource(R.drawable.botton_done2); 
        	ButtonDone.setTag(true);
        }    
    }); 
	
	ButtonFemmina.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
        	ButtonFemmina.setImageResource(R.drawable.femmina3); 
        	ButtonMaschio.setImageResource(R.drawable.maschio); 
        	ButtonDone.setImageResource(R.drawable.botton_done2); 
        	ButtonDone.setTag(true);
         }    
    }); 
	
	ButtonDone.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
        	
        	if (ButtonDone.getTag()==(Boolean)true){
        	startActivity(new Intent(GenderActivity.this,HeightActivity.class ));
        	}  
        	
          }
        }); 
	
	}
	
}