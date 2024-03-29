package gui;

import java.awt.GridLayout;
import javax.swing.*;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -4287347866047384672L;
	
	private Menu menu;
	private ScrollPanel scrollPanel1, scrollPanel2;
	
	public void buildGUI()
	{
		this.setLayout(new GridLayout(5,1));
		this.setTitle("Grupp 13 - Budgeteringsverktyg");
		this.setSize(400,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		menu = new Menu();
		this.setJMenuBar(menu);
		
		this.add(new TopPanel());
		scrollPanel1 = new ScrollPanel();
		this.add(scrollPanel1);
		this.add(new MiddlePanel());
		scrollPanel2 = new ScrollPanel();
		this.add(scrollPanel2);
		this.add(new BottomPanel());
		
		this.setVisible(true);
	}
}