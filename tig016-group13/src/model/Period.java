package model;

import java.util.ArrayList;
import java.util.List;

public class Period
{
	private String name;
	private List<BudgetPost> incomeBudgetPostList, expenceBudgetPostList;
	
	public Period(String name, ArrayList<BudgetPost> incomeBudgetPostList,
			ArrayList<BudgetPost> expenceBudgetPostList)
	{
		this.name = name;
		this.incomeBudgetPostList = incomeBudgetPostList;
		this.expenceBudgetPostList = expenceBudgetPostList;
	}

	public Period(String name)
	{
		this.name = name;
		this.incomeBudgetPostList = new ArrayList<BudgetPost>();
		this.expenceBudgetPostList = new ArrayList<BudgetPost>();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<BudgetPost> getIncomeBudgetPostList()
	{
		return incomeBudgetPostList;
	}

	public void setIncomeBudgetPostList(List<BudgetPost> incomeBudgetPostList) 
	{
		this.incomeBudgetPostList = incomeBudgetPostList;
	}

	public List<BudgetPost> getExpenceBudgetPostList()
	{
		return expenceBudgetPostList;
	}

	public void setExpenceBudgetPostList(List<BudgetPost> expenceBudgetPostList)
	{
		this.expenceBudgetPostList = expenceBudgetPostList;
	}
	
	public void addIncomeBudgetPost(BudgetPost bp)
	{
		incomeBudgetPostList.add(bp);
	}
	
	public void addExpenceBudgetPost(BudgetPost bp)
	{
		expenceBudgetPostList.add(bp);
	}
}