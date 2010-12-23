package gui;

import javax.swing.*;

public class Menu extends JMenuBar
{
	private static final long serialVersionUID = -5617155576631422259L;

	public Menu()
	{
		JMenu mnArkiv = new JMenu("Profil");
		this.add(mnArkiv);
		
		JMenuItem mntmStartaNySession = new JMenuItem("\u00D6ppna profil");
		mnArkiv.add(mntmStartaNySession);
		
		JMenuItem mntmSparaSession = new JMenuItem("Spara profil");
		mnArkiv.add(mntmSparaSession);
		
		JMenuItem mntmAvslutaSession = new JMenuItem("St\u00E4ng profil");
		mnArkiv.add(mntmAvslutaSession);
		
		JMenu mnPeriod = new JMenu("Period");
		this.add(mnPeriod);
		
		JMenuItem mntmSkapaNyPeriod = new JMenuItem("Skapa ny period");
		mnPeriod.add(mntmSkapaNyPeriod);
		
		JMenuItem mntmppnaBefintligPeriod = new JMenuItem("\u00D6ppna period");
		mnPeriod.add(mntmppnaBefintligPeriod);
		
		JMenu mnHjlp = new JMenu("Kassabok");
		this.add(mnHjlp);
		
		JMenuItem mntmRegistreraHndelse = new JMenuItem("Notera h\u00E4ndelse");
		mnHjlp.add(mntmRegistreraHndelse);
		
		JMenuItem mntmRedigeraHndelse = new JMenuItem("Hantera h\u00E4ndelser");
		mnHjlp.add(mntmRedigeraHndelse);
		
		JMenuItem menuItem = new JMenuItem("Skriv kassabok till fil");
		mnHjlp.add(menuItem);
		
		JMenu mnRedigera = new JMenu("Budget");
		this.add(mnRedigera);
		
		JMenuItem mntmLggTillBudgetpost = new JMenuItem("L\u00E4gg till budgetpost");
		mnRedigera.add(mntmLggTillBudgetpost);
		
		JMenuItem mntmndraBudgetpost = new JMenuItem("\u00C4ndra budgetpost");
		mnRedigera.add(mntmndraBudgetpost);
		
		JMenuItem mntmTaBortBudgetpost = new JMenuItem("Ta bort budgetpost(er)");
		mnRedigera.add(mntmTaBortBudgetpost);
		
		JMenuItem mntmHmtaBudgetposter = new JMenuItem("H\u00E4mta budgetposter");
		mnRedigera.add(mntmHmtaBudgetposter);
		
		JMenuItem mntmSkrivBudgetTill = new JMenuItem("Skriv budget till fil");
		mnRedigera.add(mntmSkrivBudgetTill);
		
		JMenu mnHjlp_1 = new JMenu("Hj\u00E4lp");
		this.add(mnHjlp_1);
		
		JMenuItem mntmManual = new JMenuItem("Manual");
		mnHjlp_1.add(mntmManual);
		
		JMenuItem mntmOmProjektet = new JMenuItem("Om projektet");
		mnHjlp_1.add(mntmOmProjektet);
	}
}