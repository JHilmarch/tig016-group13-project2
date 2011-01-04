package util;

import javax.swing.JOptionPane;

public class HelpFunctions
{
	public static boolean testInputAmount(String amountFieldText)
	{
		boolean numberTest = false;
		String[] splitedNumberText = amountFieldText.split(",");
			
		boolean colonTest = false;
		if(splitedNumberText.length == 2)
		{
			if(amountFieldText.contains(","));
			{
				colonTest = true;
			}
		}
			
		if(colonTest)
		{
			if(splitedNumberText[0].length()==0 || splitedNumberText[0] == null)
				splitedNumberText[0] = "0";
			if(splitedNumberText[1].length()==0 || splitedNumberText[1] == null)
				splitedNumberText[1] = "0";
				
			try
			{
				double amount = Double.parseDouble(splitedNumberText[0] + "." + splitedNumberText[1]);
				numberTest = true;
			}
				
			catch(NumberFormatException e)
			{
				numberTest = false;
			}
		}
		
		else
		{
			JOptionPane.showMessageDialog(null,
					"Beloppet måste innehålla ett komma!", "KOMMAFEL", JOptionPane.ERROR_MESSAGE);
		}
		
		return numberTest;
	}
}