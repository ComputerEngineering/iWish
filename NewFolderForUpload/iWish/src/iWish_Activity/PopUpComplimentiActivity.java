package iWish_Activity;
/** Raffaella */

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PopUpComplimentiActivity extends Activity{
	private ImageButton bt_nextPopUpComplimenti;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_complimenti);
		
		bt_nextPopUpComplimenti = (ImageButton)findViewById(R.id.bot_awesome);
		bt_nextPopUpComplimenti.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(PopUpComplimentiActivity.this,PopUpGrandeActivity.class ));
			}
		});
	}
}
