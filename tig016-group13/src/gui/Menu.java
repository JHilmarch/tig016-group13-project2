package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import util.HelpFunctions;

import model.BudgetPost;
import model.User;
import model.UserHandler;

public class Menu extends JMenuBar implements ActionListener
{
	private static final long serialVersionUID = -5617155576631422259L;
	
	private JMenu mnArkiv, mnPeriod, mnEManager, mnRedigera, mnHjlp,mnUndo;
	private JMenuItem mntmOpenProfile, mntmNewProfile, mntmSaveProfileToDisk, 
	mntmSaveProfileToNetwork, mntmCloseProfile, mntmCreatePeriod, mntmDeleteVerification,
	mntmOpenPeriod, mntmDeletePeriod, mntmRegisterVerification,
	mntmChangeVerification, mntmWriteVerificationsToFile,
	mntmAddBudgetPost, mntmChangeBudgetPost,
	mntmDeleteBudgetPost, mntmGetBudgetPosts, mntmWriteBudgetToFile,
	mntmManual, mntmAbout, mntmUndo;
	private UserHandler uh;
	private boolean profileOpen;

	public Menu(UserHandler uh)
	{
		ProfileListener profileListener = new ProfileListener();
		profileOpen = false;
		this.uh = uh;
		mnArkiv = new JMenu("Profil");
		this.add(mnArkiv);
		
		mntmOpenProfile = new JMenuItem("\u00D6ppna");
		mntmOpenProfile.setEnabled(true);
		mntmOpenProfile.addActionListener(profileListener);
		mnArkiv.add(mntmOpenProfile);
		
		mntmNewProfile = new JMenuItem("Skapa ny");
		mntmNewProfile.setEnabled(true);
		mntmNewProfile.addActionListener(profileListener);
		mnArkiv.add(mntmNewProfile);
		
		mntmSaveProfileToDisk = new JMenuItem("Spara till disk");
		mntmSaveProfileToDisk.setEnabled(false);
		mntmSaveProfileToDisk.addActionListener(profileListener);
		mnArkiv.add(mntmSaveProfileToDisk);
		
		mntmCloseProfile = new JMenuItem("St\u00E4ng");
		mntmCloseProfile.setEnabled(false);
		mntmCloseProfile.addActionListener(profileListener);
		mnArkiv.add(mntmCloseProfile);
		
		PeriodListener periodListener = new PeriodListener();
		mnPeriod = new JMenu("Period");
		this.add(mnPeriod);
		
		mntmOpenPeriod = new JMenuItem("\u00D6ppna");
		mntmOpenPeriod.setEnabled(false);
		mntmOpenPeriod.addActionListener(periodListener);
		mnPeriod.add(mntmOpenPeriod);
		
		mntmCreatePeriod = new JMenuItem("Skapa ny");
		mntmCreatePeriod.setEnabled(false);
		mntmCreatePeriod.addActionListener(periodListener);
		mnPeriod.add(mntmCreatePeriod);
		
		mntmDeletePeriod = new JMenuItem("Radera");
		mntmDeletePeriod.setEnabled(false);
		mntmDeletePeriod.addActionListener(periodListener);
		mnPeriod.add(mntmDeletePeriod);
		
		mnEManager = new JMenu("Kassabok");
		this.add(mnEManager);
		
		mntmRegisterVerification = new JMenuItem("Notera h\u00E4ndelse");
		mntmRegisterVerification.setEnabled(false);
		mntmRegisterVerification.addActionListener(this);
		mnEManager.add(mntmRegisterVerification);
		
		mntmChangeVerification = new JMenuItem("Redigera h\u00E4ndelse");
		mntmChangeVerification.setEnabled(false);
		mntmChangeVerification.addActionListener(this);
		mnEManager.add(mntmChangeVerification);
		
		mntmDeleteVerification = new JMenuItem("Radera h\u00E4ndelse");
		mntmDeleteVerification.setEnabled(false);
		mntmDeleteVerification.addActionListener(this);
		mnEManager.add(mntmDeleteVerification);
		
		mntmWriteVerificationsToFile = new JMenuItem("Skriv kassabok till fil");
		mntmWriteVerificationsToFile.setEnabled(false);
		mntmWriteVerificationsToFile.addActionListener(this);
		mnEManager.add(mntmWriteVerificationsToFile);
		
		mnRedigera = new JMenu("Budget");
		this.add(mnRedigera);
		
		mntmAddBudgetPost = new JMenuItem("L\u00E4gg till budgetpost");
		mntmAddBudgetPost.setEnabled(false);
		mntmAddBudgetPost.addActionListener(this);
		mnRedigera.add(mntmAddBudgetPost);
		
		mntmChangeBudgetPost = new JMenuItem("Redigera budgetpost(er)");
		mntmChangeBudgetPost.setEnabled(false);
		mntmChangeBudgetPost.addActionListener(this);
		mnRedigera.add(mntmChangeBudgetPost);
		
		mntmDeleteBudgetPost = new JMenuItem("Radera budgetpost(er)");
		mntmDeleteBudgetPost.addActionListener(this);
		mntmDeleteBudgetPost.setEnabled(false);
		mnRedigera.add(mntmDeleteBudgetPost);
		
		mntmGetBudgetPosts = new JMenuItem("H\u00E4mta budgetposter");
		mntmGetBudgetPosts.addActionListener(this);
		mntmGetBudgetPosts.setEnabled(false);
		mnRedigera.add(mntmGetBudgetPosts);
		
		mntmWriteBudgetToFile = new JMenuItem("Skriv budget till fil");
		mntmWriteBudgetToFile.addActionListener(this);
		mntmWriteBudgetToFile.setEnabled(false);
		mnRedigera.add(mntmWriteBudgetToFile);
		
		mnHjlp = new JMenu("Hj\u00E4lp");
		mnHjlp.setEnabled(true);
		this.add(mnHjlp);
		
		mntmManual = new JMenuItem("Manual");
		mntmManual.setEnabled(true);
		mntmManual.addActionListener(this);
		mnHjlp.add(mntmManual);
		
		mntmAbout = new JMenuItem("Om projektet");
		mntmAbout.setEnabled(true);
		mntmAbout.addActionListener(this);
		mnHjlp.add(mntmAbout);
		
		mnUndo = new JMenu("\u00c5ngra");
		mnUndo.setEnabled(true);
		this.add(mnUndo);
		
		mntmUndo = new JMenuItem("\u00c5ngra");
		mntmUndo.setEnabled(true);
		mntmUndo.addActionListener(this);
		mnUndo.add(mntmUndo);
	}

