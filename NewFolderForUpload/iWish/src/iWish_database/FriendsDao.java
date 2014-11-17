package iWish_database;
/**Raffaella*/

import iWish_Friends.Friends;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


/**This class is our DAO. 
 * It maintains the database connection and supports adding new element.**/
public class FriendsDao {
	// Database field
	
	private SQLiteDatabase database ;
	private DataBaseStorageFriends dbHelper;
	private static String[] allColumns={
		DataBaseStorageFriends.COLUMN_ID,
		DataBaseStorageFriends.COLUMN_EMAIL_FRIENDS,
		DataBaseStorageFriends.COLUMN_EMAIL_USER,
		DataBaseStorageFriends.COLUMN_NAME,
		DataBaseStorageFriends.COLUMN_SURNAME,
		DataBaseStorageFriends.COLUMN_POINT
		
		//DataBaseStorageFriends.COLUMN_PHOTO
	};
	
	public FriendsDao(Context context){
		dbHelper = new DataBaseStorageFriends(context);
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
	public void insertOnDbFriends(Friends mFriends)throws Exception{
		ContentValues values = new ContentValues();
		values.put(DataBaseStorageFriends.COLUMN_EMAIL_FRIENDS, "" + mFriends.getEmailFriends() + "");
		values.put(DataBaseStorageFriends.COLUMN_EMAIL_USER, "" + mFriends.getEmailUser() + "");
		values.put(DataBaseStorageFriends.COLUMN_NAME,"" + mFriends.getName() + "");
		values.put(DataBaseStorageFriends.COLUMN_SURNAME,"" + mFriends.getSurname() + "");
		values.put(DataBaseStorageFriends.COLUMN_POINT,"" + mFriends.getPoint() + "");
		long insertId = database.insert(DataBaseStorageFriends.TABLE_FRIENDS, null, values);
	}
	
	public void deleteOnDbAllFriends(){
		database.delete(DataBaseStorageFriends.TABLE_FRIENDS, null,null);
	}
	
	public void deleteOnDbOneFriends(String meMail){
		String email = meMail;
		System.out.print("Key Friends that we have delete is : " + email);
		database.delete(DataBaseStorageFriends.TABLE_FRIENDS, DataBaseStorageFriends.COLUMN_EMAIL_FRIENDS+"='" + email+ "'" , null);
	}
	
	public int deleteOnDbSomeFriends(List<Friends> mFriends){
		Long guid=null;
		int db=-1;
		for(Friends friends: mFriends){
			guid=friends.getKeyFriend();
			db=database.delete(DataBaseStorageFriends.TABLE_FRIENDS, DataBaseStorageFriends.COLUMN_ID+ "='" + guid+ "'", null);
		}return db;
	}
	
	public List<Friends> getAllFriends(){
		List<Friends> listFriends = new ArrayList<Friends>();
		Cursor cursor = database.query(DataBaseStorageFriends.TABLE_FRIENDS, allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Friends friends = cursorsFriends(cursor);
			listFriends.add(friends);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
				cursor.close();
				return listFriends;
	}
	

	
	private Friends cursorsFriends(Cursor cursor) {
		Friends friends = new Friends();
		friends.setKeyFriend(cursor.getLong(DataBaseStorageFriends.COLUMN_ID_INDEX));
		friends.setEmailFriends(cursor.getString(DataBaseStorageFriends.COLUMN_EMAIL_FRIENDS_INDEX));
		friends.setEmailUser(cursor.getString(DataBaseStorageFriends.COLUMN_EMAIL_USER_INDEX));
		friends.setName(cursor.getString(DataBaseStorageFriends.COLUMN_NAME_INDEX));
		friends.setSurname(cursor.getString(DataBaseStorageFriends.COLUMN_SURNAME_INDEX));
		friends.setPoint(cursor.getInt(DataBaseStorageFriends.COLUMN_POINT_INDEX));
	
		return friends;
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
