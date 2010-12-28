package gui;

import java.awt.GridLayout;

import javax.swing.*;

public class BottomPanel extends JPanel
{
	private static final long serialVersionUID = -405718142568283106L;
	JPanel expenceSummary;
	JLabel sumText, amountText, outcomeText, leftToSpendText, status;
	
	public BottomPanel()
	{
		sumText = new JLabel("Summa: ",SwingConstants.CENTER);
		amountText = new JLabel("7000",SwingConstants.CENTER);
		outcomeText = new JLabel("8600",SwingConstants.CENTER);
		leftToSpendText = new JLabel("Summa kvar att spendera: XXX kr",SwingConstants.CENTER);
		status = new JLabel("Profil: \"John Doe\" || Period: \"Januari\"",SwingConstants.CENTER);
		
		expenceSummary = new JPanel(new GridLayout(1,4));
		expenceSummary.add(sumText);
		expenceSummary.add(amountText);
		expenceSummary.add(outcomeText);
		expenceSummary.add(new JLabel());
		
		this.setLayout(new GridLayout(3,1));
		this.add(expenceSummary);
		this.add(leftToSpendText);
		this.add(status);
	}
}