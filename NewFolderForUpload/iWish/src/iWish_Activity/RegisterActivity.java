package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

		adapter = createSpinnerAdapter();
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_questions_register = (Spinner)findViewById(R.id.spinner1);
		sp_questions_register.setAdapter(adapter);

		bt_close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(RegisterActivity.this,AvatarActivity.class ));
			}
		});

	}

	private ArrayAdapter<String> createSpinnerAdapter(){
		data = getResources().getStringArray(R.array.questions_register);
		arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, data );
		return arrayAdapter;
	}
}
