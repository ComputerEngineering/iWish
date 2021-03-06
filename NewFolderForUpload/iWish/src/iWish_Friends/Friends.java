package iWish_Friends;
/**Raffaella*/

import java.io.Serializable;

import _iWish.MyFriends;


public class Friends implements MyFriends,Serializable{
	/**The serialization runtime associates with each serializable class a version number, 
	 * called a serialVersionUID, which is used during deserialization to verify that the 
	 * sender and receiver of a serialized object have loaded classes for that object that
	 * are compatible with respect to serialization.*/
	private static final long serialVersionUID = 8098509035921720548L;
	private long keyFriends;
	private String name;
	private String surname;
	private String emailFriends;
	private int point=0;
	private String emailUser;
	//TODO manca il campo photo
	
	//costruttore prova antonio
	/*public Friends(String keyFriends, String name,String surname,int point){
	this.keyFriends=keyFriends;
	this.name=name;
	this.surname=surname;
	this.point=point;
	}
	
	public Friends(){
	}
	//fine*/

	@Override
	public String getEmailUser() {
		return emailUser;
	}
	@Override
	public long getKeyFriend() {
		return keyFriends;
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
	public int getPoint() {
		return point;
	}
	@Override
	public String getEmailFriends() {
		return emailFriends;
	}
	@Override
	public void setKeyFriend(long keyFriends) {
		this.keyFriends=keyFriends;
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
	public void setPoint(int point) {
		this.point=point;
	}
	@Override
	public void setEmailUser(String emailUser) {
		this.emailUser=emailUser;
	}
	@Override
	public void setEmailFriends(String emailFriends) {
		this.emailFriends=emailFriends;
	}
	@Override
	public String toString() {
		return "Friends [keyFriends=" + keyFriends + ", name=" + name
				+ ", surname=" + surname + ", emailFriends=" + emailFriends
				+ ", point=" + point + ", emailUser=" + emailUser + "]";
	}
	
}
