package iWish_Activity;

/**Alessandro*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

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
	
	}
		
}
