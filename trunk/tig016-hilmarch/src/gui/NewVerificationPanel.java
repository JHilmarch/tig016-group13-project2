package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import util.HelpFunctions;

import model.BudgetPost;
import model.User;
import model.UserHandler;
import model.Verification;

/*
 * TIG061 H10 MDI - IT-universitetet i Göteborg
 * @version Prototyp 3
 * @author Jonatan Hilmarch
 * hilmarch@skip.chalmers.se
 */
public class NewVerificationPanel extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 7933467607111377499L;
	
	private JComboBox typeBox, budgetPostBox;
	private JTextField noteField, amountField;
	private JPanel inputPanel, buttonPanel;
	private JButton okButton, cancelButton;
	private UserHandler uh;
	
	public NewVerificationPanel(UserHandler uh)
	{
		this.uh = uh;
		TypeListener typeListener = new TypeListener(this);
		this.setLayout(new FlowLayout());
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(4,1));
		
		String[] typeText = {"Inkomst", "Utgift"};
		typeBox = new JComboBox(typeText);
		typeBox.addActionListener(typeListener);
		inputPanel.add(typeBox);
		
		budgetPostBox = new JComboBox(this.getIncomeComboBoxModel());
		inputPanel.add(budgetPostBox);
		
		noteField = new JTextField("notering");
		inputPanel.add(noteField);

		amountField = new JTextField("Belopp");
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
		this.setTitle("Notera händelse");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
	
	private Object[] setBudgetTextList(List<BudgetPost> bPostList)
	{
		Object[] budgetTextList = new Object[bPostList.size()];
		for(int i = 0; i < bPostList.size(); i++)
		{
			budgetTextList[i] = bPostList.get(i).getName();
		}
		return budgetTextList;
	}
	
	public ComboBoxModel getIncomeComboBoxModel()
	{
		ComboBoxModel bm = new MyComboBoxModel(setBudgetTextList(
				uh.getCurrentUser().getCurrentPeriod().getIncomeBudgetPostList()));
		return bm;
	}
	
	public ComboBoxModel getExpenceComboBoxModel()
	{
		ComboBoxModel bm = new MyComboBoxModel(setBudgetTextList(
				uh.getCurrentUser().getCurrentPeriod().getExpenceBudgetPostList()));
		return bm;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		JButton jb = (JButton)(e.getSource());
		String budgetType = (String)(typeBox.getSelectedItem());
		String budgetPostName = (String)(budgetPostBox.getSelectedItem());
		
		if(jb.getText().equals("OK") && budgetPostName != null && budgetPostName.length() > 0)
		{
			if(budgetType.equals("Inkomst"))
			{
				for(BudgetPost bp :
					uh.getCurrentUser().getCurrentPeriod().getIncomeBudgetPostList())
				{
					setVerification(bp, budgetPostName);
				}
			}
			
			else if(budgetType.equals("Utgift"))
			{
				for(BudgetPost bp :
					uh.getCurrentUser().getCurrentPeriod().getExpenceBudgetPostList())
				{
					setVerification(bp, budgetPostName);
				}
			}
		}
		
		else if(jb.getText().equals("Avbryt"))
		{
			this.dispose();
		}
	}
	
	private void setVerification(BudgetPost bp, String budgetPostName)
	{
		if(bp.getName().equals(budgetPostName))
		{
			if(HelpFunctions.testInputDecimalAmount(amountField.getText()))
			{
				try {
					uh.setLastUser((User) uh.getCurrentUser().clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String[] splitedNumberText = amountField.getText().split(",");
				double amount = Double.parseDouble(splitedNumberText[0] + "." + splitedNumberText[1]);
				Verification ver = new Verification(noteField.getText(),amount);
				bp.addVerification(ver);
				bp.setOutcome();
				uh.updateGUI();
				this.dispose();
			}
			
			else if(HelpFunctions.testInputIntegerAmount(amountField.getText()))
			{
				try {
					uh.setLastUser((User) uh.getCurrentUser().clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				double amount = Double.parseDouble(amountField.getText());
				Verification ver = new Verification(noteField.getText(),amount);
				bp.addVerification(ver);
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
	}
	
	private class MyComboBoxModel extends AbstractListModel implements ComboBoxModel
	{
		private static final long serialVersionUID = 4891286126261237305L;
		private Object[] budgetTextList;
		
		public MyComboBoxModel(Object[] budgetTextList)
		{
			super();
			this.budgetTextList = budgetTextList;
		}

		String selection = null;

		public Object getElementAt(int index)
		{
			return budgetTextList[index];
		}

		public int getSize()
		{
			return budgetTextList.length;
		}

		public void setSelectedItem(Object anItem)
		{
			selection = (String) anItem;
		}

		public Object getSelectedItem()
		{
			return selection;
		}
	}
	
	private class TypeListener implements ActionListener
	{
		NewVerificationPanel verPanel;
		public TypeListener(NewVerificationPanel verPanel)
		{
			this.verPanel = verPanel;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			JComboBox source = (JComboBox)(e.getSource());
			String value = (String) source.getSelectedItem();
			
			if(value.equals("Inkomst"))
			{
				budgetPostBox.setModel(verPanel.getIncomeComboBoxModel());
			}
			
			else
			{
				budgetPostBox.setModel(verPanel.getExpenceComboBoxModel());
			}
		}
		
	}
}