package iWish_Activities;
/**Raffaella*/

import java.io.Serializable;
import java.util.Date;

import android.content.Context;
import _iWish.MyActivities;

public class Activities implements MyActivities,Serializable {
	/**The serialization runtime associates with each serializable class a version number, 
	 * called a serialVersionUID, which is used during deserialization to verify that the 
	 * sender and receiver of a serialized object have loaded classes for that object that
	 * are compatible with respect to serialization.*/
	private static final long serialVersionUID = -7102900290656728874L;
	private Long KeyActivities;
	private Integer WinActivities;//in SQLite non abbiamo i boolean quindi con gli INTEGER 0(false) and 1(true).
	private String EmailFoe;
	private String EmailChallenger;
	private Context c;
	private Date Start; // da controllare per il database SQLite
	private Date Finish;
	
	@Override
	public Long getKeyActivities() {
		return KeyActivities;
	}
	@Override
	public Integer getWinActivities() {
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
	public Date getDateStart() {
		return Start;
	}
	@Override
	public Date getDateFinish() {
		return Finish;
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
	public void setWinActivities(Integer WinActivities) {
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
	public void setDateStart(Date Start) {
		this.Start=Start;
	}
	@Override
	public void setDateFinish(Date Finish) {
		this.Finish=Finish;
	}
	@Override
	public void setC(Context c) {
		this.c=c;
	}
	@Override
	public String toString() {
		return "Activities [KeyActivities=" + KeyActivities
				+ ", WinActivities=" + WinActivities + ", EmailFoe=" + EmailFoe
				+ ", EmailChallenger=" + EmailChallenger + ", c=" + c
				+ ", Start=" + Start + ", Finish=" + Finish + "]";
	}	
}
