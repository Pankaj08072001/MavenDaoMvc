package com.training.dao.impl;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.training.dao.StaffDao;
import com.training.dao.util.ConnectionFactory;
import com.training.model.Staff;

public class StaffDaoimpl implements StaffDao {

	public StaffDaoimpl() {
		createTableIfNotExists();
	}

	private void createTableIfNotExists() {
		try (Connection con = ConnectionFactory.getMySqlConnection(); Statement st = con.createStatement()) {
			st.execute("""
					create table IF NOT EXISTS  Staff(id int primary key auto_increment,
					name varchar(50),
					mobileNo bigint,
					email varchar(30),
					password varchar(20),
					role varchar(50),
					speciality varchar(50),
					managerId int,
					salary double,
					gender char(1),
					dob date,
					joinedAt timestamp,
					description varchar(255),
					constraint teamlead foreign key(managerId) references staff(id)
					);
					""");
			System.out.println("staff table is ready.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public long addStaff(Staff stf) {
		String sql = "INSERT INTO Staff (name, mobileNo, email, password, role, "
				+ "speciality, managerId, salary, gender, dob, joinedAt, description)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = ConnectionFactory.getMySqlConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, stf.getName());
			ps.setLong(2, (long) stf.getMobileNo());
			ps.setString(3, stf.getEmail());
			ps.setString(4, stf.getPassword());
			ps.setString(5, stf.getRole());
			ps.setString(6, stf.getSpeciality());
			ps.setInt(7, stf.getManagerId());
			ps.setDouble(8, stf.getSalary());
			ps.setString(9, String.valueOf(stf.getGender()));
			ps.setDate(10, Date.valueOf(stf.getDob()));
			ps.setDate(12, Date.valueOf(stf.getJoinedAt()));
			ps.setString(13, sql);

			int affectedRows = ps.executeUpdate();
			if (affectedRows > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					return rs.getLong(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public long updateStaff(Staff stf) {
		String sql = "UPDATE Staff SET " + "name = ?, mobileNo = ?, email = ?, password = ?, role = ?, "
				+ "speciality = ?, managerId = ?, salary = ?, gender = ?, dob = ?, "
				+ "joinedAt = ?, description = ? WHERE id = ?";
		try (Connection con = ConnectionFactory.getMySqlConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, stf.getName());
			ps.setLong(2, (long) stf.getMobileNo());
			ps.setString(3, stf.getEmail());
			ps.setString(4, stf.getPassword());
			ps.setString(5, stf.getRole());
			ps.setString(6, stf.getSpeciality());
			ps.setInt(7, stf.getManagerId());
			ps.setDouble(8, stf.getSalary());
			ps.setString(9, String.valueOf(stf.getGender()));
			ps.setDate(10, Date.valueOf(stf.getDob()));
			ps.setDate(12, Date.valueOf(stf.getJoinedAt()));
			ps.setString(13, stf.getDescription());

			int affectedRows = ps.executeUpdate();
			return affectedRows > 0 ? stf.getId() : -1;
			;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Staff getStaffById(long id) {
		String sql = "SELECT * FROM staff WHERE id=?";
		try (Connection con = ConnectionFactory.getMySqlConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Staff(rs.getInt("id"), 
						rs.getString("name"), 
						rs.getLong("mobileNo"),
						rs.getString("email"),
						rs.getString("password"), 
						rs.getString("role"), 
						rs.getString("speciality"),
						rs.getInt("managerId"), 
						rs.getFloat("salary"), 
						rs.getString("gender").charAt(0),
						rs.getDate("dob").toLocalDate(), 
						rs.getDate("joinedAt").toLocalDate(), 
						rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Staff> getAllStaff() {
		List<Staff> stf = new ArrayList<>();
		String sql = "SELECT * FROM staff";
		try (Connection con = ConnectionFactory.getMySqlConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				stf.add(new Staff(rs.getInt("id"),
						rs.getString("name"), 
						rs.getLong("mobileNo"), 
						rs.getString("email"),
						rs.getString("password"), 
						rs.getString("role"), 
						rs.getString("speciality"),
						rs.getInt("managerId"), 
						rs.getFloat("salary"), 
						rs.getString("gender").charAt(0),
						rs.getDate("dob").toLocalDate(), 
						rs.getDate("joinedAt").toLocalDate(), 
						rs.getString("description")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stf;
	}

	@Override
	public long deleteStaffById(long id) {
		String sql = "DELETE FROM staff WHERE id=?";
		try (Connection con = ConnectionFactory.getMySqlConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			int affectedRows = ps.executeUpdate();
			return affectedRows > 0 ? id : -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public String[] getAllColumns() {
		String sql = "SELECT * FROM Students LIMIT 1";
		try (Connection con = ConnectionFactory.getMySqlConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			String[] columns = new String[columnCount];

			for (int i = 1; i <= columnCount; i++) {
				columns[i - 1] = metaData.getColumnName(i);
			}

			return columns;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new String[0];
	}
}
