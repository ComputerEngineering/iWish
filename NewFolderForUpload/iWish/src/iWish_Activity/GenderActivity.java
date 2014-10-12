package iWish_Activity;
/**Antonio ---  Miki */

import iWish_Utente.Utente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.progect.iwish.R;

public class GenderActivity extends Activity{

	private final String WOMAN = "w"; //value sex 1
	private final String MEN = "m"; //value sex 0
	private int sex ;
	
	private Utente mUser;
	private TextView stampaNome;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gender);

		final ImageButton ButtonMaschio = (ImageButton)findViewById(R.id.maschio);
		final ImageButton ButtonFemmina = (ImageButton)findViewById(R.id.femmina);
		final ImageButton ButtonDone = (ImageButton)findViewById(R.id.done);
		ButtonDone.setTag(false);
		
		stampaNome = (TextView)findViewById(R.id.nomeUtente);
		
		// Prendiamo l'intent passato da Avatar
		Intent intent = getIntent();
		// Prendiamo l'oggetto Utente passato tramite intent
		mUser = (Utente)intent.getSerializableExtra("u");
		// Stampiamo il nome dell'utente passato  
		stampaNome.setText(mUser.getName());
		
		ButtonMaschio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ButtonMaschio.setImageResource(R.drawable.maschio2); 
				ButtonFemmina.setImageResource(R.drawable.femmina); 
				ButtonDone.setImageResource(R.drawable.botton_done2);
				sex=0;
				ButtonDone.setTag(true);
			}    
		}); 

		ButtonFemmina.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ButtonFemmina.setImageResource(R.drawable.femmina3); 
				ButtonMaschio.setImageResource(R.drawable.maschio); 
				ButtonDone.setImageResource(R.drawable.botton_done2); 
				sex=1;
				ButtonDone.setTag(true);
			}    
		}); 

		ButtonDone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (ButtonDone.getTag()==(Boolean)true){
					
					// Creaiamo un nuovo intent passando il nome dell'intent (ma si poteva fare anche passando il nome della classe) 
					Intent intent2 = new Intent("iWish_Activity.HEIGHT");
					//aggiorniamo i dati utente con il campo "sex"
					sexUser();
					//aggiungiamo questa nuova informazione nel nostro intent
					intent2.putExtra("u", mUser);
					//facciamo partire l'intent HEIGHT
					startActivity(intent2);
				}  
			}
			
			private void sexUser() {
				if(sex==0){
					mUser.setSex(MEN);
				}else {
					mUser.setSex(WOMAN);
				}
			}
		}); 
	}
}