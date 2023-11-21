package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Role;

public class RoleDao implements DaoInterface<Role> {

	public static RoleDao getInstance() {
		return new RoleDao();
	}

	@Override
	public int insert(Role t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "INSERT INTO Role (role)" + " VALUES (?);";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getRole());

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
	public int update(Role t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "UPDATE Role SET role = ? WHERE roleId = ?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getRole());
			pst.setInt(2, t.getRoleId());

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
	public int delete(Role t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "DELETE FROM Role WHERE roleId = ?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(2, t.getRoleId());

			// Bước 3: Thực thi câu lệnh SQL
			result = pst.executeUpdate();

			// Bước 4: Thực thi statement
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result +" đã xóa");

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Role> selectAll() {
		ArrayList<Role> result = new ArrayList<Role>();
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			Statement st = con.createStatement();

			// Bước 3: Thực thi câu lệnh SQL
			String sql = "SELECT * FROM Role";
			System.out.println(sql);

			ResultSet rs = st.executeQuery(sql);
			System.out.println(rs);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int roleId = rs.getInt("roleId");
				String role = rs.getString("role");
				
				Role account = new Role(roleId, role);
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
	public Role selectById(Role t) {
		Role result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Role WHERE roleId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getRoleId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int roleId = rs.getInt("roleId");
				String role = rs.getString("role");
				

				result = new Role(roleId, role);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String> selectRoles() {
		ArrayList<String> result = new ArrayList<>();
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			Statement st = con.createStatement();

			// Bước 3: Thực thi câu lệnh SQL
			String sql = "SELECT * FROM Role";
			System.out.println(sql);

			ResultSet rs = st.executeQuery(sql);
			System.out.println(rs);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				String role = rs.getString("role");
				
				result.add(role);
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
		// TODO Auto-generated method stub
		return 0;
	}

}
