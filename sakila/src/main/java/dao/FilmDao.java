package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;

public class FilmDao {
	public Map<String, Object> filmInStockCall(int filmId, int storeId) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		
		Connection conn = null;
		// PreparedStatement :  쿼리를 실행 
		// CallableStatement : 프로시저를 실행 
		CallableStatement stmt = null;
		ResultSet rs = null; 
		// select inventory_id 
		List<Integer> list = new ArrayList<>();
		// select count(inventory_id) 
		Integer count = 0; 
		conn = DBUtil.getConnection();
		try {
			stmt = conn.prepareCall("{CALL film_in_stock(?,?,?)}");
			stmt.setInt(1, filmId);
			stmt.setInt(2, storeId);
			stmt.registerOutParameter(3, Types.INTEGER);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1)); // rs.getInt("inventory_id")
			}
			count = stmt.getInt(3); // 프로시저 3번쨰 out 변수 값 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		map.put("list", list);
		map.put("count", count);
		return map; 
	}
	
	/*
	public static void main(String args[]) {
		FilmDao fd = new FilmDao();
		int filmId = 1;
		int storeId = 1; 
		Map<String, Object> map = fd.filmInStockCall(filmId, storeId);
		List<Integer> list = (List<Integer>)map.get("list");
		int count = (Integer)map.get("count");
		
		System.out.println(filmId+"번 영화가 "+storeId +"번 가게에 "+count+"개 남음");
		for(int id : list) {
			System.out.println(id);
		}
	}
	*/
	
	public Map<String, Object> filmNotInStockCall(int filmId, int storeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null; 
		// DB 연결하는 용도 
		CallableStatement stmt = null;
		// 프로시저를 실행함 
		ResultSet rs = null;
		
		List<Integer> list = new ArrayList<>();
		// select inventory_id -> 받을 변수 선언 
		
		Integer count = 0; 
		// select count(inventory_id) -> 받을 변수 선언 
		
		conn = DBUtil.getConnection();
		// DB 연결하는 메서드 호출 
		
		try {
			stmt = conn.prepareCall("{call film_in_stock(?,?,?)}");
			stmt.setInt(1, filmId);
			stmt.setInt(2, storeId);
			stmt.registerOutParameter(3, Types.INTEGER);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1)); 
				// rs.getInt("inventory_id") 
				// rs.getInt(1) 받아오는 값 파악 
			}
			count = stmt.getInt(3);
			// 프로시저 3번째 out 변수 값 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("count", count);
		return map; 
	}
	
	/*
	// 단위 테스트 용 
	public static void main(String args[]) {
		FilmDao fd = new FilmDao();
		// FilmDao 인스턴스 생성 
		
		int filmId = 7;
		int storeId = 2; 
		// 인스턴스 fd의 메서드 filmNotInStockCall에 들어갈 매개변수 선언 후 초기화 
		
		Map<String, Object> map = fd.filmNotInStockCall(filmId, storeId);
		
		List<Integer> list = (List<Integer>)map.get("list");
		
		int count = (Integer)map.get("count");
		
		System.out.println(filmId+"번 영화는 "+storeId+"번 가게에 "+count+"개 남지 않음");
		// 메서드가 올바로 동작했는지 확인 
		
		for(int id : list) {
			System.out.println(id);
		}
	}
	*/
}
