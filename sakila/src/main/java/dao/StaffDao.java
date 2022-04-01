package dao;

import java.util.*;
import java.sql.*;

public class StaffDao {
	public List<Map<String, Object>> selectStaffList() {
		// 다형성
		List<Map<String, Object>> list = new ArrayList<>(); 
		// try, catch, finally 예외 처리 블록 안에 
		// Connection, PreparedStatement, ResultSet 변수를 설정하면 
		// 블록 밖에서 못쓰니 미리 선언함. 
		
		// Connection 타입은 연결된 데이터베이스에 SQL쿼리 명령을 전송할 수 있는 메서드를 가진 타입
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// (1) 마리아 드라이버 로딩 
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("마리아 DB 드라이버 로딩 성공 // <- StaffDao.java");
			
			// (2) 마리아 RDBMS에 접속 ( IP주소, 아이디, 비밀번호 ) 
			
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
			System.out.println(conn+"//conn <- StaffDao.java");
			/*
				SELECT 
					s1.staff_id staffId,
					CONCAT(s1.first_name,' ',s1.last_name) staffName,
					s1.store_id storeId,
					s1.address_id addressId,
					ifnull(s1.email, ' ') email,
					s1.active active,
					CONCAT(a.address, IFNULL(a.address2, ' '), district) staffAddress,
					s1.last_update lastUpdate
				FROM staff s1 
				INNER JOIN address a 
				ON s1.address_id = a.address_id;
			 */
			
			// sql문 작성. 한줄로 적지 않고 여러줄로 적으려면 이런식으로 적자. 
			// 공백 잘 확인 
			String sql = "SELECT"
					+ "	s1.staff_id staffId,"
					+ "	CONCAT(s1.first_name,' ',s1.last_name) staffName,"
					+ "	s1.store_id storeId,"
					+ "	s1.address_id addressId,"
					+ "	ifnull(s1.email, ' ') email,"
					+ "	s1.active active,"
					+ "	CONCAT(a.address, IFNULL(a.address2, ' '), district) staffAddress,"
					+ "	s1.last_update lastUpdate"
					+ " FROM staff s1"
					+ " INNER JOIN address a"
					+ " ON s1.address_id = a.address_id;";
			// (3) 쿼리를 저장하는 과정 
			stmt = conn.prepareStatement(sql);
			System.out.println(stmt+"//stmt <- StaffDao.java");
			
			// (4) 쿼리를 실행한 이후 결과값(테이블 모양의 ResultSet타입)을 리턴 
			rs = stmt.executeQuery();
			System.out.println(rs+"//rs <- StaffDao.java");
			
			// next() 메서드는 다음줄로 커서를 이동해서 읽을 값들이 존재하면 true, 아니면 false 반환 
			while(rs.next()) {
				// staff 테이블을 기준으로 뽑아온 컬럼들의 ResultSet -> Map<String,Object>로 변경  
				// 다형성 
				Map<String, Object> map = new HashMap<>(); 
				map.put("staffId", rs.getInt("staffId"));
				map.put("staffName", rs.getString("staffName"));
				map.put("storeId", rs.getInt("storeId"));
				map.put("addressId", rs.getInt("addressId"));
				map.put("email", rs.getString("email"));
				map.put("active", rs.getInt("active"));
				map.put("staffAddress", rs.getString("staffAddress"));
				map.put("lastUpdate", rs.getString("lastUpdate"));
				list.add(map);
			}
		} catch (Exception e) { 
		// ClassNotFoundException, SQLException 두개의 예외를 부모타입 Exception으로 처리 -> 다형성
		// catch 블록안에 printStackTrace() 메서드를 써줘야 무슨 예외인지 콘솔창에서 확인 가능 
			e.printStackTrace();
			System.out.println("예외 발생 //catch{} StaffDao.java");
		} finally {
		// try 블록에서 일어난 일에 관계없이 항상 실행 되어야 할 코드를 포함
		// 데이터베이스 자원 반환 
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// list 반환하니 받을 list 변수 필요함 
		return list;
	}
	
	// selectStaffList() 테스트 코드 
	// 단위 테스트 
	// dao 만들고 아래쪽에 main 메서드 만들어서 테스트 해보는게 정석적인 방법 
	public static void main(String args[]) {
		StoreDao dao = new StoreDao(); 
		// 다형성 사용 
		List<Map<String, Object>> list = dao.selectStoreList();
		
		// vo안에 테이블 하나의 기본적인 컬럼들은 만들었지만 조인하는 결과가 생기기 때문에 임시로 Map 만들었었음. 
		for(Map m : list) {
			System.out.print(m.get("staffId")+", ");
			System.out.print(m.get("staffName")+", ");
			System.out.print(m.get("storeId")+", ");
			System.out.print(m.get("addressId")+", ");
			System.out.print(m.get("email")+", ");
			System.out.print(m.get("active")+", ");
			System.out.print(m.get("staffAddress")+", ");
			System.out.print(m.get("lastUpdate"));
			System.out.println();
		}
	}
}
