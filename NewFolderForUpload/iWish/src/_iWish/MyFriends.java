package _iWish;

import android.content.Context;

/**Raffaella*/

public interface MyFriends {
	String getKeyFriend();
	String getName();
	String getSurname();
	String getEmailUser();
	Context getC();
	int getPoint();
	void setKeyFriend(String keyUtente);
	void setName(String name);
	void setSurname(String surname);
	void setPoint(int point);
	void setEmailUser(String emailUser);
	void setC(Context C);
}
