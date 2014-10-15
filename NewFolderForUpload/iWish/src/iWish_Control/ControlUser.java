package iWish_Control;

import java.util.List;

import android.content.Context;
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


	public void saveOnDbUtente(Utente mUtente, Context context) throws Exception{
		try {
			mUtenteDao =  new UtenteDao(context);
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

	public void deleteOnDbSomeUtente(List<Utente> mUtente){
		try {
			mUtenteDao.deleteOnDbSomeUtente(mUtente);
		} catch (Exception e) {

		}
	}

	public List<Utente> getOnDbAllUtente(){
		try {
			return mUtenteDao.getAllUtente();
		} catch (Exception e) {

		}
		return null;
	}

	public void ConnectionUtente(){
		try {
			//ControlConnection.getIstanceControlConnection().Utente();
		} catch (Exception e) {
		}
	}
}