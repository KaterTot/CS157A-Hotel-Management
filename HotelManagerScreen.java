import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class HotelManagerScreen {
	JFrame frame;

	public void createScreen() {
		frame = new JFrame("Manager Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel("What would you like to do next?");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JPanel labelPanel = new JPanel();
		labelPanel.setMaximumSize(new Dimension(500, 50));
		labelPanel.add(Box.createHorizontalStrut(40));
		labelPanel.add(label);

		JLabel addMan = new JLabel("Add another hotel manager");
		JButton addButton = new JButton("Add");
		JPanel addPanel = new JPanel();
		addPanel.setMaximumSize(new Dimension(500, 50));
		addPanel.add(Box.createHorizontalStrut(20));
		addPanel.add(addMan);
		addPanel.add(addButton);

		JLabel delMan = new JLabel("Delete a hotel manager");
		JButton delButton = new JButton("Delete");
		JPanel delPanel = new JPanel();
		delPanel.setMaximumSize(new Dimension(500, 50));
		delPanel.add(Box.createHorizontalStrut(20));
		delPanel.add(delMan);
		delPanel.add(delButton);

		JLabel addRoom = new JLabel("Add a room");
		JButton addRoomButton = new JButton("Add");
		JPanel addRoomPanel = new JPanel();
		addRoomPanel.setMaximumSize(new Dimension(500, 50));
		addRoomPanel.add(Box.createHorizontalStrut(20));
		addRoomPanel.add(addRoom);
		addRoomPanel.add(Box.createHorizontalStrut(65));
		addRoomPanel.add(addRoomButton);

		JLabel remRoom = new JLabel("Remove a room");
		JButton remButton = new JButton("Remove");
		JPanel remPanel = new JPanel();
		remPanel.setMaximumSize(new Dimension(500, 50));
		remPanel.add(Box.createHorizontalStrut(20));
		remPanel.add(remRoom);
		remPanel.add(Box.createHorizontalStrut(50));
		remPanel.add(remButton);

		JLabel changePrice = new JLabel("Change a room price");
		JButton changeButton = new JButton("Change Price");
		JPanel changePanel = new JPanel();
		changePanel.setMaximumSize(new Dimension(500, 50));
		changePanel.add(Box.createHorizontalStrut(20));
		changePanel.add(changePrice);
		changePanel.add(Box.createHorizontalStrut(50));
		changePanel.add(changeButton);
		
		JLabel badCustomer = new JLabel("Show bad customers");
		JButton badCustButton = new JButton("Show");
		JPanel badCustPanel = new JPanel();
		badCustPanel.setMaximumSize(new Dimension(500, 50));
		badCustPanel.add(Box.createHorizontalStrut(20));
		badCustPanel.add(badCustomer);
		badCustPanel.add(Box.createHorizontalStrut(50));
		badCustPanel.add(badCustButton);
		
		JLabel archLabel = new JLabel("Archive Reservations");
		JButton archButton = new JButton("Archive");
		JPanel archPanel = new JPanel();
		archPanel.setMaximumSize(new Dimension(500, 50));
		archPanel.add(Box.createHorizontalStrut(20));
		archPanel.add(archLabel);
		archPanel.add(Box.createHorizontalStrut(50));
		archPanel.add(archButton);

		JButton logOutButton = new JButton("Log Out");
		JPanel logOutPanel = new JPanel();
		logOutPanel.setMaximumSize(new Dimension(500, 50));
		logOutPanel.add(Box.createHorizontalStrut(50));
		logOutPanel.add(logOutButton);

		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(Box.createVerticalStrut(50));
		finalPanel.add(labelPanel);
		finalPanel.add(addPanel);
		finalPanel.add(delPanel);
		finalPanel.add(addRoomPanel);
		finalPanel.add(remPanel);
		finalPanel.add(changePanel);
		finalPanel.add(badCustPanel);
		finalPanel.add(archPanel);
		finalPanel.add(logOutPanel);

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.addManager();
			}
		});

		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.deleteManager();
			}
		});

		addRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.addRoom();
			}
		});

		remButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.deleteRoom();
			}
		});
		
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.changePrice();
			}
		});

		badCustButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.showBadCust();
			}
		});

		archButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.archiveInput();
			}
		});
		
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelStartScreen screen = new HotelStartScreen();
				screen.createScreen();
			}
		});

		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}	

	public void addManager()   {
		frame = new JFrame("Manager Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel("Please enter username and password of the new manager");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JLabel user = new JLabel("Username");
		JTextField u = new JTextField();
		u.setPreferredSize(new Dimension(100, 20));
		JLabel pass = new JLabel("Password");
		JTextField p = new JTextField();
		p.setPreferredSize(new Dimension(100, 20));
		JButton button = new JButton("Add Manager");
		JButton back = new JButton("Back");
		JPanel lPanel = new JPanel();
		lPanel.setMaximumSize(new Dimension(800, 50));
		lPanel.add(label);
		JPanel uPanel = new JPanel();
		uPanel.setMaximumSize(new Dimension(500, 50));
		uPanel.add(user);
		uPanel.add(u);
		JPanel pPanel = new JPanel();
		pPanel.setMaximumSize(new Dimension(500, 50));
		pPanel.add(pass);
		pPanel.add(p);
		JPanel bPanel = new JPanel();
		bPanel.setMaximumSize(new Dimension(500, 50));
		bPanel.add(back);
		bPanel.add(button);
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(lPanel);
		finalPanel.add(uPanel);
		finalPanel.add(pPanel);
		finalPanel.add(bPanel);

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.createScreen();
			}
		});

		button.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)   {
				String username = u.getText();
				String password = p.getText();
				HotelSQLProcedures pr = new HotelSQLProcedures();

				if(username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please enter all required information");
					u.setText("");
					p.setText("");
				}
				else {
					int ret = pr.addManager(username, password);
					if(ret == 1062)   {
						JOptionPane.showMessageDialog(frame, "The user with this username already exists as a manager. Please choose a different username");
						user.setText("");
						pass.setText("");
					}
					else {
						frame.setVisible(false);
						HotelManagerScreen screen = new HotelManagerScreen();
						screen.confirmAddDelete("New manager", "added to");
					}
				}
			}
		});

		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void showBadCust()   {
		frame = new JFrame("Manager Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		
		HotelSQLProcedures proc = new HotelSQLProcedures();
		ResultSet rs = proc.getUnreliableCustomers();
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
		String[][] d = new String[count][2];
		ArrayList<String> list = new ArrayList<>();
		try {
			rs.beforeFirst();
			while(rs.next()) {
				col = 0;
				String user = rs.getString("uName");
				list.add(user);
				d[row][col] = user;
				col++;
				String cc = rs.getString("CreditCard");
				list.add(cc);
				d[row][col] = cc;
				col++;
				row ++;
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		String[] columns = {"Customer Username", "Credit Card"};
		
		JTable j = new JTable(d, columns); 
		j.setBounds(30, 40, 2000, 300); 
		JScrollPane sp = new JScrollPane(j); 
		sp.setPreferredSize(new Dimension(0, 50));


		//JButton button = new JButton("Charge Customer");
		JButton back = new JButton("Back");
		JPanel lPanel = new JPanel();
		lPanel.setMaximumSize(new Dimension(800, 50));

		JPanel bPanel = new JPanel();
		bPanel.setMaximumSize(new Dimension(500, 50));
		bPanel.add(back);
		//bPanel.add(button);

		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(sp);
		finalPanel.add(lPanel);
		finalPanel.add(bPanel);

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.createScreen();
			}
		});

		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void deleteManager()   {
		frame = new JFrame("Manager Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel("Please enter username of the new manager to be removed");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JLabel user = new JLabel("Username");
		JTextField u = new JTextField();
		u.setPreferredSize(new Dimension(100, 20));
		JButton button = new JButton("Remove Manager");
		JButton back = new JButton("Back");
		JPanel lPanel = new JPanel();
		lPanel.setMaximumSize(new Dimension(800, 50));
		lPanel.add(label);
		JPanel uPanel = new JPanel();
		uPanel.setMaximumSize(new Dimension(500, 50));
		uPanel.add(user);
		uPanel.add(u);
		JPanel bPanel = new JPanel();
		bPanel.setMaximumSize(new Dimension(500, 50));
		bPanel.add(back);
		bPanel.add(button);
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(lPanel);
		finalPanel.add(uPanel);
		finalPanel.add(bPanel);

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.createScreen();
			}
		});

		button.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)   {
				String username = u.getText();
				HotelSQLProcedures pr = new HotelSQLProcedures();

				if(username.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please enter all required information");
					u.setText("");
				}
				else {
					int res = pr.deleteManager(username);
					if(res > 0)   {
						frame.setVisible(false);
						HotelManagerScreen screen = new HotelManagerScreen();
						screen.confirmAddDelete("The manager", "deleted from");
					}
					else {
						JOptionPane.showMessageDialog(frame, "The manager you entered does not exist in database");
						u.setText("");
					}
				}
			}
		});

		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void addRoom()   {
		frame = new JFrame("Manager Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel("Please enter the room type of the room you want to add");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JLabel user = new JLabel("Room Type");
		JTextField u = new JTextField();
		u.setPreferredSize(new Dimension(100, 20));
		JButton button = new JButton("Add Room");
		JButton back = new JButton("Back");
		JPanel lPanel = new JPanel();
		lPanel.setMaximumSize(new Dimension(800, 50));
		lPanel.add(label);
		JPanel uPanel = new JPanel();
		uPanel.setMaximumSize(new Dimension(500, 50));
		uPanel.add(user);
		uPanel.add(u);
		JPanel bPanel = new JPanel();
		bPanel.setMaximumSize(new Dimension(500, 50));
		bPanel.add(back);
		bPanel.add(button);
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(lPanel);
		finalPanel.add(uPanel);
		finalPanel.add(bPanel);

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.createScreen();
			}
		});

		button.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)   {
				String roomType = u.getText();
				HotelSQLProcedures pr = new HotelSQLProcedures();

				if(roomType.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please enter all required information");
					u.setText("");
				}
				else {
					int ret = pr.addRoom(roomType);
					if(ret == 1452)   {
						JOptionPane.showMessageDialog(frame, "The room type does not exist. Please add an existing room type.");
						u.setText("");
					}
					else {
						frame.setVisible(false);
						HotelManagerScreen screen = new HotelManagerScreen();
						screen.confirmAddDelete("The room", "added to the database");
					}
				}
			}
		});

		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void deleteRoom()   {
		frame = new JFrame("Manager Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel("Please enter the room ID to be removed");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JLabel user = new JLabel("Room ID");
		JTextField u = new JTextField();
		u.setPreferredSize(new Dimension(100, 20));
		JButton button = new JButton("Remove Room");
		JButton back = new JButton("Back");
		JPanel lPanel = new JPanel();
		lPanel.setMaximumSize(new Dimension(800, 50));
		lPanel.add(label);
		JPanel uPanel = new JPanel();
		uPanel.setMaximumSize(new Dimension(500, 50));
		uPanel.add(user);
		uPanel.add(u);
		JPanel bPanel = new JPanel();
		bPanel.setMaximumSize(new Dimension(500, 50));
		bPanel.add(back);
		bPanel.add(button);
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(lPanel);
		finalPanel.add(uPanel);
		finalPanel.add(bPanel);

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.createScreen();
			}
		});

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rID = u.getText();
				HotelSQLProcedures pr = new HotelSQLProcedures();
				if(rID.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please enter all required information");
					u.setText("");
				}
				else {
					int res = pr.deleteRoom(Integer.parseInt(rID));
					if(res > 0)   {
						frame.setVisible(false);
						HotelManagerScreen screen = new HotelManagerScreen();
						screen.confirmAddDelete("The room", "removed from");
					}
					else {
						JOptionPane.showMessageDialog(frame, "The room number you entered does not exist in database");
						u.setText("");
					}
				}
			}
		});

		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void confirmAddDelete(String subject, String operation)   {
		frame = new JFrame("Manager Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		JLabel label = new JLabel(subject + " has been " + operation + " database");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JButton button = new JButton("Main Menu");
		JPanel lPanel = new JPanel();
		lPanel.setMaximumSize(new Dimension(800, 50));
		lPanel.add(label);
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(lPanel);
		finalPanel.add(button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.createScreen();
			}
		});

		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void changePrice()
	{
		frame = new JFrame("Manager Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		
		HotelSQLProcedures proc = new HotelSQLProcedures();
		ResultSet rs = proc.showRoomsAndPrices();
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
		String[][] d = new String[count][5];
		ArrayList<String> list = new ArrayList<>();
		try {
			rs.beforeFirst();
			while(rs.next()) {
				col = 0;
				String rID = rs.getString("rID");
				list.add(rID);
				d[row][col] = rID;
				col++;
				String roomType = rs.getString("roomType");
				list.add(roomType);
				d[row][col] = roomType;
				col++;
				String numBeds = rs.getString("numBeds");
				list.add(numBeds);
				d[row][col] = numBeds;
				col++;
				String numRented = rs.getString("numRented");
				list.add(numRented);
				d[row][col] = numRented;
				col++;
				String price = rs.getString("price");
				list.add(price);
				d[row][col] = price;
				col++;
				row ++;
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		String[] columns = {"Room ID", "Room Type", "Number of Beds", "Number of Times Rented", "Price"};
		
		JTable j = new JTable(d, columns); 
		j.setBounds(30, 40, 2000, 300); 
		JScrollPane sp = new JScrollPane(j); 
		sp.setPreferredSize(new Dimension(0, 50));
		
		JLabel nLabel = new JLabel("The following rooms are currently in hotel database:");
		nLabel.setFont(new Font("Serif", Font.PLAIN, 22));
		JPanel nPanel = new JPanel();
		nPanel.setMaximumSize(new Dimension(800, 50));
		nPanel.add(nLabel);
		
		JLabel label = new JLabel("Please enter the room ID you would like to edit");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JLabel rID = new JLabel("Room ID");
		JTextField u = new JTextField();
		JLabel priceLab = new JLabel("Please enter the new price of the room");
		priceLab.setFont(new Font("Serif", Font.PLAIN, 22));
		JLabel price = new JLabel("Price");
		JTextField p = new JTextField();
		u.setPreferredSize(new Dimension(100, 20));
		p.setPreferredSize(new Dimension(100, 20));
		JButton button = new JButton("Change Price");
		JButton back = new JButton("Back");
		JPanel lPanel = new JPanel();
		lPanel.setMaximumSize(new Dimension(800, 50));
		lPanel.add(label);
		JPanel uPanel = new JPanel();
		uPanel.setMaximumSize(new Dimension(500, 50));
		uPanel.add(rID);
		uPanel.add(u);
		JPanel pPanel = new JPanel();
		pPanel.setMaximumSize(new Dimension(800, 50));
		pPanel.add(priceLab);
		JPanel prPanel = new JPanel();
		prPanel.setMaximumSize(new Dimension(500, 50));
		prPanel.add(price);
		prPanel.add(p);
		JPanel bPanel = new JPanel();
		bPanel.setMaximumSize(new Dimension(500, 50));
		bPanel.add(back);
		bPanel.add(button);
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(nPanel);		
		finalPanel.add(sp);
		finalPanel.add(lPanel);
		finalPanel.add(uPanel);
		finalPanel.add(pPanel);
		finalPanel.add(prPanel);
		finalPanel.add(bPanel);

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.createScreen();
			}
		});

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rID = u.getText();
				String price = p.getText();
				HotelSQLProcedures pr = new HotelSQLProcedures();
				if(rID.isEmpty() || price.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please enter all required information");
					u.setText("");
					p.setText("");
				}
				else {
					int res = pr.updateRoomPrice(Integer.parseInt(rID), Integer.parseInt(price));
					if(res > 0)   {
						frame.setVisible(false);
						frame = new JFrame("Manager Screen");
						frame.setPreferredSize(new Dimension(2000,2000));
						JLabel label = new JLabel("The price of room number " + rID + " has been changed to " + price);
						label.setFont(new Font("Serif", Font.PLAIN, 22));
						JButton button = new JButton("Ok");
						JPanel lPanel = new JPanel();
						lPanel.setMaximumSize(new Dimension(800, 50));
						lPanel.add(label);
						JPanel finalPanel = new JPanel();
						BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
						finalPanel.setLayout(boxlayout);
						finalPanel.add(lPanel);
						finalPanel.add(button);

						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								frame.setVisible(false);
								HotelManagerScreen screen = new HotelManagerScreen();
								screen.changePrice();
							}
						});

						frame.add(finalPanel);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.pack();
						frame.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(frame, "The room number you entered does not exist in database");
						u.setText("");
						p.setText("");
					}
				}
			}
		});

		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void archiveInput()   {
		frame = new JFrame("Manager Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		
		JLabel label = new JLabel("Please enter the cuttof date for archiving reservations (yyyy-mm-dd)");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JButton button = new JButton("Archive reservations");
		JButton back = new JButton("Back");
		JTextField input = new JTextField();
		input.setPreferredSize(new Dimension(100, 20));
		JPanel lPanel = new JPanel();
		lPanel.setMaximumSize(new Dimension(1000, 50));		
		lPanel.add(label);
		JPanel inputPanel = new JPanel();
		inputPanel.setMaximumSize(new Dimension(500, 50));		
		inputPanel.add(input);
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		JPanel butPanel = new JPanel();
		butPanel.setMaximumSize(new Dimension(500, 50));
		butPanel.add(back);
		butPanel.add(button);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(lPanel);
		finalPanel.add(inputPanel);
		finalPanel.add(butPanel);
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.createScreen();
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String st = input.getText();
				if(st.isEmpty())   {
					JOptionPane.showMessageDialog(frame, "Please enter the cuttof date");
				}
				else   {
					HotelSQLProcedures pr = new HotelSQLProcedures();
					int res = pr.archiveReservations(Date.valueOf(st));
					if(res > 0)  {
						frame.setVisible(false);
		         	                HotelManagerScreen scr = new HotelManagerScreen();
		         	                scr.archiveConfirm();
					}
					else   {
						JOptionPane.showMessageDialog(frame, "Your request could not be completed at this time. Please verify that the input is in the correct format");
					}
				}
			}
		});
		
		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void archiveConfirm()   {
		frame = new JFrame("Manager Screen");
		frame.setPreferredSize(new Dimension(2000,2000));
		
		JLabel label = new JLabel("The reservations have been successfully archived!");
		label.setFont(new Font("Serif", Font.PLAIN, 22));
		JButton button = new JButton("See archived reservations");
		JButton back = new JButton("Main Menu");
		
		JPanel lPanel = new JPanel();
		lPanel.setMaximumSize(new Dimension(1000, 50));		
		lPanel.add(label);
		
		JPanel bPanel = new JPanel();
		bPanel.setMaximumSize(new Dimension(1000, 50));		
		bPanel.add(button);
		bPanel.add(back);
		
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(lPanel);
		finalPanel.add(bPanel);
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelManagerScreen screen = new HotelManagerScreen();
				screen.createScreen();
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				HotelSQLProcedures pr = new HotelSQLProcedures();
				ResultSet rs = pr.getArchivedReservations();
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
				String[][] d = new String[count][5];
				try {
					rs.beforeFirst();
					while(rs.next()) {
						col = 0;
						String resID = String.valueOf(rs.getInt("resID"));
						d[row][col] = resID;
						col++;
						String cID = String.valueOf(rs.getInt("cID"));
						d[row][col] = cID;
						col++;
						String rID = String.valueOf(rs.getInt("rID"));
						d[row][col] = rID;
						col++;
						String begin = String.valueOf(rs.getDate("beginDate"));
						d[row][col] = begin;
						col++;
						String end = String.valueOf(rs.getDate("endDate"));
						d[row][col] = end;
						col++;
						row ++;
					}
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
				String[] columns = {"Reservation ID", "Customer ID", "Room ID", "Check-in Date", "Check-out Date"};
				HotelRoomDisplay dis = new HotelRoomDisplay();
				dis.displayArchivedReservations(d, columns);
			}
		});
		
		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
