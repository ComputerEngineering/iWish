package iWish_database;
/**Raffaella*/ 

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**This class is responsible for creating the database.
 * SQLiteDatabase is the base class for working with a SQLite database in Android and 
 * provides methods to open, query, update and close the database.
 * More specifically SQLiteDatabase provides the insert(), update() and delete() methods.
 * In addition it provides the execSQL() method, which allows to execute SQL directly.*/
public class DataBaseStorageUtente extends SQLiteOpenHelper {

	static final String TABLE_UTENTE = "Utente";

	static final String COLUMN_ID="_id";
	static final String COLUMN_NAME="nome";
	static final String COLUMN_SURNAME="surname";
	static final String COLUMN_BIRTHDAY="birthday";
	static final String COLUMN_SEX="sex";
	static final String COLUMN_POINT="point";
	static final String COLUMN_TYPEUSER="type";
	static final String COLUMN_HEIGHT="height";
	static final String COLUMN_WEIGHT="weight";
	static final String COLUMN_CITY="city";
	static final String COLUMN_EMAIL="email";
	static final String COLUMN_PASSWORD="password";
	static final String COLUMN_QUESTION="question";
	static final String COLUMN_ANSWER="answer";
	static final String COLUMN_BMI="bmi";
	//	static final String COLUMN_PHOTO="photo";

	// shared index, make sure to match COLUMNS
	static final int COLUMN_ID_INDEX=0;
	static final int COLUMN_NAME_INDEX=1;
	static final int COLUMN_SURNAME_INDEX=2;
	static final int COLUMN_BIRTHDAY_INDEX=3;
	static final int COLUMN_SEX_INDEX=4;
	static final int COLUMN_TYPEUSER_INDEX=5;
	static final int COLUMN_HEIGHT_INDEX=6;
	static final int COLUMN_WEIGHT_INDEX=7;
	static final int COLUMN_CITY_INDEX=8;
	static final int COLUMN_EMAIL_INDEX=9;
	static final int COLUMN_PASSWORD_INDEX=10;
	static final int COLUMN_QUESTION_INDEX=11;
	static final int COLUMN_ANSWER_INDEX=12;
	static final int COLUMN_BMI_INDEX=13;
	static final int COLUMN_POINT_INDEX=14;
	//static final int COLUMN_PHOTO_INDEX=15;

	/** this is the version of DB. 
	 *  we must increase this number when the DB  is change **/
	private  static  final  int DATABASE_VERSION =  6;
	/** this's the DB's name**/
	private static final String DATABASE_NAME= "Utente.db";
	/** this 's the query that we use for create the table
	 *  Android use the _id  for identify the element's key **/
	private static final String DATABASE_CREATE = "create table " + TABLE_UTENTE + "("
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_NAME + " vachar(15) NOT NULL,"
			+ COLUMN_SURNAME + " vachar(15) NOT NULL,"
			+ COLUMN_BIRTHDAY + " vachar(15) NOT NULL,"
			+ COLUMN_SEX + " vachar(2) NOT NULL,"
			+ COLUMN_TYPEUSER + " vachar(25) NOT NULL,"
			+ COLUMN_HEIGHT + " numeric(6) NOT NULL,"
			+ COLUMN_POINT+ " numeric(4) DEFAULT 0,"
			+ COLUMN_WEIGHT + " numeric(6) NOT NULL,"
			+ COLUMN_CITY + " vachar(15) NOT NULL,"
			+ COLUMN_EMAIL + " vachar(30) NOT NULL UNIQUE,"
			+ COLUMN_PASSWORD + " vachar(15) NOT NULL,"
			+ COLUMN_QUESTION + " vachar(30) NOT NULL,"
			+ COLUMN_BMI + " real(5,2) NOT NULL,"
			+ COLUMN_ANSWER + " vachar(15) NOT NULL"
			//TODO vedere come definire l'attributo photo
		//	+ COLUMN_PHOTO + " ......."
			+ ");";

	public DataBaseStorageUtente(Context context) {
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
		db.execSQL("DROP TABLE IF EXISTS "+ TABLE_UTENTE);
		onCreate(db);
	}
}
