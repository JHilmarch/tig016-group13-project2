package gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.*;

import model.UserHandler;

public class MiddlePanel extends JPanel
{
	private static final long serialVersionUID = -4696796014681020374L;
	private JPanel upperPanel;
	private JLabel sum, amount, outcome, empty;

	public MiddlePanel()
	{
		this.setLayout(new GridLayout(2,1));
		
		upperPanel = new JPanel(new GridLayout(1,4));
		sum = new JLabel("Summa:",SwingConstants.CENTER);
		amount = new JLabel("0",SwingConstants.CENTER);
		outcome = new JLabel("0",SwingConstants.CENTER);
		empty = new JLabel();
		
		upperPanel.add(sum);
		upperPanel.add(amount);
		upperPanel.add(outcome);
		upperPanel.add(empty);
		
		JLabel incomeText = new JLabel("UTGIFTER");
		Font font = new Font("Arial", Font.BOLD, 13);
		incomeText.setFont(font);
		
		this.add(upperPanel); this.add(incomeText);
	}
	
	public void updateGUI(UserHandler uh)
	{
		DecimalFormat df = new DecimalFormat("#.##");
		amount.setText(""+ df.format(uh.getTotBudgetIncome()));
		outcome.setText(""+ df.format(uh.getTotOutcomeIncome()));
	}
}