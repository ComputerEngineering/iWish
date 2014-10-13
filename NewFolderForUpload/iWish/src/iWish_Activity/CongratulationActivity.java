package iWish_Activity;

import java.math.BigDecimal;

import iWish_Utente.Utente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.progect.iwish.R;

/** Raffaella*/

public class CongratulationActivity extends Activity {

	private ImageView cerchioFoto; //lo metto per in futuro aggiornare con la foto di profilo
	private ImageView imgbt_expressWish;
	private ImageView ominoUtentePigro;
	private ImageView ominoUtenteAttivo;
	private ImageView scrittaUtentePigro;
	private ImageView scrittaUtenteAttivo;
	private TextView valoreBmi;

	private double alt;
	private double pes;
	private double bmi;
	private double bmi2;

	private TextView stampaNome;
	private Utente mUser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.congratulation);
		setUpViews();

		// Prendiamo l'intent passato da Gender
		Intent intent = getIntent();

		// Prendiamo l'oggetto Utente passato tramite intent
		mUser = (Utente)intent.getSerializableExtra("u");

		//verifica typeUser
		//		int prova = (int)mUser.getHeight();
		String tipologiaUtente = mUser.getTypeUser();
		stampaNome.setText(tipologiaUtente); //per testare che fin qua ci arriva il tipo di utente

		if(tipologiaUtente=="lazy") {

			ominoUtentePigro.setVisibility(0);
			scrittaUtentePigro.setVisibility(0);
			//			ominoUtente.setImageDrawable(getResources().getDrawable(R.drawable.omino_avatar_pigro2));
			//			scrittaUtente.setImageDrawable(getResources().getDrawable(R.drawable.scritta_pigro));
		}
		else{

			ominoUtenteAttivo.setVisibility(0);
			scrittaUtenteAttivo.setVisibility(0);
			//			ominoUtente.setImageDrawable(getResources().getDrawable(R.drawable.omino_avatar_attivo));
			//			scrittaUtente.setImageDrawable(getResources().getDrawable(R.drawable.scritta_attivo));			

		}
//TODO qui alt è 0.0 anche se dentro mUser height != 0.... 
		alt = mUser.getHeight()/100;
		pes = mUser.getWeight();

		bmi = pes/(Math.pow(alt, 2));

		bmi2 = new BigDecimal(bmi).setScale(1 , BigDecimal.ROUND_UP).doubleValue();

		valoreBmi.setText(""+bmi2+"");

		//da fare invio di tutti i dati al db

		imgbt_expressWish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(CongratulationActivity.this,ProfileActivity.class ));
			}
		});

	}

	public void setUpViews(){
		cerchioFoto = (ImageView)findViewById(R.id.civ_cerchio);
		imgbt_expressWish = (ImageView)findViewById(R.id.img_bt_expressWish);
		ominoUtentePigro = (ImageView)findViewById(R.id.omino_avatar);
		ominoUtenteAttivo = (ImageView)findViewById(R.id.omino_avatar2);
		scrittaUtentePigro = (ImageView)findViewById(R.id.scritta_omino);
		scrittaUtenteAttivo = (ImageView)findViewById(R.id.scritta_omino2);
		stampaNome = (TextView)findViewById(R.id.nomeUtente);
		valoreBmi = (TextView)findViewById(R.id.bmi);
	}
}
