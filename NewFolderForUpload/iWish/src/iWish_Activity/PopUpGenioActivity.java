package iWish_Activity;
/**Alessandro --> Miki  ...ho creato il bottone "go back" per rispettare la grafica ma non penso serva */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

public class PopUpGenioActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_genio);

		ImageButton avanti = (ImageButton)findViewById(R.id.bot_remind);
		avanti.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(PopUpGenioActivity.this,PopUpTemporaleActivity.class ));
			}
		});	
	}
}
