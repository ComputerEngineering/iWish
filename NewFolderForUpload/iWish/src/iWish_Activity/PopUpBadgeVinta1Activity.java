package iWish_Activity;
/** Raffaella*/

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PopUpBadgeVinta1Activity extends Activity{
	private ImageButton bt_nextPopUpBadgeVinta1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_badge_vinta1);
		
		bt_nextPopUpBadgeVinta1= (ImageButton)findViewById(R.id.bot_grazie);
		bt_nextPopUpBadgeVinta1.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(PopUpBadgeVinta1Activity.this,PopUpGenioActivity.class ));
			}
		});				
	}
}
