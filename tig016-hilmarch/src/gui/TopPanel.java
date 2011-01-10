package gui;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

/*
 * TIG061 H10 MDI - IT-universitetet i Gšteborg
 * @version Prototyp 3
 * @author Jonatan Hilmarch
 * hilmarch@skip.chalmers.se
 */
public class TopPanel extends JPanel
{
	private static final long serialVersionUID = -4696796014681020374L;

	public TopPanel()
	{
		this.setLayout(new GridLayout(2,1));
		JLabel title = new JLabel("JHilmarch - budgeteringsverktyg",SwingConstants.CENTER);
		Font font = new Font("Arial", Font.ITALIC, 16);
		title.setFont(font);
		JLabel incomeText = new JLabel("INKOMSTER");
		font = new Font("Arial", Font.BOLD, 13);
		incomeText.setFont(font);
		this.add(title); this.add(incomeText);
	}
}