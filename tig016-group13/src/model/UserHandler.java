package model;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.TimeZone;

import javax.swing.JOptionPane;

public class UserHandler
{
	private User currentUser;
	public UserHandler()
	{
		ArrayList<Period> aTestPeriodList = new ArrayList<Period>();
    	
    	ArrayList<Verification> aTestVerificationList = new ArrayList<Verification>();
    	
    	//TimeZone timeStamp, String note, double amount
    	TimeZone tz = TimeZone.getDefault();
    	aTestVerificationList.add(new Verification(tz, "en h‰ndelse", 123));
    	
    	ArrayList<BudgetPost> aTestIncomeBudgetPostList = new ArrayList<BudgetPost>();
    	BudgetPost aTestBudgetPost = new BudgetPost("aTestBudgetPost", 3000, aTestVerificationList);
    	aTestIncomeBudgetPostList.add(aTestBudgetPost);
    	
    	ArrayList<BudgetPost> aTestExpenceBudgetPostList = new ArrayList<BudgetPost>();
    	BudgetPost aTestBudgetPost2 = new BudgetPost("aTestBudgetPost2", 3002, aTestVerificationList);
    	aTestExpenceBudgetPostList.add(aTestBudgetPost2);
    	
    	Period aTestPeriod = new Period("aTestPeriod", aTestIncomeBudgetPostList,
    			aTestExpenceBudgetPostList);
    	
    	aTestPeriodList.add(aTestPeriod);
    	
    	//User testUser = new User("aTestUser", aTestPeriodList);
    	ArrayList<URL> urlList = new ArrayList<URL>();
    	try {
			urlList.add(new URL("http://www.testurl.com/testfil.txt"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		currentUser = new User("aTestUser", urlList, aTestPeriodList);
    	currentUser.setCurrentPeriod(currentUser.getPeriodList().get(0));
	}
	
	public User getCurrentUser()
	{
		return currentUser;
	}
	public void setCurrentUser(User currentUser)
	{
		this.currentUser = currentUser;
	}
	public void openProfile()
	{
		File file = new File("Save" + File.separator);
		String[] content = file.list();
		ArrayList<String> filenames = new ArrayList<String>();
		
		for(String s : content)
		{
			if(s.endsWith(".xml"))
			{
				filenames.add(s);
			}
		}
		
		if(filenames.size()>0)
		{
			String s = null;
			Object[] data = filenames.toArray(); 
			while(s == null)
			{
				s = (String)JOptionPane.showInputDialog(
	                    null,
	                    "Var vänlig och välj profil: ",
	                    "Öppna profil",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null, data,
	                    data[0]);
			}
			currentUser = new User();
			currentUser.readUserFromDisk("Save" + File.separator + s);
		}
	}
}