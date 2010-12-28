package gui;

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

public class ScrollPanel extends JPanel
{
	private static final long serialVersionUID = -8739894531881597846L;
	private JTable income;
	
	//Här skall jag skicka med det som behövs senare...
	public ScrollPanel()
	{
		income = new JTable(new BooleanTableModel());
		income.setShowVerticalLines(false);
		income.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		income.setColumnSelectionAllowed(true);
		income.setFillsViewportHeight(true);
		income.getSelectionModel().addListSelectionListener(new ColumnListener());

		
		/*//Vet inte om man skall göra så här. Fungerar inte i alla fall.
		TableColumn col = income.getColumnModel().getColumn(3);
		col.setCellRenderer(income.getDefaultRenderer(Boolean.class));
		col.setCellEditor(income.getDefaultEditor(Boolean.class));*/
		
		this.setLayout(new GridLayout(1,1));
		JScrollPane scrollPane = new JScrollPane(income);
		this.add(scrollPane);
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

		private static final long serialVersionUID = 6215934692614135217L;
		String[] columnNames = {"Kategori","Belopp","Utfall","Markerad"};
		//Exempeldata:
		Object[][] data = {
				{"En kategori", new Double(100), new Double(150.5), new Boolean(false)},
				{"En kategori", new Double(100), new Double(150.5), new Boolean(false)},
				{"En kategori", new Double(100), new Double(150.5), new Boolean(false)},
				{"En kategori", new Double(100), new Double(150.5), new Boolean(false)},
				{"En kategori", new Double(100), new Double(150.5), new Boolean(false)},
				{"En kategori", new Double(100), new Double(150.5), new Boolean(false)},
				{"En kategori", new Double(100), new Double(150.5), new Boolean(false)},
				{"En kategori", new Double(100), new Double(150.5), new Boolean(false)},
				{"En kategori", new Double(100), new Double(150.5), new Boolean(false)}
		};

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
