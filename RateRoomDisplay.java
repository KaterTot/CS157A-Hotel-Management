import javax.swing.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RateRoomDisplay {
	JFrame frame;
	JFrame confirm;
   
   public void displayPastReservations(String[][] rooms, String[] col,int cID, ArrayList<String> list)   {
	   frame = new JFrame("Display Screen");
	   frame.setPreferredSize(new Dimension(2000,2000));
	   
	   JLabel upLabel = new JLabel("You can rate any of the following rooms:");
	   upLabel.setFont(new Font("Serif", Font.PLAIN, 22));
	   JPanel nPanel = new JPanel();
	   nPanel.setMaximumSize(new Dimension(1000, 80));
	   nPanel.add(upLabel);
	   
	   JLabel label = new JLabel("Please enter the room number of the room you wish to rate");
	   JLabel ratingLabel = new JLabel("Please enter a number from 1-5");
	   JTextField resField = new JTextField();
	   JTextField rating = new JTextField();
	   resField.setPreferredSize(new Dimension(75, 20));
	   rating.setPreferredSize(new Dimension(75, 20));
	   JButton button = new JButton("Rate");
	   JButton back = new JButton("Back");
	   JPanel panel = new JPanel();
	   panel.add(label);
	   panel.add(resField);
	   panel.add(ratingLabel);
	   panel.add(rating);
	   panel.add(button);
	   panel.add(back);
	   
	   JTable j = new JTable(rooms, col); 
	   j.setBounds(30, 40, 2000, 300); 
       JScrollPane sp = new JScrollPane(j); 
       sp.setMaximumSize(new Dimension(1000, 50));
       sp.setPreferredSize(new Dimension(0, 50));
       
       button.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
		   String st = resField.getText();
    		   int rID = Integer.parseInt(resField.getText());
    		   int stars = Integer.parseInt(rating.getText());
    		   HotelSQLProcedures proc = new HotelSQLProcedures();
		   if(list.contains(st)) {
    		     proc.rateRoom(cID, rID, stars);
		     frame.setVisible(false);
    		     RateRoomDisplay disp = new RateRoomDisplay();
    		     disp.ratingConfirmScreen(rID, cID);
		   }
		    else {
    		      JOptionPane.showMessageDialog(frame, "The room number you entered is invalid");
    		      resField.setText("");
    		   }
    	   }
       });
       
       back.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   frame.setVisible(false);
    		   HotelCustomerScreen screen = new HotelCustomerScreen();
    		   screen.createScreen(cID);
    	   }
       });
       
       frame.add(nPanel, BorderLayout.NORTH);
       frame.add(sp, BorderLayout.CENTER);
       frame.add(panel, BorderLayout.PAGE_END); 
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.pack();
	   frame.setVisible(true);
   }
   
   public void ratingConfirmScreen(int rID, int cID)   {
	   confirm = new JFrame("Start Screen");
	   confirm.setPreferredSize(new Dimension(2000,2000));
	   
	   JLabel label = new JLabel("Thank you for your rating!");
	   label.setFont(new Font("Serif", Font.PLAIN, 22));
	   JButton button = new JButton("Back to Main Menu");
	   
	   JPanel lPanel = new JPanel();
	   lPanel.setMaximumSize(new Dimension(1000, 100));
	   lPanel.add(label);
	   
	   JPanel panel = new JPanel();
	   panel.setMaximumSize(new Dimension(1000, 300));
	   panel.add(Box.createHorizontalStrut(400));
	   panel.add(lPanel);
	   panel.add(Box.createHorizontalStrut(100));
	   panel.add(button);
	   
	   button.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   confirm.setVisible(false);
			   HotelCustomerScreen screen = new HotelCustomerScreen();
			   screen.createScreen(cID);
		   }
	   });
	   
	   confirm.add(panel);
	   confirm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   confirm.pack();
	   confirm.setVisible(true);
   }
}
