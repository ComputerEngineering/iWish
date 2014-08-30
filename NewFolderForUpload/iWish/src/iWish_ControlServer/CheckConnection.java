package iWish_ControlServer;
/**Raffaella*/

import iWish_Context.ContextiWish;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**With this class we can know if ours Devise've a connection active. it's may be a connection 
 * type Wi-Fi, GPRS, UMTS, etc  */
/** The Singleton is a special kind of class that ensures that only one instance of that class can be 
 * created within a program. To obtain such a behavior is necessary to make use of access specifier 
 * 'private' for the class constructor (which is usually never practiced in class "standards") and 
 * use a static method that allows access to the only instance of class*/
public class CheckConnection {

	private static CheckConnection instance= null;
	/**Class that answers queries about the state of network connectivity. It also notifies applications 
	 * when network connectivity changes*/
	private ConnectivityManager connManager,connManager2,connManager3,connManager4;
	/**Describes the state of any Wi-fi connection that is active or is in the process of being set up.*/
	private NetworkInfo mWifi,mMobile,mEthernet,mBluetooth;
	
	private CheckConnection(){}
	
	/**the only access point to the class to the outside world comes through the static method getInstance ().
	 * this method takes care of restoring (creating it first if it has never been created) 
	 * the only instance of the class.**/
	public static synchronized CheckConnection getIstanceCheckConnection(){
		if(instance==null){
			try {
				instance= new CheckConnection();
			} catch (Exception e) {
				//TODO lancia una eccezione
			}
		}
		return instance;
	}
	
	public NetworkInfo mWiFiConnection(){
		/*Use with getSystemService(String) to retrieve a ConnectivityManager for handling management of network connections.*/
		try {
			connManager = (ConnectivityManager)getContext().getSystemService(getContext().CONNECTIVITY_SERVICE);
			mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI); 
		} catch (Exception e) {
			//TODO lancia una eccezione
		}
		return mWifi;
	}
	
	public NetworkInfo mMobileConnection(){
		try {
			/*Use with getSystemService(String) to retrieve a ConnectivityManager for handling management of network connections.*/
			connManager2=(ConnectivityManager)getContext().getSystemService(getContext().CONNECTIVITY_SERVICE);
			mMobile= connManager2.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		} catch (Exception e) {
			//TODO lancia una eccezione
		}
		return mMobile;

		
	}
	public NetworkInfo mEthernetConenction(){
		/*Use with getSystemService(String) to retrieve a ConnectivityManager for handling management of network connections.*/
		try {
			connManager3=(ConnectivityManager)getContext().getSystemService(getContext().CONNECTIVITY_SERVICE);
			mEthernet=connManager3.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
		} catch (Exception e) {
			//TODO lancia una eccezione
		}
		return mEthernet;
	}
	
	public NetworkInfo mBluetoothConnection(){
		/*Use with getSystemService(String) to retrieve a ConnectivityManager for handling management of network connections.*/
		try {
			connManager4=(ConnectivityManager)getContext().getSystemService(getContext().CONNECTIVITY_SERVICE);
			mBluetooth=connManager3.getNetworkInfo(ConnectivityManager.TYPE_BLUETOOTH);
		} catch (Exception e) {
			//TODO lancia una eccezione
		}
		return mBluetooth;
	}
	
	private Context getContext(){
		try {
			return ContextiWish.getIstance().getContext();
		} catch (Exception e) {
			//TODO lancia una eccezione
		}
		return null;
	}
}
