package iWish_Friends;
/**Raffaella*/

import java.io.Serializable;

import android.content.Context;
import _iWish.MyFriends;


public class Friends implements MyFriends,Serializable{
	/**The serialization runtime associates with each serializable class a version number, 
	 * called a serialVersionUID, which is used during deserialization to verify that the 
	 * sender and receiver of a serialized object have loaded classes for that object that
	 * are compatible with respect to serialization.*/
	private static final long serialVersionUID = 8098509035921720548L;
	private String keyFriends;
	private String name;
	private String surname;
	private int point;
	private String emailUser;
	private Context c;
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
	public String getKeyFriend() {
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
	public Context getC() {
		return c;
	}
	@Override
	public void setKeyFriend(String keyFriends) {
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
	public String toString() {
		return "Friends [keyFriends=" + keyFriends + ", name=" + name
				+ ", surname=" + surname + ", point=" + point + "]";
	}
	@Override
	public void setC(Context C) {
		this.c=c;
	}
}
