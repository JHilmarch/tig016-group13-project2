package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import util.HelpFunctions;

import model.BudgetPost;
import model.UserHandler;

public class Menu extends JMenuBar implements ActionListener
{
	private static final long serialVersionUID = -5617155576631422259L;
	
	private JMenu mnArkiv, mnPeriod, mnHjlp, mnRedigera, mnHjlp_1;
	private JMenuItem mntmOpenProfile, mntmNewProfile, mntmSaveProfile, mntmCloseProfile,
	mntmCreatePeriod, mntmOpenPeriod, mntmRegisterVerification,
	mntmChangeVerification, mntmWriteVerificationsToFile,
	mntmAddBudgetPost, mntmChangeBudgetPost,
	mntmDeleteBudgetPost, mntmGetBudgetPosts, mntmWriteBudgetToFile,
	mntmManual, mntmAbout;
	private UserHandler uh;
	private boolean profileOpen;

	public Menu(UserHandler uh)
	{
		profileOpen = false;
		this.uh = uh;
		mnArkiv = new JMenu("Profil");
		this.add(mnArkiv);
		
		mntmOpenProfile = new JMenuItem("\u00D6ppna profil");
		mntmOpenProfile.setEnabled(true);
		mntmOpenProfile.addActionListener(this);
		mnArkiv.add(mntmOpenProfile);
		
		mntmNewProfile = new JMenuItem("Ny profil");
		mntmNewProfile.setEnabled(true);
		mntmNewProfile.addActionListener(this);
		mnArkiv.add(mntmNewProfile);
		
		mntmSaveProfile = new JMenuItem("Spara profil");
		mntmSaveProfile.setEnabled(false);
		mntmSaveProfile.addActionListener(this);
		mnArkiv.add(mntmSaveProfile);
		
		mntmCloseProfile = new JMenuItem("St\u00E4ng profil");
		mntmCloseProfile.setEnabled(false);
		mntmCloseProfile.addActionListener(this);
		mnArkiv.add(mntmCloseProfile);
		
		mnPeriod = new JMenu("Period");
		this.add(mnPeriod);
		
		mntmCreatePeriod = new JMenuItem("Skapa ny period");
		mntmCreatePeriod.setEnabled(false);
		mntmCreatePeriod.addActionListener(this);
		mnPeriod.add(mntmCreatePeriod);
		
		mntmOpenPeriod = new JMenuItem("\u00D6ppna period");
		mntmOpenPeriod.setEnabled(false);
		mntmOpenPeriod.addActionListener(this);
		mnPeriod.add(mntmOpenPeriod);
		
		mnHjlp = new JMenu("Kassabok");
		this.add(mnHjlp);
		
		mntmRegisterVerification = new JMenuItem("Notera h\u00E4ndelse");
		mntmRegisterVerification.setEnabled(false);
		mntmRegisterVerification.addActionListener(this);
		mnHjlp.add(mntmRegisterVerification);
		
		mntmChangeVerification = new JMenuItem("Hantera h\u00E4ndelser");
		mntmChangeVerification.setEnabled(false);
		mntmChangeVerification.addActionListener(this);
		mnHjlp.add(mntmChangeVerification);
		
		mntmWriteVerificationsToFile = new JMenuItem("Skriv kassabok till fil");
		mntmWriteVerificationsToFile.setEnabled(false);
		mnHjlp.add(mntmWriteVerificationsToFile);
		
		mnRedigera = new JMenu("Budget");
		this.add(mnRedigera);
		
		mntmAddBudgetPost = new JMenuItem("L\u00E4gg till budgetpost");
		mntmAddBudgetPost.setEnabled(false);
		mntmAddBudgetPost.addActionListener(this);
		mnRedigera.add(mntmAddBudgetPost);
		
		mntmChangeBudgetPost = new JMenuItem("\u00C4ndra budgetpost(er)");
		mntmChangeBudgetPost.setEnabled(false);
		mntmChangeBudgetPost.addActionListener(this);
		mnRedigera.add(mntmChangeBudgetPost);
		
		mntmDeleteBudgetPost = new JMenuItem("Ta bort budgetpost(er)");
		mntmDeleteBudgetPost.addActionListener(this);
		mntmDeleteBudgetPost.setEnabled(false);
		mnRedigera.add(mntmDeleteBudgetPost);
		
		mntmGetBudgetPosts = new JMenuItem("H\u00E4mta budgetposter");
		mntmGetBudgetPosts.setEnabled(false);
		mnRedigera.add(mntmGetBudgetPosts);
		
		mntmWriteBudgetToFile = new JMenuItem("Skriv budget till fil");
		mntmWriteBudgetToFile.setEnabled(false);
		mnRedigera.add(mntmWriteBudgetToFile);
		
		mnHjlp_1 = new JMenu("Hj\u00E4lp");
		mnHjlp_1.setEnabled(true);
		this.add(mnHjlp_1);
		
		mntmManual = new JMenuItem("Manual");
		mntmManual.setEnabled(true);
		mntmManual.addActionListener(this);
		mnHjlp_1.add(mntmManual);
		
		mntmAbout = new JMenuItem("Om projektet");
		mntmAbout.setEnabled(true);
		mntmAbout.addActionListener(this);
		mnHjlp_1.add(mntmAbout);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("\u00D6ppna profil"))
		{
			if(uh.openProfile())
			{
				profileOpen = true;
				setMenuItems();
			}
		}
		
