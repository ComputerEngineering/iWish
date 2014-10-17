package iWish_database;

import iWish_Activities.Activities;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**This class is our DAO. 
 * It maintains the database connection and supports adding new element.**/
public class ActivitiesDao {
	private SQLiteDatabase database ;
	private DataBaseStorageActivities dbHelper;;
	private static String[] allColumns={
		DataBaseStorageActivities.COLUMN_ID,
		DataBaseStorageActivities.COLUMN_EMAIL_CHALLENGER,
		DataBaseStorageActivities.COLUMN_EMAIL_FOE,
		DataBaseStorageActivities.COLUMN_WIN,
		DataBaseStorageActivities.COLUMN_START,
		DataBaseStorageActivities.COLUMN_FINISH
	};
	
	public ActivitiesDao(Context context) {
		dbHelper = new DataBaseStorageActivities(context);
	}
	public void open() throws SQLException{
		// if the database is already open return directly
		if((database != null) && (database.isOpen())){
			return;
		}else{
			// we use getWritableDatabase() if we want insert, upload and delete
			database = dbHelper.getWritableDatabase();
		}
	}
	public void close(){
		if(database.isOpen()){
			dbHelper.close();
		}
	}
	@SuppressWarnings("unused")
	public void insertOnDbActivities(Activities mActivities)throws Exception{
		ContentValues values = new ContentValues();
		values.put(DataBaseStorageActivities.COLUMN_EMAIL_CHALLENGER,"" + mActivities.getEmailChallenger() + "");
		values.put(DataBaseStorageActivities.COLUMN_EMAIL_FOE,"" + mActivities.getEmailFoe() + "");
		values.put(DataBaseStorageActivities.COLUMN_WIN, "" + mActivities.getWinActivities() + "");
		values.put(DataBaseStorageActivities.COLUMN_START, "" + mActivities.getDateStart() + "");
		values.put(DataBaseStorageActivities.COLUMN_FINISH, "" + mActivities.getDateFinish());
		long insertId = database.insert(DataBaseStorageActivities.TABLE_ACTIVITIES, null, values);
	}
	public void deleteOnDbAllActivity(){
		database.delete(DataBaseStorageUtente.TABLE_UTENTE, null,null);
	}
	public void deleteOnDbOneActivities(Activities mActivities){
		String guid = (mActivities).getEmailChallenger();
		database.delete(DataBaseStorageActivities.TABLE_ACTIVITIES, DataBaseStorageActivities.COLUMN_ID +"='" + guid+ "'" , null);
		System.out.print("Activity that we have delete is : " + guid);
	}
	public int deleteOnDbSomeActivities(List<Activities> mActivies){
		String guid=null;
		int db=-1;
		for(Activities activities: mActivies){
			guid=activities.getEmailChallenger();
			db=database.delete(DataBaseStorageActivities.TABLE_ACTIVITIES, DataBaseStorageActivities.COLUMN_ID + "='" + guid+ "'", null);
		}return db;
	}
	public List<Activities> getAllActvities(){
		List<Activities> listActivities = new ArrayList<Activities>();
		Cursor cursor = database.query(DataBaseStorageActivities.TABLE_ACTIVITIES, allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Activities activities=cursorsActivities(cursor);
			listActivities.add(activities);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return listActivities;
	}
	private Activities cursorsActivities(Cursor cursor) {
		Activities activities = new Activities();
		activities.setKeyActivities(cursor.getLong(DataBaseStorageActivities.COLUMN_ID_INDEX));
		activities.setEmailChallenger(cursor.getString(DataBaseStorageActivities.COLUMN_EMAIL_CHALLENGER_INDEX));
		activities.setEmailFoe(cursor.getString(DataBaseStorageActivities.COLUMN_EMAIL_FOE_INDEX));
		activities.setWinActivities(cursor.getInt(DataBaseStorageActivities.COLUMN_WIN_INDEX));
		activities.setDateStart(cursor.getInt(DataBaseStorageActivities.COLUMN_START_INDEX));
		activities.setDateFinish(cursor.getInt(DataBaseStorageActivities.COLUMN_FINISH_INDEX));	
		return activities;
	}
	/**Invoked when the garbage collector has detected that this instance is no longer 
	 * reachable. The default implementation does nothing, but this method can be overridden 
	 * to free resources */
	@Override
	protected void finalize() throws Throwable {
		close();
		super.finalize();
	}
}
