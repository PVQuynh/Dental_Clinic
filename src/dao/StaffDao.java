package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.Account;
import model.Staff;

public class StaffDao implements DaoInterface<Staff> {

	public static StaffDao getIntance() {
		return new StaffDao();
	}

	@Override
	public int insert(Staff t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "INSERT INTO Staff (fullName, gender, dateOfBirth, email, phoneNumber, address, workingStatus, accountId) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getFullName());
			pst.setString(2, t.getGender());
			pst.setDate(3, t.getDateOfBirth());
			pst.setString(4, t.getEmail());
			pst.setString(5, t.getPhoneNumber());
			pst.setString(6, t.getAddress());
			pst.setString(7, t.getWorkingStatus());

			Account insertAccount = new Account();
			insertAccount.setEmail(t.getEmail());
			insertAccount.setPassword(t.getEmail());
			insertAccount.setRoleId(2);
			AccountDao.getInstance().insert(insertAccount);
			
			Account searchAccountId = AccountDao.getInstance().selectByEmail(insertAccount);

			pst.setInt(8, searchAccountId.getAccountId());
			
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
	public int update(Staff t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "UPDATE Staff SET fullName = ?, gender = ?, dateOfBirth = ?, phoneNumber = ?, address = ?, workingStatus = ? WHERE staffId = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getFullName());
			pst.setString(2, t.getGender());
			pst.setDate(3, t.getDateOfBirth());
			pst.setString(4, t.getPhoneNumber());
			pst.setString(5, t.getAddress());
			pst.setString(6, t.getWorkingStatus());
			pst.setInt(7, t.getStaffId());

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
	public int delete(Staff t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "DELETE FROM Staff WHERE staffId = ?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getStaffId());

			// Bước 3: Thực thi câu lệnh SQL
			result = pst.executeUpdate();

			// Bước 4: Thực thi statement
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " đã được xóa");

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Staff> selectAll() {
	    ArrayList<Staff> result = new ArrayList<Staff>();
	    try {
	        // Bước 1: Tạo kết nối với CSDL
	        Connection con = JDBCUtil.getConnection();

	        // Bước 2: Tạo ra đối tượng statement
	        Statement st = con.createStatement();

	        // Bước 3: Thực thi câu lệnh SQL
	        String sql = "SELECT * FROM Staff;";
	        
	        ResultSet rs = st.executeQuery(sql);

	        // Bước 4: Thực thi statement
	        while (rs.next()) { // duyệt qua từng dòng trong rs
	            int staffId = rs.getInt("staffId");
	            String fullName = rs.getString("fullName");
	            String gender = rs.getString("gender");
	            Date dateOfBirth = rs.getDate("dateOfBirth");
	            String email = rs.getString("email");
	            String phoneNumber = rs.getString("phoneNumber");
	            String address = rs.getString("address");
	            String workingStatus = rs.getString("workingStatus");
	            int accountId = rs.getInt("accountId");

	            Staff staff = new Staff(staffId, fullName, gender, dateOfBirth, email, phoneNumber, address, workingStatus, accountId);
	            result.add(staff);
	        }

	        // Bước 5: Ngắt kết nối
	        JDBCUtil.closeConnection(con);
	    } catch (Exception e) {
	        // Xử lý exception
	        e.printStackTrace();
	    }
	    return result;
	}

	@Override
	public Staff selectById(Staff t) {
		Staff result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Staff WHERE staffId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getStaffId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int staffId = rs.getInt("staffId");
				String fullName = rs.getString("fullName");
				String gender = rs.getString("gender");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phoneNumber");
				String address = rs.getString("address");
				String workingStatus = rs.getString("workingStatus");
				int accountId = rs.getInt("accountId");

				result = new Staff(staffId, fullName, gender, dateOfBirth, email, phoneNumber, address, workingStatus, accountId );
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public Staff selectByAccountId(Staff t) {
		Staff result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Staff WHERE accountId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getAccountId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int staffId = rs.getInt("staffId");
				String fullName = rs.getString("fullName");
				String gender = rs.getString("gender");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phoneNumber");
				String address = rs.getString("address");
				String workingStatus = rs.getString("workingStatus");
				int accountId = rs.getInt("accountId");

				// Write method to get list ...
				result = new Staff(staffId, fullName, gender, dateOfBirth, email, phoneNumber, address, workingStatus, accountId);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public int totalRows() {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT COUNT(*) AS total_rows FROM Staff";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();

			// Bước 4: Thực thi statement
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " đã được thêm vào");

			while(rs.next()) {
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
