package iWish_Control;

import iWish_Activities.Activities;
import iWish_database.ActivitiesDao;

import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

public class ControlActivities{
	private ActivitiesDao mActivitiesDao;
	private static ControlActivities instance = null;

	private ControlActivities(){}

	public static synchronized ControlActivities getIstanceControlActivities(){
		if(instance==null){
			instance = new ControlActivities();
		}
		return instance;
	};
	public void saveOnDbActivities(Activities mActivities, Context context)throws Exception{
		try {
			mActivitiesDao= new ActivitiesDao(context);
			mActivitiesDao.open();
			mActivitiesDao.insertOnDbActivities(mActivities);
			//mActivitiesDao.close();
		} catch (SQLException e) {

		}
	}
	public void deleteOnDbActivitiesAll(){
		try {
			mActivitiesDao.deleteOnDbAllActivity();
		} catch (Exception e) {

		}
	}
	public void deleteOnDbOneActivities(Activities mActivities){
		try {
			mActivitiesDao.deleteOnDbOneActivities(mActivities);
		} catch (Exception e) {

		}
	}
	public void deleteOnDbSomeActivities(List<Activities> mActivities){
		try {
			mActivitiesDao.deleteOnDbSomeActivities(mActivities);
		} catch (Exception e) {

		}
	}
	
	public List<Activities> getOnDbAllActvities(Context context){
		try {
			mActivitiesDao= new ActivitiesDao(context);
			mActivitiesDao.open();
			return mActivitiesDao.getAllActvities();
		} catch (Exception e) {

		}
		return null;
	}
	
	public int getOnDbHowManyActvitiesUser(String eMail, Context context){
		
		try {
			mActivitiesDao= new ActivitiesDao(context);
			mActivitiesDao.open();
			int numAtt = mActivitiesDao.getHowManyActvitiesUser(eMail);
			//mActivitiesDao.close();
			return numAtt;
		} catch (SQLException e) {
			Log.i("getOnDbHowManyActvitiesUser", "abbiamo un errore nella query di check delle activities");
			e.printStackTrace();
		}
		return -1;
	}
	public void ConnectionActivities(){
		try {
			//ControlConnection.getIstanceControlConnection().Activities();
		} catch (Exception e) {
		}
	}
}
