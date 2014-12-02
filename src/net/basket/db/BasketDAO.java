package net.basket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.goods.db.GoodsBean;

public class BasketDAO {
	private Connection getConnection() throws Exception{
		Connection con=null;
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
		con=ds.getConnection();
		return con;
	}
	public void basketAdd(BasketBean basketbean){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int num=0;
		try {
			//1,2 디비연결
			con=getConnection();
			// num    max(basket_num)
			sql="select max(basket_num) from basket";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				num=rs.getInt(1)+1;
			}else{
				num=1;
			}
			//3 insert   now()
			sql="insert into basket(basket_num,basket_member_id,basket_goods_num,basket_goods_amount,basket_goods_size,basket_goods_color,basket_date) values(?,?,?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, basketbean.getBasket_member_id());
			pstmt.setInt(3, basketbean.getBasket_goods_num());
			pstmt.setInt(4, basketbean.getBasket_goods_amount());
			pstmt.setString(5, basketbean.getBasket_goods_size());
			pstmt.setString(6, basketbean.getBasket_goods_color());
			//4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
	}
	public Vector getBasketList(String id){
		Vector vector=new Vector();
		List basketlist=new ArrayList();
		List goodslist=new ArrayList();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rs2=null;
		String sql="";
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql  basket
			sql="select * from basket where basket_member_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4 rs 저장
			rs=pstmt.executeQuery();
			//5 데이터가 있으면  rs => 장바구니 자바빈 
			//     3 goods 4 rs1 저장 5 rs1=>상품자바빈 저장
			//     basketlist 한칸  goodslist한칸 
			//  vector 첫번째 basketlist  두번째 goodslist
			while(rs.next()){
				BasketBean basketbean=new BasketBean();
				GoodsBean goodsbean=new GoodsBean();
				
				basketbean.setBasket_date(rs.getString("basket_date"));
				basketbean.setBasket_goods_amount(rs.getInt("basket_goods_amount"));
				basketbean.setBasket_goods_color(rs.getString("basket_goods_color"));
				basketbean.setBasket_goods_num(rs.getInt("basket_goods_num"));
				basketbean.setBasket_goods_size(rs.getString("basket_goods_size"));
				basketbean.setBasket_member_id(rs.getString("basket_member_id"));
				basketbean.setBasket_num(rs.getInt("basket_num"));
				
				sql="select * from goods where g_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, basketbean.getBasket_goods_num());
				rs2=pstmt.executeQuery();
				if(rs2.next()){
					goodsbean.setName(rs2.getString("g_name"));
					goodsbean.setPrice(rs2.getInt("g_price"));
					goodsbean.setImage(rs2.getString("g_image"));
				}
				basketlist.add(basketbean);
				goodslist.add(goodsbean);
			}
			vector.add(basketlist);
			vector.add(goodslist);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs2!=null)try{rs2.close();}catch(SQLException ex){}
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return vector;
	}
	
}//클래스
