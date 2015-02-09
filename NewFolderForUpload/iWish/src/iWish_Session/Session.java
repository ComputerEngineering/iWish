package iWish_Session;

import java.io.Serializable;

import _iWish.MySession;

public class Session implements MySession, Serializable {
	private static final long serialVersionUID = -1907227924554553866L;
	private long KeySession;
	private long KeyActivities;
	private int DurataTempo = 0;
	private int BattitiMax = 0;
	private int BattitiMin = 0;
	private int BattitiMed = 0;
	private int AltezzaMax = 0;
	private int AltezzaMin = 0;
	private int AltezzaMed = 0;
	private int StartDate;
	private int StartDateActivities;
	private String Battiti = "0,0";

	@Override
	public long getKeySession() {
		
		return KeySession;
	}

	@Override
	public long getKeyActivities() {
		
		return KeyActivities;
	}

	@Override
	public int getDurataTempo() {
		
		return DurataTempo;
	}

	@Override
	public int getBattitiMax() {
		
		return BattitiMax;
	}

	@Override
	public int getBattitiMin() {
		// TODO Auto-generated method stub
		return BattitiMin;
	}

	@Override
	public int getBattitiMed() {
		// TODO Auto-generated method stub
		return BattitiMed;
	}

	@Override
	public int getAltezzaMax() {
		// TODO Auto-generated method stub
		return AltezzaMax;
	}

	@Override
	public int getAltezzaMin() {
		// TODO Auto-generated method stub
		return AltezzaMin;
	}

	@Override
	public int getAltezzaMed() {
		// TODO Auto-generated method stub
		return AltezzaMed;
	}
	
	@Override
	public int getStartDate() {
		
		return StartDate;
	}
	
	@Override
	public int getStartDateActivities() {
		
		return StartDateActivities;
	}
	
	@Override
	public String getBattiti() {
		
		return Battiti;
	}

	@Override
	public void setKeySession(long KeySession) {
		// TODO Auto-generated method stub
		this.KeySession = KeySession;
	}

	@Override
	public void setKeyActivities(long KeyActivities) {
		// TODO Auto-generated method stub
		this.KeyActivities = KeyActivities;
	}

	@Override
	public void setDurataTempo(int DurataTempo) {
		// TODO Auto-generated method stub
		this.DurataTempo = DurataTempo;
	}

	@Override
	public void setBattitiMax(int BattitiMax) {
		// TODO Auto-generated method stub
		this.BattitiMax = BattitiMax;
	}

	@Override
	public void setBattitiMin(int BattitiMin) {
		// TODO Auto-generated method stub
		this.BattitiMin = BattitiMin;
	}

	@Override
	public void setBattitiMed(int BattitiMed) {
		// TODO Auto-generated method stub
		this.BattitiMed = BattitiMed;
	}

	@Override
	public void setAltezzaMax(int AltezzaMax) {
		// TODO Auto-generated method stub
		this.AltezzaMax = AltezzaMax;
	}

	@Override
	public void setAltezzaMin(int AltezzaMin) {
		// TODO Auto-generated method stub
		this.AltezzaMin = AltezzaMin;
	}

	@Override
	public void setAltezzaMed(int AltezzaMed) {
		// TODO Auto-generated method stub
		this.AltezzaMed = AltezzaMed;
	}
	
	@Override
	public void setStartDate(int StartDate) {
		// TODO Auto-generated method stub
		this.StartDate = StartDate;
	}
	
	@Override
	public void setStartDateActivities(int StartDateActivities) {
		// TODO Auto-generated method stub
		this.StartDateActivities = StartDateActivities;
	}
	
	@Override
	public void setBattiti(String Battiti) {
		// TODO Auto-generated method stub
		this.Battiti = Battiti;
	}

	@Override
	public String toString() {
		return "Session [KeySession=" + KeySession + ", KeyActivities="
				+ KeyActivities + ", DurataTempo=" + DurataTempo
				+ ", BattitiMax=" + BattitiMax + ", BattitiMin=" + BattitiMin
				+ ", BattitiMed=" + BattitiMed + ", AltezzaMax=" + AltezzaMax
				+ ", AltezzaMin=" + AltezzaMin + ", AltezzaMed=" + AltezzaMed
				+ ", StartDate=" + StartDate + ", StartDateActivities=" + StartDateActivities 
				+ ", Battiti=" + Battiti + "]";
	}

}
