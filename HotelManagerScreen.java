import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
		
		JPanel finalPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(finalPanel, BoxLayout.Y_AXIS);
		finalPanel.setLayout(boxlayout);
		finalPanel.add(Box.createVerticalStrut(50));
		finalPanel.add(labelPanel);
		finalPanel.add(addPanel);
		finalPanel.add(delPanel);
		finalPanel.add(addRoomPanel);
		finalPanel.add(remPanel);
		
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
					user.setText("");
					pass.setText("");
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
						screen.createScreen();
					}
				}
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
		
		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	    frame.setVisible(true);
	}
	
}
