package iWish_Activity;

/**Alessandro  --- Miki */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import antistatic.spinnerwheel.*;
import antistatic.spinnerwheel.adapters.*;

import com.progect.iwish.R;

public class WeightActivity extends Activity{

	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.weight);
	    
       
	      ImageView scorciatoia = (ImageView)findViewById(R.id.cerchio);
	      scorciatoia.setOnClickListener(new OnClickListener() {
	    		
	    		@Override
	    		public void onClick(View v) {
	    			startActivity(new Intent(WeightActivity.this, CongratulationActivity.class ));
	    		}
	    	});	    
	      
//	      	final TextView testo_peso = (TextView) findViewById(R.id.peso);
	      	final ImageButton fatto = (ImageButton)findViewById(R.id.done);
	      
	        final AbstractWheel peso = (AbstractWheel) findViewById(R.id.weight_horizontal);
	        NumericWheelAdapter pesoAdapter = new NumericWheelAdapter(this, 0, 200, "%02d");
	        pesoAdapter.setItemResource(R.xml.wheel_text_centered);
	        pesoAdapter.setItemTextResource(R.id.text);
	        peso.setViewAdapter(pesoAdapter);
	        
        
	        fatto.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Stub di metodo generato automaticamente
					fatto.setImageResource(R.drawable.botton_done2);
					startActivity(new Intent(WeightActivity.this, CongratulationActivity.class ));
				}
			});
	        
	
	}
		
}
