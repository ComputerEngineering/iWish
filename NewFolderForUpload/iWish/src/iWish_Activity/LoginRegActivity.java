package iWish_Activity;

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**Antonio*/

public class LoginRegActivity extends Activity{

	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.login_reg);
	    
	    
		ImageButton chiudi = (ImageButton)findViewById(R.id.chiudi_verde);
		chiudi.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginRegActivity.this,ForgotPswActivity.class ));
			}
		});
	    
	    
	
	}
	
	
}
