package iWish_Activity;

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/** Raffaella*/

public class PopUpBadgeVinta1Activity extends Activity{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_badge_vinta1);
		
		
		ImageButton avanti = (ImageButton)findViewById(R.id.bt_thanks2);
		avanti.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(PopUpBadgeVinta1Activity.this,PopUpGenioActivity.class ));
			}
		});			
		
		
	}

}
