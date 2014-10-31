package iWish_Activities;
/**Raffaella*/

import java.io.Serializable;

import _iWish.MyActivities;

public class Activities implements MyActivities,Serializable {
	/**The serialization runtime associates with each serializable class a version number, 
	 * called a serialVersionUID, which is used during deserialization to verify that the 
	 * sender and receiver of a serialized object have loaded classes for that object that
	 * are compatible with respect to serialization.*/
	private static final long serialVersionUID = -7102900290656728874L;
	private long KeyActivities;
	private String EmailChallenger;
	private long IdSfidato = 0;
	private int StartDate; // da controllare per il database SQLite
	private int EndDate;
	private int Win = 0;//in SQLite non abbiamo i boolean quindi con gli INTEGER 0(false) and 1(true).
	private int KmObbiettivo = 0;
	private int KmPercorsi = 0;
	private String TipoAttivita;
	private String EmailFoe = "0";
	
	
	@Override
	public Long getKeyActivities() {
		return KeyActivities;
	}
	@Override
	public int getWin() {
		return Win;
	}
	@Override
	public Long getIdSfidato() {
		return IdSfidato;
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
	public int getStartDate() {
		return StartDate;
	}
	@Override
	public int getEndDate() {
		return EndDate;
	}
	@Override
	public int getKmObbiettivo() {
		return KmObbiettivo;
	}
	public int getKmPercorsi() {
		return KmPercorsi;
	}
	public String getTipoAttivita() {
		return TipoAttivita;
	}
	@Override
	public void setKeyActivities(Long KeyActivities) {
		this.KeyActivities=KeyActivities;
	}
	@Override
	public void setWin(int Win) {
		this.Win=Win;
	}
	@Override
	public void setIdSfidato(Long IdSfidato) {
		this.IdSfidato=IdSfidato;
	}
	@Override
	public void setKmObbiettivo(int KmObbiettivo) {
		this.KmObbiettivo=KmObbiettivo;
	}
	public void setKmPercorsi(int KmPercorsi) {
		this.KmPercorsi=KmPercorsi;
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
	public void setStartDate(int StartDate) {
		this.StartDate=StartDate;
	}
	public void setTipoAttivita(String TipoAttivita) {
		this.TipoAttivita=TipoAttivita;
	}
	@Override
	public void setEndDate(int EndDate) {
		this.EndDate=EndDate;
	}
	@Override
	public String toString() {
		return "Activities [KeyActivities=" + KeyActivities
				+ ", EmailChallenger=" + EmailChallenger + ", IdSfidato="
				+ IdSfidato + ", StartDate=" + StartDate + ", EndDate="
				+ EndDate + ", Win=" + Win + ", KmObbiettivo=" + KmObbiettivo
				+ ", KmPercorsi=" + KmPercorsi + ", TipoAttivita="
				+ TipoAttivita + ", EmailFoe=" + EmailFoe + "]";
	}
	
	
}
