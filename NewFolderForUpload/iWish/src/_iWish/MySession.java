package _iWish;


public interface MySession {
	long getKeySession();
	long getKeyActivities();
	long getDurataTempo();
	int getBattitiMax();
	int getBattitiMin();
	int getBattitiMed();
	int getAltezzaMax();
	int getAltezzaMin();
	int getAltezzaMed();
	long getStartDate();
	void setKeySession(long KeySession);
	void setKeyActivities(long KeyActivities);
	void setDurataTempo(long DurataTempo);
	void setBattitiMax(int BattitiMax);
	void setBattitiMin(int BattitiMin);
	void setBattitiMed(int BattitiMed);
	void setAltezzaMax(int AltezzaMax);
	void setAltezzaMin(int AltezzaMin);
	void setAltezzaMed(int AltezzaMed);
	void setStartDate(long StartDate);
}
