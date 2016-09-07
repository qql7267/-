package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnFactory {
	public static  Connection getConn(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
			Connection conn = DriverManager.getConnection(url, "scott", "tiger");
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
