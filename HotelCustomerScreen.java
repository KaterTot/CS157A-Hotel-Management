import java.awt.Dimension;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;  
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.*;



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
		JButton dealsButton = new JButton("Choose");
		JPanel remPanel = new JPanel();
		remPanel.setMaximumSize(new Dimension(500, 50));
		remPanel.add(Box.createHorizontalStrut(20));
		remPanel.add(remRoom);
		remPanel.add(Box.createHorizontalStrut(50));
		remPanel.add(dealsButton);
		
		JLabel popularRoom = new JLabel("Choose the most popular rooms");
		JButton popularButton = new JButton("Choose");
		JPanel popularPanel = new JPanel();
		popularPanel.setMaximumSize(new Dimension(500, 50));
		popularPanel.add(Box.createHorizontalStrut(20));
		popularPanel.add(popularRoom);
		popularPanel.add(Box.createHorizontalStrut(50));
		popularPanel.add(popularButton);
		
		JLabel rateRoom = new JLabel("Rate a room you have already stayed in");
		JButton rateButton = new JButton("Rate");
		JPanel ratePanel = new JPanel();
		ratePanel.setMaximumSize(new Dimension(500, 50));
		ratePanel.add(Box.createHorizontalStrut(20));
		ratePanel.add(rateRoom);
		ratePanel.add(Box.createHorizontalStrut(50));
		ratePanel.add(rateButton);
		
		JButton logOutButton = new JButton("Log Out");
		JPanel logOutPanel = new JPanel();
		logOutPanel.setMaximumSize(new Dimension(500, 50));
		logOutPanel.add(Box.createHorizontalStrut(50));
		logOutPanel.add(logOutButton);
		
		JButton deleteButton = new JButton("Delete Account");
		JPanel deletePanel = new JPanel();
		deletePanel.setMaximumSize(new Dimension(500, 50));
		deletePanel.add(Box.createHorizontalStrut(50));
		deletePanel.add(deleteButton);
		
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(Box.createVerticalStrut(50));
		finalPanel.add(labelPanel);
		finalPanel.add(addPanel);
		finalPanel.add(delPanel);
		finalPanel.add(addRoomPanel);
		finalPanel.add(remPanel);
		finalPanel.add(popularPanel);
		finalPanel.add(ratePanel);
		finalPanel.add(logOutPanel);
		finalPanel.add(deletePanel);
		
		bookButton.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   frame.setVisible(false);
				   HotelCustomerScreen screen = new HotelCustomerScreen();
				   screen.reserveInput(cID);
			   }
		   });
		
		delButton.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   HotelSQLProcedures pr = new HotelSQLProcedures();
				   ResultSet rs = pr.getReservations(cID);
				   try {
				   if(!rs.next())   {
					   frame.setVisible(false);
					   HotelCustomerScreen screen = new HotelCustomerScreen();
					   screen.showNoReservations(cID);
				      }
				   else {
					   frame.setVisible(false);
					   rs.beforeFirst();
					   int count = 0;
					   int row = 0;
					   int col = 0;
					   while(rs.next())   {
						   count ++ ;
					   }
					   rs.beforeFirst();
					   String[][] d = new String[count][5];
					   while(rs.next())   {
						   col = 0;
						   String resID = String.valueOf(rs.getInt("resID"));
						   d[row][col] = resID;
						   col ++ ;
						   String cID = String.valueOf(rs.getInt("cID"));
						   d[row][col] = cID;
						   col ++ ;
						   String rID = String.valueOf(rs.getInt("rID"));
						   d[row][col] = rID;
						   col ++ ;
						   String beg = String.valueOf(rs.getDate("beginDate"));
						   d[row][col] = beg;
						   col ++ ;
						   String end = String.valueOf(rs.getDate("endDate"));
						   d[row][col] = end;
						   row ++;
					   }
					   String[] columns = {"Reservation ID", "Customer ID", "Room ID", "Check-in Date", "Check-out Date"};
					   HotelRoomDisplay dis = new HotelRoomDisplay();
					   dis.displayReservations(d, columns, cID);
				     }
				   }
				   catch(SQLException ex)   {
					   ex.getErrorCode();
				   }
			   }
		   });
		
		popularButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelSQLProcedures screen = new HotelSQLProcedures();
				ResultSet rs = screen.showPopularRooms();
				ArrayList<String> list = new ArrayList<>();
				try {
				frame.setVisible(false);
				   int count = 0;
				   int row = 0;
				   int col = 0;
				   while(rs.next())   {
					   count ++ ;
				   }
				   rs.beforeFirst();
				   String[][] d = new String[count][3];
				   while(rs.next())   {
					   col = 0;
					   String rID = String.valueOf(rs.getInt("rID"));
					   d[row][col] = rID;
					   list.add(rID);
					   col ++ ;
					   // roomType, numRented"
					   String roomType = rs.getString("roomType");
					   d[row][col] = roomType;
					   list.add(roomType);
					   col++;
					   String numRented = String.valueOf(rs.getInt("numRented"));
					   d[row][col] = numRented;
					   list.add(numRented);
					   col ++ ;
					   row ++;
				   }
				   String[] columns = {"Room ID", "Room Type", "# of Times Rented"};
				   frame.setVisible(false);
				   HotelRoomDisplay dis = new HotelRoomDisplay();
				   dis.displayBestDeals(d, columns, cID, list);
				}
				 catch(SQLException ex)   {
					   ex.getErrorCode();
				   }
			}
		});
		
		dealsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelSQLProcedures screen = new HotelSQLProcedures();
				ResultSet rs = screen.bestDeals(cID);
				ArrayList<String> list = new ArrayList<>();
				try {
				frame.setVisible(false);
				   int count = 0;
				   int row = 0;
				   int col = 0;
				   while(rs.next())   {
					   count ++ ;
				   }
				   rs.beforeFirst();
				   String[][] d = new String[count][2];
				   while(rs.next())   {
					   col = 0;
					   String rID = String.valueOf(rs.getInt("rID"));
					   d[row][col] = rID;
					   list.add(rID);
					   col ++ ;
					   String ave = String.valueOf(rs.getDouble("st"));
					   d[row][col] = ave;
					   list.add(ave);
					   col ++ ;
					   row ++;
				   }
				   String[] columns = {"Room ID", "Average Rating"};
				   frame.setVisible(false);
				   HotelRoomDisplay dis = new HotelRoomDisplay();
				   dis.displayPopularRooms(d, columns, cID, list);
				}
				 catch(SQLException ex)   {
					   ex.getErrorCode();
				   }
			}
		});
		
		rateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelCustomerScreen screen = new HotelCustomerScreen();
				screen.rateRoom(cID);
			}
		});
		
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelStartScreen screen = new HotelStartScreen();
				screen.createScreen();
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int s = JOptionPane.showConfirmDialog(frame, "Warning! This will cancel all active reservations and ratings.", "Delete Account", JOptionPane.YES_NO_OPTION);
				if (s == JOptionPane.YES_OPTION)
				{
					HotelSQLProcedures sq = new HotelSQLProcedures();
					sq.deleteAccount(cID);
					frame.setVisible(false);
					HotelStartScreen screen = new HotelStartScreen();
					screen.createScreen();
				}
			}
		});
		
		
		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}	
	
	public void showNoReservations(int cID)  {
		frame = new JFrame("Input Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel("You have no reservations at this time");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JButton button = new JButton("Main Menu");
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(500, 50));
		panel.add(label);
		
		JPanel bPanel = new JPanel();
		bPanel.setMaximumSize(new Dimension(500, 50));
		bPanel.add(button);
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(panel);
		finalPanel.add(bPanel);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelCustomerScreen screen = new HotelCustomerScreen();
				screen.createScreen(cID);
			}
		});	
		
		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public void reservationCancelConfirm(int cID)   {
		frame = new JFrame("Confirm Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel("Your reservation has been cancelled");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JButton button = new JButton("Main Menu");
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(500, 50));
		panel.add(label);
		
		JPanel bPanel = new JPanel();
		bPanel.setMaximumSize(new Dimension(500, 50));
		bPanel.add(button);
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(panel);
		finalPanel.add(bPanel);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelCustomerScreen screen = new HotelCustomerScreen();
				screen.createScreen(cID);
			}
		});
		
		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public void rateRoom(int cID)
	{		
		HotelSQLProcedures proc = new HotelSQLProcedures();
		ResultSet rs = proc.getReservedRooms(cID);
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
		String[][] d = new String[count][1];
		ArrayList<String> list = new ArrayList<>();
		try {
			rs.beforeFirst();
			while(rs.next()) {
				col = 0;
				String rID = String.valueOf(rs.getInt("rID"));
				list.add(rID);
				d[row][col] = rID;
				row ++;
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		String[] columns = {"Room Number"};
		RateRoomDisplay dis = new RateRoomDisplay();
		dis.displayPastReservations(d, columns, cID, list);
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
		JButton back = new JButton("Back");
		JPanel bPanel = new JPanel();
		bPanel.setMaximumSize(new Dimension(500, 100));
		bPanel.add(button);
		bPanel.add(back);
		
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(panel);
		finalPanel.add(s);
		finalPanel.add(e);
		finalPanel.add(p);
		finalPanel.add(bPanel);
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.setVisible(false);
				HotelCustomerScreen screen = new HotelCustomerScreen();
				screen.createScreen(cID);
			}
		});
		
		 button.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   String sDate = start.getText();
				   String en = end.getText();
				   String p = pr.getText();
				   Date inDate = Date.valueOf(sDate);//converting string into sql date  
				   Date outDate = Date.valueOf(en);
				   if(sDate.isEmpty() || en.isEmpty() || p.isEmpty())   {
					   JOptionPane.showMessageDialog(input,"Please enter all required information");
				   }
				   else if((Date.valueOf(sDate)).compareTo(Date.valueOf(en)) > 0)   {
					   JOptionPane.showMessageDialog(input,"Check-out date must be later than check-in date");
					   end.setText("");
				   }
				    else if(inDate.compareTo(new java.sql.Date(System.currentTimeMillis()) ) < 0) {
					   JOptionPane.showMessageDialog(input,"You cannot make reservations for past dates");
					   start.setText("");
				   }
				   else {
					input.setVisible(false);   
					int price = Integer.parseInt(pr.getText());	
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
							   String stars = String.valueOf(rs.getDouble("stars"));
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
			   }
		   });
		
		input.add(finalPanel);
		input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    input.pack();
	    input.setVisible(true);
	}
	
}
