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
	//scorciatoia lo lascio perch√® in futuro ci servir‡† per cambiare il valore dell'immagine
	private ImageView scorciatoia; 
	private int height;

	private Utente mUser;
	private TextView stampaNome;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.height);

		scorciatoia = (ImageView)findViewById(R.id.cerchio);
		stampaNome = (TextView)findViewById(R.id.nomeUtente);
		final ImageButton fatto = (ImageButton)findViewById(R.id.done);
		final AbstractWheel altezza = (AbstractWheel) findViewById(R.id.height_horizontal);

		// Prendiamo l'intent passato da Gender
		Intent intent = getIntent();
		// Prendiamo l'oggetto Utente passato tramite intent
		mUser = (Utente)intent.getSerializableExtra("u");
		// Stampiamo il nome dell'utente passato  
		stampaNome.setText(mUser.getName());

		NumericWheelAdapter altezzaAdapter = new NumericWheelAdapter(this, 90, 220, "%02d");
		altezzaAdapter.setItemResource(R.xml.wheel_text_centered);
		altezzaAdapter.setItemTextResource(R.id.text);
		altezza.setViewAdapter(altezzaAdapter); 

		fatto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//	height= altezza.getCurrentItem();
				//	stampaNome.setText(""+height+"");

				//cambiamo il colore al bottone
				fatto.setImageResource(R.drawable.botton_done2);
				// Creiamo un nuovo intent passando il nome dell'intent (ma si poteva fare anche passando il nome della classe) 
				Intent intent2 = new Intent("iWish_Activity.WEIGHT");
				//aggiorniamo i dati utente con il campo "height"
				mUser.setHeight(altezza.getCurrentItem()+90);
				//aggiungiamo questa nuova informazione nel nostro intent
				intent2.putExtra("u", mUser);
				//facciamo partire l'intent GENDER
				startActivity(intent2);			
			}
		});
	}
}