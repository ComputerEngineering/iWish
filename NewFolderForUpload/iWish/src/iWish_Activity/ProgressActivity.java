package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;
//**Alessandro*//

public class ProgressActivity extends Activity{
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.progress);
	    		
		ImageButton menu = (ImageButton)findViewById(R.id.bott_omino);
		menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Stub di metodo generato automaticamente
				startActivity(new Intent(ProgressActivity.this, ProfileActivity.class ));			
			}
		});
		
		ImageButton ok = (ImageButton)findViewById(R.id.m_lets_start);
		ok.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ProgressActivity.this, WorkingActivity.class ));
			}
		});
	    
	}
}
