package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.FilmList;

public class FilmDao {
	public List<FilmList> selectFilmListSearch(int beginRow, int rowPerPage, String category, String rating, double price, int length, String title, String actor) {		
		List<FilmList> list = new ArrayList<FilmList>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			// 동적쿼리
			String sql = "SELECT fid,title,description,category,price,length,rating,actors FROM film_list WHERE title LIKE ? AND actors LIKE ?";
			if(category.equals("") && rating.equals("") && price==-1 && length==-1) {
				sql += " ORDER BY fid LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if(category.equals("") && rating.equals("") && price==-1 && length!=-1) { // length만 입력되었다
				if(length == 0) {
					sql += " AND length<60 ORDER BY fid LIMIT ?, ?";
				} else if(length == 1) {
					sql += " AND length>=60 ORDER BY fid LIMIT ?, ?";
				}
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if(category.equals("") && rating.equals("") && price!=-1 && length==-1) {
				sql += " AND price=? ORDER BY fid LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setDouble(3, price);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if(category.equals("") && rating.equals("") && price!=-1 && length!=-1) {
				if(length == 0) {
					sql += " AND price=? AND length<60 ORDER BY fid LIMIT ?, ?";
				} else if(length == 1) {
					sql += " AND price=? AND length>=60 ORDER BY fid LIMIT ?, ?";
				}
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setDouble(3, price);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if(category.equals("") && !rating.equals("") && price==-1 && length==-1) {
				sql += " AND rating=? ORDER BY fid LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, rating);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if(category.equals("") && !rating.equals("") && price==-1 && length!=-1) {
				if(length == 0) {
					sql += " AND rating=? AND length<60 ORDER BY fid LIMIT ?, ?";
				} else if(length == 1) {
					sql += " AND rating=? AND length>=60 ORDER BY fid LIMIT ?, ?";
				}
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, rating);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if(category.equals("") && !rating.equals("") && price!=-1 && length==-1) {
				sql += " AND rating=? AND price=? ORDER BY fid LIMIT ?, ?";
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, rating);
				stmt.setDouble(4, price);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if(category.equals("") && !rating.equals("") && price!=-1 && length!=-1) {
				if(length == 0) {
					sql += " AND rating=? AND price=? AND length<60 ORDER BY fid LIMIT ?, ?";
				} else if(length == 1) {
					sql += " AND rating=? AND price=? AND length>=60 ORDER BY fid LIMIT ?, ?";
				}
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, rating);
				stmt.setDouble(4, price);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else if(!category.equals("") && rating.equals("") && price==-1 && length==-1) {
				sql += " AND category=? ORDER BY fid LIMIT ?, ?";
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else if(!category.equals("") && rating.equals("") && price==-1 && length!=-1) {
				
				if(length == 0) {
					sql += " AND category=? AND length<60 ORDER BY fid LIMIT ?, ?";
				} else if(length == 1) {
					sql += " AND category=? AND length>=60 ORDER BY fid LIMIT ?, ?";
				}
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if(!category.equals("") && rating.equals("") && price!=-1 && length==-1) {
				sql += " AND category=? AND price=? ORDER BY fid LIMIT ?, ?";
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setDouble(4, price);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if(!category.equals("") && rating.equals("") && price!=-1 && length!=-1) {
				if(length == 0) {
					sql += " AND category=? AND price=? AND length<60 ORDER BY fid LIMIT ?, ?";
				} else if(length == 1) {
					sql += " AND category=? AND price=? AND length>=60 ORDER BY fid LIMIT ?, ?";
				} 
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setDouble(4, price);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if(!category.equals("") && !rating.equals("") && price==-1 && length==-1) {
				sql += " AND category=? AND rating=? ORDER BY fid LIMIT ?, ?";
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setString(4, rating);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else if(!category.equals("") && !rating.equals("") && price==-1 && length!=-1) {
				if(length==0) {
					sql += " AND category=? AND rating=? AND length<60 ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
					sql += " AND category=? AND rating=? AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setString(4, rating);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else if(!category.equals("") && !rating.equals("") && price!=-1 && length==-1) {
				sql += " AND category=? AND rating=? AND price=? ORDER BY fid LIMIT ?, ?";
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setString(4, rating);
				stmt.setDouble(5, price);
				stmt.setInt(6, beginRow);
				stmt.setInt(7, rowPerPage);
			} else if(!category.equals("") && !rating.equals("") && price!=-1 && length!=-1) {
				if(length==0) {
					sql += " AND category=? AND rating=? AND price=? AND length<60 ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
					sql += " AND category=? AND rating=? AND price=? AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setString(4, rating);
				stmt.setDouble(5, price);
				stmt.setInt(6, beginRow);
				stmt.setInt(7, rowPerPage);
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				FilmList f = new FilmList();
				f.setFid(rs.getInt("fid"));
				f.setTitle(rs.getString("title"));
				f.setDescription(rs.getString("description"));
				f.setCategory(rs.getString("category"));
				f.setPrice(rs.getDouble("price"));
				f.setLength(rs.getInt("length"));
				f.setRating(rs.getString("rating"));
				f.setActors(rs.getString("actors"));
				list.add(f);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	
	public List<Double> selectfilmPriceList() {
	      List<Double> list = new ArrayList<Double>();
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      conn = DBUtil.getConnection();
	      String sql = "SELECT DISTINCT price FROM film_list ORDER BY price";
	      try {
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            list.add(rs.getDouble("price"));
	         }
	      } catch (SQLException e) {
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
		public int selectTotalRow(String category, String rating, double price, int length, String title, String actor) {
			// 전체 행수 0으로 초기화 
			int totalRow = 0; 
			
			//DB자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs =null;
			
			conn = DBUtil.getConnection();
			
			
			
			try { 
				String sql = "select count(*) cnt from film_list WHERE title LIKE ? AND actors LIKE ?";
				if(category.equals("") && rating.equals("") && price==-1 && length==-1) {
					sql += " ORDER BY fid";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
				} else if(category.equals("") && rating.equals("") && price==-1 && length!=-1) { // length만 입력되었다
					if(length == 0) {
						sql += " AND length<60 ORDER BY fid";
					} else if(length == 1) {
						sql += " AND length>=60 ORDER BY fid";
					}
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
				} else if(category.equals("") && rating.equals("") && price!=-1 && length==-1) {
					sql += " AND price=? ORDER BY fid";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setDouble(3, price);
				} else if(category.equals("") && rating.equals("") && price!=-1 && length!=-1) {
					if(length == 0) {
						sql += " AND price=? AND length<60 ORDER BY fid";
					} else if(length == 1) {
						sql += " AND price=? AND length>=60 ORDER BY fid";
					}
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setDouble(3, price);
				} else if(category.equals("") && !rating.equals("") && price==-1 && length==-1) {
					sql += " AND rating=? ORDER BY fid";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, rating);
					
				} else if(category.equals("") && !rating.equals("") && price==-1 && length!=-1) {
					if(length == 0) {
						sql += " AND rating=? AND length<60 ORDER BY fid";
					} else if(length == 1) {
						sql += " AND rating=? AND length>=60 ORDER BY fid";
					}
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, rating);
					
				} else if(category.equals("") && !rating.equals("") && price!=-1 && length==-1) {
					sql += " AND rating=? AND price=? ORDER BY fid";
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, rating);
					stmt.setDouble(4, price);
					
				} else if(category.equals("") && !rating.equals("") && price!=-1 && length!=-1) {
					if(length == 0) {
						sql += " AND rating=? AND price=? AND length<60 ORDER BY fid";
					} else if(length == 1) {
						sql += " AND rating=? AND price=? AND length>=60 ORDER BY fid";
					}
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, rating);
					stmt.setDouble(4, price);
					
				} else if(!category.equals("") && rating.equals("") && price==-1 && length==-1) {
					sql += " AND category=? ORDER BY fid";
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, category);
					
				} else if(!category.equals("") && rating.equals("") && price==-1 && length!=-1) {
					
					if(length == 0) {
						sql += " AND category=? AND length<60 ORDER BY fid";
					} else if(length == 1) {
						sql += " AND category=? AND length>=60 ORDER BY fid";
					}
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, category);
					
				} else if(!category.equals("") && rating.equals("") && price!=-1 && length==-1) {
					sql += " AND category=? AND price=? ORDER BY fid";
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, category);
					stmt.setDouble(4, price);
					
				} else if(!category.equals("") && rating.equals("") && price!=-1 && length!=-1) {
					if(length == 0) {
						sql += " AND category=? AND price=? AND length<60 ORDER BY fid";
					} else if(length == 1) {
						sql += " AND category=? AND price=? AND length>=60 ORDER BY fid";
					} 
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, category);
					stmt.setDouble(4, price);
					
				} else if(!category.equals("") && !rating.equals("") && price==-1 && length==-1) {
					sql += " AND category=? AND rating=? ORDER BY fid";
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, category);
					stmt.setString(4, rating);
					
				} else if(!category.equals("") && !rating.equals("") && price==-1 && length!=-1) {
					if(length==0) {
						sql += " AND category=? AND rating=? AND length<60 ORDER BY fid";
					} else if(length == 1) {
						sql += " AND category=? AND rating=? AND length>=60 ORDER BY fid";
					}
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, category);
					stmt.setString(4, rating);
					
				} else if(!category.equals("") && !rating.equals("") && price!=-1 && length==-1) {
					sql += " AND category=? AND rating=? AND price=? ORDER BY fid";
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, category);
					stmt.setString(4, rating);
					stmt.setDouble(5, price);
					
				} else if(!category.equals("") && !rating.equals("") && price!=-1 && length!=-1) {
					if(length==0) {
						sql += " AND category=? AND rating=? AND price=? AND length<60 ORDER BY fid";
					} else if(length == 1) {
						sql += " AND category=? AND rating=? AND price=? AND length>=60 ORDER BY fid";
					}
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, category);
					stmt.setString(4, rating);
					stmt.setDouble(5, price);
					
				}
				
				
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
