package libary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
	private static String severName = "MSI";
	private static String userName = "sa";
	private static String password = "a";
	private static String database = "ADMIN";
	public static Connection getJDBCConnection() throws ClassNotFoundException {
		Connection connect = null;
		final String url =
                "jdbc:sqlserver://" + severName + ":1433;"
                        + "database=" + ConnectSQL.database + ";"
                        + "user=" + userName + ";"
                        + "password=" + password + ";"
                        + "encrypt=false;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";
		try {
			connect = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (connect != null)
			return connect;
		return null;

	}

}
