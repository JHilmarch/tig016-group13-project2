package gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import model.BudgetPost;
import model.UserHandler;

public class ScrollPanel extends JPanel
{
	private static final long serialVersionUID = -8739894531881597846L;
	private JTable table;
	private List<BudgetPost> budgetPosts;
	private Type type;
	
	public enum Type
	{
		INCOME, EXPENCES
	}

	public ScrollPanel(List<BudgetPost> budgetPosts, Type type)
	{
		this.type = type;
		this.budgetPosts = budgetPosts;
		table = new JTable(new BooleanTableModel(this.budgetPosts));
		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.getSelectionModel().addListSelectionListener(new ColumnListener());
		
		this.setLayout(new GridLayout(1,1));
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane);
	}
	
	public void updateGUI(UserHandler uh)
	{	
		if(type == Type.EXPENCES)
			budgetPosts = uh.getCurrentUser().getCurrentPeriod().getExpenceBudgetPostList();
		else if(type == Type.INCOME)
			budgetPosts = uh.getCurrentUser().getCurrentPeriod().getIncomeBudgetPostList();
		table.setModel(new BooleanTableModel(this.budgetPosts));
	}

	private class ColumnListener implements ListSelectionListener
	{
        public void valueChanged(ListSelectionEvent event)
        {
            if (event.getValueIsAdjusting())
            {
                return;
            }
        }
    }
	
	private class BooleanTableModel extends AbstractTableModel
	{
		List<BudgetPost> ver;
		Object[][] data;
		public BooleanTableModel(List<BudgetPost> ver)
		{
			this.ver = ver;
			this.data = setDataModel();
		}

		private static final long serialVersionUID = 6215934692614135217L;
		String[] columnNames = {"Kategori","Belopp","Utfall","Markerad"};
		
		public Object[][] setDataModel()
		{
			Object[][] data = new Object[ver.size()][4];
			for(int i = 0; i < ver.size(); i++)
			{ 
			    BudgetPost b = ver.get(i);
			    data[i][0] = b.getName();
			    data[i][1] = b.getAmount();
			    data[i][2] = b.getOutcome();
			    data[i][3] = b.isMarked();
			}
			return data;
		}

	    public int getRowCount()
	    {
	        return data.length;
	    }

	    public int getColumnCount()
	    {
	        return columnNames.length;
	    }

	    public Object getValueAt(int rowIndex, int columnIndex)
	    {
	        return data[rowIndex][columnIndex];
	    }
	    
	    public void setValueAt(Object value, int row, int col)
	    {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
	    
	    @Override
	    public String getColumnName(int column)
	    {
	        return columnNames[column];
	    }
	    
	    public boolean isCellEditable(int row, int col)
	    {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 3)
            {
                return false;
            } else {
                return true;
            }
        }

	    @Override
	    public Class<?> getColumnClass(int columnIndex) {
	        return data[0][columnIndex].getClass();
	    }

	}
}
