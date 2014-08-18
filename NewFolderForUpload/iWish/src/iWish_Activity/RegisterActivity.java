package iWish_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
	private Spinner questions_register;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);


		bt_close = (ImageButton)findViewById(R.id.chiudi_verde);
		bt_close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(RegisterActivity.this,AvatarActivity.class ));
			}
		});
	}

}
