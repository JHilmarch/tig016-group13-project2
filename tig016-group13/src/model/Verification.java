package model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Verification
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