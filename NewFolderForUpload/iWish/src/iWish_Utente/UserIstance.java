package iWish_Utente;

import iWish_Control.ControlActivities;

public class UserIstance {
	private String emailUser;
	//TODO inserire  altri attributi
	private static UserIstance instance = null;
	
	private UserIstance(){}
	
	public static synchronized  UserIstance getIstanceUserIstance(){
		if(instance==null){
			instance = new UserIstance();
		}
		return instance;
	};

}
