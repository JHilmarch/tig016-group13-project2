package gui;

import java.awt.GridLayout;
import javax.swing.*;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -4287347866047384672L;

	JLabel empty;
	
	JPanel topPanel;
	
	JScrollPane scrollPane1;
	
	JPanel middlePanel, incomeSummary;
	JLabel sumText1, amountText1, outcomeText1, expenceText;
	
	JScrollPane scrollPane2;
	JTable expence;
	
	JPanel bottomPanel, expenceSummary;
	JLabel sumText2, amountText2, outcomeText2, leftToSpendText, status;
	public MainFrame()
	{
		this.setLayout(new GridLayout(5,1));
		this.setTitle("Grupp 13 - Budgeteringsverktyg");
		this.setSize(400,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setJMenuBar(new Menu());
		topPanel = new TopPanel();
		this.add(topPanel);
		this.add(new ScrollPanel());
		this.add(new MiddlePanel());
		this.add(new ScrollPanel());
		this.setVisible(true);
	}
}