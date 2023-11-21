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
import model.Patient;

public class PatientDao implements DaoInterface<Patient> {

	public static PatientDao getIntance() {
		return new PatientDao();
	}

	@Override
	public int insert(Patient t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "INSERT INTO Patient (fullName, gender, dateOfBirth, email, phoneNumber, address, accountId) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getFullName());
			pst.setString(2, t.getGender());
			pst.setDate(3, t.getDateOfBirth());
			pst.setString(4, t.getEmail());
			pst.setString(5, t.getPhoneNumber());
			pst.setString(6, t.getAddress());

			Account insertAccount = new Account();
			insertAccount.setEmail(t.getEmail());
			insertAccount.setPassword(t.getEmail());
			insertAccount.setRoleId(4);
			AccountDao.getInstance().insert(insertAccount);
			
			Account searchAccountId = AccountDao.getInstance().selectByEmail(insertAccount);

			pst.setInt(7, searchAccountId.getAccountId());
			
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
	
	
	public int insertNotAccount(Patient t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "INSERT INTO Patient (fullName, gender, dateOfBirth, email, phoneNumber, address, accountId) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getFullName());
			pst.setString(2, t.getGender());
			pst.setDate(3, t.getDateOfBirth());
			pst.setString(4, t.getEmail());
			pst.setString(5, t.getPhoneNumber());
			pst.setString(6, t.getAddress());
			pst.setInt(7, t.getAccountId());
			
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
	public int update(Patient t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "UPDATE Patient SET fullName = ?, gender = ?, dateOfBirth = ?, phoneNumber = ?, address = ? WHERE patientId = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getFullName());
			pst.setString(2, t.getGender());
			pst.setDate(3, t.getDateOfBirth());
//			pst.setString(4, t.getEmail());
			pst.setString(4, t.getPhoneNumber());
			pst.setString(5, t.getAddress());
			pst.setInt(6, t.getPatientId());

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
	public int delete(Patient t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "DELETE FROM Patient WHERE patientId = ?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getPatientId());

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
	public ArrayList<Patient> selectAll() {
	    ArrayList<Patient> result = new ArrayList<Patient>();
	    try {
	        // Bước 1: Tạo kết nối với CSDL
	        Connection con = JDBCUtil.getConnection();

	        // Bước 2: Tạo ra đối tượng statement
	        Statement st1 = con.createStatement();
	        Statement st2 = con.createStatement();
	        Statement st3 = con.createStatement();

	        // Bước 3: Thực thi câu lệnh SQL
	        String sql1 = "SELECT * FROM Patient;";
	        String sql2 = "SELECT * FROM Patient AS d LEFT JOIN Appointment AS a ON d.patientId = a.patientId;";
	        String sql3 = "SELECT * FROM Patient AS d LEFT JOIN Prescription AS p ON d.patientId = p.patientId;";
	        
	        ResultSet rs1 = st1.executeQuery(sql1);

	        // Bước 4: Thực thi statement
	        while (rs1.next()) { // duyệt qua từng dòng trong rs1
	            int patientId = rs1.getInt("patientId");
	            String fullName = rs1.getString("fullName");
	            String gender = rs1.getString("gender");
	            Date dateOfBirth = rs1.getDate("dateOfBirth");
	            String email = rs1.getString("email");
	            String phoneNumber = rs1.getString("phoneNumber");
	            String address = rs1.getString("address");
	            int accountId = rs1.getInt("accountId");

	            List<Integer> appointmentIds = new ArrayList<>();
	            List<Integer> prescriptionIds = new ArrayList<>();

	            ResultSet rs2 = st2.executeQuery(sql2);
	            while (rs2.next()) {
	            	int patientId2 = rs2.getInt("patientId");
	                int appointmentId = rs2.getInt("appointmentId");

	                if (patientId2 == patientId) {
	                	appointmentIds.add(appointmentId);
	                }
	            }
	            
	            ResultSet rs3 = st3.executeQuery(sql3);
	            while (rs3.next()) {
	            	int patientId3 = rs3.getInt("patientId");
	                int prescriptionId = rs3.getInt("prescriptionId");

	                if (patientId3 == patientId) {
	                	prescriptionIds.add(prescriptionId);
	                }
	            }

	            Patient patient = new Patient(patientId, fullName, gender, dateOfBirth, email, phoneNumber, address, accountId, appointmentIds, prescriptionIds);
	            result.add(patient);
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
	public Patient selectById(Patient t) {
		Patient result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Patient WHERE patientId = ?";
			String sql2 = "SELECT * FROM Appointment WHERE patientId = ?";
			String sql3 = "SELECT * FROM Prescription WHERE patientId = ?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getPatientId());
			
			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setInt(1, t.getPatientId());
			
			PreparedStatement pst3 = con.prepareStatement(sql3);
			pst3.setInt(1, t.getPatientId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int patientId = rs.getInt("patientId");
				String fullName = rs.getString("fullName");
				String gender = rs.getString("gender");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phoneNumber");
				String address = rs.getString("address");
				int accountId = rs.getInt("accountId");
				
				List<Integer> appointmentIds = new ArrayList<>();
	            List<Integer> prescriptionIds = new ArrayList<>();

	            ResultSet rs2 = pst2.executeQuery();
	            while (rs2.next()) {
	            	int patientId2 = rs2.getInt("patientId");
	                int appointmentId = rs2.getInt("appointmentId");

	                if (patientId2 == patientId) {
	                	appointmentIds.add(appointmentId);
	                }
	            }
	            
	            ResultSet rs3 = pst3.executeQuery();
	            while (rs3.next()) {
	            	int patientId3 = rs3.getInt("patientId");
	                int prescriptionId = rs3.getInt("prescriptionId");

	                if (patientId3 == patientId) {
	                	prescriptionIds.add(prescriptionId);
	                }
	            }

				result = new Patient(patientId, fullName, gender, dateOfBirth, email, phoneNumber, address, accountId, appointmentIds, prescriptionIds);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public Patient selectByAccountId(Patient t) {
		Patient result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Patient WHERE accountId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getAccountId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int patientId = rs.getInt("patientId");
				String fullName = rs.getString("fullName");
				String gender = rs.getString("gender");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phoneNumber");
				String address = rs.getString("address");
				int accountId = rs.getInt("accountId");

				// Write method to get list ...
				result = new Patient(patientId, fullName, gender, dateOfBirth, email, phoneNumber, address, accountId, null, null);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public Patient selectByEmail(Patient t) {
		Patient result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Patient WHERE email = ?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getEmail());
			
			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int patientId = rs.getInt("patientId");
				String fullName = rs.getString("fullName");
				String gender = rs.getString("gender");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phoneNumber");
				String address = rs.getString("address");
				int accountId = rs.getInt("accountId");
				
				result = new Patient(patientId, fullName, gender, dateOfBirth, email, phoneNumber, address, accountId, null, null);
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
			String sql = "SELECT COUNT(*) AS total_rows FROM Patient";
			
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
