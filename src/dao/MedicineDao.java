package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.JDBCUtil;
import model.Medicine;

public class MedicineDao implements DaoInterface<Medicine> {

	public static MedicineDao getInstance() {
		return new MedicineDao();
	}

	@Override
	public int insert(Medicine t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "INSERT INTO Medicine (medicineName, medicineDetail, medicinePrice)" + "VALUES (?, ?, ?);";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getMedicineName());
			pst.setString(2, t.getMedicineDetail());
			pst.setDouble(3, t.getMedicinePrice());

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
	public int update(Medicine t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "UPDATE Medicine SET medicineName = ?, medicineDetail = ?, medicinePrice = ?  WHERE medicineId = ?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getMedicineName());
			pst.setString(2, t.getMedicineDetail());
			pst.setDouble(3, t.getMedicinePrice());
			pst.setInt(4, t.getMedicineId());

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
	public int delete(Medicine t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "DELETE FROM Medicine WHERE medicineId = ?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getMedicineId());

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
	public ArrayList<Medicine> selectAll() {
		ArrayList<Medicine> result = new ArrayList<Medicine>();
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			Statement st1 = con.createStatement();
			Statement st2 = con.createStatement();

			// Bước 3: Thực thi câu lệnh SQL
			String sql1 = "SELECT * FROM Medicine";
			String sql2 = "SELECT * FROM Prescription_Medicine";
			System.out.println(sql1);

			ResultSet rs1 = st1.executeQuery(sql1);
			System.out.println(rs1);

			// Bước 4: Thực thi statement
			while (rs1.next()) { // duyệt qua tùng dòng trong rs
				int medicineId = rs1.getInt("medicineId");
				String medicineName = rs1.getString("medicineName");
				String medicineDetail = rs1.getString("medicineDetail");
				double medicinePrice = rs1.getDouble("medicinePrice");

				List<Integer> prescriptions = new ArrayList<>();

				ResultSet rs2 = st2.executeQuery(sql2);
				while (rs2.next()) {
					int medicineId2 = rs2.getInt("medicineId");
					int prescriptionId = rs2.getInt("prescriptionId");

					if (medicineId2 == medicineId) {
						prescriptions.add(prescriptionId);
					}
				}

				Medicine medicine = new Medicine(medicineId, medicineName, medicineDetail, medicinePrice,
						prescriptions);
				result.add(medicine);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public Object[] selectNameAndPrice(Medicine t) {
		Object[] result = new Object[3];
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Medicine WHERE medicineId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getMedicineId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int medicineId = rs.getInt("medicineId");
				String medicineName = rs.getString("medicineName");
				double medicinePrice = rs.getDouble("medicinePrice");
				
				result[0] = medicineId;
				result[1] = medicineName;
                result[2] = medicinePrice;
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public Medicine selectById(Medicine t) {
		Medicine result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Medicine WHERE medicineId = ?";
			String sql2 = "SELECT * FROM Prescription_Medicine WHERE medicineId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getMedicineId());
			
			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setInt(1, t.getMedicineId());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int medicineId = rs.getInt("medicineId");
				String medicineName = rs.getString("medicineName");
				String medicineDetail = rs.getString("medicineDetail");
				double medicinePrice = rs.getDouble("medicinePrice");
				
				List<Integer> prescriptionIds = new ArrayList<>();

	            ResultSet rs2 = pst2.executeQuery();
	            while (rs2.next()) {
	            	int medicineId2 = rs2.getInt("medicineId");
	                int prescriptionId = rs2.getInt("prescriptionId");

	                if (medicineId2 == medicineId) {
	                	prescriptionIds.add(prescriptionId);
	                }
	            }

				result = new Medicine(medicineId, medicineName, medicineDetail, medicinePrice, prescriptionIds);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String> selectMedicineName() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT medicineName FROM Medicine";

			PreparedStatement pst = con.prepareStatement(sql);

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) {
				String medicineName = rs.getString("medicineName");
				result.add(medicineName);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	public Medicine selectByName(Medicine medicineNameObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int totalRows() {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT COUNT(*) AS total_rows FROM Medicine";
			
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
