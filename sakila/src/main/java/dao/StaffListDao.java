package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.StaffList;

public class StaffListDao {
	public List<StaffList> selectStaffListViewByPage(int beginRow, int rowPerPage) {
		
		List<StaffList> list = new ArrayList<StaffList>(); 
		
		Connection conn = null; 
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		
		conn = DBUtil.getConnection();
		
		String sql = "select id, name, address, `zip code`, phone, city, country, sid from staff_list order by id limit ?,? ";
		// zip code 이 부분 때문에 자꾸 에러가 생기는 것 같음 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				StaffList sl = new StaffList();
				sl.setId(rs.getInt("id"));
				sl.setName(rs.getString("name"));
				sl.setAddress(rs.getString("address"));
				sl.setZipCode(rs.getString("zip code"));
				sl.setPhone(rs.getString("phone"));
				sl.setCity(rs.getString("country"));
				sl.setSid(rs.getInt("sid"));
				list.add(sl);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
				
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public int selectTotalRow() {
		// 리턴값 변수 초기화 
		int totalRow = 0; 
		
		// DB 자원 준비 
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		// 실행할 쿼리문 문자열 변수에 저장 
		String sql = "select count(*) cnt from staff_list";
		
		// DB에 연결하는 메서드 호출 
		conn = DBUtil.getConnection();
		
		try {
			// 쿼리문 저장 
			stmt = conn.prepareStatement(sql);
			// 쿼리문 실행 
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				// 리턴값으로 받을 정수형 변수 totalRow에 쿼리문 총 행의 수 저장 
				totalRow = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// DB 자원 반납 
				rs.close();
				stmt.close();
				conn.close(); 
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		// 리턴 
		return totalRow;
	}
}	
