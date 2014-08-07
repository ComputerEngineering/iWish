package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

/**Michela*/

public class iWishActivity extends Activity{

	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.iwish);
	    
	    
		ImageButton chiudi = (ImageButton)findViewById(R.id.chiudi_verde);
		chiudi.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(iWishActivity.this,WantToDoActivity.class ));
			}
		});
	    
	    
	
	}
}