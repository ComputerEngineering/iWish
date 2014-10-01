package iWish_Utente;
/**Raffaella*/

import java.util.Date;
import java.util.List;

import android.content.Context;
import _iWish.MyUtente;

public class Utente implements MyUtente{
	private String keyUtente;
	private long timeStamp;
	private String name;
	private String surname;
	private Date birthday;
	private String sex;
	private String typeUser;
	private double height;
	private double weightFirst;
	private List<Double> weight;
	private String city;
	private String email;
	private String password;
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
	public String getEmail() {
		return email;
	}
	@Override
	public String getPassword() {
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
	public String getSex() {
		return sex;
	}
	@Override
	public String getTypeUser() {
		return typeUser;
	}
	@Override
	public double getHeight() {
		return height;
	}
	@Override
	public double getWeight() {
		return weightFirst;
	}
	@Override
	public List<Double> getListWeight() {
		return weight;
	}
	@Override
	public long getTimeStamp() {
		return timeStamp;
	}
	@Override
	public void setTimeStamp(long timeStamp) {
		this.timeStamp=timeStamp;
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
	public void setSex(String sex) {
		this.sex=sex;
	}
	@Override
	public void setHeight(double height) {
		this.height=height;
	}
	@Override
	public void setWeight(double weight) {
		this.weightFirst=weight;
	}
	@Override
	public void setTypeUser(String typeUser) {
		this.typeUser=typeUser;
	}
	
	@Override
	public void setKeyUtente(String keyUtente) {
		this.keyUtente=keyUtente;
	}
	@Override
	public void setEmail(String  email) {
		this.email=email;
	}
	@Override
	public void setPassword(String  password) {
		this.password=password;
	}
	@Override
	public void setListWeight(Double weight) {
		//TODO DA FARE
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Utente [keyUtente=" + keyUtente + ", name=" + name
				+ ", surname=" + surname + ", birthday=" + birthday + ", sex="
				+ sex + ", typeUser=" + typeUser + ", height=" + height
				+ ", weight=" + weightFirst + ", city=" + city + ", email=" + email
				+ ", password=" + password + ", question=" + question
				+ ", answer=" + answer + "]";
	}

	
	
	
}