		else if(e.getActionCommand().equals("Ny profil"))
		{
			if(uh.newProfile())
			{
				profileOpen = true;
				setMenuItems();
			}
		}
		
		else if(e.getActionCommand().equals("Spara profil"))
		{
			if(uh.getCurrentUser().writeProfileToDisk())
			{
				JOptionPane.showMessageDialog(null, "Profile sparades till disk!");
			}
		}
		
		else if(e.getActionCommand().equals("St\u00E4ng profil"))
		{
			uh.closeProfile();
			profileOpen = false;
		}
		
		else if(e.getActionCommand().equals("Skapa ny period"))
		{
			uh.getCurrentUser().createNewPeriod();
		}
		
		else if(e.getActionCommand().equals("\u00D6ppna period"))
		{
			uh.getCurrentUser().openPeriod();
		}
		
		else if(e.getActionCommand().equals("Notera h\u00E4ndelse"))
		{
			NewVerificationPanel verpanel = new NewVerificationPanel(uh);
		}
		
		else if(e.getActionCommand().equals("L\u00E4gg till budgetpost"))
		{
			NewBudgetPostPanel bPanel = new NewBudgetPostPanel(uh);
		}
		
		else if(e.getActionCommand().equals("\u00C4ndra budgetpost(er)"))
		{
			uh.getCurrentUser().getCurrentPeriod().changeMarkedExpenceBudgetPosts();
			uh.getCurrentUser().getCurrentPeriod().changeMarkedIncomeBudgetPosts();
		}
		
		else if(e.getActionCommand().equals("Ta bort budgetpost(er)"))
		{
			if(JOptionPane.showConfirmDialog(null, "Vill du verkligen ta bort budgetposterna?",
					"Bekräfta val", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
			{
				uh.getCurrentUser().getCurrentPeriod().deleteMarkedExpenceBudgetPosts();
				uh.getCurrentUser().getCurrentPeriod().deleteMarkedIncomeBudgetPosts();
			}
		}
		
		else if(e.getActionCommand().equals("Om projektet"))
		{
			HelpFunctions.openURL("http://code.google.com/p/tig016-group13-project2/");
		}
		
		else if(e.getActionCommand().equals("Manual"))
		{
			HelpFunctions.openPDF();
		}
		
		uh.updateGUI();
	}
	
	public void setMenuItems()
	{
		if(profileOpen)
		{
			mntmSaveProfile.setEnabled(true);
			mntmCloseProfile.setEnabled(true);
			mntmCreatePeriod.setEnabled(true);
			mntmOpenPeriod.setEnabled(true);
			mntmRegisterVerification.setEnabled(true);
			mntmChangeVerification.setEnabled(true);
			mntmWriteVerificationsToFile.setEnabled(true);
			mntmAddBudgetPost.setEnabled(true);
			mntmGetBudgetPosts.setEnabled(true);
			mntmWriteBudgetToFile.setEnabled(true);
		}
		
		else
		{
			mntmSaveProfile.setEnabled(false);
			mntmCloseProfile.setEnabled(false);
			mntmCreatePeriod.setEnabled(false);
			mntmOpenPeriod.setEnabled(false);
			mntmRegisterVerification.setEnabled(false);
			mntmChangeVerification.setEnabled(false);
			mntmWriteVerificationsToFile.setEnabled(false);
			mntmAddBudgetPost.setEnabled(false);
			mntmGetBudgetPosts.setEnabled(false);
			mntmWriteBudgetToFile.setEnabled(false);
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
	}
}