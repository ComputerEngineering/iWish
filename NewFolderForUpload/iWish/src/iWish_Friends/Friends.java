package iWish_Friends;

import _iWish.MyFriends;


public class Friends implements MyFriends{
	private String keyFriends;
	private String name;
	private String surname;
	private int point;
	//TODO manca il campo photo

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
	public void setKeyFriend(String keyUtente) {
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
	public String toString() {
		return "Friends [keyFriends=" + keyFriends + ", name=" + name
				+ ", surname=" + surname + ", point=" + point + "]";
	}
}
