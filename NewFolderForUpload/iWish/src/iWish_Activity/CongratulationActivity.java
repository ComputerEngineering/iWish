package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.progect.iwish.R;

/** Raffaella*/

public class CongratulationActivity extends Activity {
	private ImageView shortcut;
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.congratulation);
	    
	    shortcut = (ImageView)findViewById(R.id.cerchio);
	    
	    shortcut.setOnClickListener(new OnClickListener() {
	  		
	  		@Override
	  		public void onClick(View v) {
	  			startActivity(new Intent(CongratulationActivity.this,iWishActivity.class ));
	  		}
	  	});
	    
	  }
}
