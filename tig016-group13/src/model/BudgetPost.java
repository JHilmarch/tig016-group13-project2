package model;

import java.util.List;

public class BudgetPost
{
	private String name;
	private double amount, outcome;
	private boolean marked;
	private List<Verification> verificationList;
	
	public BudgetPost(String name, double amount, List<Verification> verificationList)
	{
		this.name = name;
		this.amount = amount;
		this.outcome = 0;
		this.verificationList = verificationList;
		this.marked = false;
	}

	public BudgetPost(String name, double amount, double outcome,
			List<Verification> verificationList)
	{
		super();
		this.name = name;
		this.amount = amount;
		this.outcome = outcome;
		this.verificationList = verificationList;
		this.marked = false;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	public double getOutcome()
	{
		return outcome;
	}

	public void setOutcome(double outcome) 
	{
		this.outcome = outcome;
	}

	public boolean isMarked()
	{
		return marked;
	}

	public void setMarked(boolean marked)
	{
		this.marked = marked;
	}

	public List<Verification> getVerificationList()
	{
		return verificationList;
	}

	public void setVerificationList(List<Verification> verificationList)
	{
		this.verificationList = verificationList;
	}
}