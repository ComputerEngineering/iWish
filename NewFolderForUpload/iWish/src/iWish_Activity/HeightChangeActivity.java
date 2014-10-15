package iWish_Activity;
/** Miki */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.adapters.NumericWheelAdapter;

import com.progect.iwish.R;

public class HeightChangeActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.height_change);

		final ImageButton fatto = (ImageButton)findViewById(R.id.done);

		final AbstractWheel altezza = (AbstractWheel) findViewById(R.id.height_horizontal);
		NumericWheelAdapter altezzaAdapter = new NumericWheelAdapter(this, 0, 220, "%02d");
		altezzaAdapter.setItemResource(R.xml.wheel_text_centered);
		altezzaAdapter.setItemTextResource(R.id.text);
		altezza.setViewAdapter(altezzaAdapter);
		fatto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Stub di metodo generato automaticamente
				fatto.setImageResource(R.drawable.botton_done2);
				startActivity(new Intent(HeightChangeActivity.this, StatisticsActivity.class ));
			}
		}); 
	}	
}
