package iWish_Activity;
/**Antonio ---  Miki */

import iWish_Utente.Utente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.progect.iwish.R;

public class GenderActivity extends Activity{
	private Intent intent;
	private Utente mUser;
	private final String WOMAN = "w"; //value sex 1
	private final String MEN = "m"; //value sex 0
	private int sex ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gender);

		final ImageButton ButtonMaschio = (ImageButton)findViewById(R.id.maschio);
		final ImageButton ButtonFemmina = (ImageButton)findViewById(R.id.femmina);
		final ImageButton ButtonDone = (ImageButton)findViewById(R.id.done);

		intent=getIntent();
		final String pkg=getPackageName();
		mUser = (Utente)intent.getSerializableExtra(pkg+".myUtente");
		intent=new Intent(getApplicationContext(), HeightActivity.class);

		ButtonDone.setTag(false);

		ButtonMaschio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
					sexUser();
					intent.putExtra(pkg + " .Utente ", mUser);
					startActivity(intent);
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