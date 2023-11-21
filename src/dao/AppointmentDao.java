package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import database.JDBCUtil;
import model.Appointment;
public class AppointmentDao implements DaoInterface<Appointment> {

	public static AppointmentDao getInstance() {
		return new AppointmentDao();
	}

	@Override
	public int insert(Appointment t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "INSERT INTO Appointment (appointmentNote, appointmentStatus, startTime, endTime, staffId, dentistId, patientId)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getAppointmentNote());
			pst.setString(2, t.getAppointmentStatus());
			pst.setDate(3, t.getStartTime());
			pst.setInt(7, t.getPatientId());
			
			Date endTime = t.getEndTime(); // Lấy giá trị từ t.getEndTime()
			
			if (endTime != null) {
			    pst.setDate(4, endTime);
			} else {
			    pst.setNull(4, java.sql.Types.DATE);
			}
			
			if (t.getStaffId() == 0) {
				pst.setObject(5, null);
			} else {
				pst.setInt(5, t.getStaffId());
			}
			
			if (t.getDentistId() == 0) {
				pst.setObject(6, null);
			} else {
				pst.setInt(6, t.getDentistId());
			}
			
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

	public int insertAppointmentService(Appointment t) {
		int result = 0;

		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "INSERT INTO Appointment_Service (appointmentId, serviceId)" + " VALUES (?, ?);";
			PreparedStatement pst = con.prepareStatement(sql);

			List<Integer> services = t.getServices();

			for (Integer serviceId : services) {
				pst.setInt(1, t.getAppointmentId());
				pst.setInt(2, serviceId);

				// Bước 3: Thực thi câu lệnh SQL
				result = pst.executeUpdate();
			}

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
	
	public int insertAppSer(Appointment t) {
		int result = 0;

		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "INSERT INTO Appointment_Service (appointmentId, serviceId)" + " VALUES (?, ?);";
			PreparedStatement pst = con.prepareStatement(sql);

			List<Integer> services = t.getServices();

			for (Integer serviceId : services) {
				pst.setInt(1, t.getAppointmentId());
				pst.setInt(2, serviceId);

				// Bước 3: Thực thi câu lệnh SQL
				result = pst.executeUpdate();
			}

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
	public int update(Appointment t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql1 = "UPDATE Appointment SET appointmentNote = ?, appointmentStatus = ?, startTime = ?, endTime = ? , staffId =?, dentistId = ? WHERE appointmentId = ?;";
			String sql2 = "DELETE FROM Appointment_Service WHERE appointmentId = ?;";
			String sql3 = "INSERT INTO Appointment_Service (appointmentId, serviceId) VALUES (?, ?)";
			
			PreparedStatement pst1 = con.prepareStatement(sql1);
			pst1.setString(1, t.getAppointmentNote());
			pst1.setString(2, t.getAppointmentStatus());
			pst1.setDate(3, t.getStartTime());
			pst1.setDate(4, t.getEndTime());
			pst1.setInt(7, t.getAppointmentId());
			
			if (t.getStaffId() == 0) {
				pst1.setObject(5, null);
			} else {
				pst1.setInt(5, t.getStaffId());
			}
			
			if (t.getDentistId() == 0) {
				pst1.setObject(6, null);
			} else {
				pst1.setInt(6, t.getDentistId());
			}
			
			result = pst1.executeUpdate();
			
			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setInt(1, t.getAppointmentId());
			pst2.executeUpdate();
			
			PreparedStatement pst3 = con.prepareStatement(sql3);
			List<Integer> services = t.getServices();
			for (Integer serviceId : services) {
				pst3.setInt(1, t.getAppointmentId());
				pst3.setInt(2, serviceId);

				// Bước 3: Thực thi câu lệnh SQL
				pst3.executeUpdate();
			}

			// Bước 4: Thực thi statement
			System.out.println("Bạn đã thực thi: " + sql1);
			System.out.println("Có " + result + " đã được thêm vào");

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int updateDentist(Appointment t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql1 = "UPDATE Appointment SET dentistId = ? WHERE appointmentId = ?;";
			
			PreparedStatement pst1 = con.prepareStatement(sql1);
			pst1.setInt(1, t.getDentistId());
			pst1.setInt(2, t.getAppointmentId());
			
			result = pst1.executeUpdate();
			
			// Bước 4: Thực thi statement
			System.out.println("Bạn đã thực thi: " + sql1);
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
	public int delete(Appointment t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql1 = "DELETE FROM Appointment WHERE appointmentId = ?;";
			String sql2 = "DELETE FROM Appointment_Medicine WHERE appointmentId = ?;";
			
			PreparedStatement pst1 = con.prepareStatement(sql1);
			pst1.setInt(1, t.getAppointmentId());
			result = pst1.executeUpdate();
			
			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setInt(1, t.getAppointmentId());
			result = pst2.executeUpdate();

			// Bước 4: Thực thi statement
			System.out.println("Bạn đã thực thi: " + sql1);
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
	public ArrayList<Appointment> selectAll() {
		ArrayList<Appointment> result = new ArrayList<Appointment>();
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			Statement st1 = con.createStatement();
			Statement st2 = con.createStatement();

			// Bước 3: Thực thi câu lệnh SQL
			String sql1 = "SELECT * FROM Appointment";
			String sql2 = "SELECT * FROM Appointment_Service";
			System.out.println(sql1);

			ResultSet rs1 = st1.executeQuery(sql1);
			System.out.println(rs1);

			// Bước 4: Thực thi statement
			while (rs1.next()) { // duyệt qua tùng dòng trong rs
				int appointmentId = rs1.getInt("appointmentId");
				String appointmentNote = rs1.getString("appointmentNote");
				String appointmentStatus = rs1.getString("appointmentStatus");
				Date startTime = rs1.getDate("startTime");
				Date endTime = rs1.getDate("endTime");
				int staffId = rs1.getInt("staffId");
				int dentistId = rs1.getInt("dentistId");
				int patientId = rs1.getInt("patientId");
				Timestamp appointmentCreationTime = rs1.getTimestamp("appointmentCreationTime");

				List<Integer> services = new ArrayList<>();

				ResultSet rs2 = st2.executeQuery(sql2);
				while (rs2.next()) {
					int appointmentId2 = rs2.getInt("appointmentId");
					int serviceId = rs2.getInt("serviceId");

					if (appointmentId2 == appointmentId) {
						services.add(serviceId);
					}
				}

				Appointment appointment = new Appointment(appointmentId, appointmentNote, appointmentStatus, startTime,
						endTime, staffId, dentistId, patientId, services, appointmentCreationTime);
				result.add(appointment);
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
	public Appointment selectById(Appointment t) {
		Appointment result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Appointment WHERE appointmentId = ?";
			String sql2 = "SELECT * FROM Appointment_Service WHERE appointmentId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getAppointmentId());
			
			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setInt(1, t.getAppointmentId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int appointmentId = rs.getInt("appointmentId");
				String appointmentNote = rs.getString("appointmentNote");
				String appointmentStatus = rs.getString("appointmentStatus");
				Date startTime = rs.getDate("startTime");
				Date endTime = rs.getDate("endTime");
				int staffId = rs.getInt("staffId");
				int dentistId = rs.getInt("dentistId");
				int patientId = rs.getInt("patientId");
				Timestamp appointmentCreationTime = rs.getTimestamp("appointmentCreationTime");

				List<Integer> services = new ArrayList<>();

				ResultSet rs2 = pst2.executeQuery();
				while (rs2.next()) {
					int appointmentId2 = rs2.getInt("appointmentId");
					int serviceId = rs2.getInt("serviceId");

					if (appointmentId2 == appointmentId) {
						services.add(serviceId);
					}
				}
				
				
				result = new Appointment(appointmentId, appointmentNote, appointmentStatus, startTime, endTime, staffId, dentistId, patientId, services, appointmentCreationTime);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public Appointment selectByPatientID(Appointment t) {
		Appointment result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Appointment WHERE patientId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getPatientId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int appointmentId = rs.getInt("appointmentId");
				String appointmentNote = rs.getString("appointmentNote");
				String appointmentStatus = rs.getString("appointmentStatus");
				Date startTime = rs.getDate("startTime");
				Date endTime = rs.getDate("endTime");
				int staffId = rs.getInt("staffId");
				int dentistId = rs.getInt("dentistId");
				int patientId = rs.getInt("patientId");
				Timestamp appointmentCreationTime = rs.getTimestamp("appointmentCreationTime");

				result = new Appointment(appointmentId, appointmentNote, appointmentStatus, startTime, endTime, staffId,
						dentistId, patientId, null, appointmentCreationTime);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<Integer> selectAppointmentsIdByDentistId(Appointment t) {
		List<Integer> result = new ArrayList<>();
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Appointment WHERE dentistId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getDentistId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int appointmentId = rs.getInt("appointmentId");
//				String appointmentNote = rs.getString("appointmentNote");
//				String appointmentStatus = rs.getString("appointmentStatus");
//				Date startTime = rs.getDate("startTime");
//				Date endTime = rs.getDate("endTime");
//				int staffId = rs.getInt("staffId");
//				int dentistId = rs.getInt("dentistId");
//				int patientId = rs.getInt("patientId");
//				Timestamp appointmentCreationTime = rs.getTimestamp("appointmentCreationTime");

				result.add(appointmentId);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<Integer> selectAppointmentsIdByPatientId(Appointment t) {
		List<Integer> result = new ArrayList<>();
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Appointment WHERE patientId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getPatientId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int appointmentId = rs.getInt("appointmentId");
//				String appointmentNote = rs.getString("appointmentNote");
//				String appointmentStatus = rs.getString("appointmentStatus");
//				Date startTime = rs.getDate("startTime");
//				Date endTime = rs.getDate("endTime");
//				int staffId = rs.getInt("staffId");
//				int dentistId = rs.getInt("dentistId");
//				int patientId = rs.getInt("patientId");
//				Timestamp appointmentCreationTime = rs.getTimestamp("appointmentCreationTime");

				result.add(appointmentId);
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
			String sql = "SELECT COUNT(*) AS total_rows FROM Appointment";
			
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
