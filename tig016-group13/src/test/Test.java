package test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import model.*;
import gui.MainFrame;

public class Test
{
        public static void main(String[] arg)
        {
        	ArrayList<Period> aTestPeriodList = new ArrayList<Period>();
        	
        	ArrayList<Verification> aTestVerificationList = new ArrayList<Verification>();
        	
        	//TimeZone timeStamp, String note, double amount
        	TimeZone tz = TimeZone.getDefault();
        	aTestVerificationList.add(new Verification(tz, "en händelse", 123));
        	
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
        	
        	User testUser = new User("aTestUser", urlList, aTestPeriodList);
        	testUser.setCurrentPeriod(testUser.getPeriodList().get(0));
        	
        	MainFrame mainFrame = new MainFrame(testUser);
            mainFrame.buildGUI();
            
            if(testUser.writeProfileToDisk(testUser))
            {
            	JOptionPane.showMessageDialog(null, "Profilen skrevs till fil utan varningar och fel!");
            }
            
            if(testUser.readUserFromDisk("Save\\aTestUser.xml"))
            {
            	JOptionPane.showMessageDialog(null, "Profil lästes in från fil utan varningar och fel!");
            }
            
            testUser.setName("after");
            if(testUser.writeProfileToDisk(testUser))
            {
            	JOptionPane.showMessageDialog(null, "Profilen skrevs till fil utan varningar och fel!");
            }
        }
}
