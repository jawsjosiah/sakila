package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.DBUtil;
import vo.ActorInfo;

public class ActorInfoDao {
	public List<ActorInfo> selectActorInfoListByPage(int beginRow, int rowPerPage) {
		Connection conn = null; 
		conn = DBUtil.getConnection();
		String sql = "select * from actor_info order by actor_id limit ?,?"; // * 대신 다른 컬럼 사용할 것 
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		
		try {
			stmt = conn.prepareStatement(sql);
			// ? 
			rs = stmt.executeQuery();
			// rs.next() ... list.add 
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
		return null; 
	}
}
