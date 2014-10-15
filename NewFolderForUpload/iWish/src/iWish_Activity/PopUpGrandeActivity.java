package iWish_Activity;
/**Michela*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

public class PopUpGrandeActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_grande);
		
		ImageButton avanti = (ImageButton)findViewById(R.id.bot_grazie);
		avanti.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(PopUpGrandeActivity.this,PopUpADV2Activity.class ));
			}
		});			
	}
}
