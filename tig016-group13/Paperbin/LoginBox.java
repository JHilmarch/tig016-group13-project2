package gui;

import java.awt.GridLayout;

import javax.swing.*;

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