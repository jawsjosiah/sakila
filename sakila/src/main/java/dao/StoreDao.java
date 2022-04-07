package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.Store;

import util.DBUtil;
public class StoreDao {
	public List<Integer> selectStoreIdList() {
		// 메서드 리턴값 담을 변수 list 선언 
		List<Integer> list = new ArrayList<Integer>();
		
		// DB 자원 준비 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		// DB 연결 
		conn = DBUtil.getConnection();
		
		// 쿼리 작성 
		String sql = "SELECT store_id storeId from store";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt("storeId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// DB 자원 반납 
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list; 
	}
	
	public List<Map<String, Object>> selectStoreList() {
		List<Map<String, Object>> list = new ArrayList<>(); // 다형성
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//Class.forName("org.mariadb.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/sakila","root","java1234");
			conn = DBUtil.getConnection();
			/*
				SELECT 
					s1.store_id storeId,
					s1.manager_staff_id staffId, 
					concat(s2.first_name,' ',s2.last_name) staffName,
					s1.address_id addressId,
					CONCAT(a.address, IFNULL(a.address2, ' '), district) staffAddress,
					s1.last_update lastUpdate
				FROM store s1 
					INNER JOIN staff s2
					INNER JOIN address a
				ON s1.manager_staff_id = s2.staff_id 
				AND s1.address_id = a.address_id
			 */
			String sql = "SELECT"
					+ "		s1.store_id storeId,"
					+ "		s1.manager_staff_id staffId,"
					+ "		concat(s2.first_name,' ',s2.last_name) staffName,"
					+ "		s1.address_id addressId,"
					+ "		CONCAT(a.address, IFNULL(a.address2, ' '), district) staffAddress,"
					+ "		s1.last_update lastUpdate"
					+ " FROM store s1"
					+ " INNER JOIN staff s2"
					+ " INNER JOIN address a"
					+ " ON s1.manager_staff_id = s2.staff_id"
					+ " AND s1.address_id = a.address_id;";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>(); // 다형성
				map.put("storeId", rs.getInt("storeId"));
				map.put("staffId", rs.getInt("staffId"));
				map.put("staffName", rs.getString("staffName"));
				map.put("addressId", rs.getInt("addressId"));
				map.put("staffAddress", rs.getString("staffAddress"));
				map.put("lastUpdate", rs.getString("lastUpdate"));
				list.add(map);
			}
		} catch (Exception e) { // ClassNotFoundException, SQLException두개의 예외를 부모타입 Exception으로 처리 -> 다형성
			e.printStackTrace();
			System.out.println("예외발생");
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
	
	// selectStoreList() 테스트 코드 
	// 단위 테스트 
	public static void main(String args[]) {
		StoreDao dao = new StoreDao(); 
		List<Map<String, Object>> list = dao.selectStoreList();
		for(Map m : list) {
			System.out.print(m.get("storeId")+", ");
			System.out.print(m.get("staffId")+", ");
			System.out.print(m.get("staffName")+", ");
			System.out.print(m.get("addressId")+", ");
			System.out.print(m.get("staffAddress")+", ");
			System.out.print(m.get("lastUpdate"));
			System.out.println();
		}
	}
}
