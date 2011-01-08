package gui;

import java.awt.GridLayout;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import catalog.Movie;

public class LoginBox extends JFrame
{
	private static final long serialVersionUID = 7042876427265608637L;
	private JLabel usernameLabel, passwordLabel, informationLabel, empty;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JButton okButton, cancelButton;
	
	public void buildLoginBox()
	{
		this.setTitle("Login");
		usernameLabel = new JLabel("Username:");
		usernameTextField = new JTextField();
		passwordLabel = new JLabel("Password:");
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		cancelButton = new JButton("Cancel");
		okButton = new JButton("OK");
		empty = new JLabel("");
		informationLabel = new JLabel("Pleace sign in or create a new profile.");
		
		this.setLayout(new GridLayout(4,4));
		this.add(informationLabel);
		this.add(empty);
		this.add(usernameLabel);
		this.add(passwordLabel);
		this.add(usernameTextField);
		this.add(passwordField);
		this.add(okButton);
		this.add(cancelButton);
		this.setSize(400, 300);
		this.setVisible(true);
	}
}

@Override
public List<Movie> getNewMoviesFromNetwork(URL url)
{
	List<Movie> movieList = new ArrayList<Movie>();
	System.out.println("Försöker att läsa från nätverk.");
	try
	{
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder;
		docBuilder = docBuilderFactory.newDocumentBuilder();
		InputStream stream = url.openStream();
		if(stream.available()!=0)
		{
			Document doc = docBuilder.parse(stream);
		    movieList = readDocument(doc);
		}
		else
		{
			System.out.println("Det går inte att läsa just nu. Filen är förmodligen tom!");
		}
	}
	catch (ParserConfigurationException e)
	{
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return movieList;
}