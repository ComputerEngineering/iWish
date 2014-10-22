package iWish_Activity;
/**Alessandro*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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

import com.progect.iwish.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ForgotPswActivity extends Activity implements OnItemSelectedListener{
	private EditText edt_eMail=null;
	private EditText edt_answer=null;
	private String eMail=null;
	private String answer=null;
	private Spinner sp_questions=null;
	private String question;
	private Button oksend;
	private String jsonResult;
	private String url = "http://iwish.suroot.com/iwishapp/forgot.php";

	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.forgotpsw);
	    
	    
	    sp_questions =(Spinner) findViewById(R.id.spinnerQuestion);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.question, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    sp_questions.setAdapter(adapter);
	    sp_questions.setOnItemSelectedListener(this);
	    
	    
		edt_eMail = (EditText) findViewById(R.id.editTextForgotMail);
		edt_answer =(EditText) findViewById(R.id.editTextForgotAnswer);
	    
	    oksend = (Button)findViewById(R.id.bt_oksend);
		oksend.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				if(edt_eMail.getText().toString().equals("") || edt_answer.getText().toString().equals("")){
					CharSequence eMail= "eMail or answer missing!!!";
					Toast.makeText(getApplicationContext(), eMail, Toast.LENGTH_LONG).show();
				}
				else{
					eMail = edt_eMail.getText().toString();
					answer = edt_answer.getText().toString();
					accessWebService();
				}
				
				//startActivity(new Intent(ForgotPswActivity.this,LoginRegActivity.class ));
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
    
 // Async Task to access the web
 	private class JsonReadTask extends AsyncTask<String, Void, String> {
 		@Override
 		protected String doInBackground(String... params) {
 			HttpClient httpclient = new DefaultHttpClient();
 			HttpPost httppost = new HttpPost(params[0]);
 			
 			try {
 				JSONObject json = new JSONObject();;
 				json.put("eMail", params[1]);
 				json.put("question", params[2]);
 				json.put("answer", params[3]);
 				
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
 			CharSequence pass= "Not connected to the internet or server error";
			Toast.makeText(getApplicationContext(), pass, Toast.LENGTH_LONG).show();
 		}

 		@Override
 		protected void onPostExecute(String result) {
 			System.out.println(jsonResult);
 			if(jsonResult.equals("[]")){
 				CharSequence pass= "eMail or answer error";
 				Toast.makeText(getApplicationContext(), pass, Toast.LENGTH_LONG).show();
 			}
 			else{
 				try {
 	 				JSONObject jsonResponse = new JSONObject(jsonResult);
 	 				JSONArray jsonMainNode = jsonResponse.optJSONArray("User");
 	 				JSONObject jsonChildNode = jsonMainNode.getJSONObject(0);
 	 				String password = jsonChildNode.optString("password");
 	 				CharSequence pass= "Your Password is: " + password;
 	 				Toast.makeText(getApplicationContext(), pass, Toast.LENGTH_LONG).show();
 	 			} 
 	 			catch (JSONException e) {
 	 				Toast.makeText(getApplicationContext(), "Error" + e.toString(),Toast.LENGTH_SHORT).show();
 	 			}
 			}
 		}
 	}// end async task
    
    public void accessWebService(){
    	JsonReadTask task = new JsonReadTask();
		// passes values for the urls string array
		task.execute(new String[] { url, eMail, question, answer});
    }
    
}
