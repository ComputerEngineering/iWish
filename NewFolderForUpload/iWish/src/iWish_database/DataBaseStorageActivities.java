package iWish_database;
/**Raffaella*/ 

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseStorageActivities extends SQLiteOpenHelper {
	/**This class is responsible for creating the database.
	 * SQLiteDatabase is the base class for working with a SQLite database in Android and 
	 * provides methods to open, query, update and close the database.
	 * More specifically SQLiteDatabase provides the insert(), update() and delete() methods.
	 * In addition it provides the execSQL() method, which allows to execute SQL directly.*/
	static final String TABLE_ACTIVITIES = "Activities";
	
	static final String COLUMN_ID="id";
	static final String COLUMN_EMAIL_CHALLENGER="EmailChallenger";
	static final String COLUMN_EMAIL_FOE="EmailFoe";
//	static final String COLUMN_WIN="WinActivities";
	//TODO inserire start e finish
	
	static final int COLUMN_ID_INDEX=0;
	static final int COLUMN_EMAIL_CHALLENGER_INDEX=1;
	static final int COLUMN_EMAIL_FOE_INDEX=2;
//	static final int COLUMN_WIN_INDEX=3;
	
	/** this is the version of DB. 
	 *  we must increase this number when the DB  is change **/
	private  static  final  int DATABASE_VERSION =  1;
	/** this's the DB's name**/
	private static final String DATABASE_NAME= "Activities.db";
	/** this 's the query that we use for create the table
	 *  Android use the _id  for identify the element's key **/
	
	private static final String DATABASE_CREATE  = "create table " + TABLE_ACTIVITIES + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_EMAIL_CHALLENGER + " vachar(15) NOT NULL,"
			+ COLUMN_EMAIL_FOE + " vachar(30) NOT NULL,"
		//	+ COLUMN_WIN + " vachar(15) NOT NULL"
			+ ");";
	
	public DataBaseStorageActivities(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**with this method we create the table. onCreate(---) is called when we created DB for the first time
	 * or better when the application is just installed **/
	@Override
	public void onCreate(SQLiteDatabase db) {
		/*we use execSQL(---) if we want execute SQL's code*/
		db.execSQL(DATABASE_CREATE);
	}
	/** this method in called when DB just exist but, the when the number version is absolutely. 
	 * Usually it's called when the number Version of an application just exist is changed
	 * This method allows you to update the database schema.**/
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DataBaseStorageUtente.class.getName(),"Upgrading database from version " + oldVersion 
				+ "to" + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ACTIVITIES);
		onCreate(db);
	}
}
