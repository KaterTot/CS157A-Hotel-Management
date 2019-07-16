import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.Date;  
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.*;

import com.sun.xml.sp.ParseException;

public class HotelCustomerScreen {
JFrame frame;
JFrame input;
	
	public void createScreen(int cID) {
		frame = new JFrame("Customer Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel("How can we help you today? Please choose from the options below");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JPanel labelPanel = new JPanel();
		labelPanel.setMaximumSize(new Dimension(700, 50));
		labelPanel.add(Box.createHorizontalStrut(40));
		labelPanel.add(label);
		
		JLabel addMan = new JLabel("Book a room");
		JButton bookButton = new JButton("Book");
		JPanel addPanel = new JPanel();
		addPanel.setMaximumSize(new Dimension(500, 50));
		addPanel.add(Box.createHorizontalStrut(20));
		addPanel.add(addMan);
		addPanel.add(bookButton);
		
		JLabel delMan = new JLabel("Cancel my reservation");
		JButton delButton = new JButton("Cancel");
		JPanel delPanel = new JPanel();
		delPanel.setMaximumSize(new Dimension(500, 50));
		delPanel.add(Box.createHorizontalStrut(20));
		delPanel.add(delMan);
		delPanel.add(delButton);
		
		JLabel addRoom = new JLabel("Change dates of my reservation");
		JButton addRoomButton = new JButton("Change");
		JPanel addRoomPanel = new JPanel();
		addRoomPanel.setMaximumSize(new Dimension(500, 50));
		addRoomPanel.add(Box.createHorizontalStrut(20));
		addRoomPanel.add(addRoom);
		addRoomPanel.add(Box.createHorizontalStrut(65));
		addRoomPanel.add(addRoomButton);
		
		JLabel remRoom = new JLabel("Choose best rooms for your budget");
		JButton remButton = new JButton("Choose");
		JPanel remPanel = new JPanel();
		remPanel.setMaximumSize(new Dimension(500, 50));
		remPanel.add(Box.createHorizontalStrut(20));
		remPanel.add(remRoom);
		remPanel.add(Box.createHorizontalStrut(50));
		remPanel.add(remButton);
		
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(Box.createVerticalStrut(50));
		finalPanel.add(labelPanel);
		finalPanel.add(addPanel);
		finalPanel.add(delPanel);
		finalPanel.add(addRoomPanel);
		finalPanel.add(remPanel);
		
		bookButton.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   frame.setVisible(false);
				   HotelCustomerScreen screen = new HotelCustomerScreen();
				   screen.reserveInput(cID);
			   }
		   });
		
		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}	
	
	public void reserveInput(int cID)   {
		input = new JFrame("Input Screen");
		input.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel("Please fill out information below:");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		
		JLabel stDate = new JLabel("Checking in on (yyyy-mm-dd)");
		JLabel endDate = new JLabel("Checking out on (yyyy-mm-dd)");
		JLabel price = new JLabel("Price per night");
		
		JTextField start = new JTextField();
		JTextField end = new JTextField();
		JTextField pr = new JTextField();
		start.setPreferredSize(new Dimension(100, 20));
		end.setPreferredSize(new Dimension(100, 20));
		pr.setPreferredSize(new Dimension(100, 20));
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(500, 50));
		panel.add(label);
		
		JPanel s = new JPanel();
		s.setMaximumSize(new Dimension(500, 50));
		s.add(stDate);
		s.add(start);
		
		JPanel e = new JPanel();
		e.setMaximumSize(new Dimension(500, 50));
		e.add(endDate);
		e.add(end);
		
		JPanel p = new JPanel();
		p.setMaximumSize(new Dimension(500, 50));
		p.add(price);
		p.add(pr);
		
		JButton button = new JButton("Search for rooms");
		JPanel bPanel = new JPanel();
		bPanel.setMaximumSize(new Dimension(500, 100));
		bPanel.add(button);
		
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(panel);
		finalPanel.add(s);
		finalPanel.add(e);
		finalPanel.add(p);
		finalPanel.add(bPanel);
		
		 button.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   input.setVisible(false);
				   String sDate = start.getText();
				   String en = end.getText();
				   int price = Integer.parseInt(pr.getText());
				    Date inDate = Date.valueOf(sDate);//converting string into sql date  
				    Date outDate = Date.valueOf(en);
				    HotelSQLProcedures proc = new HotelSQLProcedures();
				    ResultSet rs = proc.getAvailableRooms(inDate,  outDate, price);
				    int count = 0;
					   int row = 0;
					   int col = 0;
					   try {
					   while(rs.next()) {
						    count ++ ;
					      }
					   }
					   catch(Exception ex) {
						   System.out.println(ex);
					   }
					   String[][] d = new String[count][4];
					   try {
						   rs.beforeFirst();
						   while(rs.next()) {
							   col = 0;
							   String rID = String.valueOf(rs.getInt("rID"));
							   d[row][col] = rID;
							   col ++ ;
							   String roomType = rs.getString("ROOMTYPE.roomType");
							   d[row][col] = roomType;
							   col ++ ;
							   String ppNight = String.valueOf(rs.getDouble("price"));
							   d[row][col] = ppNight;
							   col ++ ;
							   String stars = String.valueOf(rs.getInt("stars"));
							   d[row][col] = stars;
							   col ++ ;
							   row ++;
						   }
						   }
						   catch(Exception ex) {
							   System.out.println(ex);
						   }
					   String[] columns = {"Room Number", "Room Type", "Price per Night", "Rating"};
					   HotelRoomDisplay dis = new HotelRoomDisplay();
					   dis.displayAvailableRooms(d, columns, inDate, outDate, cID);
			   }
		   });
		
		input.add(finalPanel);
		input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    input.pack();
	    input.setVisible(true);
	}
}
