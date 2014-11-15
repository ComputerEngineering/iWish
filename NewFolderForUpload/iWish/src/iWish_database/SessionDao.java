package iWish_database;
/**Raffaella*/

import iWish_Session.Session;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**This class is our DAO. 
 * It maintains the database connection and supports adding new element.**/
public class SessionDao {
	private SQLiteDatabase database ;
	private DataBaseStorageSession dbHelper;
	private static String[] allColumns={
		DataBaseStorageSession.COLUMN_ID,
		DataBaseStorageSession.COLUMN_ID_ACTIVITIES,
		DataBaseStorageSession.COLUMN_DURATA_TEMPO,
		DataBaseStorageSession.COLUMN_BATTITI_MAX,
		DataBaseStorageSession.COLUMN_BATTITI_MIN,
		DataBaseStorageSession.COLUMN_BATTITI_MED,
		DataBaseStorageSession.COLUMN_ALTEZZA_MAX,
		DataBaseStorageSession.COLUMN_ALTEZZA_MIN,
		DataBaseStorageSession.COLUMN_ALTEZZA_MED,
		DataBaseStorageSession.COLUMN_START_DATE

	};

	public SessionDao(Context context){
		dbHelper = new DataBaseStorageSession(context);
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
	public void insertOnDbUtente(Session mSession)throws Exception{
		ContentValues values = new ContentValues();
		values.put(DataBaseStorageSession.COLUMN_ID_ACTIVITIES,"" + mSession.getKeyActivities() + "");
		values.put(DataBaseStorageSession.COLUMN_DURATA_TEMPO, "" + mSession.getDurataTempo() + "");
		values.put(DataBaseStorageSession.COLUMN_BATTITI_MAX,""+ mSession.getBattitiMax() + "");
		values.put(DataBaseStorageSession.COLUMN_BATTITI_MIN,""+ mSession.getBattitiMin() + "");
		values.put(DataBaseStorageSession.COLUMN_BATTITI_MED,""+ mSession.getBattitiMed() + "");
		values.put(DataBaseStorageSession.COLUMN_ALTEZZA_MAX,""+ mSession.getAltezzaMax() + "");
		values.put(DataBaseStorageSession.COLUMN_ALTEZZA_MIN,""+ mSession.getAltezzaMin() + "");
		values.put(DataBaseStorageSession.COLUMN_ALTEZZA_MED,""+ mSession.getAltezzaMed() + "");
		values.put(DataBaseStorageSession.COLUMN_START_DATE,""+ mSession.getStartDate() + "");
		long insertId = database.insert(DataBaseStorageSession.TABLE_SESSION, null, values);
	}

	public void deleteOnDbAllSession(){
		database.delete(DataBaseStorageSession.TABLE_SESSION, null,null);
	}



	public List<Session> getAllSessionOfOneActivities(Long KeyActivities){
		List<Session> listSession = new ArrayList<Session>();
		Cursor cursor = database.query(DataBaseStorageSession.TABLE_SESSION, allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Session session = cursorsSession(cursor);
			listSession.add(session);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return listSession;
	}

	public List<Session> getAllSession(){
		List<Session> listSession = new ArrayList<Session>();
		Cursor cursor = database.query(DataBaseStorageSession.TABLE_SESSION, allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Session session=cursorsSession(cursor);
			listSession.add(session);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return listSession;
	}

	//TODO controllare le variabili char che danno problemi nel database
	private Session cursorsSession(Cursor cursor) {
		Session session = new Session();

		session.setKeySession(cursor.getLong(DataBaseStorageSession.COLUMN_ID_INDEX));
		session.setKeyActivities(cursor.getLong(DataBaseStorageSession.COLUMN_ID_ACTIVITIES_INDEX));
		session.setDurataTempo(cursor.getLong(DataBaseStorageSession.COLUMN_DURATA_TEMPO_INDEX));
		session.setBattitiMax(cursor.getInt(DataBaseStorageSession.COLUMN_BATTITI_MAX_INDEX));
		session.setBattitiMin(cursor.getInt(DataBaseStorageSession.COLUMN_BATTITI_MIN_INDEX));
		session.setBattitiMed(cursor.getInt(DataBaseStorageSession.COLUMN_BATTITI_MED_INDEX));
		session.setAltezzaMax(cursor.getInt(DataBaseStorageSession.COLUMN_ALTEZZA_MAX_INDEX));
		session.setAltezzaMin(cursor.getInt(DataBaseStorageSession.COLUMN_ALTEZZA_MIN_INDEX));
		session.setAltezzaMed(cursor.getInt(DataBaseStorageSession.COLUMN_ALTEZZA_MED_INDEX));
		session.setStartDate(cursor.getLong(DataBaseStorageSession.COLUMN_START_DATE_INDEX));
		return  session;
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
