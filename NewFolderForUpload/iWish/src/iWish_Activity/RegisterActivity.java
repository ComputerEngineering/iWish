package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

/** Raffaella*/

public class RegisterActivity extends Activity{

	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.register);
	    
	    
		ImageButton chiudi = (ImageButton)findViewById(R.id.chiudi_verde);
		chiudi.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(RegisterActivity.this,AvatarActivity.class ));
			}
		});
	    
	    
	
	}
	
}
