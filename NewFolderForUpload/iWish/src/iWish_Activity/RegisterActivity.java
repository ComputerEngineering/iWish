package iWish_Activity;
/** Raffaella*/
import iWish_Utente.Utente;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//scelta immagine profilo
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.progect.iwish.R;

public class RegisterActivity extends Activity implements OnItemSelectedListener{
	public Context c;
	private ImageButton bt_change=null;
	private ImageButton bt_go_ok=null;
	private EditText edt_name=null;
	private EditText edt_surname=null;
	private EditText edt_birthday=null;
	private EditText edt_city=null;
	private EditText edt_email=null;
	private EditText edt_rp_email=null;
	private EditText edt_password=null;
	private EditText edt_rp_password=null;
	private EditText edt_answer_register=null;
	private Spinner sp_questions_register=null;
	private String question;
	private ArrayAdapter<CharSequence> adapter=null;
	//private ArrayAdapter<String> arrayAdapter=null;
	private String[] data=null;
	private ImageView selectPhoto=null;
	private static final int SELECT_PICTURE = 1;
	private String selectedImagePath=null;
	private AlertDialog alertDialog;
	private Utente mUser ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		setUpViews();

		sp_questions_register  =(Spinner) findViewById(R.id.spinner1);
		adapter = ArrayAdapter.createFromResource(this, R.array.question, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_questions_register.setAdapter(adapter);
		sp_questions_register.setOnItemSelectedListener(this);

		//cliccando sul cerchietto si sceglie la propria immagine di profilo tra le foto in memoria nel cel
		selectPhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// in onCreate or any event where your want the user to select a file
				Intent intentPhoto = new Intent();
				intentPhoto.setType("image/*");
				intentPhoto.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(Intent.createChooser(intentPhoto,"Select Picture"), SELECT_PICTURE);
			}
		});
		bt_go_ok.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				if((edt_name.getText().toString().equals(""))||(edt_surname.getText().toString().equals(""))
						||(edt_birthday.getText().toString().equals(""))||(edt_city.getText().toString().equals(""))
						||(edt_email.getText().toString().equals(""))||(edt_rp_email.getText().toString().equals(""))
						||(edt_password.getText().toString().equals(""))||(edt_rp_password.getText().toString().equals(""))
						||(edt_answer_register.getText().toString().equals(""))){
					//TODO controlli
					CharSequence eMail= "Registration is not complete";
					Toast.makeText(getApplicationContext(), eMail, Toast.LENGTH_SHORT).show();
				}else{

					// Creiamo un nuovo intent passando il nome dell'intent successivo (ma si poteva fare anche passando il nome della classe) 
					Intent intent = new Intent("iWish_Activity.AVATAR");
					//creiamo un utente u con tutte le info inserite
					Utente u = createUser();
					//aggiungiamo il tutto al nostro intent
					intent.putExtra("u", u);
					//facciamo partire l'intent AVATAR
					startActivity(intent);
				}
			}
		});
	}

	//utilizzato per prendere il dato dello spinner
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		//prendo il valore dell'elemento selezionato
		question = parent.getItemAtPosition(pos).toString();

		//visualizzo l'elemento selezionato
		//  Toast.makeText(parent.getContext(), item, Toast.LENGTH_LONG).show();
	}
	public void onNothingSelected(AdapterView<?> parent) {
		// Another interface callback
	}

	private void setUpViews() {
		bt_change = (ImageButton)findViewById(R.id.bott_modifica);
		bt_go_ok = (ImageButton)findViewById(R.id.bt_go_ok);
		edt_name = (EditText)findViewById(R.id.editText1);
		edt_surname = (EditText)findViewById(R.id.editText2);
		edt_birthday = (EditText)findViewById(R.id.editText3);
		edt_city = (EditText)findViewById(R.id.editText4);
		edt_email = (EditText)findViewById(R.id.editText5);
		edt_rp_email = (EditText)findViewById(R.id.editText6);
		edt_password = (EditText)findViewById(R.id.editText7);
		edt_rp_password = (EditText)findViewById(R.id.editText8);
		edt_answer_register = (EditText)findViewById(R.id.editText9);
		selectPhoto = (ImageView)findViewById(R.id.foto);
	}
	private Utente createUser() {
		mUser = new Utente();
		mUser.setName(edt_name.getText().toString());
		mUser.setSurname(edt_surname.getText().toString());
		mUser.setBirthday(edt_birthday.getText().toString());
		mUser.setCity(edt_city.getText().toString());
		mUser.setEmail(edt_email.getText().toString());
		mUser.setPassword(edt_password.getText().toString());
		mUser.setAnswer(edt_answer_register.getText().toString());
		mUser.setQuestion(question);
		return mUser;
	}

	/**metodo per la gestione dell'interazione con la gallery del telefono**/
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == SELECT_PICTURE) {
				Uri selectedImageUri = data.getData();
				selectedImagePath = getPath(selectedImageUri);

				// affinch� l'immagine non venga deformata viene prima resa quadrata mediante il metodo Ritaglia
				Bitmap immScelta = Ritaglia(selectedImagePath);               
				selectPhoto.setImageBitmap(immScelta);
				// se non ci importava della deformazione potevamo fare direttamente:
				// scegliFoto.setImageURI(selectedImageUri);
			}
		}
	}
	/** metodo per la gestione dell'interazione con la gallery del telefono
	helper to retrieve the path of an image URI*/
	@SuppressWarnings("deprecation")
	public String getPath(Uri uri) {
		// just some safety built in 
		if( uri == null ) {
			// TODO perform some logging or show user feedback
			return null;
		}
		// try to retrieve the image from the media store first
		// this will only work for images selected from gallery
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		if( cursor != null ){
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		}
		// this is our fallback here
		return uri.getPath();
	}
	/**metodi che ho creato io
	 * chiama convert e poi ritaglia l'immagine*/
	public Bitmap Ritaglia(String s){
		Bitmap srcBmp = Convert(s);
		Bitmap dstBmp;
		if (srcBmp.getWidth() >= srcBmp.getHeight()){
			dstBmp = Bitmap.createBitmap(srcBmp, srcBmp.getWidth()/2 - srcBmp.getHeight()/2, 0,
					srcBmp.getHeight(), srcBmp.getHeight());
		}else{
			dstBmp = Bitmap.createBitmap(srcBmp, 0, srcBmp.getHeight()/2 - srcBmp.getWidth()/2,
					srcBmp.getWidth(), srcBmp.getWidth() );
		}
		return dstBmp;
	}
	/**converte il path bitmap*/
	public Bitmap Convert(String ss){
		File imgFile = new  File(ss);
		if(imgFile.exists()){
			Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
			return myBitmap;
		}
		return null;
	}
}

