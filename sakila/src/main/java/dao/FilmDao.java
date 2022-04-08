
package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			// out 파라미터의 자료형 설정 ( 데이터 타입을 생성 )  몇 개 남았는지 out 매개 변수 
			stmt.registerOutParameter(3, Types.INTEGER);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1)); // rs.getInt("inventory_id")
				System.out.println(rs.getInt(1) + " // rs.getInt(1)");
				// rs.getInt(1) 데이터 검증 
			}
			count = stmt.getInt(3); // 프로시저 3번쨰 out 변수 값
			
			System.out.println(count+" // count(FilmDao.filmNotInStock())");
			// 3번째 파라미터 count 데이터 검증 
			
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
			System.out.println(rs + " // rs(FilmDao.filmNotInStock()");
			while(rs.next()) {
				list.add(rs.getInt(1)); 
				// rs.getInt("inventory_id") 
				// rs.getInt(1) 받아오는 값 파악 
				
			}
			System.out.println(rs.getInt(2) + " // rs.getInt(1)");
			// rs.getInt(1) 데이터 검증 
			count = stmt.getInt(3);
			// 프로시저 3번째 out 변수 값 
			System.out.println(count+" // count(FilmDao.filmNotInStock())");
			// 3번째 파라미터 count 데이터 검증 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("count", count);
		return map; 
	}
	
	
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
		
		System.out.println(filmId+"번 영화는 "+storeId+"번 가게에 "+count+"개 대여됨");
		// 메서드가 올바로 동작했는지 확인 
		
		for(int id : list) {
			System.out.println(id);
		}
	}
	
	public Map<String, Object> rewardsReportCall(int minMonthlyPurchases, double minDollarAmountPurchased) {
		Map<String, Object> map = new HashMap<>();
		
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null; 
		
		List<Integer> list = new ArrayList<>();
		
		Integer count = 0;
		
		conn = DBUtil.getConnection();
		
		try {
			cstmt = conn.prepareCall("{CALL rewards_report(?,?,?)}");
			cstmt.setInt(1, minMonthlyPurchases);
			cstmt.setDouble(2, minDollarAmountPurchased);
			cstmt.registerOutParameter(3,Types.INTEGER);
			rs = cstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		map.put("list", list );
		map.put("count", count);
		return map;
	}
}

