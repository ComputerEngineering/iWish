package iWish_Activities;
/**Raffaella*/

import java.io.Serializable;

import android.content.Context;
import _iWish.MyActivities;

public class Activities implements MyActivities,Serializable {
	/**The serialization runtime associates with each serializable class a version number, 
	 * called a serialVersionUID, which is used during deserialization to verify that the 
	 * sender and receiver of a serialized object have loaded classes for that object that
	 * are compatible with respect to serialization.*/
	private static final long serialVersionUID = -7102900290656728874L;
	private Long KeyActivities;
	private Boolean WinActivities;
	private String EmailFoe;
	private String EmailChallenger;
	private Context c;
	//TODO inserire campo data Start --> private String Start;
	//TODO inserire campo data Finish --> private String Finish;
	
	@Override
	public Long getKeyActivities() {
		return KeyActivities;
	}
	@Override
	public boolean getWinActivities() {
		return WinActivities;
	}
	@Override
	public String getEmailChallenger() {
		return EmailChallenger;
	}
	@Override
	public String getEmailFoe() {
		return EmailFoe;
	}
	@Override
	public Context getC() {
		return c;
	}
	@Override
	public void setKeyActivities(Long KeyActivities) {
		this.KeyActivities=KeyActivities;
	}
	@Override
	public void setWinActivities(Boolean WinActivities) {
		this.WinActivities=WinActivities;
	}
	@Override
	public void setEmailChallenger(String EmailChallenger) {
		this.EmailChallenger=EmailChallenger;
	}
	
	@Override
	public void setEmailFoe(String EmailFoe) {
		this.EmailFoe=EmailFoe;
	}
	@Override
	public void setC(Context c) {
		this.c=c;
	}
}
