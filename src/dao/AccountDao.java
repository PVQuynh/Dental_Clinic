package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Account;

public class AccountDao implements DaoInterface<Account> {

	public static AccountDao getInstance() {
		return new AccountDao();
	}

	@Override
	public int insert(Account t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "INSERT INTO Account (email, password, roleId)" + " VALUES (?, ?, ?);";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getEmail());
			pst.setString(2, t.getPassword());
			pst.setInt(3, t.getRoleId());

			// Bước 3: Thực thi câu lệnh SQL
			result = pst.executeUpdate();

			// Bước 4: Thực thi statement
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " đã được thêm vào");

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Account t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "UPDATE Account SET password = ? WHERE accountId = ?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getPassword());
			pst.setInt(2, t.getAccountId());

			// Bước 3: Thực thi câu lệnh SQL
			result = pst.executeUpdate();

			// Bước 4: Thực thi statement
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " đã được thêm vào");

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Account t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "DELETE FROM Account WHERE accountId = ?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getAccountId());

			// Bước 3: Thực thi câu lệnh SQL
			result = pst.executeUpdate();

			// Bước 4: Thực thi statement
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " đã xóa");

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Account> selectAll() {
		ArrayList<Account> result = new ArrayList<Account>();
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			Statement st = con.createStatement();

			// Bước 3: Thực thi câu lệnh SQL
			String sql = "SELECT * FROM Account";
			System.out.println(sql);

			ResultSet rs = st.executeQuery(sql);
			System.out.println(rs);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int accountId = rs.getInt("accountId");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int roleId = rs.getInt("roleId");
				Timestamp accountCreationTime = rs.getTimestamp("accountCreationTime");

				Account account = new Account(accountId, email, password, roleId, accountCreationTime);
				result.add(account);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Account selectById(Account t) {
		Account result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Account WHERE accountId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getAccountId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int accountId = rs.getInt("accountId");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int roleId = rs.getInt("roleId");
				Timestamp accountCreationTime = rs.getTimestamp("accountCreationTime");

				result = new Account(accountId, email, password, roleId, accountCreationTime);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public Account selectByEmailAndPassword(Account t) {
		Account result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Account WHERE email = ? AND password = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getEmail());
			pst.setString(2, t.getPassword());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int accountId = rs.getInt("accountId");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int roleId = rs.getInt("roleId");
				Timestamp accountCreationTime = rs.getTimestamp("accountCreationTime");

				result = new Account(accountId, email, password, roleId, accountCreationTime);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public Account selectByEmail(Account t) {
		Account result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Account WHERE email = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getEmail());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int accountId = rs.getInt("accountId");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int roleId = rs.getInt("roleId");
				Timestamp accountCreationTime = rs.getTimestamp("accountCreationTime");

				result = new Account(accountId, email, password, roleId, accountCreationTime);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int totalRows() {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT COUNT(*) AS total_rows FROM Account";

			PreparedStatement pst = con.prepareStatement(sql);

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// Bước 4: Thực thi statement
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " đã được thêm vào");

			while (rs.next()) {
				int rowCount = rs.getInt("total_rows");

				result = rowCount;
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
