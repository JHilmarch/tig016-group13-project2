package gui;

import java.awt.GridLayout;
import javax.swing.*;

//Ta bort denna texten när onödiga referenser och variabler har städats bort.
public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -4287347866047384672L;
	
	Menu menu;
	ScrollPanel scrollPanel1, scrollPanel2;
	BottomPanel bottomPanel;
	
	public MainFrame()
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