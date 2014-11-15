package _iWish;


public interface MySession {
	long getKeySession();
	long getKeyActivities();
	int getDurataTempo();
	int getBattitiMax();
	int getBattitiMin();
	int getBattitiMed();
	int getAltezzaMax();
	int getAltezzaMin();
	int getAltezzaMed();
	int getStartDate();
	int getStartDateActivities();
	void setKeySession(long KeySession);
	void setKeyActivities(long KeyActivities);
	void setDurataTempo(int DurataTempo);
	void setBattitiMax(int BattitiMax);
	void setBattitiMin(int BattitiMin);
	void setBattitiMed(int BattitiMed);
	void setAltezzaMax(int AltezzaMax);
	void setAltezzaMin(int AltezzaMin);
	void setAltezzaMed(int AltezzaMed);
	void setStartDate(int StartDate);
	void setStartDateActivities(int StartDateActivities);
}
