package iWish_Network;
/**Raffaella*/

import iWish_Context.ContextiWish;
import iWish_Control.ControlActivities;
import iWish_ControlServer.CheckConnection;
import iWish_Activities.Activities;

import java.io.BufferedReader;
import java.io.IOException;
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
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * AsyncTask enables proper and easy use of the UI thread. This class allows to perform background 
 * operations and publish results on the UI thread without having to manipulate threads and/or handlers**/
public class NetworkActivities extends AsyncTask<String, Void,  String> {
	private static final String uri= "http://www.iwishapp.eu/iwishapp/activities.php"; 
	/**Describes the state of any Wi-fi connection that is active or is in the process of being set up.*/
	private NetworkInfo mWifi,mMobile,mEthernet,mBluetooth;
	/**The POST method is used to request that the origin server accept the entity enclosed in the request 
	 * as a new subordinate of the resource identified by the Request-URI in the Request-Line. */
	private HttpPost httppost;
	/**HttpClient interface represents the most essential contract for HTTP request execution. It imposes 
	 * no restrictions or particular details on the request execution process and leaves the specifics of 
	 * connection management, state management, authentication and redirect handling up to individual 
	 * implementations.*/
	private HttpClient httpclient;
	/**This class comforms to the generic grammar and formatting rules */
	private List<NameValuePair> nameValuePairs;
	private HashMap<Long, Activities> obj;
	private long key;
	/**A JSONObject is an unordered collection of name/value pairs. A JSONObject constructor can be used 
	 * to convert an external form JSON text into an internal form whose values can be retrieved with 
	 * the get and opt methods, or to convert values into a JSON text using the put and toString methods. 
	 * A get method returns a value if one can be found, and throws an exception if one cannot be found. 
	 * An opt method returns a default value instead of throwing an exception, and so is useful for 
	 * obtaining optional values.*/
	private JSONObject json;
	/**I will setting "resending to true when I have send the file but it has not been delivered  **/
	private boolean resending= false; 
	/**The HTTP::Response class encapsulates HTTP style responses. A response consists of a response line, 
	 * some headers, and a content body*/
	private HttpResponse response;
	private String result=null;
	/**A ResponseHandler that returns the response body as a String for successful (2xx) responses. If the 
	 * response code was >= 300, the response body is consumed and an HttpResponseException is thrown.*/
	private ResponseHandler <String> resonseHandler; //Handler that encapsulates the process of generating a response object from a HttpResponse.
	private Context c;
	private static Boolean status =  true;
	private final String a="The server is connected but the Device not send registration";
	private final String b="The server isn't connected";
	private int count=0;
	private HttpResponse re;
	private String res=null;

