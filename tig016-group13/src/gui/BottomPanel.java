package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.*;

import model.UserHandler;

public class BottomPanel extends JPanel
{
	private static final long serialVersionUID = -405718142568283106L;
	JPanel expenceSummary;
	JLabel sumText, amountText, outcomeText, leftToSpendText, status;
	
	public BottomPanel()
	{
		sumText = new JLabel("Summa: ",SwingConstants.CENTER);
		amountText = new JLabel("0",SwingConstants.CENTER);
		outcomeText = new JLabel("0",SwingConstants.CENTER);
		leftToSpendText = new JLabel("Summa kvar att spendera: 0",SwingConstants.CENTER);
		status = new JLabel("Profil: \"\" || Period: \"\"",SwingConstants.CENTER);
		
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
	
	public void updateGUI(UserHandler uh)
	{
		status.setText("Profil: \"" + uh.getCurrentUser().getName() + "\" || Period: \"" +
				uh.getCurrentUser().getCurrentPeriod().getName() + "\"");
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		amountText.setText(""+ df.format(uh.getTotBudgetExpence()));
		outcomeText.setText(""+ df.format(uh.getTotOutcomeExpence()));
		
		if(uh.getTotLeftToSpend() < 0)
		{
			leftToSpendText.setForeground(Color.RED);
			leftToSpendText.setText("Summa spenderat šver budget: " + df.format(uh.getTotLeftToSpend() * -1));
		}
		
		else
		{
			leftToSpendText.setForeground(Color.BLACK);
			leftToSpendText.setText("Kvar att spendera: " + df.format(uh.getTotLeftToSpend()));
		}
		
	}
}