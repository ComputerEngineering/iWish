package iWish_Control;

import iWish_Utente.Utente;
import iWish_database.UtenteDao;

public class ControlUser {

	private static ControlUser instance = null;
	private UtenteDao mUtenteDao;

	private ControlUser(){}


	public static synchronized ControlUser getIstanceControlUser(){
		if(instance==null){
			instance = new ControlUser();
		}
		return instance;
	};


	public void saveOnDbUtente(Utente mUtente) throws Exception{
		try {
			mUtenteDao =  new UtenteDao(mUtente.getC());
			mUtenteDao.open();
			mUtenteDao.insertOnDbUtente(mUtente);
		} catch (Exception e) {

		}
	}

	public void deleteOnDbUtenteAll(){
		try {
			mUtenteDao.deleteOnDbAllUtente();
		} catch (Exception e) {

		}
	}
	
	public void deleteOnDbOneUtente(Utente mUtente){
		try {
			mUtenteDao.deleteOnDbOneUtente(mUtente);
		} catch (Exception e) {
			
		}
	}

}