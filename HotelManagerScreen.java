import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.*;

public class HotelManagerScreen {
	JFrame frame;
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
		JButton delButton = new JButton("Add");
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
		
		frame.add(finalPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}	
}
