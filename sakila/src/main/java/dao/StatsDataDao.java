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

public class StatsDataDao {
	public List<Map<String, Object>> amountByCustomer() {
		/*
		 
		# 제일 많이(금액) 빌려간 사람
		
		SELECT p.customer_id, 
				CONCAT(c.first_name,' ', c.last_name),
				SUM(amount)
		FROM payment p INNER JOIN customer c
		ON p.customer_id = c.customer_id 
		GROUP BY p.customer_id
		ORDER BY SUM(amount) DESC;
		 */
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		conn = DBUtil.getConnection();
		
		String sql = "SELECT p.customer_id customerId,"
				+ "				CONCAT(c.first_name,' ', c.last_name) name,"
				+ "				SUM(amount) total"
				+ "		FROM payment p INNER JOIN customer c"
				+ "		ON p.customer_id = c.customer_id"
				+ "		GROUP BY p.customer_id"
				+ "     HAVING sum(p.amount) >= 180"	
				+ "		ORDER BY SUM(amount) DESC Limit 0,10";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() ) {
				Map<String, Object> m = new HashMap<>();
				m.put("customerId", rs.getInt("customerId"));
				m.put("name", rs.getString("name"));
				m.put("total", rs.getInt("total"));
				list.add(m);
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
		
		return list;
	}
	
	public List<Map<String, Object>> filmCountByRentalRate() {
		/*
		 # rental_rate 별 영화 개수 
		 
		SELECT rental_rate, COUNT(*)
		FROM film
		GROUP BY rental_rate
		ORDER BY COUNT(*) desc;
		 */
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		
		String sql = "SELECT rental_rate rentalRate, COUNT(*) cnt"
				+ "		FROM film"
				+ "		GROUP BY rentalRate"
				+ "		ORDER BY cnt desc";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("rentalRate", rs.getDouble("rentalRate"));
				m.put("cnt", rs.getInt("cnt"));
				list.add(m);
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
		return list;
	}
	
	public List<Map<String, Object>> filmCountByRating() {
		/*
		 * rating별 영화 개수 
		 */
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null; 
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		
		conn = DBUtil.getConnection();
		
		String sql = "SELECT rating, COUNT(*) cnt"
				+ " FROM film"
				+ " GROUP BY rating"
				+ " ORDER BY cnt desc";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("rating", rs.getString("rating"));
				m.put("cnt", rs.getInt("cnt"));
				list.add(m);
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
		return list;
	}
	
	public List<Map<String, Object>> filmCountByLanguage() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		
		conn = DBUtil.getConnection();
		
		String sql = "SELECT l.name name, COUNT(*) cnt"
				+ " FROM film f INNER JOIN language l"
				+ " ON f.language_id = l.language_id"
				+ " GROUP BY l.name";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("name", rs.getString("name"));
				m.put("cnt", rs.getInt("cnt"));
				list.add(m);
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
		
		
		return list;
	}
	
	public List<Map<String, Object>> filmCountByLength() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		
		String sql = "SELECT t.length2, COUNT(*) cnt"
				+ " FROM (SELECT title, LENGTH,"
				+ "		case when LENGTH<=60 then 'less60'"
				+ "				when LENGTH between 61 AND 119 then 'between60and119'"
				+ "				when LENGTH between 121 and 180 then 'between121and180'"
				+ "				ELSE 'more180'"
				+ "		END LENGTH2"
				+ "	FROM film) t"
				+ " GROUP BY t.length2";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("t.length2", rs.getInt("t.length2"));
				m.put("cnt", rs.getInt("cnt"));
				list.add(m);
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
		
		return list;
	}
}
