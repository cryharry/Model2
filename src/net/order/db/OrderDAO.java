package net.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.basket.db.BasketBean;
import net.goods.db.GoodsBean;

public class OrderDAO {
	private Connection getConnection() throws Exception{
		Connection con=null;
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
		con=ds.getConnection();
		return con;
	}
	public void addOrder(OrderBean orderbean,Vector vector){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int num=0,ordernum=0;
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		try {
			//1,2 디비연결
			con=getConnection();
			// max(order_num) +1
			sql="select max(order_num) from goods_order";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				num=rs.getInt(1);
				ordernum=rs.getInt(1);
			}
			// vector=> basketlist  goodslist
			List basketlist=(ArrayList)vector.get(0);
			List goodslist=(ArrayList)vector.get(1);
			// for 3 insert  주문번호 구해서 insert
			ordernum=ordernum+1;
			for(int i=0;i<basketlist.size();i++){
				++num;
				BasketBean basketbean=(BasketBean)basketlist.get(i);
				GoodsBean goodsbean=(GoodsBean)goodslist.get(i);
				sql="insert into goods_order(order_num,order_trade_num,order_goods_num,order_goods_name,order_goods_amount,order_member_id,order_sum_money,order_date,order_status) values(?,?,?,?,?,?,?,now(),?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setString(2, sdf.format(cal.getTime()).toString()+"-"+(ordernum));//주문번호
				pstmt.setInt(3, basketbean.getBasket_goods_num());
				pstmt.setString(4, goodsbean.getName());
				pstmt.setInt(5, basketbean.getBasket_goods_amount());
				pstmt.setString(6, orderbean.getOrder_member_id());
				pstmt.setInt(7, basketbean.getBasket_goods_amount()*goodsbean.getPrice());
				pstmt.setInt(8, 0);//0 대기중 1발송준비 2 발송완료 3배송중 4배송완료 5 주문취소
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
	}
	
	public List getOrderList(String id){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		List orderlist=new ArrayList();
		try {
			//1,2 디비연결
			con=getConnection();
			//3 id에 해당하는 주문테이블에서 
			// order_trade_num그룹으로 묶어서 
			// order_trade_num order_trade_type
			// sum(order_sum_money) order_status 
			// order_date 주문가져오기
			sql="select order_trade_num,order_trade_type,sum(order_sum_money),order_status,order_date from goods_order where order_member_id=? group by order_trade_num";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4 rs 저장
			rs=pstmt.executeQuery();
			//5 rs => orderbean저장 => orderlist한칸 저장 
			while(rs.next()){
				OrderBean orderbean=new OrderBean();
				orderbean.setOrder_trade_num(rs.getString("order_trade_num"));
				orderbean.setOrder_trade_type(rs.getString("order_trade_type"));
				orderbean.setOrder_sum_money(rs.getInt(3)); //order_sum_money
				orderbean.setOrder_status(rs.getInt("order_status"));
				orderbean.setOrder_date(rs.getDate("order_date"));
				
				orderlist.add(orderbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return orderlist;
	}
	public List getOrderDetailList(String num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		List orderlist=new ArrayList();
		try {
			//1,2 디비연결
			con=getConnection();
			//3
			sql="select * from goods_order where order_trade_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, num);
			//4
			rs=pstmt.executeQuery();
			//5
			while(rs.next()){
				OrderBean orderbean=new OrderBean();
				orderbean.setOrder_trade_num(rs.getString("order_trade_num"));
				orderbean.setOrder_trade_type(rs.getString("order_trade_type"));
				orderbean.setOrder_status(rs.getInt("order_status"));
				orderbean.setOrder_date(rs.getDate("order_date"));
				orderbean.setOrder_sum_money(rs.getInt("order_sum_money"));
				orderbean.setOrder_goods_name(rs.getString("order_goods_name"));
				orderbean.setOrder_goods_amount(rs.getInt("order_goods_amount"));
				
				orderlist.add(orderbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return orderlist;
	}
	
}//클래스
