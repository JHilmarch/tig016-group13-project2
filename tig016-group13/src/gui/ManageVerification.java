package gui;

import gui.ScrollPanel.Type;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import util.HelpFunctions;

import model.BudgetPost;
import model.UserHandler;
import model.Verification;

public class ManageVerification extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -5192337754141104694L;
	private JComboBox typeBox, budgetPostBox, verificationBox;
	private JTextField noteField, amountField;
	private JPanel inputPanel, buttonPanel;
	private JButton okButton, cancelButton;
	private TypeListener typeListener;
	private PostListener postListener;
	private BudgetPost selectedBudgetPost;
	private UserHandler uh;
	private Type currentBudgetListType;
	
	public ManageVerification(UserHandler uh)
	{
		this.uh = uh;
		typeListener = new TypeListener();
		postListener = new PostListener();
		this.setLayout(new FlowLayout());
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(5,1));
		
		String[] typeText = {"Inkomst", "Utgift"};
		typeBox = new JComboBox(typeText);
		typeBox.addActionListener(typeListener);
		inputPanel.add(typeBox);
		
		selectedBudgetPost = uh.getCurrentUser().getCurrentPeriod().getIncomeBudgetPostList().get(0);
		currentBudgetListType = Type.INCOME;
		budgetPostBox = new JComboBox(this.getIncomeComboBoxModel());
		budgetPostBox.addActionListener(postListener);
		inputPanel.add(budgetPostBox);
		
		verificationBox = new JComboBox(this.getVerificationModel());
		inputPanel.add(verificationBox);
		
		noteField = new JTextField("notering");
		inputPanel.add(noteField);

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
		
		this.setSize(200, 220);
		this.setTitle("Notera händelse");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
	
	private Object[] setVerificationTextList(List<Verification> verificationList)
	{
		Object[] verificationTextList = new Object[verificationList.size()];
		for(int i = 0; i < verificationList.size(); i++)
		{
			verificationTextList[i] = verificationList.get(i).getNote();
		}
		return verificationTextList;
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
	
	public ComboBoxModel getVerificationModel()
	{
		ComboBoxModel bm = new MyComboBoxModel(setVerificationTextList(
				selectedBudgetPost.getVerificationList()));
		return bm;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		JButton jb = (JButton)(e.getSource());
		String budgetPostName = (String)(budgetPostBox.getSelectedItem());
		String verificationNote = (String)(verificationBox.getSelectedItem());
		
		if(jb.getText().equals("OK") && budgetPostName != null && budgetPostName.length() > 0 &&
				verificationNote != null && verificationNote.length() > 0)
		{
			if(currentBudgetListType == Type.INCOME)
			{
				for(BudgetPost bp :
					uh.getCurrentUser().getCurrentPeriod().getIncomeBudgetPostList())
				{
					changeVerification(bp, budgetPostName, verificationNote);
				}
			}
			
			else
			{
				for(BudgetPost bp :
					uh.getCurrentUser().getCurrentPeriod().getExpenceBudgetPostList())
				{
					changeVerification(bp, budgetPostName, verificationNote);
				}
			}
		}
		
		else if(jb.getText().equals("Avbryt"))
		{
			this.dispose();
		}
	}
	
	private void changeVerification(BudgetPost bp, String budgetPostName, String verificationNote)
	{
		if(bp.getName().equals(budgetPostName))
		{
			for(Verification v : bp.getVerificationList())
			{
				if(v.getNote().equals(verificationNote))
				{
					if(HelpFunctions.testInputAmount(amountField.getText()))
					{
						String[] splitedNumberText = amountField.getText().split(",");
						double amount = Double.parseDouble(splitedNumberText[0] + "." + splitedNumberText[1]);
						
						v.setNote(noteField.getText());
						v.setAmount(amount);
						
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
		}
	}
	
	class MyComboBoxModel extends AbstractListModel implements ComboBoxModel
	{
		private static final long serialVersionUID = 4891286126261237305L;
		private Object[] textList;
		
		public MyComboBoxModel(Object[] textList)
		{
			super();
			this.textList = textList;
		}

		String selection = null;

		public Object getElementAt(int index)
		{
			return textList[index];
		}

		public int getSize()
		{
			return textList.length;
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
	
	class TypeListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e)
		{
			JComboBox source = (JComboBox)(e.getSource());
			String value = (String) source.getSelectedItem();
			
			if(value.equals("Inkomst"))
			{
				currentBudgetListType = Type.INCOME;
				budgetPostBox.setModel(getIncomeComboBoxModel());
			}
			
			else
			{
				currentBudgetListType = Type.EXPENCES;
				budgetPostBox.setModel(getExpenceComboBoxModel());
			}
		}
		
	}
	
	class PostListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e)
		{
			JComboBox source = (JComboBox)(e.getSource());
			String value = (String) source.getSelectedItem();
			
			if(currentBudgetListType == Type.INCOME)
			{
				for(BudgetPost bp : uh.getCurrentUser().getCurrentPeriod().getIncomeBudgetPostList())
				{
					if(value.equals(bp.getName()))
					{
						selectedBudgetPost = bp;
					}
				}
			}
			
			else
			{
				for(BudgetPost bp : uh.getCurrentUser().getCurrentPeriod().getExpenceBudgetPostList())
				{
					if(value.equals(bp.getName()))
					{
						selectedBudgetPost = bp;
					}
				}
			}
			verificationBox.setModel(getVerificationModel());
		}
	}
}