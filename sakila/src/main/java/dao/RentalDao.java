package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;

public class RentalDao {
	public List<Map<String, Object>> selectRentalSearchList(
		int storeId, String customerName, String beginDate, String endDate, int beginRow, int rowPerPage) {
		/*
		 SELECT 
			r.*, 
			CONCAT(c.first_name,' ',c.last_name) cName, 
			s.store_id storeId, 
			i.film_id filmId,
			f.title
		FROM rental r INNER JOIN customer c 
		ON r.customer_id = c.customer_id
			INNER JOIN staff s
			ON r.staff_id = s.staff_id
				INNER JOIN inventory i
				ON r.inventory_id = i.inventory_id
					INNER JOIN film f
					ON i.film_id = f.film_id
		WHERE s.store_id = ? AND CONCAT(c.first_name,' ',c.last_name) LIKE ?
		AND r.rental_date between STR_TO_DATE(?,'%Y-%m-%d')
		AND STR_TO_DATE(?,'%Y-%m-%d');
		 */
		
		// 메서드 리턴값 타입 선언 
		List<Map<String, Object>> list = new ArrayList<>();
		
		// DB 자원 준비 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		// DB 연결 
		conn = DBUtil.getConnection();
		
		try {
			// 중복되는 쿼리 내용 작성 
			String sql = "SELECT"
					+ "	r.rental_id rentalId, r.rental_date rentalDate, r.inventory_id inventoryId,"
					+ " r.customer_id customerId, r.return_date returnDate, r.staff_id staffId,"
					+ " r.last_update lastUpdate,"
					+ "	CONCAT(c.first_name,' ',c.last_name) cName,"
					+ "	s.store_id storeId,"
					+ "	i.film_id filmId,"
					+ "	f.title filmTitle"
					+ "	FROM rental r INNER JOIN customer c"
					+ "	ON r.customer_id = c.customer_id"
					+ "	INNER JOIN staff s"
					+ "	ON r.staff_id = s.staff_id"
					+ "	INNER JOIN inventory i"
					+ "	ON r.inventory_id = i.inventory_id"
					+ "	INNER JOIN film f"
					+ "	ON i.film_id = f.film_id" // 
					+ "	WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ?";
			// 공통 쿼리 디버깅 코드 
			System.out.println(sql+" // sql(RentalDao.java)");
			
			// 동적 쿼리 작성 
			
			if(storeId==-1&&beginDate.equals("")&&endDate.equals("")){ // 스토어 ID 체크 안함, 대여 일자 선택 안함
				sql = sql + " ORDER BY rental_id limit ?,?";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
			} else if(storeId==-1&&beginDate.equals("")&&!endDate.equals("")) { //스토어 ID 체크 안함, 시작날짜 선택안함,마지막날짜 선택
				sql = sql + " AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')  ORDER BY rental_id limit ?,?";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, endDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			}else if(storeId==-1&&!beginDate.equals("")&&endDate.equals("")) { //스토어 ID 체크 안함, 시작날짜 선택,마지막날짜 선택안함
				sql = sql + " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND NOW()  ORDER BY rental_id limit ?,?";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			}else if(storeId==-1&&!beginDate.equals("")&&!endDate.equals("")) { //스토어 ID 체크 안함, 시작날짜 선택,마지막날짜 선택
				sql = sql + " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rental_id limit ?,?";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			}else if(storeId!=-1&&beginDate.equals("")&&endDate.equals("")) { //스토어 ID 체크 함, 시작날짜 선택안함,마지막날짜 선택안함
				sql = sql + " AND s.store_id=? ORDER BY rental_id limit ?,?";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			}else if(storeId!=-1&&beginDate.equals("")&&!endDate.equals("")) { //스토어 ID 체크 함, 시작날짜 선택안함,마지막날짜 선택함
				sql = sql + " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')  ORDER BY rental_id limit ?,?";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3,endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			}else if(storeId!=-1&&!beginDate.equals("")&&endDate.equals("")) { //스토어 ID 체크 함, 시작날짜 선택,마지막날짜 선택안함
				sql = sql + " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND NOW()  ORDER BY rental_id limit ?,?";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			}else if(storeId!=-1&&!beginDate.equals("")&&!endDate.equals("")) { //스토어 ID 체크 함, 시작날짜 선택,마지막날짜 선택
				sql = sql + " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rental_id limit ?,?";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				stmt.setString(4, endDate);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			}
			// 동적 쿼리 디버깅 
			System.out.println(sql+" // sql++(RentalDao.java)");
			
			// 쿼리 실행 
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("rentalId", rs.getInt("rentalId"));
				map.put("rentalDate", rs.getString("rentalDate"));
				map.put("inventoryId", rs.getInt("inventoryId"));
				map.put("customerId", rs.getInt("customerId"));
				map.put("returnDate", rs.getString("returnDate"));
				map.put("staffId", rs.getInt("staffId"));
				map.put("lastUpdate", rs.getString("lastUpdate"));
				map.put("cName", rs.getString("cName"));
				map.put("storeId", rs.getInt("storeId"));
				map.put("filmId", rs.getInt("filmId"));
				map.put("filmTitle", rs.getString("filmTitle"));
				list.add(map);
				// 받아온 값 list에 담기 
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
		
		return list; // 수정 
	}
	
	public int selectTotalRow(int storeId, String customerName, String beginDate, String endDate) {
		// 전체 행 0으로 초기화 
		int totalRow = 0;
				
		// DB 자원 준비 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		// DB 연결 
		conn = DBUtil.getConnection();
		
		
		
		try {
			String sql = "SELECT"
					+ "	count(*) cnt"
					+ " FROM rental r INNER JOIN customer c"
					+ " ON r.customer_id = c.customer_id"
					+ "	INNER JOIN staff s"
					+ "	ON r.staff_id = s.staff_id"
					+ "	INNER JOIN inventory i "
					+ "	ON i.inventory_id = r.inventory_id "
					+ "	INNER JOIN film f"
					+ "	ON f.film_id = i.film_id"
					+ " WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ?";
			//검색쿼리 분기
			if(storeId==-1&&beginDate.equals("")&&endDate.equals("")){ // 스토어 ID 체크 안함, 대여 일자 선택 안함
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
			}else if(storeId==-1&&beginDate.equals("")&&!endDate.equals("")) { //스토어 ID 체크 안함, 시작날짜 선택안함,마지막날짜 선택
				sql = sql + " AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')  ";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, endDate);
			}else if(storeId==-1&&!beginDate.equals("")&&endDate.equals("")) {//스토어 ID 체크 안함, 시작날짜 선택,마지막날짜 선택안함
				sql = sql + " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND NOW()  ";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
			}else if(storeId==-1&&!beginDate.equals("")&&!endDate.equals("")) {//스토어 ID 체크 안함, 시작날짜 선택,마지막날짜 선택
				sql = sql + " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
			}else if(storeId!=-1&&beginDate.equals("")&&endDate.equals("")) {//스토어 ID 체크 함, 시작날짜 선택안함,마지막날짜 선택안함
				sql = sql + " AND s.store_id=? ";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
			}else if(storeId!=-1&&beginDate.equals("")&&!endDate.equals("")) {//스토어 ID 체크 함, 시작날짜 선택안함,마지막날짜 선택함
				sql = sql + " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')  ";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, endDate);
			}else if(storeId!=-1&&!beginDate.equals("")&&endDate.equals("")) {//스토어 ID 체크 함, 시작날짜 선택,마지막날짜 선택안함
				sql = sql + " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND NOW()  ";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
			}else if(storeId!=-1&&!beginDate.equals("")&&!endDate.equals("")) {//스토어 ID 체크 함, 시작날짜 선택,마지막날짜 선택
				sql = sql + " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				stmt.setString(4, endDate);
			}
			// 분기된 쿼리 디버깅
			System.out.println(sql +" // sql+Total(RentalDao.java)");
			
			// 쿼리 실행 
			rs = stmt.executeQuery();
			if(rs.next()) {
				totalRow=rs.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		return totalRow;
	}
}
