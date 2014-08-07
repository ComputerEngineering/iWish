package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

/**Alessandro --> Miki*/

public class PopUpBadgeVinta3Activity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_badge_vinta3);
		
		
		ImageButton avanti = (ImageButton)findViewById(R.id.bot_grazie);
		avanti.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(PopUpBadgeVinta3Activity.this,PopUpBadgeVinta1Activity.class ));
			}
		});			
		
	}
}
