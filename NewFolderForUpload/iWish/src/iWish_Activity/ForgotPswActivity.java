package iWish_Activity;

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**Alessandro*/

public class ForgotPswActivity extends Activity{

	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.forgotpsw);
	    
	    
		ImageButton chiudi = (ImageButton)findViewById(R.id.chiudi_verde);
		chiudi.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ForgotPswActivity.this,RegisterActivity.class ));
			}
		});
	    
	    
	
	}
	
}
