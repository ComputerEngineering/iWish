package iWish_Activity;

import iWish_Context.ContextiWish;

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

/**Antonio */

public class LoginRegActivity extends Activity{
	private EditText edt_username;
	private EditText edt_password;
	private ImageButton bt_ok_go;
	private ImageButton bt_forgot_psw;
	private ImageButton bt_fb;
	private ImageButton bt_register;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContext();
		setContentView(R.layout.login_reg);
		Log.i("LoginRegActivity", "after setContentView");

		edt_username = (EditText)findViewById(R.id.edtx_username);
		edt_password = (EditText)findViewById(R.id.edtx_password);
		bt_ok_go = (ImageButton)findViewById(R.id.imgbt_ok_go);
		bt_forgot_psw = (ImageButton)findViewById(R.id.imgbt_forgot_psw);
		bt_fb = (ImageButton)findViewById(R.id.imgbt_fb);

		bt_register = (ImageButton)findViewById(R.id.imgbt_register_done);

		bt_ok_go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginRegActivity.this, ProfileActivity.class ));
			}
		});
				
		bt_forgot_psw.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginRegActivity.this,ForgotPswActivity.class ));
			}
		});
		
		bt_fb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			//TODO mettere il collegamento alla activity
			}
		});
        		
		bt_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginRegActivity.this,RegisterActivity.class));			
			}
		}); 



	}
	/**Return the context of the single, global Application object of the current process. 
	 * This generally should only be used if you need a Context whose lifecycle is separate 
	 * from the current context, that is tied to the lifetime of the process rather than 
	 * the current component*/

	private void setContext() {
		ContextiWish.getIstance().setContext(this.getApplicationContext());
	}                                                                       
}

