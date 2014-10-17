package iWish_Date;

import java.io.Serializable;

import _iWish.MyDate;

public class Date implements Serializable,MyDate{
	/**The serialization runtime associates with each serializable class a version number, 
	 * called a serialVersionUID, which is used during deserialization to verify that the 
	 * sender and receiver of a serialized object have loaded classes for that object that
	 * are compatible with respect to serialization.*/
	private static final long serialVersionUID = -4941293397154468648L;
	private int Day;
	private int Month;
	private int Year;
	
	@Override
	public int getDay() {
		return Day;
	}
	@Override
	public int getMonth() {
		return Month;
	}
	@Override
	public int getYear() {
		return Year;
	}
	@Override
	public void setDay(int Day) {
		this.Day=Day;
	}
	@Override
	public void setMonth(int Month) {
		this.Month=Month;
	}
	@Override
	public void setYear(int Year) {
		this.Year=Year;
	}
	@Override
	public String toString() {
		return "Date [Day=" + Day + ", Month=" + Month + ", Year=" + Year + "]";
	}
}
