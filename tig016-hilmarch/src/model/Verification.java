package model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/*
 * TIG061 H10 MDI - IT-universitetet i Gšteborg
 * @version Prototyp 3
 * @author Jonatan Hilmarch
 * hilmarch@skip.chalmers.se
 */
public class Verification implements Cloneable
{
	private GregorianCalendar timeStamp;
	private String note;
	private double amount;
	
	public Verification(long timeRepresentation, TimeZone timeZone, String note, double amount)
	{
		this.timeStamp = new GregorianCalendar(timeZone);
		Date trialTime = new Date(timeRepresentation);
		this.timeStamp.setTime(trialTime);
		this.note = note;
		this.amount = amount;
	}
	public Verification(TimeZone timeZone, String note, double amount)
	{
		this.timeStamp = new GregorianCalendar(timeZone);
		Date trialTime = new Date();
		this.timeStamp.setTime(trialTime);
		this.note = note;
		this.amount = amount;
	}
	
	public Verification(String note, double amount)
	{
		this.timeStamp = new GregorianCalendar(TimeZone.getDefault());
		Date trialTime = new Date();
		this.timeStamp.setTime(trialTime);
		this.note = note;
		this.amount = amount;
	}
	
	public Object clone() throws java.lang.CloneNotSupportedException
	{
		Verification verification = (Verification) super.clone();
		verification.timeStamp = (GregorianCalendar) timeStamp.clone();
		return verification;
	}

	public GregorianCalendar getTimeStamp()
	{
		return timeStamp;
	}

	public void setTimeStamp(GregorianCalendar timeStamp)
	{
		this.timeStamp = timeStamp;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}
}