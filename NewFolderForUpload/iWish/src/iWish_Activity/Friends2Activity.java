package iWish_Activity;


import iWish_Activities.Activities;
import iWish_Control.ControlFriends;
import iWish_Control.ControlUser;
import iWish_Friends.Friends;
import iWish_Utente.UserIstance;
import iWish_database.FriendsDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import sottostante utile per effettuare comparazione e ordinamenti //
import java.util.Comparator;

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

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.app.ListActivity;

import com.progect.iwish.R;

/**Antonio*/

public class Friends2Activity extends Activity {

	private TextView FriendsList;
	private TextView DeleteFriends;
	private FriendsDao datasource;  
	private List<Friends> values = null;
	private Friends mFriends;
	private ListView mListView;
	private EditText emailFriends;
	private String jsonResult;
	private String url = "http://www.iwishapp.eu/iwishapp/addfriend.php";
	private String url2 = "http://www.iwishapp.eu/iwishapp/deletefriends.php";
	private ProgressBar waitfriends;
	private String eMailFriends;
	private String deleteEmailFriends;
	private String eMailUser;
	private String name;
	private String surname;
	private Button search;
	private ImageButton botcerca;
	private List<Map> data;
	private SimpleAdapter adapter;
	private ImageButton bottomino;
	private String challengeProfile;
	private Intent intent;
	private Activities mActivities;
	private Button delete;
	private EditText deleteemailfriends;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friends2);
		// quando arriva un intent e voglio prendere qualcosa//
		intent = getIntent();
		challengeProfile =(String)intent.getSerializableExtra("Activity");

		delete = (Button)findViewById(R.id.deletemail);
		delete.setVisibility(View.INVISIBLE);
		deleteemailfriends = (EditText)findViewById(R.id.deleteemailfriends);
		deleteemailfriends.setVisibility(View.INVISIBLE);
		eMailUser = UserIstance.getIstanceUserIstance().getEmailUser();
		name = UserIstance.getIstanceUserIstance().getName();
		surname = UserIstance.getIstanceUserIstance().getSurname();
		emailFriends=(EditText)findViewById(R.id.emailfriends);
		emailFriends.setVisibility(View.INVISIBLE);
		search=(Button)findViewById(R.id.search);
		search.setVisibility(View.INVISIBLE);
		waitfriends=(ProgressBar)findViewById(R.id.waitfriends);
		waitfriends.setVisibility(View.INVISIBLE);
		datasource = new FriendsDao(this);
		datasource.open();
		values = datasource.getAllFriends();
		mListView = (ListView)findViewById(R.id.listfriends2);
		data = getFriendsData(values);
		adapter = new SimpleAdapter(this, (List<? extends Map<String, ?>>) data, R.xml.friends2item, new String[] { "nome", "cognome", "email" }, new int[] { R.id.nome, R.id.cognome, R.id.email });
		mListView.setAdapter(adapter);


		//datasource = new FriendsDao(this);
		//datasource.open();
		//mFriends = new Friends();
		//mFriends.setName("antonio");
		//mFriends.setSurname("torcasio");
		//mFriends.setPoint(0);
		//mFriends.setEmailFriends("antonio@libero.it");
		//mFriends.setEmailUser("amico@libero.it");

		//metodo per mandare dati al db//
		//try{
		//ControlFriends.getIstanceControlFriends().saveOnDbFriends(mFriends,getApplicationContext());
		//}catch  (Exception e) {
		//	Log.i("Friends2Activity", "errore query");
		//}



		//uso il simplecursoradapter per mostrare gli elementi della listview

		//ArrayAdapter<Friends> adapter= new ArrayAdapter<Friends>(this,android.R.layout.simple_list_item_1,values);


		FriendsList = (TextView)findViewById(R.id.friendslist); 
		DeleteFriends = (TextView)findViewById(R.id.deletefriends);
		botcerca = (ImageButton)findViewById(R.id.bot_cerca);
		bottomino =(ImageButton)findViewById(R.id.bott_omino);
		if(challengeProfile!=null){
			bottomino.setVisibility(View.INVISIBLE);
			botcerca.setVisibility(View.INVISIBLE);
			FriendsList.setVisibility(View.INVISIBLE);
			DeleteFriends.setVisibility(View.INVISIBLE);
			//per ritornare attività di challenger//
			mActivities = (Activities)intent.getSerializableExtra("a");
		}

		FriendsList.setOnClickListener(myListener);
		DeleteFriends.setOnClickListener(myListener);
		search.setOnClickListener(myListener);
		botcerca.setOnClickListener(myListener);
		bottomino.setOnClickListener(myListener);
		delete.setOnClickListener(myListener);
	}
	OnClickListener myListener=new View.OnClickListener(){

		public void onClick(View v) {
			@SuppressWarnings("unchecked")
			//	ArrayAdapter<Friends> adapter = (ArrayAdapter<Friends>)getListAdapter();
			Friends friends = null;
			if(v==bottomino){
				Intent intent3 = new Intent(Friends2Activity.this, ProfileActivity.class );
				startActivity(intent3);
			}
			if (v==FriendsList)   {
				delete.setVisibility(View.INVISIBLE);
				deleteemailfriends.setVisibility(View.INVISIBLE);
				values = datasource.getAllFriends();
				data = getFriendsData(values);
				adapter=setAdapter();
				mListView.setAdapter(adapter);
				emailFriends.setVisibility(View.INVISIBLE);
				search.setVisibility(View.INVISIBLE);
				mListView.setVisibility(View.VISIBLE);

			}
			if (v==DeleteFriends) {
				mListView.setVisibility(View.INVISIBLE);
				emailFriends.setVisibility(View.INVISIBLE);
				search.setVisibility(View.INVISIBLE);
				delete.setVisibility(View.VISIBLE);
				deleteemailfriends.setVisibility(View.VISIBLE);
				
			}

			if (v==botcerca) {
				delete.setVisibility(View.INVISIBLE);
				deleteemailfriends.setVisibility(View.INVISIBLE);
				mListView.setVisibility(View.INVISIBLE);
				emailFriends.setVisibility(View.VISIBLE);
				search.setVisibility(View.VISIBLE);
			}
			if(v==search){
				if(emailFriends.getText().toString().equals("")){
					CharSequence eMail= "eMail or answer missing!!!";
					Toast.makeText(getApplicationContext(), eMail, Toast.LENGTH_LONG).show();
				}
				else{
					eMailFriends = emailFriends.getText().toString();
					accessWebService(); //inserire amico//
				}

			}
			if(v==delete){
				if(deleteemailfriends.getText().toString().equals("")){
					CharSequence eMailMissing= "eMail or answer missing!!!";
					Toast.makeText(getApplicationContext(), eMailMissing, Toast.LENGTH_LONG).show();
				}
				else{
					deleteEmailFriends = deleteemailfriends.getText().toString();
					accessWebServiceDelete();
				}

			}

			//	adapter.notifyDataSetChanged();
		}
	};
	protected SimpleAdapter setAdapter(){
		adapter = new SimpleAdapter(this, (List<? extends Map<String, ?>>) data, R.xml.friends2item, new String[] { "nome", "cognome", "email" }, new int[] { R.id.nome, R.id.cognome, R.id.email });
		return adapter;
	}
	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}
	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

	private List<Map> getFriendsData(List<Friends> mFriends ) {
		List<Map>list = new ArrayList<Map>();
		Map map = new HashMap();
		Iterator<Friends> it= mFriends.iterator(); 
		while(it.hasNext()){
			Friends amico = it.next(); 
			map.put("nome", amico.getName());
			map.put("cognome", amico.getSurname());
			map.put("email", amico.getEmailFriends());
			list.add(map);
			map=new HashMap();
		}
		return list;
	}
	// Async Task to access the web
	private class JsonReadTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute(){
			waitfriends.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(String... params) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(params[0]);

			try {
				JSONObject json = new JSONObject();;
				json.put("eMailFriends", params[1]);
				json.put("eMailUser", params[2]);
				json.put("name", params[3]);
				json.put("surname", params[4]);

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
			waitfriends.setVisibility(View.INVISIBLE);
			CharSequence pass= "Not connected to the internet or server error";
			Toast.makeText(getApplicationContext(), pass, Toast.LENGTH_LONG).show();
		}

		@Override
		protected void onPostExecute(String result) {
			System.out.println(jsonResult);
			waitfriends.setVisibility(View.INVISIBLE);
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
					String name = jsonChildNode.optString("name");
					String surname = jsonChildNode.optString("surname");
					String point = jsonChildNode.optString("point");
					//una volta ricevuti gli amici creo un oggetto friends e lo passo al db locale//
					Friends amico = new Friends();
					amico.setName(name);
					amico.setSurname(surname);
					//faccio il cast di punti a int //
					int punti = 0 ;
					try {
						punti = Integer.parseInt(point);
					} 
					catch (Exception e){ 
						e.printStackTrace();

					}
					amico.setPoint(punti);
					amico.setEmailFriends(eMailFriends);
					amico.setEmailUser(eMailUser);


					//metodo per mandare dati al db locale//
					try{
						ControlFriends.getIstanceControlFriends().saveOnDbFriends(amico,getApplicationContext());
					}catch  (Exception e) {
						Log.i("Friends2Activity", "errore query");
					}

					CharSequence amico2= "Friend added" ;
					Toast.makeText(getApplicationContext(), amico2, Toast.LENGTH_LONG).show();
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
		task.execute(new String[] { url, eMailFriends,eMailUser, name, surname});
	}
	
	// Async Task to delete from the web
		private class JsonDeleteTask extends AsyncTask<String, Void, String> {
			@Override
			protected void onPreExecute(){
				waitfriends.setVisibility(View.VISIBLE);
			}

			@Override
			protected String doInBackground(String... params) {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(params[0]);

				try {
					JSONObject json = new JSONObject();;
					json.put("eMailFriends", params[1]);
					json.put("eMailUser", params[2]);
					
                    List<NameValuePair> nameValuePairs;
					Map<String, String> user = new HashMap<String, String>();
					//"Friends2" lo ritrovo nel php online//
					user.put("Friends", json.toString());
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
				waitfriends.setVisibility(View.INVISIBLE);
				CharSequence pass= "Not connected to the internet or server error";
				Toast.makeText(getApplicationContext(), pass, Toast.LENGTH_LONG).show();
			}

			@Override
			protected void onPostExecute(String result) {
				System.out.println(jsonResult);
				waitfriends.setVisibility(View.INVISIBLE);
				if(jsonResult.equals("[]")){
					CharSequence pass= "eMail friends no found";
					Toast.makeText(getApplicationContext(), pass, Toast.LENGTH_LONG).show();
				}
				else{
					try {
						JSONObject jsonResponse = new JSONObject(jsonResult);
						JSONArray jsonMainNode = jsonResponse.optJSONArray("Friends1");
						System.out.println(jsonResponse);
						System.out.println(jsonMainNode);
						//JSONObject jsonChildNode = jsonMainNode.getJSONObject(0);
						//quello che ricevo dal db online//
						//String name = jsonChildNode.optString("name");
						//String surname = jsonChildNode.optString("surname");
						//String point = jsonChildNode.optString("point");
						//una volta ricevuti gli amici creo un oggetto friends e lo passo al db locale//
						//Friends amico = new Friends();
						//amico.setName(name);
						//amico.setSurname(surname);
						//faccio il cast di punti a int //
						//int punti = 0 ;
						//try {
					    //		punti = Integer.parseInt(point);
						//} 
						//catch (Exception e){ 
					    //		e.printStackTrace();

						//}
						//amico.setPoint(punti);
						//amico.setEmailFriends(eMailFriends);
						//amico.setEmailUser(eMailUser);


						//metodo per mandare dati al db locale//
						try{
							ControlFriends.getIstanceControlFriends().deleteOnDBOneFriends(deleteEmailFriends, getApplicationContext());
						}catch  (Exception e) {
							Log.i("Friends2Activity", "errore query");
						}

						CharSequence amico2= "Friend deleted" ;
						Toast.makeText(getApplicationContext(), amico2, Toast.LENGTH_LONG).show();
					} 
					catch (JSONException e) {
						Toast.makeText(getApplicationContext(), "Error" + e.toString(),Toast.LENGTH_SHORT).show();
					}
				}
			}
		}// end async task

		public void accessWebServiceDelete(){
			JsonDeleteTask task = new JsonDeleteTask();
			// passes values for the urls string array
			task.execute(new String[] { url2, deleteEmailFriends,eMailUser});
		}

	//@Override
	//public void onBackPressed() {
	//Intent intent3 = new Intent(Friends2Activity.this, ProfileActivity.class );
	//startActivity(intent3);

	//}

}



