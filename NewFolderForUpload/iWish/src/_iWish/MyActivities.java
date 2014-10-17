package _iWish;

import java.util.Date;

import android.content.Context;

/**Raffaella*/

public interface MyActivities {
	Long getKeyActivities();
	Integer getWinActivities();
	String getEmailChallenger();
	String getEmailFoe();
	Date getDateStart();
	Date getDateFinish();
	Context getC();
	void setKeyActivities(Long KeyActivities);
	void setWinActivities(Integer WinActivities);
	void setEmailChallenger(String EmailChallenger);
	void setEmailFoe(String EmailFoe);
	void setDateStart(Date Start);
	void setDateFinish(Date Finish);
	void setC(Context c);
}
