package gui;

import gui.ScrollPanel.Type;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import model.UserHandler;

/*
 * TIG061 H10 MDI - IT-universitetet i Gšteborg
 * @version Prototyp 3
 * @author Jonatan Hilmarch
 * hilmarch@skip.chalmers.se
 */
public class MainFrame extends JFrame implements WindowListener
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
		this.setTitle("JHilmarch - Budgeteringsverktyg");
		this.setSize(400,600);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		this.addWindowListener(this);
		
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
		uh.countAndSetSum();
		scrollPanel1.updateGUI();
		scrollPanel2.updateGUI();
		mPanel.updateGUI(uh);
		bPanel.updateGUI(uh);
		menu.setMenuItems();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		uh.saveProfileQuestion();
			System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}