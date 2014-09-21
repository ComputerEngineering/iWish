package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

/**Alessandro*/

public class NewWishActivity extends Activity{
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.new_wish);
	    
		ImageButton ok = (ImageButton)findViewById(R.id.m_letsgo);
		ok.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(NewWishActivity.this,WorkingActivity.class ));
			}
		});
	    
	}
}
