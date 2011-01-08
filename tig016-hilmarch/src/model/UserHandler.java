package model;

import gui.MainFrame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class UserHandler
{
	private MainFrame mainframe;
	private User currentUser, lastUser;

	private double totBudgetIncome, totOutcomeIncome, totBudgetExpence,
		totOutcomeExpence, totLeftToSpend;
	
	public UserHandler()
	{
		currentUser = new User();
		lastUser = new User();
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
	
	public User getLastUser()
	{
		return lastUser;
	}

	public void setLastUser(User lastUser)
	{
		this.lastUser = lastUser;
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
			saveProfileQuestion();
			
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
				try {
					setLastUser((User) getCurrentUser().clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				open = true;
				currentUser = new User();
				if(currentUser.readUserFromDisk("Save" + File.separator + s))
				{
					JOptionPane.showMessageDialog(null, "En profil lästes in utan fel eller varningar!");
				}
			}
		}
		
		else
		{
			File dir = new File(".");
			String dirText = "";
			try {
				dirText = dir.getCanonicalPath();
			} catch (IOException e) {
				dirText = "FEL: Aktuell katalog hittades inte!";
			}
			JOptionPane.showMessageDialog(null, "Det finns inga profiler att öppna i katalogen:\n" +
					dirText);
		}
		return open;
	}
	
	public boolean newProfile()
	{
		saveProfileQuestion();
		
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
			try {
				setLastUser((User) getCurrentUser().clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentUser = new User(name, periods);
			openProfile = true;
		}
		return openProfile;
	}
	
	public void closeProfile()
	{
		try {
			setLastUser((User) getCurrentUser().clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saveProfileQuestion();
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
	
	public boolean saveProfileQuestion()
	{
		if(!currentUser.getPeriodList().isEmpty())
		{
			int save = JOptionPane.showConfirmDialog(null, "Vill du spara den aktuella profilen innan??",
					"Spara", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(save == JOptionPane.YES_OPTION)
			{
				if(getCurrentUser().writeProfileToDisk(this))
				{
					JOptionPane.showMessageDialog(null, "Profilen sparades till disk!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Profile sparades inte till disk!");
				}
			}
		}
		return true;
	}
}