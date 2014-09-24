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
import android.widget.Spinner;

import com.progect.iwish.R;

/** Raffaella*/

public class RegisterActivity extends Activity{
	private ImageButton bt_close;
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


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		bt_close = (ImageButton)findViewById(R.id.chiudi_verde);
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
			}
		});

		bt_close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(RegisterActivity.this,AvatarActivity.class ));
			}
		});
	}

	private ArrayAdapter<String> createSpinnerAdapter(){
		data = getResources().getStringArray(R.array.question);
		arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, data );
		return arrayAdapter;
	}
}
