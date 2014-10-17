package _iWish;

import android.content.Context;

/**Raffaella*/

public interface MyFriends {
	Long getKeyFriend();
	String getName();
	String getSurname();
	String getEmailUser();
	String getEmailFriends();
	Context getC();
	int getPoint();
	void setKeyFriend(Long keyUtente);
	void setName(String name);
	void setSurname(String surname);
	void setPoint(int point);
	void setEmailUser(String emailUser);
	void setEmailFriends(String emailFriends);
	void setC(Context C);
}
