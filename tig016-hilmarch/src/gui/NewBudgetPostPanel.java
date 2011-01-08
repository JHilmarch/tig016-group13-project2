package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

import util.HelpFunctions;

import model.BudgetPost;
import model.User;
import model.UserHandler;
import model.Verification;

public class NewBudgetPostPanel extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -1803337201875594781L;
	private JComboBox typeBox;
	private JTextField nameField, amountField;
	private JPanel inputPanel, buttonPanel;
	private JButton okButton, cancelButton;
	private UserHandler uh;
	
	public NewBudgetPostPanel(UserHandler uh)
	{
		this.uh = uh;
		this.setLayout(new FlowLayout());
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(3,1));
		
		String[] typeText = {"Inkomst", "Utgift"};
		typeBox = new JComboBox(typeText);
		inputPanel.add(typeBox);
		
		nameField = new JTextField("Postnamn");
		inputPanel.add(nameField);

		amountField = new JTextField("Belopp med komma");
		inputPanel.add(amountField);
		
		this.add(inputPanel);
		
		buttonPanel = new JPanel();
		
		okButton = new JButton("OK");
		cancelButton = new JButton("Avbryt");
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		buttonPanel.add(cancelButton);
		buttonPanel.add(okButton);
		
		this.add(buttonPanel);
		
		this.setSize(200, 180);
		this.setTitle("LŠgg till budgetpost");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		JButton jb = (JButton)(e.getSource());
		String budgetType = (String)(typeBox.getSelectedItem());
		
		if(jb.getText().equals("OK"))
		{
			if(HelpFunctions.testInputDecimalAmount(amountField.getText()))
			{
				try {
					uh.setLastUser((User) uh.getCurrentUser().clone());
				} catch (CloneNotSupportedException exception) {
					// TODO Auto-generated catch block
					exception.printStackTrace();
				}
				
				String[] splitedNumberText = amountField.getText().split(",");
				double amount = Double.parseDouble(splitedNumberText[0] + "." + splitedNumberText[1]);
				BudgetPost bp = new BudgetPost(nameField.getText(), amount, new ArrayList<Verification>());
				
				if(budgetType.equals("Inkomst"))
				{
					uh.getCurrentUser().getCurrentPeriod().addIncomeBudgetPost(bp);
				}
				
				else if(budgetType.equals("Utgift"))
				{
					uh.getCurrentUser().getCurrentPeriod().addExpenceBudgetPost(bp);
				}
				
				bp.setOutcome();
				uh.updateGUI();
				this.dispose();
			}
			
			else if(HelpFunctions.testInputIntegerAmount(amountField.getText()))
			{
				try {
					uh.setLastUser((User) uh.getCurrentUser().clone());
				} catch (CloneNotSupportedException exception) {
					// TODO Auto-generated catch block
					exception.printStackTrace();
				}
				
				double amount = Double.parseDouble(amountField.getText());
				BudgetPost bp = new BudgetPost(nameField.getText(), amount, new ArrayList<Verification>());
				
				if(budgetType.equals("Inkomst"))
				{
					uh.getCurrentUser().getCurrentPeriod().addIncomeBudgetPost(bp);
				}
				
				else if(budgetType.equals("Utgift"))
				{
					uh.getCurrentUser().getCurrentPeriod().addExpenceBudgetPost(bp);
				}
				bp.setOutcome();
				uh.updateGUI();
				this.dispose();
			}
			
			else
			{
				JOptionPane.showMessageDialog(null,
						"Det gick inte att formatera beloppet!\n" +
						"Exempel: 103,50", "FORMATFEL", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(jb.getText().equals("Avbryt"))
		{
			this.dispose();
		}
	}
	
	private class MyKeyListener implements KeyListener
	{
		int lastID;
		public MyKeyListener()
		{
			super();
			lastID = 0;
		}
		public void keyPressed(KeyEvent e)
		{
			int id = e.getID();
			if(id==KeyEvent.VK_S && lastID==KeyEvent.VK_CONTROL)
			{
				
			}
			
			lastID = id;
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}