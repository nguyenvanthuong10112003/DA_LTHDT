package libary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
	private static String severName = "MSI";
	private static String userName = "sa";
	private static String password = "a";

	public static Connection getJDBCConnection(String database) throws ClassNotFoundException {
		Connection connect = null;
		final String url = "jdbc:sqlserver://" + severName + ";databaseName=" + database + ";user=" + userName
				+ ";password=" + password + ";integratedSecurity=false;trustServerCertificate=true;";
//				"jdbc:sqlserver://localhost:1433;" +
//				"databaseName="+ database + ";integratedSecurity=true;" +
//				"encrypt=true;trustServerCertificate=true";
		try {
			connect = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (connect != null)
			return connect;
		return null;

	}

}
