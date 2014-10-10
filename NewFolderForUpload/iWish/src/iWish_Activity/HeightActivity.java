package iWish_Activity;

import iWish_Utente.Utente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.adapters.NumericWheelAdapter;






//import com.mikhaellopez.circularimageview.CircularImageView;
import com.progect.iwish.R;

/**Alessandro*/

public class HeightActivity extends Activity{
	private Intent intent;
	private Utente mUser;
	private ImageView scorciatoia;
	private int height;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.height);

		scorciatoia = (ImageView)findViewById(R.id.cerchio);
		final ImageButton fatto = (ImageButton)findViewById(R.id.done);
		final AbstractWheel altezza = (AbstractWheel) findViewById(R.id.height_horizontal);
		final String pkg=getPackageName(); 
		intent=getIntent();
		mUser = (Utente)intent.getSerializableExtra(pkg+".myUtente");
		intent=new Intent(getApplicationContext(),WeightActivity.class);

		NumericWheelAdapter altezzaAdapter = new NumericWheelAdapter(this, 0, 220, "%02d");
		altezzaAdapter.setItemResource(R.xml.wheel_text_centered);
		altezzaAdapter.setItemTextResource(R.id.text);
		altezza.setViewAdapter(altezzaAdapter); 
		
		height=altezzaAdapter.getItemResource();

		fatto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			
				mUser.setHeight(height);
				intent.putExtra(pkg + " .Utente ", mUser);
				fatto.setImageResource(R.drawable.botton_done2);
				startActivity(intent);
			}
		});



	}


}