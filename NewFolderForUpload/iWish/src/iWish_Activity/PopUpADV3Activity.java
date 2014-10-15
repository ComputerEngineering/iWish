package iWish_Activity;
/** Raffaella*/

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PopUpADV3Activity extends Activity {
	private ImageButton bt_nextPopUpADV3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_adv3);

		bt_nextPopUpADV3 = (ImageButton)findViewById(R.id.bot_grazie);
		bt_nextPopUpADV3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(PopUpADV3Activity.this,PopUpADV1Activity.class ));
			}
		});	
	}
}
