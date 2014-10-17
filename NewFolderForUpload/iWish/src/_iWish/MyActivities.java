package _iWish;

import java.util.Date;

import android.content.Context;

/**Raffaella*/

public interface MyActivities {
	Long getKeyActivities();
	Integer getWinActivities();
	String getEmailChallenger();
	String getEmailFoe();
	int getDateStart();
	int getDateFinish();
	Context getC();
	void setKeyActivities(Long KeyActivities);
	void setWinActivities(Integer WinActivities);
	void setEmailChallenger(String EmailChallenger);
	void setEmailFoe(String EmailFoe);
	void setDateStart(int Start);
	void setDateFinish(int Finish);
	void setC(Context c);
}
