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
import android.widget.TextView;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.adapters.NumericWheelAdapter;

import com.progect.iwish.R;

public class WeightActivity extends Activity{
	
	private ImageView scorciatoia; //scorciatoia lo lascio perchè in futuro ci servirà per cambiare il valore dell'immagine
	private int weight;
	
	private Utente mUser;
	private TextView stampaNome;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weight);

		scorciatoia = (ImageView)findViewById(R.id.cerchio);
		final ImageButton fatto = (ImageButton)findViewById(R.id.done);
		final AbstractWheel peso = (AbstractWheel) findViewById(R.id.weight_horizontal);
		
		stampaNome = (TextView)findViewById(R.id.nomeUtente);
		
		// Prendiamo l'intent passato da Gender
		Intent intent = getIntent();

		// Prendiamo l'oggetto Utente passato tramite intent
		mUser = (Utente)intent.getSerializableExtra("u");
		
		// Stampiamo il nome dell'utente passato  
		stampaNome.setText(mUser.getName());
		
		NumericWheelAdapter pesoAdapter = new NumericWheelAdapter(this, 30, 200, "%02d");
		pesoAdapter.setItemResource(R.xml.wheel_text_centered);
		pesoAdapter.setItemTextResource(R.id.text);
		peso.setViewAdapter(pesoAdapter);
		
		weight= pesoAdapter.getEmptyItemResource();

		fatto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//cambiamo il colore al bottone
				fatto.setImageResource(R.drawable.botton_done2);
				
				// Creiamo un nuovo intent passando il nome dell'intent (ma si poteva fare anche passando il nome della classe) 
				Intent intent2 = new Intent("iWish_Activity.CONGRATULATION");
				
				//aggiorniamo i dati utente con il campo "weight"
				mUser.setWeight(peso.getCurrentItem()+30);
				
				//aggiungiamo questa nuova informazione nel nostro intent
				intent2.putExtra("u", mUser);
				
				//facciamo partire l'intent GENDER
				startActivity(intent2);		

			}
		});


	}

}
