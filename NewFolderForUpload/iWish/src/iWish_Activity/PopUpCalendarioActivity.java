package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

/**Michela*/

public class PopUpCalendarioActivity extends Activity{

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_calendario);
		
		
		
		ImageButton avanti = (ImageButton)findViewById(R.id.bot_oh_yes);
		avanti.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(PopUpCalendarioActivity.this,PopUpSunActivity.class ));
			}
		});
		
	}
}
