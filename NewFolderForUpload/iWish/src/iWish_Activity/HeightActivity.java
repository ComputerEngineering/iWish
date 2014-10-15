package iWish_Activity;
/**Alessandro*/

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

public class HeightActivity extends Activity{
	private ImageView scorciatoia; //scorciatoia lo lascio perch√® in futuro ci servir‡† per cambiare il valore dell'immagine
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

		Intent intent = getIntent();// Prendiamo l'intent passato da Gender
		mUser = (Utente)intent.getSerializableExtra("u");// Prendiamo l'oggetto Utente passato tramite intent
		stampaNome.setText(mUser.getName());// Stampiamo il nome dell'utente passato  

		NumericWheelAdapter altezzaAdapter = new NumericWheelAdapter(this, 90, 220, "%02d");
		altezzaAdapter.setItemResource(R.xml.wheel_text_centered);
		altezzaAdapter.setItemTextResource(R.id.text);
		altezza.setViewAdapter(altezzaAdapter); 
		fatto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//	height= altezza.getCurrentItem();
				//	stampaNome.setText(""+height+"");
				fatto.setImageResource(R.drawable.botton_done2);//cambiamo il colore al bottone
				// Creiamo un nuovo intent passando il nome dell'intent (ma si poteva fare anche passando il nome della classe)
				Intent intent2 = new Intent("iWish_Activity.WEIGHT");
				mUser.setHeight(altezza.getCurrentItem()+90);//aggiorniamo i dati utente con il campo "height"
				intent2.putExtra("u", mUser);//aggiungiamo questa nuova informazione nel nostro intent
				startActivity(intent2);//facciamo partire l'intent GENDER	
			}
		});
	}
}