package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.CustomerList;

public class CustomerListDao {
	public List<CustomerList> selectCustomerListByPage(int beginRow, int rowPerPage) {
		
		List<CustomerList> list = new ArrayList<CustomerList>();
		
		// DB 자원 준비 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		
		String sql = "select id, name, address, zip code, phone, city, country, notes, SID sid from customer_list order by id limit ?,?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				CustomerList cl = new CustomerList();
				cl.setId(rs.getInt("id"));
				cl.setName(rs.getString("name"));
				cl.setAddress(rs.getString("address"));
				cl.setZipCode(rs.getString("zip Code"));
				cl.setPhone(rs.getString("phone"));
				cl.setCity(rs.getString("city"));
				cl.setCountry(rs.getString("country"));
				cl.setNotes(rs.getString("notes"));
				cl.setSid(rs.getInt("sid"));
				list.add(cl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
		
	}
	
	public int selectTotalRow() {
		int totalRow = 0;
		
		Connection conn = null; 
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		
		String sql = "select count(*) cnt from customer_list";
		
		conn = DBUtil.getConnection();
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				totalRow = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return totalRow;
	}
}
