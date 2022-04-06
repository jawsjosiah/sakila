package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.SalesByFilmCategory;

public class SalesByFilmCategoryDao {
	public List<SalesByFilmCategory> selectSalesByFilmCategoryByPage(int beginRow, int rowPerPage) {
		List<SalesByFilmCategory> list = new ArrayList<>(); 
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		conn = DBUtil.getConnection();
		
		String sql = "SELECT category, total_sales totalSales FROM sales_by_film_category ORDER BY category limit ?,?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				SalesByFilmCategory sbfc = new SalesByFilmCategory();
				sbfc.setCategory(rs.getString("category"));
				sbfc.setTotalSales(rs.getInt("totalSales"));
				list.add(sbfc);
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
		
		String sql = "SELECT count(*) cnt FROM sales_by_film_category";
		
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
			}
		}
		
		return totalRow;
	}
}
