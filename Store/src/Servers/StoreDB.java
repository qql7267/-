package Servers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StoreDB {
	private Connection conn = null;

	public Connection connOracle() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String password = "tiger";
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeOracle() {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
