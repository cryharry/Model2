package net.admin.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.order.db.OrderBean;

public class AdminOrderDAO {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql="";
	
	private Connection dbConn() throws Exception{
		Connection conn=null;
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
		conn=ds.getConnection();
		return conn;
	}
		
	public List getOrderList(){
		List orderlist=new ArrayList();
		try {
			//1,2 디비연결
			conn=dbConn();
			//3 id에 해당하는 주문테이블에서 
			// order_trade_num그룹으로 묶어서 
			// order_trade_num order_trade_type
			// sum(order_sum_money) order_status 
			// order_date 주문가져오기
			sql="select order_trade_num,order_trade_type,sum(order_sum_money),order_status,order_date from goods_order  group by order_trade_num";
			pstmt=conn.prepareStatement(sql);
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
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
		}
		return orderlist;
	}
	public List getOrderDetailList(String num){
		List orderlist=new ArrayList();
		try {
			//1,2 디비연결
			conn=dbConn();
			//3
			sql="select * from goods_order where order_trade_num=?";
			pstmt=conn.prepareStatement(sql);
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
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
		}
		return orderlist;
	}

	public void modifyOrder(String num, int status) {
		try {
			conn = dbConn();
			sql = "UPDATE goods_order SET order_status = ? WHERE order_trade_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, status);
			pstmt.setString(2, num);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
		}
	}
}
