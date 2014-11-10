package iWish_database;
/** Raffaella*/

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

/**This class is responsible for creating the database.
 * SQLiteDatabase is the base class for working with a SQLite database in Android and 
 * provides methods to open, query, update and close the database.
 * More specifically SQLiteDatabase provides the insert(), update() and delete() methods.
 * In addition it provides the execSQL() method, which allows to execute SQL directly.*/

public class DataBaseStorageFriends extends SQLiteOpenHelper {

	static final String TABLE_FRIENDS= "Friends";
	
	static final String COLUMN_ID="_id";
	static final String COLUMN_EMAIL_FRIENDS="emailFirends";
	static final String COLUMN_EMAIL_USER="emailUser";
	static final String COLUMN_NAME="nome";
	static final String COLUMN_SURNAME="surname";
	static final String COLUMN_POINT="point";
	
//	static final String COLUMN_PHOTO="photo";
	
	// shared index, make sure to match COLUMNS
	static final int COLUMN_ID_INDEX=0;
	static final int COLUMN_EMAIL_FRIENDS_INDEX=1;
	static final int COLUMN_EMAIL_USER_INDEX=2;
	static final int COLUMN_NAME_INDEX=3;
	static final int COLUMN_SURNAME_INDEX=4;
	static final int COLUMN_POINT_INDEX=5;
	
//  static final int COLUMN_PHOTO_INDEX=4;
	
	/** this is the version of DB. 
	 *  we must increase this number when the DB  is change **/
	private  static  final  int DATABASE_VERSION =  3;
	
	/** this's the DB's name**/
	private static final String DATABASE_NAME= "Friends.db";
	
	/** this 's the query that we use for create the table
	 *  Android use the _id  for identify the element's key **/
	private static final String DATABASE_CREATE = "create table " + TABLE_FRIENDS + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_EMAIL_FRIENDS + " vachar(25) NOT NULL, "
			+ COLUMN_EMAIL_USER + " vachar(25) NOT NULL, "
			+ COLUMN_NAME + " vachar(15) NOT NULL, "
			+ COLUMN_SURNAME + " vachar(15) NOT NULL, "
			+ COLUMN_POINT + " numeric(4) NOT NULL "
			
			//TODO vedere come definire l'attributo photo
		//	+ COLUMN_PHOTO + " ......."
			+ ");";
	
	public DataBaseStorageFriends(Context context) {
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
		db.execSQL("DROP TABLE IF EXISTS "+ TABLE_FRIENDS);
		onCreate(db);
	}
}
