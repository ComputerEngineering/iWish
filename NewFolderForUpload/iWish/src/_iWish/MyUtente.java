package _iWish;
/**Raffaella*/

import java.util.Date;
import java.util.List;

import android.content.Context;

public interface MyUtente {
	long getKeyUtente();
	String getName();
	String getSurname();
	String getBirthday();
	Date getBirthdayD();
	String getCity();
	String getSex();
	String getTypeUser();
	int getHeight();
	int getWeight();
	String getEmail();
	String getPassword();
	String getQuestion();
	String getAnswer();
	List<Integer> getListWeight();
	void setKeyUtente(long keyUtente);
	void setName(String name);
	void setSurname(String surname);
	void setCity(String city);
	void setEmail(String  email);
	void setSex(String sex);
	void setHeight(int height);
	void setWeight(int weight);
	void setTypeUser(String typeUser);
	void setPassword(String  password);
	void setQuestion(String question);
	void setAnswer(String answer);
	void setListWeight(int weight);
	void setBirthday(String birthday);
	void setBirthdayD(Date birthdayD);
	
}
