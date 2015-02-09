package iWish_Control;

import java.util.List;

import android.content.Context;
import android.database.SQLException;
import iWish_Friends.Friends;
import iWish_database.FriendsDao;

public class ControlFriends {

	private FriendsDao mFriendsDao;
	private static ControlFriends instance = null;


	private ControlFriends(){}

	public static synchronized ControlFriends getIstanceControlFriends(){
		if(instance==null){
			instance = new ControlFriends();
		}
		return instance;
	};

	public void saveOnDbFriends(Friends mFriends, Context context)throws Exception{
		try {
			mFriendsDao= new FriendsDao(context);
			mFriendsDao.open();
			mFriendsDao.insertOnDbFriends(mFriends);
		} catch (SQLException e) {

		}
	}

	public void deleteOnDbFriedsAll(){
		try {
			mFriendsDao.deleteOnDbAllFriends();
		} catch (Exception e) {

		}
	}

	public void deleteOnDBOneFriends(String meMail, Context context){
		try {
			mFriendsDao= new FriendsDao(context);
			mFriendsDao.open();
			mFriendsDao.deleteOnDbOneFriends(meMail);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deteteOnDbSomeFriends(List<Friends> mFriends){
		try {
			mFriendsDao.deleteOnDbSomeFriends(mFriends);
		} catch (Exception e) {

		}
	}

	public List<Friends> getOnDbAllFriends(){
		try {
			return mFriendsDao.getAllFriends();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}


	public void ConnectionFriends(){
		try {
			ControlConnection.getIstanceControlConnection().onInsertFriends();
		} catch (Exception e) {
		}
	}

}
