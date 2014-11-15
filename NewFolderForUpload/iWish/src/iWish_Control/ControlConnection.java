package iWish_Control;
/**Raffaella*/

import iWish_Network.NetworkActivities;
import iWish_Network.NetworkFriends;
import iWish_Network.NetworkSession;
import iWish_Network.NetworkUtente;

/** The Singleton is a special kind of class that ensures that only one instance of that class can be 
 * created within a program. To obtain such a behavior is necessary to make use of access specifier 
 * 'private' for the class constructor (which is usually never practiced in class "standards") and 
 * use a static method that allows access to the only instance of class*/
//TODO DA TERMINARE
public class ControlConnection {

	private static ControlConnection istanz;

	private ControlConnection(){}
	
	/**the only access point to the class to the outside world comes through the static method getInstance ().
	 * this method takes care of restoring (creating it first if it has never been created) 
	 * the only instance of the class.**/
	public static synchronized ControlConnection getIstanceControlConnection(){
		if(istanz==null){
			istanz = new ControlConnection();
		}
		return istanz;
	};
	
	public void onInsertUtente(){
		new NetworkUtente().execute();
	}
	
	public void onInsertFriends(){
		new NetworkFriends().execute();
	}
	
	public void onInsertSession(String beats){
		new NetworkSession().execute(beats);
	}
	
	public void onInsertActivities(){
		new NetworkActivities().execute();
	}


}
