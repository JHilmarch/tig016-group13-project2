package model;

public class BudgetPost
{
	private String name;
	private double amount, outcome;
	
	public BudgetPost(String name, double amount)
	{
		this.name = name;
		this.amount = amount;
		this.outcome = 0;
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
}