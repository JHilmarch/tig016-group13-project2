package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class Menu extends JMenuBar implements ActionListener, ItemListener
{
	private static final long serialVersionUID = -5617155576631422259L;
	
	private JMenu mnArkiv, mnPeriod, mnHjlp, mnRedigera, mnHjlp_1;
	private JMenuItem mntmOpenProfile, mntmSaveProfile, mntmCloseProfile,
	mntmCreatePeriod, mntmOpenPeriod, mntmRegisterVerification,
	mntmChangeVerification, mntmWriteVerificationsToFile,
	mntmAddBudgetPost, mntmChangeBudgetPost,
	mntmDeleteBudgetPost, mntmGetBudgetPosts, mntmWriteBudgetToFile,
	mntmManual, mntmAbout;

	public Menu()
	{
		mnArkiv = new JMenu("Profil");
		this.add(mnArkiv);
		
		mntmOpenProfile = new JMenuItem("\u00D6ppna profil");
		mntmOpenProfile.setEnabled(true);
		mntmOpenProfile.addActionListener(this);
		mnArkiv.add(mntmOpenProfile);
		
		mntmSaveProfile = new JMenuItem("Spara profil");
		mntmSaveProfile.setEnabled(false);
		mnArkiv.add(mntmSaveProfile);
		
		mntmCloseProfile = new JMenuItem("St\u00E4ng profil");
		mntmCloseProfile.setEnabled(false);
		mnArkiv.add(mntmCloseProfile);
		
		mnPeriod = new JMenu("Period");
		this.add(mnPeriod);
		
		mntmCreatePeriod = new JMenuItem("Skapa ny period");
		mntmCreatePeriod.setEnabled(false);
		mnPeriod.add(mntmCreatePeriod);
		
		mntmOpenPeriod = new JMenuItem("\u00D6ppna period");
		mntmOpenPeriod.setEnabled(false);
		mnPeriod.add(mntmOpenPeriod);
		
		mnHjlp = new JMenu("Kassabok");
		this.add(mnHjlp);
		
		mntmRegisterVerification = new JMenuItem("Notera h\u00E4ndelse");
		mntmRegisterVerification.setEnabled(false);
		mnHjlp.add(mntmRegisterVerification);
		
		mntmChangeVerification = new JMenuItem("Hantera h\u00E4ndelser");
		mntmChangeVerification.setEnabled(false);
		mnHjlp.add(mntmChangeVerification);
		
		mntmWriteVerificationsToFile = new JMenuItem("Skriv kassabok till fil");
		mntmWriteVerificationsToFile.setEnabled(false);
		mnHjlp.add(mntmWriteVerificationsToFile);
		
		mnRedigera = new JMenu("Budget");
		this.add(mnRedigera);
		
		mntmAddBudgetPost = new JMenuItem("L\u00E4gg till budgetpost");
		mntmAddBudgetPost.setEnabled(false);
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
			// TODO create and open a new JFrame.
		}
	}
}