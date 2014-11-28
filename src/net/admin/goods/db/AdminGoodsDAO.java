package net.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.sun.jmx.snmp.Timestamp;

public class AdminGoodsDAO {
	Context initCtx = null;
	DataSource ds = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	private Connection dbConn() throws Exception {
		initCtx = new InitialContext();
		ds =(DataSource)initCtx.lookup("java:comp/env/jdbc/jspbeginner");
		conn = ds.getConnection();
		return conn;
	}

	public int insertGoods(GoodsBean goodsBean) {
		int result = 0;
		int num = 0;
		try {
			conn = dbConn();
			sql = "SELECT MAX(g_num) FROM GOODS";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1)+1;
			} else {
				num = 1;
			}
			// insert
			sql = "INSERT INTO GOODS VALUES(?,?,?,?,?,?,?,?,?,?,now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, goodsBean.getG_category());
			pstmt.setString(3, goodsBean.getG_name());
			pstmt.setString(4, goodsBean.getG_content());
			pstmt.setString(5, goodsBean.getG_size());
			pstmt.setString(6, goodsBean.getG_color());
			pstmt.setInt(7, goodsBean.getG_amount());
			pstmt.setInt(8, goodsBean.getG_price());
			pstmt.setString(9, goodsBean.getG_image());
			pstmt.setInt(10, goodsBean.getG_best());
			// result = 실행
			result = pstmt.executeUpdate(); //성공하면 result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)try{rs.close();}catch(Exception e){}
			if(pstmt!=null)try{pstmt.close();}catch(Exception e){}
			if(conn!=null)try{conn.close();}catch(Exception e){}
		}
		return result;
	}

	public ArrayList<GoodsBean> getGoodsList() {
		ArrayList<GoodsBean> list = new ArrayList<GoodsBean>();
		try {
			conn = dbConn();
			sql = "SELECt * FROM GOODS ORDER BY g_num";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				GoodsBean goodsBean = new GoodsBean();
				goodsBean.setG_amount(rs.getInt("g_amount"));
				goodsBean.setG_best(rs.getInt("g_best"));
				goodsBean.setG_category(rs.getString("g_category"));
				goodsBean.setG_color(rs.getString("g_color"));
				goodsBean.setG_content(rs.getString("g_content"));
				goodsBean.setG_date(rs.getString("g_date"));
				goodsBean.setG_image(rs.getString("g_image"));
				goodsBean.setG_name(rs.getString("g_name"));
				goodsBean.setG_num(rs.getInt("g_num"));
				goodsBean.setG_price(rs.getInt("g_price"));
				goodsBean.setG_size(rs.getString("g_size"));
				list.add(goodsBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)try{rs.close();}catch(Exception e){}
			if(pstmt!=null)try{pstmt.close();}catch(Exception e){}
			if(conn!=null)try{conn.close();}catch(Exception e){}
		}
		return list;
	}
}
