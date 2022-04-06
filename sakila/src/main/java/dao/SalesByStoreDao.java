package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.SalesByStore;

public class SalesByStoreDao {
	public List<SalesByStore> selectSalesByStoreListByPage(int beginRow, int rowPerPage) {
		// List... 
		List<SalesByStore> list = new ArrayList<SalesByStore>();
		
		// DB 자원 준비 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		// DB 연결 메서드 호출 
		conn = DBUtil.getConnection();
		
		String sql = "select store, manager, total_sales totalSales from sales_by_store order by store limit ?,?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				SalesByStore sbs = new SalesByStore();
				sbs.setStore(rs.getString("store"));
				sbs.setStore(rs.getString("manager"));
				sbs.setTotal_sales(rs.getInt("totalSales"));
				list.add(sbs);
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
	
	// 행의 총 갯수를 구하기 위한 메서드 구현 
		public int totalRow() {
			// 전체 행수 0으로 초기화 
			int totalRow = 0; 
			
			//DB자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs =null;
			
			String sql = "select count(*) cnt from sales_by_store";

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
