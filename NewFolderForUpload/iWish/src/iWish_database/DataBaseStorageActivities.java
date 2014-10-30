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
	
	static final String COLUMN_ID="_id";
	static final String COLUMN_EMAIL="eMail";
	static final String COLUMN_IDSFIDATO="idSfidato";
	static final String COLUMN_STARTDATE="startDate";
	static final String COLUMN_ENDDATE="endDate";
	static final String COLUMN_WIN="win";
	static final String COLUMN_KMOBBIETTIVO="KmObbiettivo";
	static final String COLUMN_KMPERCORSI="KmPercorsi";
	static final String COLUMN_TIPOATTIVITA="tipoAttivita";
	static final String COLUMN_EMAIL_FOE="EmailFoe";
	
	static final int COLUMN_ID_INDEX=0;
	static final int COLUMN_EMAIL_INDEX=1;
	static final int COLUMN_IDSFIDATO_INDEX=2;
	static final int COLUMN_STARTDATE_INDEX=3;
	static final int COLUMN_ENDDATE_INDEX=4;
	static final int COLUMN_WIN_INDEX=5;
	static final int COLUMN_KMOBBIETTIVO_INDEX=6;
	static final int COLUMN_KMPERCORSI_INDEX=7;
	static final int COLUMN_TIPOATTIVITA_INDEX=8;
	static final int COLUMN_EMAIL_FOE_INDEX=9;
	
	/** this is the version of DB. 
	 *  we must increase this number when the DB  is change **/
	private  static  final  int DATABASE_VERSION = 4;
	/** this's the DB's name**/
	private static final String DATABASE_NAME= "Activities.db";
	/** this 's the query that we use for create the table
	 *  Android use the _id  for identify the element's key **/
	private static final String DATABASE_CREATE  = "create table " + TABLE_ACTIVITIES + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_EMAIL + " vachar(25) NOT NULL,"
			+ COLUMN_IDSFIDATO + " INTEGER DEFAULT 0,"
			/*SQLite does not have a storage class set aside for storing dates and/or times. 
				Instead, the built-in Date And Time Functions of SQLite are capable of storing 
				dates and times as TEXT, REAL, or INTEGER values:
					TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS").
					REAL as Julian day numbers, the number of days since noon in Greenwich on 
						November 24, 4714 B.C. according to the proleptic Gregorian calendar.
					INTEGER as Unix Time, the number of seconds since 1970-01-01 00:00:00 UTC.*/
			+ COLUMN_STARTDATE + " numeric(8) NOT NULL,"
			+ COLUMN_ENDDATE + " numeric(8) NOT NULL,"
			+ COLUMN_WIN + " numeric(1) DEFAULT 0,"
			+ COLUMN_KMOBBIETTIVO + " numeric(2) NOT NULL,"
			+ COLUMN_KMPERCORSI + " numeric(2) DEFAULT 0,"
			+ COLUMN_EMAIL_FOE + " vachar(25) DEFAULT 0"
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
