package _iWish;


public interface MySession {
	Long getKeySession();
	Long getKeyActivities();
	int getDurataTempo();
	int getBattitiMax();
	int getBattitiMin();
	int getBattitiMed();
	int getAltezzaMax();
	int getAltezzaMin();
	int getAltezzaMed();
	void setKeySession(Long KeySession);
	void setKeyActivities(Long KeyActivities);
	void setDurataTempo(int DurataTempo);
	void setBattitiMax(int BattitiMax);
	void setBattitiMin(int BattitiMin);
	void setBattitiMed(int BattitiMed);
	void setAltezzaMax(int AltezzaMax);
	void setAltezzaMin(int AltezzaMin);
	void setAltezzaMed(int AltezzaMed);
}
