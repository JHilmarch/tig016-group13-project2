package gui;

import java.awt.GridLayout;
import javax.swing.*;

public class TopPanel extends JPanel
{
	private static final long serialVersionUID = -4696796014681020374L;

	public TopPanel()
	{
		this.setLayout(new GridLayout(2,1));
		
		JLabel title = new JLabel("Grupp 13 - budgeteringsverktyg",SwingConstants.CENTER);
		JLabel incomeText = new JLabel("INKOMSTER");
		this.add(title); this.add(incomeText);
	}
}