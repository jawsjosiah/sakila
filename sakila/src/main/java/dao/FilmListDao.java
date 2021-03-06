package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.FilmList;

public class FilmListDao {
	public List<FilmList> selectFilmListByPage(int beginRow, int rowPerPage) {
		
		List<FilmList> list = new ArrayList<FilmList>();
		
		// DB 자원 준비 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		conn = DBUtil.getConnection();
		
		String sql = "select fid, title, description, category, price, length, rating, actors from film_list order by fid limit ?,?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				FilmList fl = new FilmList();
				fl.setFid(rs.getInt("fid"));
				fl.setTitle(rs.getString("title"));
				fl.setDescription(rs.getString("description"));
				fl.setCategory(rs.getString("category"));
				fl.setPrice(rs.getDouble("price"));
				fl.setLength(rs.getInt("length"));
				fl.setRating(rs.getString("rating"));
				fl.setActors(rs.getString("actors"));
				list.add(fl);
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
			
			String sql = "select count(*) cnt from film_list";

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
