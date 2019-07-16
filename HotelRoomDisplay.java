import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class HotelRoomDisplay {
	JFrame frame;
	JFrame confirm;
   public void createScreen(ResultSet rs)   {
	   frame = new JFrame("Start Screen");
	   frame.setPreferredSize(new Dimension(2000,2000));
	   
	   int count = 0;
	   int row = 0;
	   int col = 0;
	   try {
	   while(rs.next()) {
		    count ++ ;
	      }
	   }
	   catch(Exception e) {
		   System.out.println(e);
	   }
	   String[][] d = new String[count][2];
	   try {
	   rs.beforeFirst();
	   while(rs.next()) {
		   col = 0;
		   String title = rs.getString("title");
		   d[row][col] = title;
		   col ++ ;
		   String auth = rs.getString("author");
		   d[row][col] = auth;
		   row ++;
	   }
	   }
	   catch(Exception e) {
		   System.out.println(e);
	   }
	   
	   String[] columnNames = { "Title", "Author"};
	   JTable j = new JTable(d, columnNames); 
       j.setBounds(30, 40, 2000, 300); 
       JScrollPane sp = new JScrollPane(j); 
       sp.setMaximumSize(new Dimension(1000, 50));
       sp.setPreferredSize(new Dimension(0, 50));
       JButton button = new JButton("Back");
       JPanel panel = new JPanel();
       panel.add(button);
       JLabel label = new JLabel("Displaying stuff");
       JPanel lPanel = new JPanel();
       lPanel.add(label);
       lPanel.add(Box.createVerticalStrut(50));
       
       frame.add(lPanel, BorderLayout.NORTH);
       frame.add(sp, BorderLayout.CENTER);
       frame.add(panel, BorderLayout.PAGE_END); 
	   
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.pack();
	   frame.setVisible(true);
   }
   
   public void displayAvailableRooms(String[][] rooms, String[] col, Date in, Date out, int cID)   {
	   frame = new JFrame("Display Screen");
	   frame.setPreferredSize(new Dimension(2000,2000));
	   JLabel label = new JLabel("Please select the room number you wish to book");
	   JTextField field = new JTextField();
	   field.setPreferredSize(new Dimension(100, 20));
	   JButton button = new JButton("Book");
	   JPanel panel = new JPanel();
	   panel.add(label);
	   panel.add(field);
	   panel.add(button);
	   
	   JTable j = new JTable(rooms, col); 
	   j.setBounds(30, 40, 2000, 300); 
       JScrollPane sp = new JScrollPane(j); 
       sp.setMaximumSize(new Dimension(1000, 50));
       sp.setPreferredSize(new Dimension(0, 50));
       
       button.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   frame.setVisible(false);
    		   int rID = Integer.parseInt(field.getText());
    		   HotelSQLProcedures proc = new HotelSQLProcedures();
    		   proc.insertReservation(rID, in, out, cID);
    		   HotelRoomDisplay disp = new HotelRoomDisplay();
    		   disp.registrationConfirmScreen(rID, cID);
    	   }
       });
       
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
}
