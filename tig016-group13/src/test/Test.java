package test;
import java.io.File;
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
        	UserHandler uh = new UserHandler();
        	MainFrame mainFrame = new MainFrame(uh);
            mainFrame.buildGUI();
            if(uh.getCurrentUser().writeProfileToDisk())
            {
            	JOptionPane.showMessageDialog(null, "Profilen skrevs till fil utan varningar och fel!");
            }
            
            if(uh.getCurrentUser().readUserFromDisk("Save" + File.separator + "aTestUser.xml"))
            {
            	JOptionPane.showMessageDialog(null, "Profil lästes in från fil utan varningar och fel!");
            }
            
            uh.getCurrentUser().setName("after");
            if(uh.getCurrentUser().writeProfileToDisk())
            {
            	JOptionPane.showMessageDialog(null, "Profilen skrevs till fil utan varningar och fel!");
            }
        }
}
