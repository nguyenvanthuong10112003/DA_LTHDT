package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
	public static Connection getJDBCConnection(String database) {
		final String url = "jdbc:sqlserver://localhost:1433;" +
				"databaseName="+ database + ";integratedSecurity=true;" +
				"encrypt=true;trustServerCertificate=true";
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
