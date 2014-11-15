package iWish_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**This class is responsible for creating the database.
 * SQLiteDatabase is the base class for working with a SQLite database in Android and 
 * provides methods to open, query, update and close the database.
 * More specifically SQLiteDatabase provides the insert(), update() and delete() methods.
 * In addition it provides the execSQL() method, which allows to execute SQL directly.*/

public class DataBaseStorageSession extends SQLiteOpenHelper {

	static final String TABLE_SESSION = "Session";

	static final String COLUMN_ID="_id";
	static final String COLUMN_ID_ACTIVITIES="idActivities";
	static final String COLUMN_DURATA_TEMPO="durataTempo";
	static final String COLUMN_BATTITI_MAX="battitiMax";
	static final String COLUMN_BATTITI_MIN="battiMin";
	static final String COLUMN_BATTITI_MED="battitiMed";
	static final String COLUMN_ALTEZZA_MAX="altezzaMax";
	static final String COLUMN_ALTEZZA_MIN="altezzaMin";
	static final String COLUMN_ALTEZZA_MED="altezzaMed";
	static final String COLUMN_START_DATE="startDate";
	
	
	// shared index, make sure to match COLUMNS
	static final int COLUMN_ID_INDEX=0;
	static final int COLUMN_ID_ACTIVITIES_INDEX=1;
	static final int COLUMN_DURATA_TEMPO_INDEX=2;
	static final int COLUMN_BATTITI_MAX_INDEX=3;
	static final int COLUMN_BATTITI_MIN_INDEX=4;
	static final int COLUMN_BATTITI_MED_INDEX=5;
	static final int COLUMN_ALTEZZA_MAX_INDEX=6;
	static final int COLUMN_ALTEZZA_MIN_INDEX=7;
	static final int COLUMN_ALTEZZA_MED_INDEX=8;
	static final int COLUMN_START_DATE_INDEX=9;
	

	/** this is the version of DB. 
	 *  we must increase this number when the DB  is change **/
	private  static  final  int DATABASE_VERSION =  2;
	/** this's the DB's name**/
	private static final String DATABASE_NAME= "Session.db";
	/** this 's the query that we use for create the table
	 *  Android use the _id  for identify the element's key **/
	private static final String DATABASE_CREATE = "create table " + TABLE_SESSION + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_ID_ACTIVITIES + " INTEGER NOT NULL,"
			+ COLUMN_DURATA_TEMPO + " INTEGER DEFAULT 0,"
			+ COLUMN_BATTITI_MAX + " NUMERIC(3) DEFAULT 0,"
			+ COLUMN_BATTITI_MIN + " NUMERIC(3) DEFAULT 0,"
			+ COLUMN_BATTITI_MED + " NUMERIC(3) DEFAULT 0,"
			+ COLUMN_ALTEZZA_MAX + " NUMERIC(5) DEFAULT 0,"
			+ COLUMN_ALTEZZA_MIN + " NUMERIC(5) DEFAULT 0,"
			+ COLUMN_ALTEZZA_MED + " NUMERIC(5) DEFAULT 0,"
			+ COLUMN_START_DATE + " INTEGER NOT NULL "
			+ ");";

	public DataBaseStorageSession(Context context) {
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
		db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SESSION);
		onCreate(db);
	}
}
