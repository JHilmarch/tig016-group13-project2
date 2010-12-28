package model;

import java.util.ArrayList;
import java.util.List;

public class Period
{
	private String name;
	private List<Verification> verificationList;
	private List<BudgetPost> budgetPostList;
	
	public Period(String name, ArrayList<BudgetPost> budgetPostList)
	{
		this.name = name;
		this.budgetPostList = budgetPostList;
		this.verificationList = new ArrayList<Verification>();
	}
	
	public Period(String name)
	{
		this.name = name;
		this.budgetPostList = new ArrayList<BudgetPost>();
		this.verificationList = new ArrayList<Verification>();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Verification> getVerificationList()
	{
		return verificationList;
	}

	public void setVerificationList(List<Verification> verificationList)
	{
		this.verificationList = verificationList;
	}

	public List<BudgetPost> getBudgetPostList()
	{
		return budgetPostList;
	}

	public void setBudgetPostList(List<BudgetPost> budgetPostList)
	{
		this.budgetPostList = budgetPostList;
	}
}