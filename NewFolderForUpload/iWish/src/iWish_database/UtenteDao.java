package iWish_database;
/**Raffaella*/

import iWish_Utente.Utente;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**This class is our DAO. 
 * It maintains the database connection and supports adding new element.**/
public class UtenteDao {
	private SQLiteDatabase database ;
	private DataBaseStorageUtente dbHelper;
	private static String[] allColumns={
		DataBaseStorageUtente.COLUMN_ID,
		DataBaseStorageUtente.COLUMN_NAME,
		DataBaseStorageUtente.COLUMN_SURNAME,
		DataBaseStorageUtente.COLUMN_BIRTHDAY,
		DataBaseStorageUtente.COLUMN_SEX,
		DataBaseStorageUtente.COLUMN_TYPEUSER,
		DataBaseStorageUtente.COLUMN_HEIGHT,
		DataBaseStorageUtente.COLUMN_WEIGHT,
		DataBaseStorageUtente.COLUMN_CITY,
		DataBaseStorageUtente.COLUMN_EMAIL,
		DataBaseStorageUtente.COLUMN_PASSWORD,
		DataBaseStorageUtente.COLUMN_QUESTION,
		DataBaseStorageUtente.COLUMN_ANSWER,
		DataBaseStorageUtente.COLUME_BMI
		//DataBaseStorageUtenteCOLUMN_PHOTO
		//aggiungere elemento bmi
	};
	public UtenteDao(Context context){
		dbHelper = new DataBaseStorageUtente(context);
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
	public void insertOnDbUtente(Utente mUtente)throws Exception{
		ContentValues values = new ContentValues();
		values.put(DataBaseStorageUtente.COLUMN_NAME,"" + mUtente.getName() + "");
		values.put(DataBaseStorageUtente.COLUMN_SURNAME, "" + mUtente.getSurname() + "");
		values.put(DataBaseStorageUtente.COLUMN_BIRTHDAY,""+ mUtente.getBirthday() + "");
		values.put(DataBaseStorageUtente.COLUMN_SEX,""+ mUtente.getSex() + "");
		values.put(DataBaseStorageUtente.COLUMN_TYPEUSER,""+ mUtente.getTypeUser() + "");
		values.put(DataBaseStorageUtente.COLUMN_HEIGHT,""+ mUtente.getHeight() + "");
		values.put(DataBaseStorageUtente.COLUMN_WEIGHT,""+ mUtente.getWeight() + "");
		values.put(DataBaseStorageUtente.COLUMN_CITY,""+ mUtente.getCity() + "");
		values.put(DataBaseStorageUtente.COLUMN_EMAIL,""+ mUtente.getEmail() + "");
		values.put(DataBaseStorageUtente.COLUMN_PASSWORD,""+ mUtente.getPassword() + "");
		values.put(DataBaseStorageUtente.COLUMN_QUESTION,""+ mUtente.getQuestion() + "");
		values.put(DataBaseStorageUtente.COLUMN_ANSWER,""+ mUtente.getAnswer() + "");
		values.put(DataBaseStorageUtente.COLUME_BMI, "" + mUtente.getBmi() +"");
		long insertId = database.insert(DataBaseStorageUtente.TABLE_UTENTE, null, values);
	}

	public void deleteOnDbAllUtente(){
		database.delete(DataBaseStorageUtente.TABLE_UTENTE, null,null);
	}

	public void deleteOnDbOneUtente(Utente mUtente){
		String guid = mUtente.getEmail();
		System.out.print("Key Utente that we have delete is : " + guid);
		database.delete(DataBaseStorageUtente.TABLE_UTENTE, DataBaseStorageUtente.COLUMN_ID +"='" + guid+ "'" , null);
	}
	
	

	public int deleteOnDbSomeUtente(List<Utente> mUtente){
		String guid=null;
		int db=-1;
		for(Utente utente: mUtente){
			guid=utente.getEmail();
			db=database.delete(DataBaseStorageUtente.TABLE_UTENTE, DataBaseStorageUtente.COLUMN_ID+ "='" + guid+ "'", null);
		}return db;
	}
	
	public boolean checkRegistrationOnDbOneUtente(String eMail){
		boolean i= true;//there is email
		String where = DataBaseStorageUtente.COLUMN_EMAIL + " = '"+eMail+"'";
		Cursor cursor = database.query(DataBaseStorageUtente.TABLE_UTENTE, new String[] {DataBaseStorageUtente.COLUMN_EMAIL}, where, null, null, null, null);
		//String app= String.valueOf(cursor.getCount());
		//Log.i("UtenteDao",app);
		if(cursor.getCount()==0){
			i=false;//there isn't email
		}
		return i;
	}

	public List<Utente> getAllUtente(){
		List<Utente> listUtente = new ArrayList<Utente>();
		Cursor cursor = database.query(DataBaseStorageUtente.TABLE_UTENTE, allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Utente utente=cursorsUtente(cursor);
			listUtente.add(utente);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return listUtente;
	}

	//TODO controllare le variabili char che danno problemi nel database
	private Utente cursorsUtente(Cursor cursor) {
		Utente utente = new Utente();

		utente.setKeyUtente(cursor.getLong(DataBaseStorageUtente.COLUMN_ID_INDEX));
		utente.setName(cursor.getString(DataBaseStorageUtente.COLUMN_NAME_INDEX));
		utente.setSurname(cursor.getString(DataBaseStorageUtente.COLUMN_SURNAME_INDEX));
		utente.setBirthday(cursor.getString(DataBaseStorageUtente.COLUMN_BIRTHDAY_INDEX));
		utente.setSex(cursor.getString(DataBaseStorageUtente.COLUMN_SEX_INDEX));
		utente.setTypeUser(cursor.getString(DataBaseStorageUtente.COLUMN_TYPEUSER_INDEX));
		utente.setHeight(cursor.getInt(DataBaseStorageUtente.COLUMN_HEIGHT_INDEX));
		utente.setWeight(cursor.getInt(DataBaseStorageUtente.COLUMN_WEIGHT_INDEX));
		utente.setCity(cursor.getString(DataBaseStorageUtente.COLUMN_CITY_INDEX));
		utente.setEmail(cursor.getString(DataBaseStorageUtente.COLUMN_EMAIL_INDEX));
		utente.setPassword(cursor.getString(DataBaseStorageUtente.COLUMN_PASSWORD_INDEX));
		utente.setQuestion(cursor.getString(DataBaseStorageUtente.COLUMN_QUESTION_INDEX));
		utente.setAnswer(cursor.getString(DataBaseStorageUtente.COLUMN_ANSWER_INDEX));
		utente.setBmi(cursor.getDouble(DataBaseStorageUtente.COLUMN_BMI_INDEX));
		return  utente;
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
