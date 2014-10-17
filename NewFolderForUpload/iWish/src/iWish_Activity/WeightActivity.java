package iWish_Activity;

/**Alessandro  --- Miki */

import iWish_Utente.Utente;
import android.app.Activity;
import android.content.Context;
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
	//scorciatoia lo lascio perchè in futuro ci servirà per cambiare il valore dell'immagine
	private ImageView scorciatoia; 
	private int weight;
	private Context c;
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

		Intent intent = getIntent();// Prendiamo l'intent passato da Gender
		mUser = (Utente)intent.getSerializableExtra("u");// Prendiamo l'oggetto Utente passato tramite intent
		stampaNome.setText(mUser.getName());// Stampiamo il nome dell'utente passato  

		NumericWheelAdapter pesoAdapter = new NumericWheelAdapter(this, 30, 200, "%02d");
		pesoAdapter.setItemResource(R.xml.wheel_text_centered);
		pesoAdapter.setItemTextResource(R.id.text);
		peso.setViewAdapter(pesoAdapter);
		weight= pesoAdapter.getEmptyItemResource();

		fatto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				fatto.setImageResource(R.drawable.botton_done2);//cambiamo il colore al bottone
				// Creiamo un nuovo intent passando il nome dell'intent (ma si poteva fare anche passando il nome della classe) 
				Intent intent2 = new Intent("iWish_Activity.CONGRATULATION");
				mUser.setWeight(peso.getCurrentItem()+30);//aggiorniamo i dati utente con il campo "weight"
				intent2.putExtra("u", mUser);//aggiungiamo questa nuova informazione nel nostro intent
				//aggiungiamo questa nuova informazione nel nostro intent
				startActivity(intent2);	//facciamo partire l'intent GENDER	
			}
		});
	}
}
