package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.ActorInfo;

public class ActorInfoDao {
	public List<ActorInfo> selectActorInfoListByPage(int beginRow, int rowPerPage) {
		
		List<ActorInfo> list = new ArrayList<ActorInfo>();
		
		// DB 자원 준비 
		Connection conn = null; 
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		
		// static 메서드라 객체 생성 안해도 된다. 
		conn = DBUtil.getConnection();
		
		String sql = "select actor_id actorId, first_name firstName, last_name lastName, film_info filmInfo from actor_info order by actor_id limit ?,?"; // * 대신 다른 컬럼 사용할 것 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			 
			// rs.next() ... list.add 
			while(rs.next()) {
				ActorInfo ai = new ActorInfo();
				ai.setActorId(rs.getInt("actorId"));
				ai.setFirstName(rs.getString("firstName"));
				ai.setLastName(rs.getString("lastName"));
				ai.setFilmInfo(rs.getString("filmInfo"));
				list.add(ai);
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
				e.printStackTrace();
			}
		}
		return list; 
	}
	
	// 행의 총 갯수를 구하기 위한 메서드 구현 
	public int selectTotalRow() {
		// 전체 행수 0으로 초기화 
		int totalRow = 0; 
		
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =null;
		
		String sql = "select count(*) cnt from actor_info";

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
				// DB 자원 반납 
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return totalRow; 
		// 전체 행 수 리턴 
	}
}
