package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.adapters.NumericWheelAdapter;



//import com.mikhaellopez.circularimageview.CircularImageView;
import com.progect.iwish.R;

/**Alessandro*/

public class HeightActivity extends Activity{

	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.height);
	    	    
	      ImageView scorciatoia = (ImageView)findViewById(R.id.cerchio);
	      scorciatoia.setOnClickListener(new OnClickListener() {
	    		
	    		@Override
	    		public void onClick(View v) {
	    			startActivity(new Intent(HeightActivity.this,WeightActivity.class ));
	    		}
	    	});
	      
//	      	final TextView testo_altezza = (TextView) findViewById(R.id.altezza);
	      	final ImageButton fatto = (ImageButton)findViewById(R.id.done);
	    
	      	
	        final AbstractWheel altezza = (AbstractWheel) findViewById(R.id.height_horizontal);
	        NumericWheelAdapter altezzaAdapter = new NumericWheelAdapter(this, 0, 220, "%02d");
	        altezzaAdapter.setItemResource(R.xml.wheel_text_centered);
	        altezzaAdapter.setItemTextResource(R.id.text);
	        altezza.setViewAdapter(altezzaAdapter);
	        altezza.setClickable(true);
	      	
	        
	        fatto.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Stub di metodo generato automaticamente
					fatto.setImageResource(R.drawable.botton_done2);
					startActivity(new Intent(HeightActivity.this, WeightActivity.class ));
				}
			});
	        
	        
	        
	}
	
	
}