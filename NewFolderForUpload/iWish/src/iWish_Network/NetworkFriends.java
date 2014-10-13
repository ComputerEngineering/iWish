package iWish_Network;

import iWish_Context.ContextiWish;

import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import _iWish.MyFriends;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

/**
 * AsyncTask enables proper and easy use of the UI thread. This class allows to perform background 
 * operations and publish results on the UI thread without having to manipulate threads and/or handlers**/
//TODO DA TERMINARE
public class NetworkFriends extends AsyncTask<String, Void,  String>{
	private static final String uri= "http://....."; //devo sapere l'uri
	private NetworkInfo mWifi,mMobile,mEthernet,mBluetooth;
	private HttpPost httppost;
	private HttpClient httpclient;
	private List<NameValuePair> nameValuePairs;
	private HashMap<String, MyFriends> obj;
	private long key;
	private JSONObject json;
	private boolean resending= false; 
	private HttpResponse response;
	private String result=null;
	private ResponseHandler <String> resonseHandler;
	private Context c;
	private static Boolean status =  true;
	private final String a="The server is connected but the Device not send registration";
	private final String b="The server isn't connected";
	private int count=0;
	private HttpResponse re;
	private String res=null;

	@Override
	protected String doInBackground(String... params) {
		c=ContextiWish.getIstance().getContext();
		httpclient= new DefaultHttpClient(); // inizializziamo con il costruttore di default
		httppost = new HttpPost(uri); // creiamo un oggetto di tipo HttpPost
		resonseHandler = new BasicResponseHandler();
		json = new JSONObject();
		obj = new HashMap<String, MyFriends>();
		
		takeListFriends();
		
		return res;
	}
	
	private HashMap<String, MyFriends> takeListFriends() {
		return obj;
	}

	/**Runs on the UI thread before doInBackground(Params...).**/
	@Override
	protected void onPreExecute() {
		Log.i("AsyncTask", "onPreExecute");
	}
}
