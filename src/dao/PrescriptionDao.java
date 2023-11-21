package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import database.JDBCUtil;
import model.Prescription;

public class PrescriptionDao implements DaoInterface<Prescription> {

	public static PrescriptionDao getInstance() {
		return new PrescriptionDao();
	}

	@Override
	public int insert(Prescription t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "INSERT INTO Prescription (prescriptionName, dentistId, patientId)" + " VALUES (?, ?, ?);";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getPrescriptionName());
			pst.setInt(3, t.getPatientId());

			if (t.getDentistId() == 0) {
				pst.setObject(2, null);
			} else {
				pst.setInt(2, t.getDentistId());
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

	public int insertPrescriptionMedicine(Prescription t) {
		int result = 0;

		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "INSERT INTO Prescription_Medicine (prescriptionId, medicineId)" + " VALUES (?, ?);";
			PreparedStatement pst = con.prepareStatement(sql);

			List<Integer> medicines = t.getMedicines();

			for (Integer medicineId : medicines) {
				pst.setInt(1, t.getPrescriptionId());
				pst.setInt(2, medicineId);

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
	public int update(Prescription t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql1 = "UPDATE Prescription SET prescriptionName = ?, patientId = ? WHERE prescriptionId = ?;";
			String sql2 = "DELETE FROM Prescription_Medicine WHERE prescriptionId = ?;";
			String sql3 = "INSERT INTO Prescription_Medicine (prescriptionId, medicineId) VALUES (?, ?)";

			PreparedStatement pst1 = con.prepareStatement(sql1);
			pst1.setString(1, t.getPrescriptionName());
			pst1.setInt(2, t.getPatientId());
			pst1.setInt(3, t.getPrescriptionId());
			result = pst1.executeUpdate();

			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setInt(1, t.getPrescriptionId());
			pst2.executeUpdate();

			PreparedStatement pst3 = con.prepareStatement(sql3);
			List<Integer> medicines = t.getMedicines();
			for (Integer medicineId : medicines) {
				pst3.setInt(1, t.getPrescriptionId());
				pst3.setInt(2, medicineId);

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

	public int updatePrice(Prescription t) {
		int result = 0;

		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "UPDATE Prescription SET prescriptionPrice = ? WHERE prescriptionId = ?";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setDouble(1, t.getPrescriptionPrice());
			pst.setInt(2, t.getPrescriptionId());

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

	public int updateDentistId(Prescription t) {
		int result = 0;

		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "UPDATE Prescription SET dentistId = ? WHERE prescriptionId = ?";
			PreparedStatement pst = con.prepareStatement(sql);

			if (t.getDentistId() == 0) {
				pst.setObject(1, null);
			} else {
				pst.setInt(1, t.getDentistId());
			}

			pst.setInt(2, t.getPrescriptionId());

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
	public int delete(Prescription t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql1 = "DELETE FROM Prescription WHERE prescriptionId = ?;";
			String sql2 = "DELETE FROM Prescription_Medicine WHERE prescriptionId = ?;";

			PreparedStatement pst1 = con.prepareStatement(sql1);
			pst1.setInt(1, t.getPrescriptionId());
			result = pst1.executeUpdate();

			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setInt(1, t.getPrescriptionId());
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
	public ArrayList<Prescription> selectAll() {
		ArrayList<Prescription> result = new ArrayList<Prescription>();
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			Statement st1 = con.createStatement();
			Statement st2 = con.createStatement();

			// Bước 3: Thực thi câu lệnh SQL
			String sql1 = "SELECT * FROM Prescription";
			String sql2 = "SELECT * FROM Prescription_Medicine";

			ResultSet rs1 = st1.executeQuery(sql1);

			// Bước 4: Thực thi statement
			while (rs1.next()) { // duyệt qua tùng dòng trong rs
				int prescriptionId = rs1.getInt("prescriptionId");
				String prescriptionName = rs1.getString("prescriptionName");
				double prescriptionPrice = rs1.getDouble("prescriptionPrice");
				int dentistId = rs1.getInt("dentistId");
				int patientId = rs1.getInt("patientId");
				Timestamp prescriptionCreationTime = rs1.getTimestamp("prescriptionCreationTime");

				List<Integer> medicines = new ArrayList<>();

				ResultSet rs2 = st2.executeQuery(sql2);
				while (rs2.next()) {
					int prescriptionId2 = rs2.getInt("prescriptionId");
					int medicineId = rs2.getInt("medicineId");

					if (prescriptionId2 == prescriptionId) {
						medicines.add(medicineId);
					}
				}

				Prescription prescription = new Prescription(prescriptionId, prescriptionName, prescriptionPrice,
						dentistId, patientId, medicines, prescriptionCreationTime);
				result.add(prescription);
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
	public Prescription selectById(Prescription t) {
		Prescription prescription = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql1 = "SELECT * FROM Prescription WHERE prescriptionId = ?";
			String sql2 = "SELECT * FROM Prescription_Medicine WHERE prescriptionId = ?";

			PreparedStatement st1 = con.prepareStatement(sql1);
			st1.setInt(1, t.getPrescriptionId());

			PreparedStatement st2 = con.prepareStatement(sql2);
			st2.setInt(1, t.getPrescriptionId());

			ResultSet rs1 = st1.executeQuery();

			// Bước 4: Thực thi statement
			while (rs1.next()) { // duyệt qua tùng dòng trong rs
				int prescriptionId = rs1.getInt("prescriptionId");
				String prescriptionName = rs1.getString("prescriptionName");
				double prescriptionPrice = rs1.getDouble("prescriptionPrice");
				int dentistId = rs1.getInt("dentistId");
				int patientId = rs1.getInt("patientId");
				Timestamp prescriptionCreationTime = rs1.getTimestamp("prescriptionCreationTime");

				List<Integer> medicines = new ArrayList<>();

				ResultSet rs2 = st2.executeQuery();
				while (rs2.next()) {
					int prescriptionId2 = rs2.getInt("prescriptionId");
					int medicineId = rs2.getInt("medicineId");

					if (prescriptionId2 == prescriptionId) {
						medicines.add(medicineId);
					}
				}

				prescription = new Prescription(prescriptionId, prescriptionName, prescriptionPrice, dentistId,
						patientId, medicines, prescriptionCreationTime);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return prescription;
	}

	public List<Integer> selectPrescriptionsIdByDentistId(Prescription t) {
		List<Integer> result = new ArrayList<>();
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Prescription WHERE dentistId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getDentistId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int prescriptionId = rs.getInt("prescriptionId");

				result.add(prescriptionId);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<Integer> selectPrescriptionsIdByPatientId(Prescription t) {
		List<Integer> result = new ArrayList<>();
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Prescription WHERE patientId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getPatientId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int prescriptionId = rs.getInt("prescriptionId");

				result.add(prescriptionId);
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
			String sql = "SELECT COUNT(*) AS total_rows FROM Prescription";

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
