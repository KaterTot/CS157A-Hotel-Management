import java.sql.CallableStatement;
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
	
	public void deleteAccount(int cID) {
		try {
			PreparedStatement pst = myConn.prepareStatement("DELETE FROM CUSTOMER WHERE cID=?");
			pst.setInt(1, cID);
			pst.executeUpdate();
		} catch (SQLException exc) {
			exc.printStackTrace();
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
			   PreparedStatement pst = 
					   myConn.prepareStatement("select rID, ROOMTYPE.roomType, price, avg(CAST(stars AS DOUBLE)) stars"
					   		+ " from ROOMTYPE natural join ROOM left outer join RATING using(rID) where price<?"
					   		+ " and rID not in ("
					   			+ "select rID from RESERVATION where (? between beginDate and endDate) "
					   			+ "UNION "
					   			+ "select rID from RESERVATION where (? between beginDate and endDate) "
					   			+ "UNION "
					   			+ "select rID from RESERVATION where (? >= beginDate and ? <= endDate)"
					   		+ ") "
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
	
	//# 26
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
	
	// # 14
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
	
	// # 17
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
	
	// # 19
	public int updateRoomPrice(int rID, int price) {
		try {
			PreparedStatement pst = myConn.prepareStatement(
					"UPDATE ROOMTYPE SET price=? WHERE ROOMTYPE.roomType = (SELECT roomType FROM ROOM WHERE rID=?)");
			pst.setInt(1, price);
			pst.setInt(2, rID);
			int res = pst.executeUpdate();
			return res;
		} catch (SQLException exc) {
			exc.printStackTrace();
			return 0;
		}
	}
	
	// # 15
	public void rateRoom(int cID, int rID, int stars) {
		try {
			PreparedStatement pst = myConn.prepareStatement("INSERT INTO RATING (cID, rID, stars, ratingDate) VALUES(?,?,?,?)");
			pst.setInt(1, cID);
			pst.setInt(2, rID);
			pst.setInt(3, stars);
			pst.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
			pst.executeUpdate();
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}

	// # 13
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
	// # 5
	public ResultSet getUnreliableCustomers() {
		ResultSet rs = null;
		try {
			PreparedStatement pst = myConn.prepareStatement(
					"select uNAME, CreditCard from CUSTOMER natural join CANCELLATION"
					+ " where cancellationDate between NOW() - INTERVAL 30 DAY and NOW()" 
					+ " group by cID having count(*)>=3");
			ResultSet r = pst.executeQuery();
			  rs = r;
		} catch (SQLException exc) {
			System.out.println("An error occured. Error: " + exc.getMessage());
		}
		return rs;
	}
	
	// no function in the report for this!
	public ResultSet showRoomsAndPrices()
	{
		ResultSet rs = null;
		try {
			PreparedStatement pst = myConn.prepareStatement(
					"select rID, roomType, price, numBeds, numRented, price FROM room natural join roomtype"
					+ " where room.roomType = roomType.roomType" 
					+ " group by rID");
			ResultSet r = pst.executeQuery();
			  rs = r;
		} catch (SQLException exc) {
			System.out.println("An error occured. Error: " + exc.getMessage());
		}
		return rs;
	}
	
	// no function in the report for this! 
	public int archiveReservations(Date date) {
		try {
			CallableStatement cs = myConn.prepareCall("{CALL archiveReservation(?)}");
			cs.setDate(1, date);
			boolean hasResult = cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
			return 1;
		}
	
	// no function in the report for this! 
	public ResultSet getArchivedReservations()   {
			ResultSet rs = null;
			try {
				PreparedStatement pst = myConn.prepareStatement(
						"select * from ARCHIVE");
				ResultSet r = pst.executeQuery();
				  rs = r;
		      }
			catch (SQLException exc) {
				System.out.println("An error occured. Error: " + exc.getMessage());
			}
			return rs;
	}
	
	// # 1
	public ResultSet bestDeals(int cID)   {
			ResultSet rs = null;
			try {
				PreparedStatement pst = myConn.prepareStatement(
						"SELECT R.rID, st " + 
						"FROM (SELECT AVG(cast(stars as DOUBLE)) st, rID, price FROM RATING natural join ROOM natural join ROOMTYPE GROUP BY rID) R " + 
						"WHERE R.st > all(SELECT AVG(stars) " + 
						            "FROM RATING natural join ROOM natural join ROOMTYPE " + 
						            "WHERE price > R.price " + 
						            "GROUP BY rID)");
				ResultSet r = pst.executeQuery();
				  rs = r;
		      }
			catch (SQLException exc) {
				System.out.println("An error occured. Error: " + exc.getMessage());
			}
			return rs;
		}

	// # 24
	public ResultSet lowestAvgRating()
	{
		ResultSet rs = null;
		try {
			PreparedStatement pst = myConn.prepareStatement(
					"SELECT rID, AVG(cast(stars as DOUBLE)) st FROM room NATURAL JOIN rating WHERE rID NOT IN"
					+ " (SELECT rID FROM reservation)"
					+ " GROUP BY rID ORDER BY st ASC");
			ResultSet r = pst.executeQuery();
			  rs = r;
	      }
		catch (SQLException exc) {
			System.out.println("An error occured. Error: " + exc.getMessage());
		}
		return rs;
	}
	
	// # 2
	public ResultSet highestAvgRating()
	{
		ResultSet rs = null;
		try {
			PreparedStatement pst = myConn.prepareStatement(
					"SELECT rID, roomType, AVG(cast(stars as DOUBLE)) avg"
					+ " FROM room INNER JOIN rating USING(rID)"
					+ " GROUP BY rID HAVING avg ="
					+ " (SELECT MAX(ave) FROM"
					+ " (SELECT rID, AVG(stars) as ave FROM rating GROUP BY rID) Temp)");
			ResultSet r = pst.executeQuery();
			  rs = r;
	      }
		catch (SQLException exc) {
			System.out.println("An error occured. Error: " + exc.getMessage());
		}
		return rs;
	}
	
	// #21
	public ResultSet getRatings()
	{
		ResultSet rs = null;
		try {
			PreparedStatement pst = myConn.prepareStatement(
					"select distinct cID, rID, stars "
					  + "from (select * from customer natural join rating) CR "
					  + "where stars = (select max(stars) "
					                 + "from rating where rID=CR.rID "
					                 + " group by rID) "
					                 + "order by rID");
			ResultSet r = pst.executeQuery();
			  rs = r;
	      }
		catch (SQLException exc) {
			System.out.println("An error occured. Error: " + exc.getMessage());
		}
		return rs;
	}
	
	// # 18
	public ResultSet showMostRented()
	{
		ResultSet rs = null;
		try {
			PreparedStatement pst = myConn.prepareStatement(
						"SELECT rID, roomType, cast(count(*) as INTEGER) c"
						+ " FROM reservation INNER JOIN room USING(rID)"
						+ " WHERE beginDate > DATE_SUB(CURDATE(), INTERVAL 30 DAY) and endDate < CURDATE()"
						+ " group by rID"
						+ " ORDER BY c DESC");
			ResultSet r = pst.executeQuery();
			  rs = r;
	      }
		catch (SQLException exc) {
			System.out.println("An error occured. Error: " + exc.getMessage());
		}
		return rs;
	}
	
	// # 4
	public ResultSet showFreqCust(int days)
	{
		ResultSet rs = null;
		try {
			PreparedStatement pst = myConn.prepareStatement(
						"SELECT distinct cID FROM customer INNER JOIN reservation USING(cID)"
						+ " WHERE beginDate > DATE_SUB(CURDATE(), INTERVAL ? DAY) and endDate < CURDATE() "
								+ "group by cID having count(*) >= 2");
			pst.setInt(1, days);
			ResultSet r = pst.executeQuery();
			  rs = r;
	      }
		catch (SQLException exc) {
			System.out.println("An error occured. Error: " + exc.getMessage());
		}
		return rs;
	}
	
	// # 23
	public ResultSet showPopularRooms() {
		ResultSet rs = null;
		try {
			PreparedStatement pst = myConn
					.prepareStatement("SELECT rID, roomType, numRented " + 
							"FROM ROOM room1 " + 
							"WHERE numRented > (SELECT avg(numRented) FROM ROOM WHERE roomType = room1.roomType)");
			ResultSet r = pst.executeQuery();
			rs = r;
		} catch (SQLException exc) {
			System.out.println("An error occured. Error: " + exc.getMessage());
		}
		return rs;
	}
	
	public int changeReservationDates(Date in, Date out, int resID)   {
			int result = 0;
			 try { 
				   PreparedStatement st = myConn.prepareStatement("SELECT rID FROM RESERVATION WHERE resID=?");
				   st.setInt(1, resID);
				   ResultSet res = st.executeQuery();
				   if(!res.next())  {
					   return 0;
				   }
				   int rID = res.getInt("rID");
				   PreparedStatement pst = 
						   myConn.prepareStatement("select rID, ROOMTYPE.roomType, price, avg(cast(stars AS DOUBLE)) stars"
						   		+ " from ROOMTYPE natural join ROOM left outer join RATING using(rID) where rID=?"
						   		+ " and rID not in (select rID from RESERVATION where (? between beginDate and endDate) "
						   		+ "or (? between beginDate and endDate) or (? >= beginDate and ? <= endDate))"
						   		+ "group by rID");
				 
				   pst.setInt(1, rID);
				   pst.setDate(2, in);
				   pst.setDate(3, out);
				   pst.setDate(4, in);
				   pst.setDate(5, out);
				   ResultSet r = pst.executeQuery();
				   if(!r.next())  {
					   return 0;
				      }
				   PreparedStatement stat = myConn.prepareStatement("UPDATE RESERVATION SET beginDate=?, endDate=?, updateAt=? WHERE resID=?");
				   stat.setDate(1, in);
				   stat.setDate(2, out);
				   stat.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				   stat.setInt(4, resID);
				   int upd = stat.executeUpdate();
				   result = upd;
				   }
				   catch (SQLException exc) {
						System.out.println("An error occured. Error: " + exc.getMessage());
			   }
			 return result;
		}
	
	public ResultSet roomsToAvoid()     {
			ResultSet rs = null;
			try {
				PreparedStatement pst = myConn
						.prepareStatement("SELECT R.rID, RT.roomType, RAT.stars " + 
								"FROM ROOM R, ROOMTYPE RT, RATING RAT " + 
								"WHERE R.roomType = RT.roomType and R.rID = RAT.rID and " + 
								"stars = any (SELECT MIN(stars)" + 
								                   " FROM RATING" + 
								                   " GROUP BY rID) and stars < 3" );
				ResultSet r = pst.executeQuery();
				rs = r;
			} catch (SQLException exc) {
				System.out.println("An error occured. Error: " + exc.getMessage());
			}
			return rs;
		}
}
