package net.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GoodsDAO {
	private Connection getConnection() throws Exception{
		Connection con=null;
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
		con=ds.getConnection();
		return con;
	}
	
	public List getItemList(String item){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List itemList=new ArrayList();
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql
			StringBuffer sql=new StringBuffer();
			sql.append(" select * from goods ");
			if(item.equals("all")){
				
			}else if(item.equals("best")){
				sql.append(" where g_best=1 ");
			}else{
				sql.append(" where g_category=? "); 
			}
			pstmt=con.prepareStatement(sql.toString());
			if(item.equals("all")){
			}else if(item.equals("best")){
			}else{
				pstmt.setString(1, item);
			}
			//4 rs 실행 저장
			rs=pstmt.executeQuery();
			//5 rs 데이터 있으면 
			// rs => 자바빈 생성저장=>itemList한칸저장
			while(rs.next()){
				GoodsBean goodsbean=new GoodsBean();
				goodsbean.setAmount(rs.getInt("g_amount"));
				goodsbean.setBest(rs.getInt("g_best"));
				goodsbean.setCategory(rs.getString("g_category"));
				goodsbean.setColor(rs.getString("g_color"));
				goodsbean.setContent(rs.getString("g_content"));
				goodsbean.setDate(rs.getString("g_date"));
				goodsbean.setImage(rs.getString("g_image"));
				goodsbean.setName(rs.getString("g_name"));
				goodsbean.setNum(rs.getInt("g_num"));
				goodsbean.setPrice(rs.getInt("g_price"));
				goodsbean.setSize(rs.getString("g_size"));
				
				itemList.add(goodsbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return itemList;
	}
	public GoodsBean getGoods(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		GoodsBean goodsbean=null;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql num에 해당하는 상품정보 가져오기
			sql="select * from goods where g_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//4 rs 실행 저장
			rs=pstmt.executeQuery();
			//5 rs데이터있으면 자바빈객체 생성 
			//   rs=> 자바빈 저장
			if(rs.next()){
				goodsbean=new GoodsBean();
				goodsbean.setAmount(rs.getInt("g_amount"));
				goodsbean.setBest(rs.getInt("g_best"));
				goodsbean.setCategory(rs.getString("g_category"));
				goodsbean.setColor(rs.getString("g_color"));
				goodsbean.setContent(rs.getString("g_content"));
				goodsbean.setDate(rs.getString("g_date"));
				goodsbean.setImage(rs.getString("g_image"));
				goodsbean.setName(rs.getString("g_name"));
				goodsbean.setNum(rs.getInt("g_num"));
				goodsbean.setPrice(rs.getInt("g_price"));
				goodsbean.setSize(rs.getString("g_size"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return goodsbean;
	}
	
}//클래스
