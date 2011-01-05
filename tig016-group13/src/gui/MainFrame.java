package gui;

import gui.ScrollPanel.Type;

import java.awt.GridLayout;

import javax.swing.*;

import model.UserHandler;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -4287347866047384672L;
	
	private Menu menu;
	private ScrollPanel scrollPanel1, scrollPanel2;
	private UserHandler uh;
	private BottomPanel bPanel;
	private TopPanel tPanel;
	private MiddlePanel mPanel;
	
	public void buildGUI(UserHandler uh)
	{
		this.uh = uh;
		this.setLayout(new GridLayout(5,1));
		this.setTitle("Grupp 13 - Budgeteringsverktyg");
		this.setSize(400,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		menu = new Menu(uh);
		this.setJMenuBar(menu);
		
		tPanel = new TopPanel();
		this.add(tPanel);
		scrollPanel1 = new ScrollPanel(uh, uh.getCurrentUser().getCurrentPeriod().getIncomeBudgetPostList(), Type.INCOME);
		this.add(scrollPanel1);
		mPanel = new MiddlePanel();
		this.add(mPanel);
		scrollPanel2 = new ScrollPanel(uh, uh.getCurrentUser().getCurrentPeriod().getExpenceBudgetPostList(), Type.EXPENCES);
		this.add(scrollPanel2);
		bPanel = new BottomPanel();
		this.add(bPanel);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void updateGUI()
	{
		menu.setMenuItems();
		uh.countAndSetSum();
		scrollPanel1.updateGUI();
		scrollPanel2.updateGUI();
		mPanel.updateGUI(uh);
		bPanel.updateGUI(uh);
	}
}