package iWish_Context;
/**Raffaella*/

import android.content.Context;

/** The Singleton is a special kind of class that ensures that only one instance of that class can be 
 * created within a program. To obtain such a behavior is necessary to make use of access specifier 
 * 'private' for the class constructor (which is usually never practiced in class "standards") and 
 * use a static method that allows access to the only instance of class*/

public class ContextiWish {
	
	private static ContextiWish istanz=null;
	private Context c;
	
	private ContextiWish(){}

	/**the only access point to the class to the outside world comes through the static method getInstance ().
	 * this method takes care of restoring (creating it first if it has never been created) 
	 * the only instance of the class.**/
	public static synchronized ContextiWish getIstance(){
		if(istanz == null){
			istanz = new ContextiWish();
		}
		return istanz;
	}
	
	public Context geContext(){
		return c;
	}
	
	public void setContext(Context c){
		this.c=c;
	}
	
}
