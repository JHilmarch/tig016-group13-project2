package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

import model.UserHandler;

public class Menu extends JMenuBar implements ActionListener, ItemListener
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
	private boolean postMarked, profileOpen;

	public Menu(UserHandler uh)
	{
		postMarked = false;
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
		
		mntmChangeBudgetPost = new JMenuItem("\u00C4ndra budgetpost");
		mntmChangeBudgetPost.setEnabled(false);
		mnRedigera.add(mntmChangeBudgetPost);
		
		mntmDeleteBudgetPost = new JMenuItem("Ta bort budgetpost(er)");
		mntmDeleteBudgetPost.setEnabled(false);
		mnRedigera.add(mntmDeleteBudgetPost);
		
		mntmGetBudgetPosts = new JMenuItem("H\u00E4mta budgetposter");
		mntmGetBudgetPosts.setEnabled(false);
		mnRedigera.add(mntmGetBudgetPosts);
		
		mntmWriteBudgetToFile = new JMenuItem("Skriv budget till fil");
		mntmWriteBudgetToFile.setEnabled(false);
		mnRedigera.add(mntmWriteBudgetToFile);
		
		mnHjlp_1 = new JMenu("Hj\u00E4lp");
		this.add(mnHjlp_1);
		
		mntmManual = new JMenuItem("Manual");
		mnHjlp_1.add(mntmManual);
		
		mntmAbout = new JMenuItem("Om projektet");
		mnHjlp_1.add(mntmAbout);
	}

	@Override
	public void itemStateChanged(ItemEvent item)
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem source = (JMenuItem)(e.getSource());
		if(source.getText().equals("\u00D6ppna profil"))
		{
			if(uh.openProfile())
			{
				profileOpen = true;
				setMenuItems();
				uh.updateGUI();
			}
		}
		
		else if(source.getText().equals("Ny profil"))
		{
			if(uh.newProfile())
			{
				profileOpen = true;
				setMenuItems();
				uh.updateGUI();
			}
		}
		
		else if(source.getText().equals("Spara profil"))
		{
			if(uh.getCurrentUser().writeProfileToDisk())
			{
				JOptionPane.showMessageDialog(null, "Profile sparades till disk!");
			}
		}
		
		else if(source.getText().equals("St\u00E4ng profil"))
		{
			uh.closeProfile();
			profileOpen = false;
			setMenuItems();
		}
		
		else if(source.getText().equals("Skapa ny period"))
		{
			uh.getCurrentUser().createNewPeriod();
			uh.updateGUI();
		}
		
		else if(source.getText().equals("\u00D6ppna period"))
		{
			uh.getCurrentUser().openPeriod();
			uh.updateGUI();
		}
		
		else if(source.getText().equals("Notera h\u00E4ndelse"))
		{
			NewVerificationPanel verpanel = new NewVerificationPanel(uh);
		}
		
		else if(source.getText().equals("L\u00E4gg till budgetpost"))
		{
			NewBudgetPostPanel bPanel = new NewBudgetPostPanel(uh);
		}
		
	}
	
	private void setMenuItems()
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
	}
}