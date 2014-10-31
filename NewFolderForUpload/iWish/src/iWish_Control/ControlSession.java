package iWish_Control;

import iWish_Session.Session;
import iWish_database.SessionDao;

import java.util.List;

import android.content.Context;

public class ControlSession {

	private static ControlSession instance = null;
	private SessionDao mSessionDao;

	private ControlSession(){}


	public static synchronized ControlSession getIstanceControlSession(){
		if(instance==null){
			instance = new ControlSession();
		}
		return instance;
	};


	public void saveOnDbSession(Session mSession, Context context) throws Exception{
		try {
			mSessionDao =  new SessionDao(context);
			mSessionDao.open();
			mSessionDao.insertOnDbUtente(mSession);
		} catch (Exception e) {

		}
	}

	public void deleteOnDbAllSession(){
		try {
			mSessionDao.deleteOnDbAllSession();
		} catch (Exception e) {

		}
	}

	public List<Session> getAllSessionOfOneActivities(Long KeyActivities){
		try {
			return mSessionDao.getAllSessionOfOneActivities(KeyActivities);
		} catch (Exception e) {

		}
		return null;
	}
	
	public List<Session> getOnDbAllSession(){
		try {
			return mSessionDao.getAllSession();
		} catch (Exception e) {

		}
		return null;
	}


	public void ConnectionSession(){
		try {
			//ControlConnection.getIstanceControlConnection().Session();
		} catch (Exception e) {
		}
	}
}