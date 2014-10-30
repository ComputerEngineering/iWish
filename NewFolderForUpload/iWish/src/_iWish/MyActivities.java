package _iWish;

import iWish_database.DataBaseStorageActivities;

import java.util.Date;

import android.content.Context;

/**Raffaella*/

public interface MyActivities {
	
	Long getKeyActivities();
	String getEmailChallenger();
	Long getIdSfidato();
	int getStartDate();
	int getEndDate();
	int getWin();
	int getKmObbiettivo();
	int getKmPercorsi();
	String getTipoAttivita();
	String getEmailFoe();
	Context getC();
	void setKeyActivities(Long KeyActivities);
	void setEmailChallenger(String EmailChallenger);
	void setIdSfidato(Long IdSfidato);
	void setStartDate(int StartDate);
	void setEndDate(int EndDate);
	void setWin(int Win);
	void setKmObbiettivo(int KmObbiettivo);
	void setKmPercorsi(int KmPercorsi);
	void setTipoAttivita(String TipoAttivita);
	void setEmailFoe(String EmailFoe);
	void setC(Context c);
	
}