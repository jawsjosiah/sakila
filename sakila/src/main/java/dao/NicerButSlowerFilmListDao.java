package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.NicerButSlowerFilmList;

public class NicerButSlowerFilmListDao {
	public List<NicerButSlowerFilmList> selectNicerButSlowerFilmListByPage(int beginRow, int rowPerPage) {
		List<NicerButSlowerFilmList> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		conn = DBUtil.getConnection();
		
		String sql = "SELECT fid, title, description, category, price, length, rating, actors FROM nicer_but_slower_film_list ORDER BY fid limit ?,?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				NicerButSlowerFilmList nbsfl = new NicerButSlowerFilmList();
				nbsfl.setFid(rs.getInt("fid"));
				nbsfl.setTitle(rs.getString("title"));
				nbsfl.setDescription(rs.getString("description"));
				nbsfl.setCategory(rs.getString("category"));
				nbsfl.setPrice(rs.getDouble("price"));
				nbsfl.setLength(rs.getInt("length"));
				nbsfl.setRating(rs.getString("rating"));
				nbsfl.setActors(rs.getString("actors"));
				list.add(nbsfl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
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
	
	public int selectTotalRow() {
		int totalRow = 0; 
		
		Connection conn = null; 
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		
		conn = DBUtil.getConnection();
		
		String sql = "SELECT count(*) cnt from nicer_but_slower_film_list";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				totalRow = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return totalRow; 
	}
}
