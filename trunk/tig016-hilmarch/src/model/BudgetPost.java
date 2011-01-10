package model;

import java.text.DecimalFormat;
import java.util.ArrayList;

import util.HelpFunctions;

/*
 * TIG061 H10 MDI - IT-universitetet i Gšteborg
 * @version Prototyp 3
 * @author Jonatan Hilmarch
 * hilmarch@skip.chalmers.se
 */
public class BudgetPost implements Cloneable
{
	private String name;
	private double amount, outcome;
	private boolean marked;
	private ArrayList<Verification> verificationList;
	
	public BudgetPost(String name, double amount, ArrayList<Verification> verificationList)
	{
		this.name = name;
		this.amount = amount;
		this.outcome = 0;
		this.verificationList = verificationList;
		setOutcome();
		this.marked = false;
	}

	public BudgetPost(String name, double amount, double outcome,
			ArrayList<Verification> verificationList)
	{
		super();
		this.name = name;
		this.amount = amount;
		this.outcome = 0;
		this.verificationList = verificationList;
		setOutcome();
		this.marked = false;
	}
	
	public Object clone() throws java.lang.CloneNotSupportedException
	{
		BudgetPost budgetPost = (BudgetPost) super.clone();
		budgetPost.verificationList = HelpFunctions.cloneVerificationList(verificationList);
		return budgetPost;
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

	public void setOutcome()
	{
		this.outcome = 0;
		for(Verification v : verificationList)
		{
			this.outcome += v.getAmount();
		}
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

	public ArrayList<Verification> getVerificationList()
	{
		return verificationList;
	}

	public void setVerificationList(ArrayList<Verification> verificationList)
	{
		this.verificationList = verificationList;
	}
	
	public void addVerification(Verification ver)
	{
		verificationList.add(ver);
	}
	
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return ("\n" + name + "\t" + df.format(amount)  + "\t" + df.format(outcome));
	}
}