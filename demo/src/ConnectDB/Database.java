package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	public static Connection con =  null;
	public static Database instance = new Database();
	public static Database getInstance () {
		return instance;
	}
	public void connect() {
		String url = "jdbc:sqlserver://localhost:1433;databasename=QLLop";
		String user = "sa";
		String password = "sapassword";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return con;
	}
	public void disconnect() {
		if(con!=null)
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
}
