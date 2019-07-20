import java.sql.Connection;

import java.sql.Date;
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
				//  Class.forName("com.mysql.jdbc.Driver"); 
				myConn = DriverManager.getConnection(DatabaseCredentials.URL, 
								     DatabaseCredentials.USER, DatabaseCredentials.PASSWORD);
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}
	
	public int validateLogin(String uName, String password, String position)   {
		   try {
			   PreparedStatement pst = 
					   myConn.prepareStatement("select * from " + position + " where uNAME=? and pWORD=?");
			   pst.setString(1, uName);
			   pst.setString(2, password);
			  ResultSet rs = pst.executeQuery();
			  if(position.equals("customer")) {
			     if(rs.next()) {
				     int cID = rs.getInt("cID");
				     return cID;
			     }
			  }
			  else if(position.equals("manager")) {
				  if(rs.next())  {
					  return 1;
				  }
			    }
			   }
			   catch (SQLException exc) {
					System.out.println("An error occured. Error: " + exc.getMessage());
		   }
		   return 0;
	}
	
	public int addManager(String uName, String password) {
		try {
			PreparedStatement pst = myConn.prepareStatement("INSERT INTO MANAGER (uNAME, pWORD) values(?, ?)");
			pst.setString(1, uName);
			pst.setString(2, password);
			pst.executeUpdate();
			return 1;
		} catch (SQLException exc) {
			int err = exc.getErrorCode();
			return err;
		}
	}
	
	public int deleteManager(String uName) {
		int res = 0;
		try {
			PreparedStatement pst = myConn.prepareStatement("DELETE FROM MANAGER WHERE uNAME=?");
			pst.setString(1, uName);
			res = pst.executeUpdate();
			return res;
		} catch (SQLException exc) {
			exc.printStackTrace();
			return 0;
		}
	}
	
	public int addRoom(String roomType) {
		try {
			PreparedStatement pst = myConn.prepareStatement("INSERT INTO ROOM (roomType, numRented) values(?, 0)");
			pst.setString(1, roomType);
			pst.executeUpdate();
			return 1;
		} catch (SQLException exc) {
			int err = exc.getErrorCode();
			return err;
		}
	}
	
	public int createAccount(String uName, String pass, String card)   {
		 try {
			   PreparedStatement pst = 
					   myConn.prepareStatement("insert into CUSTOMER (uNAME, pWORD, CreditCard) values(?,?,?)");
			   pst.setString(1, uName);
			   pst.setString(2, pass);
			   pst.setString(3, card);
	    		   pst.executeUpdate();
	    		  return 1;
			   }
			   catch (SQLException exc) {
					int err = exc.getErrorCode();
					return err;
		   }
	}
	
	public ResultSet getAvailableRooms(Date in, Date out, int price)   {
		ResultSet rs = null;
		 try { 
			 /*
			   PreparedStatement pst = 
					   myConn.prepareStatement("select rID, ROOMTYPE.roomType, price, avg(stars) as stars"
					   		+ " from ROOMTYPE natural join ROOM natural join RATING where price<?"
					   		+ " and rID not in (select rID from RESERVATION where (? between beginDate and endDate) "
					   		+ "or (? between beginDate and endDate) or (? >= beginDate and ? <= endDate))"
					   		+ "group by rID");
			   */
			   PreparedStatement pst = 
					   myConn.prepareStatement("select rID, ROOMTYPE.roomType, price, avg(CAST(stars AS DOUBLE)) stars"
					   		+ " from ROOMTYPE natural join ROOM left outer join RATING using(rID) where price<?"
					   		+ " and rID not in (select rID from RESERVATION where (? between beginDate and endDate) "
					   		+ "or (? between beginDate and endDate) or (? >= beginDate and ? <= endDate))"
					   		+ "group by rID");
			 
			   pst.setDouble(1, price);
			   pst.setDate(2, in);
			   pst.setDate(3, out);
			   pst.setDate(4, in);
			   pst.setDate(5, out);
			   ResultSet r = pst.executeQuery();
			   rs = r;
			   }
			   catch (SQLException exc) {
					System.out.println("An error occured. Error: " + exc.getMessage());
		   }
		return rs;
	}
	
	public int insertReservation(int rID, Date in, Date out, int cID)   {
		int res = 0;
		 try {
			   PreparedStatement pst = 
					   myConn.prepareStatement("insert into RESERVATION (cID, rID, beginDate, endDate, updateAt) values(?,?,?,?,?)");
			   pst.setInt(1, cID);
			   pst.setInt(2, rID);
			   pst.setDate(3, in);
			   pst.setDate(4, out);
	                   pst.setDate(5, new java.sql.Date(System.currentTimeMillis()));
		           res = pst.executeUpdate();
			   return res;
			   }
			   catch (SQLException exc) {
			     exc.getMessage();
		             return 0;		   
		   }
	}
	
	public ResultSet getReservations(int cID)   {
		 ResultSet rs = null;
		 try {
			   PreparedStatement pst = 
					   myConn.prepareStatement("select * from RESERVATION where cID = ? and beginDate >= CURDATE()");
			   pst.setInt(1, cID);
			   ResultSet r = pst.executeQuery();
			   rs = r;
			   }
			   catch (SQLException exc) {
					System.out.println("An error occured. Error: " + exc.getMessage());
		   }
		 return rs;
	}
	
	public int cancelReservations(int cID, int resID)   {
		 try {
	
			   PreparedStatement pst = 
					   myConn.prepareStatement("delete from RESERVATION where resID = ? and cID = ?");
			   pst.setInt(1, resID);
			   pst.setInt(2, cID);
			   int num = pst.executeUpdate();
			   return num;
			   }
			   catch (SQLException exc) {
					System.out.println("An error occured. Error: " + exc.getMessage());
					return 0;
		   }
	}
	
	public int deleteRoom(int rID) {
		try {
			PreparedStatement pst = myConn.prepareStatement("DELETE FROM ROOM WHERE rID=?");
			pst.setInt(1, rID);
			int res = pst.executeUpdate();
			return res;
		} catch (SQLException exc) {
			exc.printStackTrace();
			return 0;
		}
	}
	
	public void showMostPopularRooms()   {
		
	}
	
	public void updateRoomPrice(int rID, int price) {
		try {
			PreparedStatement pst = myConn.prepareStatement(
					"UPDATE ROOMTYPE SET price=? WHERE ROOMTYPE.roomType = (SELECT roomType FROM ROOM WHERE rID=?)");
			pst.setInt(1, price);
			pst.setInt(2, rID);
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public void rateRoom(int cID, int rID, int stars) {
		try {
			PreparedStatement pst = myConn.prepareStatement("INSERT INTO RATING (cID, rID, stars, ratingDate) VALUES(?,?,?,?)");
			pst.setInt(1, cID);
			pst.setInt(2, rID);
			pst.setInt(3, stars);
			pst.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			pst.executeUpdate();
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}

	public ResultSet getReservedRooms(int cID) {
		ResultSet rs = null;
		try {
			PreparedStatement pst = myConn.prepareStatement("SELECT distinct rID FROM RESERVATION WHERE cID = ? group by rID");
			pst.setInt(1, cID);
			ResultSet r = pst.executeQuery();
			rs = r;
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return rs;
	}
	
	// Return the name and credit card number of the customers who made and cancelled reservations
	// at least three times in the last month. (let’s charge them extra money!)
	public ResultSet getUnreliableCustomers() {
		ResultSet rs = null;
		try {
			PreparedStatement pst = myConn.prepareStatement(
					"select uNAME, CreditCard from CUSTOMER natural join CANCELLATION"
					+ "where cancellationDate between NOW() - INTERVAL 30 DAY and NOW()" 
					+ "group by cID having count(*)>=3");
			ResultSet r = pst.executeQuery();
			  rs = r;
		} catch (SQLException exc) {
			System.out.println("An error occured. Error: " + exc.getMessage());
		}
		return rs;
	}
	
}
