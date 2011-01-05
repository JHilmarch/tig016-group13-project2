package util;

import java.awt.Desktop;
import java.io.File;
import java.util.Arrays;

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
	
	static final String[] browsers = { "google-chrome", "firefox", "opera",
	      "epiphany", "konqueror", "conkeror", "midori", "kazehakase", "mozilla" };
	static final String errMsg = "Error attempting to launch web browser";

	/**
	 * Taken from: http://www.centerkey.com/java/browser/javadoc/com/centerkey/utils/BareBonesBrowserLaunch.html
	 * Opens the specified web page in the user's default browser
	 * @param url A web address (URL) of a web page (ex: "http://www.google.com/")
	 */
	public static void openURL(String url)
	{
	   try
	   {  //attempt to use Desktop library from JDK 1.6+
	      Class<?> d = Class.forName("java.awt.Desktop");
	      d.getDeclaredMethod("browse", new Class[] {java.net.URI.class}).invoke(
	         d.getDeclaredMethod("getDesktop").invoke(null),
	         new Object[] {java.net.URI.create(url)});
	      //above code mimicks:  java.awt.Desktop.getDesktop().browse()
	      }
	   catch (Exception ignore)
	   {  //library not available or failed
	      String osName = System.getProperty("os.name");
	      try {
	         if (osName.startsWith("Mac OS")) {
	            Class.forName("com.apple.eio.FileManager").getDeclaredMethod(
	               "openURL", new Class[] {String.class}).invoke(null,
	               new Object[] {url});
	            }
	         else if (osName.startsWith("Windows"))
	            Runtime.getRuntime().exec(
	               "rundll32 url.dll,FileProtocolHandler " + url);
	         else { //assume Unix or Linux
	            String browser = null;
	            for (String b : browsers)
	               if (browser == null && Runtime.getRuntime().exec(new String[]
	                     {"which", b}).getInputStream().read() != -1)
	                 Runtime.getRuntime().exec(new String[] {browser = b, url});
	            if (browser == null)
	               throw new Exception(Arrays.toString(browsers));
	           	}
	         }
	      catch (Exception e)
	      {
	         JOptionPane.showMessageDialog(null, errMsg + "\n" + e.toString());
	         }
	      }
	   }
	
	public static void openPDF()
	{
		File dir = new File(".");
		String dirText = "";
		try
		{
			dirText = dir.getCanonicalPath();
			Desktop d = java.awt.Desktop.getDesktop();  
			d.open (new java.io.File (dirText + File.separator + "Manual.pdf"));
		}
		
		catch (Exception e)
		{
			System.out.println("Error" + e );
		}
	}
}