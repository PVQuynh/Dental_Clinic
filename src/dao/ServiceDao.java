package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.JDBCUtil;
import model.Service;

public class ServiceDao implements DaoInterface<Service> {

	public static ServiceDao getInstance() {
		return new ServiceDao();
	}

	@Override
	public int insert(Service t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "INSERT INTO Service (serviceName, serviceDetail, servicePrice)" + "VALUES (?, ?, ?);";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getServiceName());
			pst.setString(2, t.getServiceDetail());
			pst.setDouble(3, t.getServicePrice());

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
	public int update(Service t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "UPDATE Service SET serviceName = ?, serviceDetail = ?, servicePrice = ?  WHERE serviceId = ?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getServiceName());
			pst.setString(2, t.getServiceDetail());
			pst.setDouble(3, t.getServicePrice());
			pst.setInt(4, t.getServiceId());

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
	public int delete(Service t) {
		int result = 0;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "DELETE FROM Service WHERE serviceId = ?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getServiceId());
			
			System.out.println("Model: "+t.getServiceId());

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
	public ArrayList<Service> selectAll() {
		ArrayList<Service> result = new ArrayList<Service>();
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			Statement st1 = con.createStatement();
			Statement st2 = con.createStatement();

			// Bước 3: Thực thi câu lệnh SQL
			String sql1 = "SELECT * FROM Service";
			String sql2 = "SELECT * FROM Appointment_Service";

			ResultSet rs1 = st1.executeQuery(sql1);

			// Bước 4: Thực thi statement
			while (rs1.next()) { // duyệt qua tùng dòng trong rs
				int serviceId = rs1.getInt("serviceId");
				String serviceName = rs1.getString("serviceName");
				String serviceDetail = rs1.getString("serviceDetail");
				double servicePrice = rs1.getDouble("servicePrice");

				List<Integer> appointments = new ArrayList<>();

				ResultSet rs2 = st2.executeQuery(sql2);
				while (rs2.next()) {
					int serviceId2 = rs2.getInt("serviceId");
					int appointmentId = rs2.getInt("appointmentId");

					if (serviceId2 == serviceId) {
						appointments.add(appointmentId);
					}
				}

				Service service = new Service(serviceId, serviceName, serviceDetail, servicePrice, appointments);
				result.add(service);
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
	public Service selectById(Service t) { // truyền 1 object với cái id cần tìm
		Service result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Service WHERE serviceId = ?";
			String sql2 = "SELECT * FROM Appointment_Service WHERE serviceId = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, t.getServiceId());
			
			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setInt(1, t.getServiceId());
			
			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int serviceId = rs.getInt("serviceId");
				String serviceName = rs.getString("serviceName");
				String serviceDetail = rs.getString("serviceDetail");
				double servicePrice = rs.getDouble("servicePrice");
				
				List<Integer> appointmentIds = new ArrayList<>();

	            ResultSet rs2 = pst2.executeQuery();
	            while (rs2.next()) {
	            	int serviceId2 = rs2.getInt("serviceId");
	                int appointmentId = rs2.getInt("appointmentId");

	                if (serviceId2 == serviceId) {
	                	appointmentIds.add(appointmentId);
	                }
	            }

				result = new Service(serviceId, serviceName, serviceDetail, servicePrice, appointmentIds);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String> selectServiceName() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT serviceName FROM Service";

			PreparedStatement pst = con.prepareStatement(sql);

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) {
				String serviceName = rs.getString("serviceName");
				result.add(serviceName);
			}

			// Bước 5: Ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	public Service selectByName(Service t) {
		Service result = null;
		try {
			// Bước 1: Tạo kết nối với CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM Service WHERE serviceName = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getServiceName());

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = pst.executeQuery();
//			System.out.println(rs);
//			System.out.println(sql);

			// Bước 4: Thực thi statement
			while (rs.next()) { // duyệt qua tùng dòng trong rs
				int serviceId = rs.getInt("serviceId");
				String serviceName = rs.getString("serviceName");
				String serviceDetail = rs.getString("serviceDetail");
				double servicePrice = rs.getDouble("servicePrice");

				result = new Service(serviceId, serviceName, serviceDetail, servicePrice, null);
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
			String sql = "SELECT COUNT(*) AS total_rows FROM Service";
			
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
