package iWish_Listener;
/** Raffaella*/


import com.progect.iwish.R;

import iWish_Activity.AvatarActivity;
import iWish_Control.ControlConnection;
import iWish_Control.ControlUser;
import iWish_Utente.Utente;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterListerner implements OnClickListener {
	public Context c;
	private EditText edt_name;
	private EditText edt_surname;
	private EditText edt_birthday;
	private EditText edt_city;
	private EditText edt_email;
	private EditText edt_rp_email;
	private EditText edt_password;
	private EditText edt_rp_password;
	private EditText edt_answer_register;
	private Utente mUser;
	private AlertDialog alertDialog;
	private String questions;
	private String firstQuestionsSelected="Select Question";

	public RegisterListerner(){}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		Intent open_activity= new Intent(c,AvatarActivity.class);
		switch (v.getId()){
		case R.id.bt_go_ok:
			if((edt_name.getText().equals(""))||(edt_surname.getText().equals(""))
					||(edt_birthday.getText().equals(""))||(edt_city.getText().equals(""))
					||(edt_email.getText().equals(""))||(edt_rp_email.getText().equals(""))
					||(edt_password.getText().equals(""))||(edt_rp_password.getText().equals(""))
					||(edt_answer_register.getText().equals(""))){
				alertDialog = new AlertDialog.Builder(c).create();
				alertDialog.setTitle("Warning");
				alertDialog.setMessage("You don't have write all information"); 
				alertDialog.setIcon(R.drawable.bt_ok_go);  
				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {  
					public void onClick(DialogInterface dialog, int which) {  
						return;  
					} });  
				alertDialog.show();
			}else{
				if(questions.toString().equalsIgnoreCase(firstQuestionsSelected)){
					alertDialog = new AlertDialog.Builder(c).create();  
					alertDialog.setTitle("Warning");
					alertDialog.setMessage("If you want register your account you must chose a question");
					//alertDialog.setIcon(R.drawable.);
					alertDialog.setButton2("OK", new DialogInterface.OnClickListener() {  
						public void onClick(DialogInterface dialog, int which) {  
							return;  
						} });   
					alertDialog.show();
				}
				else{
					createUser();
				}
			}
		}
	}

	private void createUser(){
		mUser = new Utente();
		
		mUser.setName(edt_name.getText().toString());
		mUser.setSurname(edt_surname.getText().toString());
		//mUser.setBirthday(edt_birthday.getText().toString());
		mUser.setCity(edt_city.getText().toString());
		mUser.setEmail(edt_email.getText().toString());
		mUser.setPassword(edt_password.getText().toString());
		mUser.setAnswer(edt_answer_register.getText().toString());
		
		mUser.setQuestion(questions.toString());
		mUser.setC(getC());
		try {
			ControlUser.getIstanceControlUser().saveOnDbUtente(mUser);
			ControlConnection.getIstanceControlConnection().onInsertUtente();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(c, "Your registration is done",Toast.LENGTH_LONG).show();
	}

	private Context getC() {
		return c;
	}
	
	public void setC(Context c) {
		try {
			this.c = c;
		} catch (Exception e) {
		}
	}
	
	private String createKeyUser() {
		String key=null;
		key = getTimeStamp()+ mUser.getName() + mUser.getSurname();
		return key;
	}

	public long getTimeStamp(){
		try {
			return System.nanoTime() ;
		} catch (Exception e) {

		}return 0;
	}

}
