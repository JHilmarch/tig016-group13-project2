package gui;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class MiddlePanel extends JPanel
{
	private static final long serialVersionUID = -4696796014681020374L;

	public MiddlePanel()
	{
		this.setLayout(new GridLayout(2,1));
		
		JPanel upperPanel = new JPanel(new GridLayout(1,4));
		JLabel sum = new JLabel("Summa:",SwingConstants.CENTER);
		JLabel belopp = new JLabel("1234",SwingConstants.CENTER);
		JLabel utfall = new JLabel("1234",SwingConstants.CENTER);
		JLabel empty = new JLabel();
		
		upperPanel.add(sum);
		upperPanel.add(belopp);
		upperPanel.add(utfall);
		upperPanel.add(empty);
		
		JLabel incomeText = new JLabel("UTGIFTER");
		Font font = new Font("Arial", Font.BOLD, 13);
		incomeText.setFont(font);
		
		this.add(upperPanel); this.add(incomeText);
	}
}