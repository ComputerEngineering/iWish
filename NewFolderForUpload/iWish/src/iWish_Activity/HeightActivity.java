package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

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
	    
	
	}
	
	
}