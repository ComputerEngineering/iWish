package iWish_Activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
//scelta immagine profilo
import android.net.Uri;
import android.provider.MediaStore;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;


import com.progect.iwish.R;

/** Raffaella*/

public class RegisterActivity extends Activity{

	private ImageButton bt_change;
	private ImageButton bt_go_ok;
	private EditText edt_name;
	private EditText edt_surname;
	private EditText edt_birthday;
	private EditText edt_city;
	private EditText edt_email;
	private EditText edt_rp_email;
	private EditText edt_password;
	private EditText edt_rp_password;
	private EditText edt_answer_register;
	private Spinner sp_questions_register;
	private ArrayAdapter<String> adapter;
	private ArrayAdapter<String> arrayAdapter;
	private String[] data;

	//scelta immagine profilo
	private ImageView scegliFoto;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;

    


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

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
		
		scegliFoto = (ImageView)findViewById(R.id.foto);
		
		
		
		adapter = createSpinnerAdapter();
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_questions_register = (Spinner)findViewById(R.id.spinner1);
		sp_questions_register.setAdapter(adapter);
		
		bt_change.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		
		bt_go_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Editable tx_name= edt_name.getText();
				Editable tx_surname = edt_surname.getText();
				Editable tx_birthday = edt_birthday.getText();
				Editable tx_city = edt_city.getText();
				Editable tx_email = edt_email.getText();
				Editable tx_password = edt_password.getText();
				Editable tx_rp_password = edt_rp_password.getText();
				Editable tx_rp_email = edt_rp_email.getText();
				Editable tx_answer_register = edt_answer_register.getText();
				
				startActivity(new Intent(RegisterActivity.this, AvatarActivity.class ));
			}
		});

		//cliccando sul cerchietto si sceglie la propria immagine di profilo tra le foto in memoria nel cel
		
			scegliFoto.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					// in onCreate or any event where your want the user to select a file
					Intent intent = new Intent();
					intent.setType("image/*");
					intent.setAction(Intent.ACTION_GET_CONTENT);
					startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
				}
			});
	
	}

	private ArrayAdapter<String> createSpinnerAdapter(){
		data = getResources().getStringArray(R.array.question);
		arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, data );
		return arrayAdapter;
	}
	
	// metodo per la gestione dell'interazione con la gallery del telefono
	
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                
                // affinchè l'immagine non venga deformata viene prima resa quadrata mediante il metodo Ritaglia
                Bitmap immScelta = Ritaglia(selectedImagePath);               
                scegliFoto.setImageBitmap(immScelta);
                
                // se non ci importava della deformazione potevamo fare direttamente:
                // scegliFoto.setImageURI(selectedImageUri);
            }
        }
    }
    
 // metodo per la gestione dell'interazione con la gallery del telefono
    /**
     * helper to retrieve the path of an image URI
     */
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
    
       
    ///////metodi che ho creato io
    
    //chiama convert e poi ritaglia l'immagine
    
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
    
    
    //converte il path bitmap
    
    public Bitmap Convert(String ss){
    	
    	File imgFile = new  File(ss);
    	
    	if(imgFile.exists()){
    		
    	    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
    	    return myBitmap;
    	}
    	
    	return null;
    }
    
}

