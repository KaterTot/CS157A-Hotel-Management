import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.*;

public class HotelRoomDisplay {
   public void createScreen(ResultSet rs)   {
	   JFrame frame = new JFrame("Start Screen");
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
//	   d[0][0] = "Title";
//	   d[0][1] = "Author";
//	   d[1][0] = "";
//	   d[1][1] = "";
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
	   
	   String[][] data = { 
			   {"name", "Roll Number", "Department"},
			   {"", "", ""},
	            { "Kundan Kumar Jha", "4031", "CSE" }, 
	            { "Anand Jha", "6014", "IT" } 
	        }; 
	//   String[] columnNames = { "Name", "Roll Number", "Department" }; 
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
       
//       frame.add(j);
//       frame.add(panel); 
	   
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.pack();
	   frame.setVisible(true);
   }
}
