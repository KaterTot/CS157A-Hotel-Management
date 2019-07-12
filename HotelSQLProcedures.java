import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

public class HotelSQLProcedures {

	JFrame frame;
	JFrame manLogin;
	DBConnection mt = new DBConnection();
	Connection myConn = mt.myConn;
	
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
	
	public void validateLogin(String uName, String password)   {
		boolean res = false;
		   try {
			   PreparedStatement pst = 
					   myConn.prepareStatement("select * from User where username=? and password=?");
			   pst.setString(1, uName);
			   pst.setString(2, password);
			  ResultSet rs = pst.executeQuery();
			  if(rs.next()) {
				  HotelManagerScreen screen = new HotelManagerScreen();
				  screen.createScreen();
			  }
			   }
			   catch (SQLException exc) {
					System.out.println("An error occured. Error: " + exc.getMessage());
		   }
	}
}