	public void actionPerformed(ActionEvent e)
	{	
		if(e.getActionCommand().equals("Notera h\u00E4ndelse"))
		{
			NewVerificationPanel verpanel = new NewVerificationPanel(uh);
		}
		
		else if(e.getActionCommand().equals("Redigera h\u00E4ndelse"))
		{
			ManageVerification manPanel = new ManageVerification(uh);
		}
		
		else if(e.getActionCommand().equals("Radera h\u00E4ndelse"))
		{
			// TODO function and gui
			//temporary dialogbox
			JOptionPane.showMessageDialog(null, "Ej implementerat än");
		}
		
		else if(e.getActionCommand().equals("Skriv kassabok till fil"))
		{
			// TODO function and gui
			//temporary dialogbox
			JOptionPane.showMessageDialog(null, "Ej implementerat än");
		}
		
		else if(e.getActionCommand().equals("L\u00E4gg till budgetpost"))
		{
			NewBudgetPostPanel bPanel = new NewBudgetPostPanel(uh);
		}
		
		else if(e.getActionCommand().equals("Redigera budgetpost(er)"))
		{
			uh.getCurrentUser().getCurrentPeriod().changeMarkedExpenceBudgetPosts(uh);
			uh.getCurrentUser().getCurrentPeriod().changeMarkedIncomeBudgetPosts(uh);
		}
		
		else if(e.getActionCommand().equals("Radera budgetpost(er)"))
		{
			if(JOptionPane.showConfirmDialog(null, "Vill du verkligen ta bort budgetposterna?",
					"Bekräfta val", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
			{
				uh.getCurrentUser().getCurrentPeriod().deleteMarkedExpenceBudgetPosts(uh);
				uh.getCurrentUser().getCurrentPeriod().deleteMarkedIncomeBudgetPosts(uh);
			}
		}
		
		else if(e.getActionCommand().equals("H\u00E4mta budgetposter"))
		{
			// TODO function and gui
			//Temp dialog box
			JOptionPane.showMessageDialog(null, "Ej implementerat ännu.");
		}
		
		else if(e.getActionCommand().equals("Skriv budget till fil"))
		{
			// TODO function and gui
			//Temp dialog box
			JOptionPane.showMessageDialog(null, "Ej implementerat ännu.");
		}
		
		else if(e.getActionCommand().equals("Om projektet"))
		{
			HelpFunctions.openURL("http://code.google.com/p/tig016-group13-project2/");
		}
		
		else if(e.getActionCommand().equals("Manual"))
		{
			HelpFunctions.openPDF();
		}
		
		else if(e.getActionCommand().equals("\u00c5ngra"))
		{
			try {
				uh.setCurrentUser((User) uh.getLastUser().clone());
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		uh.updateGUI();
	}
	
	private class ProfileListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("\u00D6ppna"))
			{
				if(uh.openProfile())
				{
					profileOpen = true;
					setMenuItems();
				}
			}
			
			else if(e.getActionCommand().equals("Skapa ny"))
			{
				if(uh.newProfile())
				{
					profileOpen = true;
					setMenuItems();
				}
			}
			
			else if(e.getActionCommand().equals("Spara"))
			{
				if(uh.getCurrentUser().writeProfileToDisk())
				{
					JOptionPane.showMessageDialog(null, "Profilen sparades till disk!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Profilen sparades inte till disk!");
				}
			}
			
			else if(e.getActionCommand().equals("St\u00E4ng"))
			{
				uh.closeProfile();
				profileOpen = false;
			}
			uh.updateGUI();
		}
	}
	
	private class PeriodListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("Skapa ny"))
			{
				uh.getCurrentUser().createNewPeriod(uh);
			}
			
