package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	public static Connection getJDBCConnection(String sever, String port ,String user, String password, String database) {
		final String url = "jdbc:sqlserver://" + sever 
				           + ":" + port 
		                   + ";user=" + user
				           + ";password=" + password 
				           + ";databaseName=" + database 
				           + ";encrypt=false";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			return DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}

