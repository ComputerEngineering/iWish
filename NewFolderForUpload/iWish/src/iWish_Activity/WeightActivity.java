package iWish_Activity;

/**Alessandro  --- Miki */

import iWish_Control.ControlUser;
import iWish_Utente.Utente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.adapters.NumericWheelAdapter;

import com.progect.iwish.R;

public class WeightActivity extends Activity{
	private Intent intent;
	private Utente mUser;
	private int weight;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weight);


		ImageView scorciatoia = (ImageView)findViewById(R.id.cerchio);
		intent=getIntent();
		final String pkg=getPackageName();
		mUser = (Utente)intent.getSerializableExtra(pkg+".myUtente");
		    
		//	      	final TextView testo_peso = (TextView) findViewById(R.id.peso);
		final ImageButton fatto = (ImageButton)findViewById(R.id.done);

		final AbstractWheel peso = (AbstractWheel) findViewById(R.id.weight_horizontal);
		NumericWheelAdapter pesoAdapter = new NumericWheelAdapter(this, 0, 200, "%02d");
		pesoAdapter.setItemResource(R.xml.wheel_text_centered);
		pesoAdapter.setItemTextResource(R.id.text);
		peso.setViewAdapter(pesoAdapter);
		weight= pesoAdapter.getEmptyItemResource();

		fatto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mUser.setWeight(weight);
				try {
					ControlUser.getIstanceControlUser().saveOnDbUtente(mUser);
				//	ControlConnection.getIstanceControlConnection().onInsertUtente();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fatto.setImageResource(R.drawable.botton_done2);
				startActivity(new Intent(getApplicationContext(), GenderActivity.class));
			}
		});


	}

}
