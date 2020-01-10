package facility;


import javax.annotation.*;
import javax.enterprise.context.*;
import javax.inject.*;

import java.util.Properties;
import java.util.Vector;

import java.sql.*;
import java.net.URI;

import java.net.URISyntaxException;


@Named
@ApplicationScoped
public class AccessDB {

	private int count = 0;

	private Connection cn;

	private String floorTableSubName = "floorDescription";
	private String coordinatesTableSubName = "floorCoordinates";

	private String floorNumbersTableName = "floorNumbersList";
	private String stairNumbersTableName = "stairNumbersList";

	//for FloorDescription class
	public Integer getNumberVertexOfSubjectFloor(Integer floorNumber, String subject) {

		String tableName = floorTableSubName + floorNumber;

		String query = "select vertex from " + tableName + " where subject='" + subject + "'";

		Integer result = null;

		Statement st = null;

		ResultSet rs = null;

		try { 
			
			st = cn.createStatement();

			rs = st.executeQuery(query);
				
			if (rs.next()) result = rs.getInt(1);


			} catch (SQLException e) { 

				System.err.println("Ошибка при чтении из БД");
		}

		try {

			if (rs != null) rs.close();
			if (st != null) st.close();
		} catch (SQLException e) {}


		return result;

		}

	public Integer getFloorNumberOfSubject(String subject) {

		/*
		получить номера этажей из БД
		*/
		String tableName = floorNumbersTableName;

		String query = "select * from " + tableName;

		Statement st = null;

		ResultSet rs = null;

		Vector<Integer> floorNumbersList = new Vector<Integer>();

		try { 
			
			st = cn.createStatement();

			rs = st.executeQuery(query);
			
			while (rs.next()) { floorNumbersList.add(rs.getInt(1));}

			for (int floorNumber : floorNumbersList) {

				tableName = floorTableSubName + floorNumber;

				query = "select vertex from " + tableName + " where subject='" + subject + "'";

				rs = st.executeQuery(query);

				if (rs.next()) return floorNumber;

			}

			} catch (SQLException e) { 

				System.err.println("Ошибка при чтении из БД");
			}

			try {

			if (rs != null) rs.close();
			if (st != null) st.close();

		} catch (SQLException e) {}

			return null;
		}		

	public String[] getNumberStairs(int floorNumber) {

		String tableName = stairNumbersTableName;

		String query = "select stairNumbers from " + tableName + " where floorNumber=" + floorNumber;

		String stairNumbersList = "";

		Statement st = null;

		ResultSet rs = null;
		
		try { 
			
			st = cn.createStatement();

			rs = st.executeQuery(query);

			if (rs.next()) stairNumbersList = rs.getString(1);

			} catch (SQLException e) { 

				System.err.println("Ошибка при чтении из БД");
			}

		try {

			if (rs != null) rs.close();
			if (st != null) st.close();

		} catch (SQLException e) {}

		return stairNumbersList.split(",");
	}

	//for Coordinates class
	public double[] getVertex(int floorNumber, int vertexNumber) {

		String tableName = coordinatesTableSubName + floorNumber;

		String query = "select coordX,coordY from " + tableName + " where vertex=" + vertexNumber;

		double[] result = new double[2];

		Statement st = null;

		ResultSet rs = null;

		try { 
			
			st = cn.createStatement();

			rs = st.executeQuery(query);

			rs.next();

			result[0] = rs.getDouble(1);
			result[1] = rs.getDouble(2);

		} catch (SQLException e) { 

			System.err.println("Ошибка при чтении из БД");
		}

		try {

			if (rs != null) rs.close();
			if (st != null) st.close();

		} catch (SQLException e) {}

		return result;
	}

	private static Connection getConnection() throws URISyntaxException, SQLException {
    URI jdbUri = new URI(System.getenv("JAWSDB_URL"));

    String username = jdbUri.getUserInfo().split(":")[0];
    String password = jdbUri.getUserInfo().split(":")[1];
    String port = String.valueOf(jdbUri.getPort());
    String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();

    return DriverManager.getConnection(jdbUrl, username, password);
}
	

	@PostConstruct
	private void setConnection() {

		try {

			cn = getConnection();

		} catch (Exception  e) {

			System.err.println(e);
		}
		/*
		установить соединение с бд
		
		String url = "jdbc:mysql://localhost:3306/facility";
		Properties prop = new Properties();

		prop.put("user","root");
		prop.put("password","pass");
		prop.put("autoReconnect","true");
		prop.put("characterEncoding","utf8");
		prop.put("useUnicode","true");
		prop.put("serverTimezone", "UTC");
		prop.put("useSSL", "false");

		try {

			DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			cn = DriverManager.getConnection(url,prop);

		} catch (SQLException e) {
			System.err.println(e);
		}

		*/
	}

	@PreDestroy
	private void closeConnection() {

		if (cn != null) {

			try { 

				cn.close();

			} catch (SQLException e) {}

		}
	}

}