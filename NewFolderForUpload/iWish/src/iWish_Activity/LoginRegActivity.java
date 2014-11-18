package iWish_Activity;
/**Antonio */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import iWish_Context.ContextiWish;
import iWish_Control.ControlFriends;
import iWish_Control.ControlUser;
import iWish_Friends.Friends;
import iWish_Utente.Utente;

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginRegActivity extends Activity{
	private EditText edt_username;
	private EditText edt_password;
	private ImageButton bt_ok_go;
	private ImageButton bt_forgot_psw;
	private ImageButton bt_fb;
	private ImageButton bt_register;
	private Utente mUtente;
	private String url = "http://www.iwishapp.eu/iwishapp/login.php";
	private String jsonResult;
	private ProgressBar waitLogin;



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
		waitLogin=(ProgressBar)findViewById(R.id.progressLog);
		waitLogin.setVisibility(View.INVISIBLE);

		bt_ok_go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				controlLogin();
				//startActivity(new Intent(LoginRegActivity.this, ProfileActivity.class ));
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

	private void controlLogin(){
		if((edt_username.getText().toString().equals(""))||(edt_password.getText().toString().equals(""))){
			CharSequence missData= "email or password missing! ";
			Toast.makeText(getApplicationContext(), missData, Toast.LENGTH_SHORT).show();
		}
		else {
			//controllo in locale//
			String eMailUtente=edt_username.getText().toString();
			Boolean eMailExist = ControlUser.getIstanceControlUser().checkRegistrationOnDbOneUtente(eMailUtente,getApplicationContext());
			if(eMailExist){
				mUtente = ControlUser.getIstanceControlUser().getOneUtente(eMailUtente,getApplicationContext());
				System.out.println(mUtente);
				if(edt_password.getText().toString().equals(mUtente.getPassword())){
					Intent intent = new Intent(LoginRegActivity.this,ProfileActivity.class );
					intent.putExtra("u",mUtente);
					startActivity(intent);
				}
				else {
					CharSequence pswErrata= "invalid password or email! ";
					Toast.makeText(getApplicationContext(), pswErrata, Toast.LENGTH_SHORT).show();
				}
			}
			else {
				accessWebService();
			}
		}
	}

	// Async Task to access the web
	private class JsonReadTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute(){
			waitLogin.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(String... params) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(params[0]);

			try {
				JSONObject json = new JSONObject();;
				json.put("eMailUser", params[1]);

				List<NameValuePair> nameValuePairs;
				Map<String, String> user = new HashMap<String, String>();
				user.put("User", json.toString());
				nameValuePairs= new ArrayList<NameValuePair>(user.size());
				String k,v;
				Iterator<String> itKeys= user.keySet().iterator();
				while (itKeys.hasNext()){
					k=itKeys.next();
					v=user.get(k);
					nameValuePairs.add(new BasicNameValuePair(k,v));
				}
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				System.out.println(nameValuePairs);

				HttpResponse response = httpclient.execute(httppost);
				jsonResult = inputStreamToString(
						response.getEntity().getContent()).toString();
			}

			catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				cancel(true);
				return null;
			}
			catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
			return null;
		}

		private StringBuilder inputStreamToString(InputStream is) { 
			System.out.println(is);
			String rLine = "";
			StringBuilder answer = new StringBuilder();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));

			try {
				while ((rLine = rd.readLine()) != null) {
					answer.append(rLine);
				}
			}

			catch (IOException e) {
				// e.printStackTrace();
				Toast.makeText(getApplicationContext(),
						"Error..." + e.toString(), Toast.LENGTH_LONG).show();
			}
			return answer;
		}

		protected void onCancelled(){
			super.onCancelled();
			waitLogin.setVisibility(View.INVISIBLE);
			CharSequence pass= "Not connected to the internet or server error";
			Toast.makeText(getApplicationContext(), pass, Toast.LENGTH_LONG).show();
		}

		@Override
		protected void onPostExecute(String result) {
			System.out.println(jsonResult);
			waitLogin.setVisibility(View.INVISIBLE);
			if(jsonResult.equals("[]")){
				CharSequence pass= "eMail friends no found";
				Toast.makeText(getApplicationContext(), pass, Toast.LENGTH_LONG).show();
			}
			else{
				try {
					JSONObject jsonResponse = new JSONObject(jsonResult);
					JSONArray jsonMainNode = jsonResponse.optJSONArray("User");
					System.out.println(jsonResponse);
					System.out.println(jsonMainNode);
					JSONObject jsonChildNode = jsonMainNode.getJSONObject(0);
					//quello che ricevo dal db online//
					String eMail = jsonChildNode.optString("eMail");
					String name = jsonChildNode.optString("name");
					String surname = jsonChildNode.optString("surname");
					String birthday = jsonChildNode.optString("birthday");
					String city = jsonChildNode.optString("city");
					String password = jsonChildNode.optString("password");
					String point = jsonChildNode.optString("point");
					String altezza = jsonChildNode.optString("altezza");
					String tipoUser = jsonChildNode.optString("tipoUser");
					String sex= jsonChildNode.optString("sex");
					String answer = jsonChildNode.optString("answer");
					String question = jsonChildNode.optString("question");
					//una volta ricevuti l'user creo un oggetto user e lo passo al db locale//
					Utente user = new Utente();
					user.setEmail(eMail);
					user.setName(name);
					user.setSurname(surname);
					user.setBirthday(birthday);
					user.setCity(city);
					user.setPassword(password);
					//user.setPoint(point);
					user.setHeight(Integer.parseInt (altezza));
					user.setTypeUser(tipoUser);
					user.setSex(sex);
					user.setAnswer(answer);
					user.setQuestion(question);


					//metodo per mandare dati al db locale//
					try{
						ControlUser.getIstanceControlUser().saveOnDbUtente(user,getApplicationContext());
						mUtente=user;
					}catch  (Exception e) {
						Log.i("LogRegActivity", "errore login : utente inesistente");
					}

					CharSequence mexLogin= "Login eseguito" ;
					Toast.makeText(getApplicationContext(), mexLogin, Toast.LENGTH_LONG).show();
				} 
				catch (JSONException e) {
					Toast.makeText(getApplicationContext(), "Error login" + e.toString(),Toast.LENGTH_SHORT).show();
				}
				//utente esistente sul server online
				if(mUtente!=null){
					if(edt_password.getText().toString().equals(mUtente.getPassword())){
						Intent intent = new Intent(LoginRegActivity.this,ProfileActivity.class );
						intent.putExtra("u",mUtente);
						startActivity(intent);
					}
					else {
						CharSequence pswErrata= "invalid password or email3! ";
						Toast.makeText(getApplicationContext(), pswErrata, Toast.LENGTH_SHORT).show();
					}


				}
				else {
					CharSequence pswErrata= "invalid password or email 2! ";
					Toast.makeText(getApplicationContext(), pswErrata, Toast.LENGTH_SHORT).show();
				}
			}
		}
	}// end async task

	public void accessWebService(){
		JsonReadTask task = new JsonReadTask();
		// passes values for the urls string array
		task.execute(new String[] { url, edt_username.getText().toString()});
	}

}

