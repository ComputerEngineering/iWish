package iWish_Activity;
/**Antonio --> Miki*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

public class PopUpSunActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_sun);
		
		ImageButton avanti = (ImageButton)findViewById(R.id.bot_oh_yes);
		avanti.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(PopUpSunActivity.this,PopUpOraActivity.class ));
			}
		});
	}
}
