package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class JDBCUtil {

	public static Connection getConnection() {
		Connection c = null;

		try {
			// B1: Tạo driver để thiết lập kết nối

			Driver driver = new Driver();

			// Đăng ký driver với DriverManager và cung cấp kết nối tới MySQL
			DriverManager.registerDriver(driver);

			// B2: Thông tin đăng ký
			String url = "jdbc:MySQL://localhost:3306/DentalClinic";
			String username = "root";
			String password = "200123";

			// B3: Kết nối được tạo ra
			c = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}

	public static void closeConnection(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void prinInfo(Connection c) {
		try {
			if (c != null) {
				DatabaseMetaData mtdt = c.getMetaData();
				System.out.println(mtdt.getDatabaseProductName());
				System.out.println(mtdt.getDatabaseProductVersion());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