			else if(e.getActionCommand().equals("\u00D6ppna"))
			{
				uh.getCurrentUser().openPeriod(uh);
			}
			
			else if(e.getActionCommand().equals("Radera"))
			{
				// TODO function and gui
				//temporary dialogbox
				JOptionPane.showMessageDialog(null, "Ej implementerat än");
			}
			uh.updateGUI();
		}
		
	}
	
	public void setMenuItems()
	{
		if(profileOpen)
		{
			mntmCloseProfile.setEnabled(true);
			mntmCreatePeriod.setEnabled(true);
			
			if(uh.getCurrentUser().getPeriodList().isEmpty())
			{
				mntmSaveProfileToDisk.setEnabled(false);
				mntmOpenPeriod.setEnabled(false);
				mntmRegisterVerification.setEnabled(false);
				mntmChangeVerification.setEnabled(false);
				mntmWriteVerificationsToFile.setEnabled(false);
				mntmAddBudgetPost.setEnabled(false);
				mntmGetBudgetPosts.setEnabled(false);
				mntmWriteBudgetToFile.setEnabled(false);
				mntmDeletePeriod.setEnabled(false);
			}
			
			else
			{
				mntmSaveProfileToDisk.setEnabled(true);
				mntmOpenPeriod.setEnabled(true);
				mntmRegisterVerification.setEnabled(true);
				mntmChangeVerification.setEnabled(true);
				mntmWriteVerificationsToFile.setEnabled(true);
				mntmAddBudgetPost.setEnabled(true);
				mntmGetBudgetPosts.setEnabled(true);
				mntmWriteBudgetToFile.setEnabled(true);
				mntmDeletePeriod.setEnabled(true);
			}
		}
		
		else
		{
			mntmSaveProfileToDisk.setEnabled(false);
			mntmCloseProfile.setEnabled(false);
			mntmCreatePeriod.setEnabled(false);
			mntmOpenPeriod.setEnabled(false);
			mntmRegisterVerification.setEnabled(false);
			mntmChangeVerification.setEnabled(false);
			mntmWriteVerificationsToFile.setEnabled(false);
			mntmAddBudgetPost.setEnabled(false);
			mntmGetBudgetPosts.setEnabled(false);
			mntmWriteBudgetToFile.setEnabled(false);
			mntmDeletePeriod.setEnabled(false);
		}
		
		boolean marked = false;
		for(BudgetPost bp : uh.getCurrentUser().getCurrentPeriod().getExpenceBudgetPostList())
		{
			if(bp.isMarked()==true)
			{
				marked = true;
			}
		}
		for(BudgetPost bp : uh.getCurrentUser().getCurrentPeriod().getIncomeBudgetPostList())
		{
			if(bp.isMarked()==true)
			{
				marked = true;
			}
		}
		if(marked)
		{
			mntmChangeBudgetPost.setEnabled(true);
			mntmDeleteBudgetPost.setEnabled(true);
		}
		else
		{
			mntmChangeBudgetPost.setEnabled(false);
			mntmDeleteBudgetPost.setEnabled(false);
		}
		
		if(uh.getCurrentUser().getCurrentPeriod().getExpenceBudgetPostList().isEmpty() &&
				uh.getCurrentUser().getCurrentPeriod().getIncomeBudgetPostList().isEmpty())
		{
			mntmChangeVerification.setEnabled(false);
			mntmRegisterVerification.setEnabled(false);
			mntmWriteVerificationsToFile.setEnabled(false);
			mntmWriteBudgetToFile.setEnabled(false);
		}
		
		boolean expenceVerListEmpty = false;
		for(BudgetPost bp : uh.getCurrentUser().getCurrentPeriod().getExpenceBudgetPostList())
		{
			if(bp.getVerificationList().isEmpty())
			{
				expenceVerListEmpty = true;
			}
		}
		boolean incomeVerListEmpty = false;
		for(BudgetPost bp : uh.getCurrentUser().getCurrentPeriod().getIncomeBudgetPostList())
		{
			if(bp.getVerificationList().isEmpty())
			{
				incomeVerListEmpty = true;
			}
		}
		
		if(expenceVerListEmpty && incomeVerListEmpty)
		{
			mntmChangeVerification.setEnabled(false);
			mntmWriteVerificationsToFile.setEnabled(false);
		}
	}
}