package _iWish;

import android.content.Context;

/**Raffaella*/

public interface MyActivities {
	Long getKeyActivities();
	boolean getWinActivities();
	String getEmailChallenger();
	String getEmailFoe();
	Context getC();
	void setKeyActivities(Long KeyActivities);
	void setWinActivities(Boolean WinActivities);
	void setEmailChallenger(String EmailChallenger);
	void setEmailFoe(String EmailFoe);
	void setC(Context c);
}
