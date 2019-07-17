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
				// depricated
				// Class.forName("com.mysql.jdbc.Driver");
				myConn = DriverManager.getConnection(DatabaseCredentials.URL, DatabaseCredentials.USER,
						DatabaseCredentials.PASSWORD);
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}

	public int validateLogin(String uName, String password, String position) {
		try {
			PreparedStatement pst = myConn.prepareStatement("select * from " + position + " where uNAME=? and pWORD=?");
			pst.setString(1, uName);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (position.equals("customer")) {
				if (rs.next()) {
					int cID = rs.getInt("cID");
					return cID;
				}
			} else if (position.equals("manager")) {
				if (rs.next()) {
					return 1;
				}
			}
		} catch (SQLException exc) {
			System.out.println("An error occured. Error: " + exc.getMessage());
		}
		return 0;
	}

	public void addManager(String uName, String password) {
		try {
			PreparedStatement pst = myConn.prepareStatement("INSERT INTO MANAGER (uNAME, pWORD) values(?, ?)");
			pst.setString(1, uName);
			pst.setString(2, password);
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}

	public void deleteManager(String uName) {
		try {
			PreparedStatement pst = myConn.prepareStatement("DELETE FROM MANAGER WHERE uNAME=?");
			pst.setString(1, uName);
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}

	public void addRoom(String roomType) {
		try {
			PreparedStatement pst = myConn.prepareStatement("INSERT INTO ROOM (roomType, numRented) values(?, 0)");
			pst.setString(1, roomType);
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}

	public int createAccount(String uName, String pass, int card) {
		try {
			PreparedStatement pst = myConn
					.prepareStatement("insert into CUSTOMER (uNAME, pWORD, CreditCard) values(?,?,?)");
			pst.setString(1, uName);
			pst.setString(2, pass);
			pst.setInt(3, card);
			pst.executeUpdate();
			return 1;
		} catch (SQLException exc) {
			int err = exc.getErrorCode();
			return err;
		}
	}

	public ResultSet getAvailableRooms(Date in, Date out, int price) {
		ResultSet rs = null;
		try {
			// PreparedStatement pst =
			// myConn.prepareStatement("select rID, ROOMTYPE.roomType, price, stars"
			// + " from ROOMTYPE natural join ROOM natural join RATING where price<?"
			// + "and ROOM.rID in (select rID from RESERVATION where (beginDate>? and
			// beginDate>?)"
			// + "or (endDate<? and endDate<?))");

			PreparedStatement pst = myConn.prepareStatement("select rID, ROOMTYPE.roomType, price, avg(stars) as stars"
					+ " from (ROOMTYPE natural join ROOM) left outer join RATING using(rID) where price<?"
					+ " and rID not in (select rID from RESERVATION where (? between beginDate and endDate) "
					+ "or (? between beginDate and endDate) or (? >= beginDate and ? <= endDate))" + "group by rID");
			pst.setDouble(1, price);
			pst.setDate(2, in);
			pst.setDate(3, out);
			pst.setDate(4, in);
			pst.setDate(5, out);
			ResultSet r = pst.executeQuery();
			rs = r;
		} catch (SQLException exc) {
			System.out.println("An error occured. Error: " + exc.getMessage());
		}
		return rs;
	}

	public void insertReservation(int rID, Date in, Date out, int cID) {
		try {
			PreparedStatement pst = myConn
					.prepareStatement("insert into RESERVATION (cID, rID, beginDate, endDate) values(?,?,?,?)");
			pst.setInt(1, cID);
			pst.setInt(2, rID);
			pst.setDate(3, in);
			pst.setDate(4, out);

			pst.executeUpdate();
		} catch (SQLException exc) {
			System.out.println("An error occured. Error: " + exc.getMessage());
		}
	}

	public void deleteRoom(int rID) {
		try {
			PreparedStatement pst = myConn.prepareStatement("DELETE FROM ROOM WHERE rID=?");
			pst.setInt(1, rID);
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}

	public void showMostPopularRooms() {

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

}
