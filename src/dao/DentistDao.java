package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.JDBCUtil;
import model.Account;
import model.Dentist;

public class DentistDao implements DaoInterface<Dentist> {

	public static DentistDao getIntance() {
		return new DentistDao();
	}

	@Override
	public int insert(Dentist t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "INSERT INTO Dentist (fullName, gender, dateOfBirth, email, phoneNumber, address, workingStatus, accountId) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
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
			insertAccount.setRoleId(3);
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
	public int update(Dentist t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "UPDATE Dentist SET fullName = ?, gender = ?, dateOfBirth = ?, phoneNumber = ?, address = ?, workingStatus = ? WHERE dentistId = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getFullName());
			pst.setString(2, t.getGender());
			pst.setDate(3, t.getDateOfBirth());
//			pst.setString(4, t.getEmail());
			pst.setString(4, t.getPhoneNumber());
			pst.setString(5, t.getAddress());
			pst.setString(6, t.getWorkingStatus());
			pst.setInt(7, t.getDentistId());

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
	public int delete(Dentist t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "DELETE FROM Dentist WHERE dentistId = ?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getDentistId());

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
	public ArrayList<Dentist> selectAll() {
	    ArrayList<Dentist> result = new ArrayList<Dentist>();
	    try {
	        // Bước 1: Tạo kết nối với CSDL
	        Connection con = JDBCUtil.getConnection();

	        // Bước 2: Tạo ra đối tượng statement
	        Statement st1 = con.createStatement();
	        Statement st2 = con.createStatement();
	        Statement st3 = con.createStatement();

	        // Bước 3: Thực thi câu lệnh SQL
	        String sql1 = "SELECT * FROM Dentist;";
	        String sql2 = "SELECT * FROM Dentist AS d LEFT JOIN Appointment AS a ON d.dentistId = a.dentistId;";
	        String sql3 = "SELECT * FROM Dentist AS d LEFT JOIN Prescription AS p ON d.dentistId = p.dentistId;";
	        
	        ResultSet rs1 = st1.executeQuery(sql1);

	        // Bước 4: Thực thi statement
	        while (rs1.next()) { // duyệt qua từng dòng trong rs1
	            int dentistId = rs1.getInt("dentistId");
	            String fullName = rs1.getString("fullName");
	            String gender = rs1.getString("gender");
	            Date dateOfBirth = rs1.getDate("dateOfBirth");
	            String email = rs1.getString("email");
	            String phoneNumber = rs1.getString("phoneNumber");
	            String address = rs1.getString("address");
	            String workingStatus = rs1.getString("workingStatus");
	            int accountId = rs1.getInt("accountId");

	            List<Integer> appointmentIds = new ArrayList<>();
	            List<Integer> prescriptionIds = new ArrayList<>();

	            ResultSet rs2 = st2.executeQuery(sql2);
	            while (rs2.next()) {
	            	int dentistId2 = rs2.getInt("dentistId");
	                int appointmentId = rs2.getInt("appointmentId");

	                if (dentistId2 == dentistId) {
	                	appointmentIds.add(appointmentId);
	                }
	            }
	            
	            ResultSet rs3 = st3.executeQuery(sql3);
	            while (rs3.next()) {
	            	int dentistId3 = rs3.getInt("dentistId");
	                int prescriptionId = rs3.getInt("prescriptionId");

	                if (dentistId3 == dentistId) {
	                	prescriptionIds.add(prescriptionId);
	                }
	            }

	            Dentist dentist = new Dentist(dentistId, fullName, gender, dateOfBirth, email, phoneNumber, address, workingStatus, accountId, appointmentIds, prescriptionIds);
	            result.add(dentist);
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
	public Dentist selectById(Dentist t) {
		Dentist result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Dentist WHERE dentistId = ?";
			String sql2 = "SELECT * FROM Appointment WHERE dentistId = ?";
			String sql3 = "SELECT * FROM Prescription WHERE dentistId = ?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getDentistId());
			
			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setInt(1, t.getDentistId());
			
			PreparedStatement pst3 = con.prepareStatement(sql3);
			pst3.setInt(1, t.getDentistId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int dentistId = rs.getInt("dentistId");
				String fullName = rs.getString("fullName");
				String gender = rs.getString("gender");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phoneNumber");
				String address = rs.getString("address");
				String workingStatus = rs.getString("workingStatus");
				int accountId = rs.getInt("accountId");
				
				List<Integer> appointmentIds = new ArrayList<>();
	            List<Integer> prescriptionIds = new ArrayList<>();

	            ResultSet rs2 = pst2.executeQuery();
	            while (rs2.next()) {
	            	int dentistId2 = rs2.getInt("dentistId");
	                int appointmentId = rs2.getInt("appointmentId");

	                if (dentistId2 == dentistId) {
	                	appointmentIds.add(appointmentId);
	                }
	            }
	            
	            ResultSet rs3 = pst3.executeQuery();
	            while (rs3.next()) {
	            	int dentistId3 = rs3.getInt("dentistId");
	                int prescriptionId = rs3.getInt("prescriptionId");

	                if (dentistId3 == dentistId) {
	                	prescriptionIds.add(prescriptionId);
	                }
	            }

				result = new Dentist(dentistId, fullName, gender, dateOfBirth, email, phoneNumber, address, workingStatus, accountId, appointmentIds, prescriptionIds);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public Dentist selectByAccountId(Dentist t) {
		Dentist result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Dentist WHERE accountId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getAccountId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int dentistId = rs.getInt("dentistId");
				String fullName = rs.getString("fullName");
				String gender = rs.getString("gender");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phoneNumber");
				String address = rs.getString("address");
				String workingStatus = rs.getString("workingStatus");
				int accountId = rs.getInt("accountId");

				// Write method to get list ...
				result = new Dentist(dentistId, fullName, gender, dateOfBirth, email, phoneNumber, address, workingStatus, accountId, null, null);
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
			String sql = "SELECT COUNT(*) AS total_rows FROM Dentist";
			
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
