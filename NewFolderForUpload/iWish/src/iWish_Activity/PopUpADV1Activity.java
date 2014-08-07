package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

/**Antonio --> Miki*/

public class PopUpADV1Activity extends Activity{

	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.popup_adv1);
	
	
	ImageButton avanti = (ImageButton)findViewById(R.id.bot_grazie);
	avanti.setOnClickListener(new OnClickListener() {
	
		@Override
		public void onClick(View v) {
			startActivity(new Intent(PopUpADV1Activity.this,PopUpBadgeVinta2Activity.class ));
		}
	});
	
	
	
	
	}
}
