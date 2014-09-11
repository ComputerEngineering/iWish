package _iWish;
/**Raffaella*/

import java.util.Date;

public interface MyUtente {
	
	String getKeyUtente();
	String getName();
	String getSurname();
	Date getBirthday();
	String getCity();
	String getSex();
	String getTypeUser();
	double getHeight();
	double getWeight();
	String getEmail();
	String getPassword();
	String getQuestion();
	String getAnswer();
	void setKeyUtente(String keyUtente);
	void setName(String name);
	void setSurname(String surname);
	void setBirthday(Date birthday);
	void setCity(String city);
	void setEmail(String  email);
	void setSex(String sex);
	void setHeight(double height);
	void setWeight(double weight);
	void setTypeUser(String typeUser);
	void setPassword(String  password);
	void setQuestion(String question);
	void setAnswer(String answer);
	
}
