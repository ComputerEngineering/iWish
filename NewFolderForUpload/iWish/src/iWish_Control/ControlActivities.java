package iWish_Control;

import iWish_Activities.Activities;
import iWish_database.ActivitiesDao;

import java.util.List;

import android.database.SQLException;

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
	public void saveOnDbActivities(Activities mActivities)throws Exception{
		try {
			mActivitiesDao= new ActivitiesDao(mActivities.getC());
			mActivitiesDao.open();
			mActivitiesDao.insertOnDbActivities(mActivities);
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
	
	public List<Activities> getOnDbAllActvities(){
		try {
			return mActivitiesDao.getAllActvities();
		} catch (Exception e) {

		}
		return null;
	}
	public void ConnectionActivities(){
		try {
			//ControlConnection.getIstanceControlConnection().Activities();
		} catch (Exception e) {
		}
	}
}
