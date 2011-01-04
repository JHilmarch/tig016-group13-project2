package model;

import gui.MainFrame;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class UserHandler
{
	private MainFrame mainframe;
	private User currentUser;
	private double totBudgetIncome, totOutcomeIncome, totBudgetExpence,
		totOutcomeExpence, totLeftToSpend;
	
	public UserHandler()
	{
		currentUser = new User();
		this.mainframe = new MainFrame();
		mainframe.buildGUI(this);
	}
	
	public double getTotBudgetIncome()
	{
		return totBudgetIncome;
	}

	public double getTotOutcomeIncome() 
	{
		return totOutcomeIncome;
	}

	public double getTotBudgetExpence() 
	{
		return totBudgetExpence;
	}

	public double getTotOutcomeExpence()
	{
		return totOutcomeExpence;
	}

	public double getTotLeftToSpend() 
	{
		return totLeftToSpend;
	}

	public User getCurrentUser()
	{
		return currentUser;
	}
	
	public void setCurrentUser(User currentUser)
	{
		this.currentUser = currentUser;
	}
	
	public boolean openProfile()
	{
		boolean open = false;
		File file = new File("Save" + File.separator);
		String[] content = file.list();
		ArrayList<String> filenames = new ArrayList<String>();
		
		for(String s : content)
		{
			if(s.endsWith(".xml"))
			{
				filenames.add(s);
			}
		}
		
		if(filenames.size()>0)
		{
			String s = null;
			Object[] data = filenames.toArray(); 
			s = (String)JOptionPane.showInputDialog(
					null,
	                "Var vänlig och välj profil: ",
	                "Öppna profil",
	                JOptionPane.PLAIN_MESSAGE,
	                null, data,
	                data[0]);
			
			if(s != null)
			{
				open = true;
				currentUser = new User();
				if(currentUser.readUserFromDisk("Save" + File.separator + s))
				{
					JOptionPane.showMessageDialog(null, "En ny profil lästes in utan fel!");
				}
				mainframe.updateGUI();
			}
		}
		return open;
	}
	
	public boolean newProfile()
	{
		boolean openProfile;
		ArrayList<Period> periods = new ArrayList<Period>();
		String name = "";
		name = JOptionPane.showInputDialog(null, "Mata in profilnamn: ");
		
		if(name == null)
		{
			openProfile = false;
		}
		else
		{
			currentUser = new User(name, periods);
			openProfile = true;
		}
		return openProfile;
	}
	
	public void closeProfile()
	{
		currentUser = new User();
		mainframe.updateGUI();
	}
	
	public void countAndSetSum()
	{
		totBudgetIncome = 0;
		totOutcomeIncome = 0;
		totBudgetExpence = 0;
		totOutcomeExpence = 0;
		
		for(BudgetPost bp : currentUser.getCurrentPeriod().getIncomeBudgetPostList())
		{
			totBudgetIncome += bp.getAmount();
			totOutcomeIncome += bp.getOutcome();
		}
		
		for(BudgetPost bp : currentUser.getCurrentPeriod().getExpenceBudgetPostList())
		{
			totBudgetExpence += bp.getAmount();
			totOutcomeExpence += bp.getOutcome();
		}
		
		totLeftToSpend = totBudgetExpence - totOutcomeExpence;
	}
	
	public void updateGUI()
	{
		mainframe.updateGUI();
	}
}