	/**Runs on the UI thread before doInBackground(Params...).**/
	//metodo indicato prima dell'esecuzione del task
	@Override
	protected void onPreExecute() {
		Log.i("AsyncTask", "onPreExecute");
	}
	//contiene la logica del del task
	@Override
	protected String doInBackground(String... params) {
		try {
			c=ContextiWish.getIstance().getContext();
			httpclient= new DefaultHttpClient();// inizializziamo con il costruttore di default
			httppost = new HttpPost(uri); // creiamo un oggetto di tipo HttpPost
			resonseHandler = new BasicResponseHandler();
			json = new JSONObject();
			obj=new HashMap<Long, Activities>();

			takeListActivities();

			if(count==0){
				re=SendActivities(obj);
				res=readResponseFromServer(re);
			}else{
				do{
					count++;
					try {
						connection();
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}while (res==null && count!=5);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	/**I will take the list Actrivities that we have insert on db */
	private HashMap<Long,Activities> takeListActivities() {
		//Take the file to send at DbUser
		List<Activities> mActivities = ControlActivities.getIstanceControlActivities().getOnDbAllActvities();
		if(mActivities!=null){
			Long k;
			for (Activities activities : mActivities){
				k = activities.getKeyActivities();
				obj.put(k, activities);
			}
		}
		key=(mActivities.get(mActivities.size()-1)).getKeyActivities();
		return obj;
	}
	/**into this method we send the file User to the server*/
	private HttpResponse SendActivities(HashMap<Long,Activities> obj2) {
		long s = (obj2.get(key)).getKeyActivities();
		//First we create a JsonObject
		try {
			json.put("id", (obj2.get(key)).getKeyActivities());
			json.put("eMail", (obj2.get(key)).getEmailChallenger());
			json.put("idSfidato", (obj2.get(key)).getIdSfidato());
			json.put("startDate", (obj2.get(key)).getStartDate());
			json.put("endDate",(obj2.get(key)).getEndDate());
			json.put("win", (obj2.get(key)).getWin());
			json.put("KmObbiettivo", (obj2.get(key)).getKmObbiettivo());
			json.put("KmPercorsi", (obj.get(key)).getKmPercorsi());
			json.put("tipoAttivita",(obj.get(key)).getTipoAttivita());
			json.put("emailFoe",(obj.get(key)).getEmailFoe());

			Map<String, String> activities = new HashMap<String, String>();
			activities.put("Attivita", json.toString());
			nameValuePairs= new ArrayList<NameValuePair>(activities.size());
			String k,v;
			Iterator<String> itKeys= activities.keySet().iterator();
			while (itKeys.hasNext()){
				k=itKeys.next();
				v=activities.get(k);
				nameValuePairs.add(new BasicNameValuePair(k,v));
			}
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			System.out.println(nameValuePairs);
			// Execute HTTP Post Request
			response = httpclient.execute(httppost);
			return response;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**into this method we read the response that Server send to owns Device*/
	private String readResponseFromServer(HttpResponse re) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(re.getEntity().getContent(), "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");
			String line = "0";
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			reader.close();
			result= sb.toString();	
			// parsing data
			Log.d("result server", result);
			return result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**Runs on the UI thread after doInBackground(Params...). The specified result is the value returned
	 *  by doInBackground(Params...).This method won't be invoked if the task was cancelled.**/
	//utilizzo del risultato del task
	@Override
	protected void onPostExecute(String result) {
		if (result != null){
			Log.i("AsyncTask", "onPostExecute: Completed.");
			Toast.makeText(c, "Completed!We have save your activities ! ",Toast.LENGTH_LONG).show();
			//TODO finire
			obj.remove(key);
		}else {
			// error occurred
			Log.i("AsyncTask", "onPostExecute: Completed with an Error.");
			Toast.makeText(c, "Completed with an Error!Developper haven't receved your activities !",Toast.LENGTH_LONG).show();
		}
	}
	private static Boolean isConnected() {
		Runnable runnable = new Runnable() {
			public void run() {
				isNetworkAvailable(h,2000);             
			}           
		};
		runnable.run();
		return status;
	}
	private static Handler h = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			if (msg.what != 1) { // code if not connected
				status = false;
				System.out.println("Status False");
			} else { // code if connected
				status = true;
				System.out.println("Status True");
			}
		}
	};
	/**ask for message '0' (not connected) or '1' (connected) on 'handler'
	 * the answer must be send before before within the 'timeout' (in milliseconds)**/
	public static void isNetworkAvailable(final Handler handler, final int timeout) {

		new Thread() {

			private boolean responded = false;

			@Override
			public void run() {

				// set 'responded' to TRUE if is able to connect with uri (responds fast)

				new Thread() {

					@Override
					public void run() {
						HttpPost requestForTest = new HttpPost(uri);
						try {
							new DefaultHttpClient().execute(requestForTest); // can last...
							responded = true;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}.start();

				try {
					int waited = 0;
					while(!responded && (waited < timeout)) {
						sleep(100);
						if(!responded ) { 
							waited += 100;
						}
					}
				} 
				catch(InterruptedException e) {} // do nothing 
				finally { 
					if (!responded) { handler.sendEmptyMessage(0); } 
					else { handler.sendEmptyMessage(1); }
				}
			}
		}.start();
	}
	private void connection(){
		try {
			if(NetworkActivities.isConnected()){
				Toast.makeText(c, "The server is connected ",Toast.LENGTH_LONG).show();
				re= SendActivities(obj);
				res=readResponseFromServer(re);
			}else{
				Toast.makeText(c, "The server isn't connected ",Toast.LENGTH_LONG).show();
				checkMobile();
				re= SendActivities(obj);
				res=readResponseFromServer(re);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void checkMobile() {
		mMobile= CheckConnection.getIstanceCheckConnection().mMobileConnection();
		/* Indicates whether network connectivity exists or is in the process of being established. This is good for applications 
		 * that need to do anything related to the network other than read or write data. For the latter, call isConnected() instead,
		 * which guarantees that the network is fully usable.
		 * return true if network connectivity exists or is in the process of being established, false otherwise*/
		if(mMobile.isConnectedOrConnecting()){
			// Do whatever
		}else{
			Toast.makeText(c, "Please connect Mobile or wifi ",Toast.LENGTH_LONG).show();
		}
	}
	private void checkWiFi(){
		mWifi=CheckConnection.getIstanceCheckConnection().mWiFiConnection();
		/* Indicates whether network connectivity exists or is in the process of being established. This is good for applications 
		 * that need to do anything related to the network other than read or write data. For the latter, call isConnected() instead,
		 * which guarantees that the network is fully usable.
		 * return true if network connectivity exists or is in the process of being established, false otherwise*/	

		if (mWifi.isConnectedOrConnecting()) {
			// Do whatever
		}else {
			Toast.makeText(c, "Please connect wi-fi ",Toast.LENGTH_LONG).show();
		}
	}

	private void checkBluetooth(){
		/* Indicates whether network connectivity exists or is in the process of being established. This is good for applications 
		 * that need to do anything related to the network other than read or write data. For the latter, call isConnected() instead,
		 * which guarantees that the network is fully usable.
		 * return true if network connectivity exists or is in the process of being established, false otherwise*/	
		mBluetooth=CheckConnection.getIstanceCheckConnection().mBluetoothConnection();
		if(mBluetooth.isConnectedOrConnecting()){

		}else{
			Toast.makeText(c, "Please connect Bluetooth ",Toast.LENGTH_LONG).show();
		}
	}
	private void checkEthernet(){
		/* Indicates whether network connectivity exists or is in the process of being established. This is good for applications 
		 * that need to do anything related to the network other than read or write data. For the latter, call isConnected() instead,
		 * which guarantees that the network is fully usable.
		 * return true if network connectivity exists or is in the process of being established, false otherwise*/	
		mEthernet=CheckConnection.getIstanceCheckConnection().mEthernetConenction();
		if(mEthernet.isConnectedOrConnecting()){

		}else{
			Toast.makeText(c, "Please connect Ethernet ",Toast.LENGTH_LONG).show();
		}
	}
}
