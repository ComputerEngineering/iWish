package iWish_Network;

import android.os.AsyncTask;
import android.util.Log;


/**
 * AsyncTask enables proper and easy use of the UI thread. This class allows to perform background 
 * operations and publish results on the UI thread without having to manipulate threads and/or handlers**/
//TODO DA TERMINARE
public class NetworkUtente extends AsyncTask<String, Void,  String> {

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**Runs on the UI thread before doInBackground(Params...).**/
	@Override
	protected void onPreExecute() {
		Log.i("AsyncTask", "onPreExecute");
	}
	
	

}
