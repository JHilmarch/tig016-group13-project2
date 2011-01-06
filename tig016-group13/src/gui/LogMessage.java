package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogMessage extends JFrame implements ActionListener
{	
	private static final long serialVersionUID = -1929119798833123151L;
	JTextArea textArea;
	JScrollPane scrollPane;
	JPanel panel;
	JButton okButton;
	
	public LogMessage(String text)
	{
		textArea = new JTextArea(10, 30);
		textArea.setText(text);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(400,180));
		
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		
		panel = new JPanel();
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(okButton);
		
		this.add(panel);
		this.setSize(new Dimension(400, 240));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Inl�sningslogg");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		JButton jb = (JButton)(e.getSource());
		if(jb.getText().equals("OK"));
		{
			this.dispose();
		}
	}
}