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
	String getCity();
	String getSex();
	String getTypeUser();
	double getHeight();
	double getWeight();
	String getEmail();
	String getPassword();
	String getQuestion();
	String getAnswer();
	Context getC();
	List<Double> getListWeight();
	void setKeyUtente(long keyUtente);
	void setC(Context c);
	void setName(String name);
	void setSurname(String surname);

	void setCity(String city);
	void setEmail(String  email);
	void setSex(String sex);
	void setHeight(double height);
	void setWeight(double weight);
	void setTypeUser(String typeUser);
	void setPassword(String  password);
	void setQuestion(String question);
	void setAnswer(String answer);
	void setListWeight(Double weight);
	void setBirthday(String birthday);
	
}
