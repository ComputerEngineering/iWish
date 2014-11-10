package iWish_Control;

import java.util.List;

import android.content.Context;
import android.util.Log;
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
			Log.i("insertOnDbUtente", "abbiamo un errore nella query ");
			System.out.println("errore nella query");
		}
	}

	public void deleteOnDbUtenteAll(){
		try {
			mUtenteDao.deleteOnDbAllUtente();
		} catch (Exception e) {
			Log.i("deleteOnDbUtenteAll", "abbiamo un errore nella query ");
			System.out.println("errore nella query");
		}
	}

	public void deleteOnDbOneUtente(Utente mUtente){
		try {
			mUtenteDao.deleteOnDbOneUtente(mUtente);
		} catch (Exception e) {
			Log.i("deleteOnDbOneUtente", "abbiamo un errore nella query ");
			System.out.println("errore nella query");
		}
	}

	public void deleteOnDbSomeUtente(List<Utente> mUtente){
		try {
			mUtenteDao.deleteOnDbSomeUtente(mUtente);
		} catch (Exception e) {
			Log.i("deleteOnDbSomeUtente", "abbiamo un errore nella query ");
			System.out.println("errore nella query");
		}
	}
	
	public boolean checkRegistrationOnDbOneUtente(String eMail, Context context){
		try {
			mUtenteDao =  new UtenteDao(context);
			mUtenteDao.open();
			return mUtenteDao.checkRegistrationOnDbOneUtente(eMail);
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("checkRegistrationOnDbOneUtente", "abbiamo un errore nella query di check dell'email");
			System.out.println("errore nella query");
		}
		return true;
	}

	public List<Utente> getOnDbAllUtente(){
		try {
			return mUtenteDao.getAllUtente();
		} catch (Exception e) {
			Log.i("getOnDbAllUtente", "abbiamo un errore nella query ");
			System.out.println("errore nella query");
		}
		return null;
	}

	public void ConnectionUtente(){
		try {
			//ControlConnection.getIstanceControlConnection().Utente();
		} catch (Exception e) {
			Log.i("cConnectionUtente", "abbiamo un errore nella query");
			System.out.println("errore nella query");
		}
	}
}