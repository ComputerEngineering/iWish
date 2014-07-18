package _iWish;
/**Raffaella*/

import java.util.Date;

public interface MyUtente {
	
	String getKeyUtente();
	String getName();
	String getSurname();
	Date getBirthday();
	String getCity();
	char getEmail();
	char getPassword();
	String getQuestion();
	String getAnswer();
	void setKeyUtente(String keyUtente);
	void setName(String name);
	void setSurname(String surname);
	void setBirthday(Date birthday);
	void setCity(String city);
	void setEmail(char email);
	void setPassword(char password);
	void setQuestion(String question);
	void setAnswer(String answer);
	
}
