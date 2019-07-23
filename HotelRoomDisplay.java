import javax.swing.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class HotelRoomDisplay {
	JFrame frame;
	JFrame confirm;
   public void displayReservations(String[][] data, String[] col, int cID)   {
	   frame = new JFrame("Start Screen");
	   frame.setPreferredSize(new Dimension(2000,2000));
	   
	   JLabel upLabel = new JLabel("You have the following reservations at this time:");
	   upLabel.setFont(new Font("Serif", Font.PLAIN, 22));
	   JPanel nPanel = new JPanel();
	   nPanel.setMaximumSize(new Dimension(1000, 80));
	   nPanel.add(upLabel);
	   
	   JLabel label = new JLabel("Please select the the Reservation ID that you would like to cancel");
	   JTextField field = new JTextField();
	   field.setPreferredSize(new Dimension(100, 20));
	   JButton button = new JButton("Cancel Reservation");
	   JButton back = new JButton("Back");
	   
	   JTable j = new JTable(data, col); 
           j.setBounds(30, 40, 2000, 300); 
           JScrollPane sp = new JScrollPane(j); 
           sp.setMaximumSize(new Dimension(1000, 50));
           sp.setPreferredSize(new Dimension(0, 50));
  
           JPanel panel = new JPanel();
           panel.add(label);
           panel.add(field);    
           panel.add(button);
           panel.add(back);
       
           back.addActionListener(new ActionListener() {
    	       public void actionPerformed(ActionEvent e) {
    		   frame.setVisible(false);
    		   HotelCustomerScreen screen = new HotelCustomerScreen();
    		   screen.createScreen(cID);
    	   }
       });
       
       button.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   int rID = Integer.parseInt(field.getText());
    		   HotelSQLProcedures proc = new HotelSQLProcedures();
    		   int num = proc.cancelReservations(cID, rID);
    		   if(num > 0)  {
    		   frame.setVisible(false);
    		   HotelCustomerScreen screen = new HotelCustomerScreen();
    		   screen.reservationCancelConfirm(cID);
    		   }
    		   else {
    			   JOptionPane.showMessageDialog(frame,"You have entered incorrect"
    			   		+ "room number. Please try again.");
				   field.setText("");
    		   }
    	   }
       });
       
       frame.add(nPanel, BorderLayout.NORTH);
       frame.add(sp, BorderLayout.CENTER);
       frame.add(panel, BorderLayout.PAGE_END); 
	   
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.pack();
	   frame.setVisible(true);
   }
   
   public void displayAvailableRooms(String[][] rooms, String[] col, Date in, Date out, int cID)   {
	   frame = new JFrame("Display Screen");
	   frame.setPreferredSize(new Dimension(2000,2000));
	   
	   JLabel upLabel = new JLabel("The following rooms are available for the dates you entered:");
	   upLabel.setFont(new Font("Serif", Font.PLAIN, 22));
	   JPanel nPanel = new JPanel();
	   nPanel.setMaximumSize(new Dimension(1000, 80));
	   nPanel.add(upLabel);
	   
	   JLabel label = new JLabel("Please select the room number you wish to book");
	   JTextField field = new JTextField();
	   field.setPreferredSize(new Dimension(100, 20));
	   JButton button = new JButton("Book");
	   JButton back = new JButton("Back");
	   JPanel panel = new JPanel();
	   panel.add(label);
	   panel.add(field);
	   panel.add(button);
	   panel.add(back);
	   
	   JTable j = new JTable(rooms, col); 
	   j.setBounds(30, 40, 2000, 300); 
       JScrollPane sp = new JScrollPane(j); 
       sp.setMaximumSize(new Dimension(1000, 50));
       sp.setPreferredSize(new Dimension(0, 50));
       
       button.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   String input = field.getText();
    		   if(input.isEmpty())   {
    			   JOptionPane.showMessageDialog(frame,  "Please enter the room number you wish to book");
    		   }
    		   else {
    	     	   int rID = Integer.parseInt(field.getText());
    		       HotelSQLProcedures proc = new HotelSQLProcedures();
    		       int res = proc.insertReservation(rID, in, out, cID);
    		       if(res > 0)   {
    		      	   frame.setVisible(false);
    		           HotelRoomDisplay disp = new HotelRoomDisplay();
    		           disp.registrationConfirmScreen(rID, cID);
    		       }
    		       else   {
    		      	   JOptionPane.showMessageDialog(frame,  "You have entered an invalid room number. Please try again");
    		      	   field.setText("");
    		       }
    		   }
    	   }
       });
       
       back.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   frame.setVisible(false);
    		   HotelCustomerScreen screen = new HotelCustomerScreen();
    		   screen.reserveInput(cID);
    	   }
       });
       
       frame.add(nPanel, BorderLayout.NORTH);
       frame.add(sp, BorderLayout.CENTER);
       frame.add(panel, BorderLayout.PAGE_END); 
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.pack();
	   frame.setVisible(true);
   }
   
   public void registrationConfirmScreen(int rID, int cID)   {
	   confirm = new JFrame("Start Screen");
	   confirm.setPreferredSize(new Dimension(2000,2000));
	   
	   JLabel label = new JLabel("Thank you for your booking! The room " + rID + " has been added to your registration list.");
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
	
	public void displayArchivedReservations(String[][] data, String[] col)   {
	   frame = new JFrame("Display Screen");
	   frame.setPreferredSize(new Dimension(2000,2000));
	   
	   JLabel upLabel = new JLabel("The following reservations are currently archived:");
	   upLabel.setFont(new Font("Serif", Font.PLAIN, 22));
	   JPanel nPanel = new JPanel();
	   nPanel.setMaximumSize(new Dimension(1000, 80));
	   nPanel.add(upLabel);
	   
	   JTable j = new JTable(data, col); 
	   j.setBounds(30, 40, 2000, 300); 
           JScrollPane sp = new JScrollPane(j); 
           sp.setMaximumSize(new Dimension(1000, 50));
           sp.setPreferredSize(new Dimension(0, 50));
       
           JButton button = new JButton("Main Menu");
           JPanel sPanel = new JPanel();
	   sPanel.setMaximumSize(new Dimension(1000, 80));
	   sPanel.add(button);
	   
	   button.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   frame.setVisible(false);
			   HotelManagerScreen screen = new HotelManagerScreen();
			   screen.createScreen();
		   }
	   });
	   
	   frame.add(nPanel, BorderLayout.NORTH);
           frame.add(sp, BorderLayout.CENTER);
           frame.add(sPanel, BorderLayout.PAGE_END); 
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.pack();
	   frame.setVisible(true);
   }
	
   public void displayBestDeals(String[][] data, String[] col, int cID, ArrayList<String> list)  {
	   frame = new JFrame("Display Screen");
	   frame.setPreferredSize(new Dimension(2000,2000));
	   
	   JLabel upLabel = new JLabel("The following rooms have higher average rating than the average rating of the rooms of higher price:");
	   upLabel.setFont(new Font("Serif", Font.PLAIN, 22));
	   JPanel nPanel = new JPanel();
	   nPanel.setMaximumSize(new Dimension(1000, 80));
	   nPanel.add(upLabel);
	   
	   JTable j = new JTable(data, col); 
	   j.setBounds(30, 40, 2000, 300); 
       JScrollPane sp = new JScrollPane(j); 
       sp.setMaximumSize(new Dimension(1000, 50));
       sp.setPreferredSize(new Dimension(0, 50));
       
       JButton button = new JButton("Main Menu"); 
       JLabel bookLabel = new JLabel("Please enter the ID of the room you would like to book as well as dates:");
       JPanel lPanel = new JPanel();
       lPanel.add(bookLabel);
       JLabel in = new JLabel("Check-in date (yyyy-mm-dd)");
       JTextField inDate = new JTextField();
       inDate.setPreferredSize(new Dimension(100, 20));
       JPanel inPanel = new JPanel();
       inPanel.add(in);
       inPanel.add(inDate);
       JLabel out = new JLabel("Check-out date (yyyy-mm-dd)");
       JTextField outDate = new JTextField();
       outDate.setPreferredSize(new Dimension(100, 20));
       JPanel outPanel = new JPanel();
       outPanel.add(out);
       outPanel.add(outDate);
       JTextField input = new JTextField();
       input.setPreferredSize(new Dimension(100, 20));
       JPanel bookPanel = new JPanel();
       bookPanel.add(bookLabel);
       bookPanel.add(input);
       JButton book = new JButton("Book");
       JPanel buttonPanel = new JPanel();
       buttonPanel.add(book);
       buttonPanel.add(button);
	   
	   JPanel finalPanel = new JPanel();
	   BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
	   finalPanel.setLayout(boxlayout);
	   finalPanel.add(lPanel);
	   finalPanel.add(bookPanel);
	   finalPanel.add(inPanel);
	   finalPanel.add(outPanel);
	   finalPanel.add(buttonPanel);
	   
	   button.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   frame.setVisible(false);
			   HotelCustomerScreen screen = new HotelCustomerScreen();
			   screen.createScreen(cID);
		   }
	   });
	   
	   book.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   String inp = input.getText();
			   String inD = inDate.getText();
			   String outD = outDate.getText();
			   if(inp.isEmpty() || inD.isEmpty() || outD.isEmpty())   {
				   JOptionPane.showMessageDialog(frame,  "Please enter all required information");
			   }
			   else if(!list.contains(inp))   {
				   JOptionPane.showMessageDialog(frame,  "Please enter a valid room ID");
				   input.setText("");
			   }
			   else   {
				   HotelSQLProcedures pr = new HotelSQLProcedures();
			       int res = pr.insertReservation(Integer.parseInt(inp), Date.valueOf(inD), Date.valueOf(outD), cID);
			       if(res > 0)   {
		         	   frame.setVisible(false);
			           HotelRoomDisplay dis = new HotelRoomDisplay();
			           dis.registrationConfirmScreen(Integer.parseInt(inp), cID);
			       }
			       else {
			    	   JOptionPane.showMessageDialog(frame,  "There was a problem processing your request. Please try again later");
			       }
			   }
		   }
	   });
	   
	   frame.add(nPanel, BorderLayout.NORTH);
           frame.add(sp, BorderLayout.CENTER);
           frame.add(finalPanel, BorderLayout.PAGE_END); 
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.pack();
	   frame.setVisible(true);
   }
}
