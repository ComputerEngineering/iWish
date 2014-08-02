package iWish_Utente;
/**Raffaella*/

import java.util.Date;

import android.content.Context;
import _iWish.MyUtente;

public class Utente implements MyUtente{
	private String keyUtente;
	private String name;
	private String surname;
	private Date birthday;
	private String city;
	private char email;
	private char password;
	private String question;
	private String answer;
	private Context c;
	//TODO devo aggiungere l'attributo PHOTO
	
	@Override
	public String getKeyUtente() {
		return keyUtente;
	}
	public Context getC() {
		return c;
	}
		@Override
	public String getName() {
		return name;
	}
	@Override
	public String getSurname() {
		return surname;
	}
	@Override
	public Date getBirthday() {
		return birthday;
	}
	@Override
	public String getCity() {
		return city;
	}
	@Override
	public char getEmail() {
		return email;
	}
	@Override
	public char getPassword() {
		return password;
	}
	@Override
	public String getQuestion() {
		return question;
	}
	@Override
	public String getAnswer() {
		return answer;
	}
	@Override
	public void setName(String name) {
		this.name=name;
	}
	@Override
	public void setSurname(String surname) {
		this.surname=surname;
	}
	@Override
	public void setBirthday(Date birthday) {
		this.birthday=birthday;
	}
	@Override
	public void setCity(String city) {
		this.city=city;
	}
	@Override
	public void setEmail(char email) {
		this.email=email;
	}
	@Override
	public void setPassword(char password) {
		this.password=password;
	}
	@Override
	public void setQuestion(String question) {
	this.question=question;
	}
	@Override
	public void setAnswer(String answer) {
		this.answer=answer;
	}
	public void setC(Context c) {
		this.c = c;
	}
	
	@Override
	public void setKeyUtente(String keyUtente) {
		this.keyUtente=keyUtente;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Utente [keyUtente=" + keyUtente + ", name=" + name
				+ ", surname=" + surname + ", birthday=" + birthday + ", city="
				+ city + ", email=" + email + ", password=" + password
				+ ", question=" + question + ", answer=" + answer + ", c=" + c
				+ "]";
	}
	
}
