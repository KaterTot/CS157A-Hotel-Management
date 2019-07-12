import java.sql.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HotelStartScreen {
	HotelSQLProcedures pr = new HotelSQLProcedures();
	JFrame frame;
	JFrame manLogin;
//	DBConnection mt = new DBConnection();
//	Connection myConn = mt.myConn;
	/*
	private class DBConnection {
		public Connection myConn;

		public DBConnection() {

			try {
				Class.forName("com.mysql.jdbc.Driver"); 
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3308/UserDatabase", "root", "");
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}
	*/
	public void createScreen() {
   frame = new JFrame("Start Screen");
   frame.setPreferredSize(new Dimension(2000,2000));
   JLabel label = new JLabel("Please choose one of the options below to continue");
   label.setFont(new Font("Serif", Font.PLAIN, 22));
   JPanel labelPanel = new JPanel();
   labelPanel.setMaximumSize(new Dimension(500, 100));
   labelPanel.add(Box.createHorizontalStrut(40));
   labelPanel.add(label);
  
   JButton manager = new JButton("I am a manager");
   JButton customer = new JButton("I am a customer");
   JPanel panel = new JPanel();
   JPanel finalPanel = new JPanel();
   BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
   finalPanel.setLayout(boxlayout);
   panel.add(manager);   
   panel.add(customer);
   finalPanel.add(Box.createVerticalStrut(100));
   finalPanel.add(labelPanel);
   finalPanel.add(panel);
   finalPanel.add(Box.createVerticalStrut(100));
   frame.add(finalPanel);
   
   manager.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
		   frame.setVisible(false);
		   HotelStartScreen screen = new HotelStartScreen();
		   screen.managerLogin();
	   }
   });
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.pack();
     frame.setVisible(true);
	}
	
	public void managerLogin()   {
		manLogin = new JFrame("Start Screen");
		manLogin.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel("Please log in to continue");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JLabel username = new JLabel("Username:");
		JLabel password = new JLabel("Password:");
		JTextField user = new JTextField();
		JTextField pass = new JTextField();
		user.setPreferredSize(new Dimension(100, 20));
		pass.setPreferredSize(new Dimension(100, 20));
		
		JButton button = new JButton("Log in");
		
		JPanel labelPanel = new JPanel();
		labelPanel.add(Box.createVerticalStrut(50));
		labelPanel.add(label);
		labelPanel.setMaximumSize(new Dimension(300, 50));
		
		JPanel uPanel = new JPanel();
		uPanel.add(username);
		uPanel.add(user);
		uPanel.setMaximumSize(new Dimension(200, 30));
		
		JPanel pPanel= new JPanel();
		pPanel.add(password);
		pPanel.add(pass);
		pPanel.setMaximumSize(new Dimension(200, 50));
		
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(labelPanel);
		finalPanel.add(uPanel);
		finalPanel.add(pPanel);
		finalPanel.add(button);
		
		button.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   manLogin.setVisible(false);
				   String uName = user.getText();
				   String passw = pass.getText();
			//	   HotelSQLProcedures pr = new HotelSQLProcedures();
				   pr.validateLogin(uName, passw);
		/*
				   try {
				   PreparedStatement pst = 
						   myConn.prepareStatement("select * from User where username=? and password=?");
				   pst.setString(1, uName);
				   pst.setString(2, passw);
				  ResultSet rs = pst.executeQuery();
				  if(rs.next()) {
					  HotelManagerScreen screen = new HotelManagerScreen();
					  screen.createScreen();
				  }
				   }
				   catch (SQLException exc) {
						System.out.println("An error occured. Error: " + exc.getMessage());
			   }
			   */
		   }});
		
		manLogin.add(finalPanel);
		manLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    manLogin.pack();
	    manLogin.setVisible(true);
	}
}
