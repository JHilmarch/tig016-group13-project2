package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import util.HelpFunctions;

/*
 * TIG061 H10 MDI - IT-universitetet i Gšteborg
 * @version Prototyp 3
 * @author Jonatan Hilmarch
 * hilmarch@skip.chalmers.se
 */
public class Period implements Cloneable
{
	private String name;
	private ArrayList<BudgetPost> incomeBudgetPostList, expenceBudgetPostList;
	
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
	
	public Object clone() throws java.lang.CloneNotSupportedException
	{
		Period period = (Period) super.clone();
		period.expenceBudgetPostList = HelpFunctions.cloneBudgetPostList(expenceBudgetPostList);
		period.incomeBudgetPostList = HelpFunctions.cloneBudgetPostList(incomeBudgetPostList);
		
		return period;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList<BudgetPost> getIncomeBudgetPostList()
	{
		return incomeBudgetPostList;
	}

	public void setIncomeBudgetPostList(ArrayList<BudgetPost> incomeBudgetPostList) 
	{
		this.incomeBudgetPostList = incomeBudgetPostList;
	}

	public ArrayList<BudgetPost> getExpenceBudgetPostList()
	{
		return expenceBudgetPostList;
	}

	public void setExpenceBudgetPostList(ArrayList<BudgetPost> expenceBudgetPostList)
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
	
	public void deleteMarkedIncomeBudgetPosts(UserHandler uh)
	{	
		try {
			uh.setLastUser((User) uh.getCurrentUser().clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<BudgetPost> tempList = new ArrayList<BudgetPost>();
		for(int i = 0; i < incomeBudgetPostList.size(); i++)
		{
			if(incomeBudgetPostList.get(i).isMarked()==true)
			{
				tempList.add(incomeBudgetPostList.get(i));
			}
		}
		
		incomeBudgetPostList.removeAll(tempList);
	}
	
	public void deleteMarkedExpenceBudgetPosts(UserHandler uh)
	{
		try {
			uh.setLastUser((User) uh.getCurrentUser().clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<BudgetPost> tempList = new ArrayList<BudgetPost>();
		for(int i = 0; i < expenceBudgetPostList.size(); i++)
		{
			if(expenceBudgetPostList.get(i).isMarked()==true)
			{
				tempList.add(expenceBudgetPostList.get(i));
			}
		}
		
		expenceBudgetPostList.removeAll(tempList);
	}
	
	public void changeMarkedIncomeBudgetPosts(UserHandler uh)
	{
		try {
			uh.setLastUser((User) uh.getCurrentUser().clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < incomeBudgetPostList.size(); i++)
		{
			if(incomeBudgetPostList.get(i).isMarked()==true)
			{
				String newName = JOptionPane.showInputDialog("" +
						"Mata in ett nytt namn fšr budgetpost " +
						incomeBudgetPostList.get(i).getName());
				if(newName==null)
					newName = incomeBudgetPostList.get(i).getName();
				incomeBudgetPostList.get(i).setName(newName);
			}
		}
	}
	
	public void changeMarkedExpenceBudgetPosts(UserHandler uh)
	{
		try {
			uh.setLastUser((User) uh.getCurrentUser().clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < expenceBudgetPostList.size(); i++)
		{
			if(expenceBudgetPostList.get(i).isMarked()==true)
			{
				String newName = JOptionPane.showInputDialog("" +
						"Mata in ett nytt namn fšr budgetpost " +
						expenceBudgetPostList.get(i).getName());
				if(newName==null)
					newName = expenceBudgetPostList.get(i).getName();
				expenceBudgetPostList.get(i).setName(newName);
			}
		}
	}
}