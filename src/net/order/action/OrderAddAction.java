package net.order.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketDAO;
import net.order.db.OrderBean;
import net.order.db.OrderDAO;

public class OrderAddAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//세션 제어하기
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		//세션값 없으면 ./MemberLogin.me
		ActionForward forward=new ActionForward();
		if(id==null){
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		//basketdao 객체 생성
		BasketDAO basketdao=new BasketDAO();
		//vector 메서드 호출 getBasketList(id)
		Vector vector=basketdao.getBasketList(id);
		// basketlist    goodslist
		
		//net.order.db  OrderBean
		//객체 생성 orderbean
		OrderBean orderbean=new OrderBean();
		// 폼,세션 => 자바빈 저장 
		request.setCharacterEncoding("utf-8");
		orderbean.setOrder_member_id(id);
		orderbean.setOrder_memo(request.getParameter("order_memo"));
		orderbean.setOrder_receive_name(request.getParameter("order_receive_name"));
		orderbean.setOrder_trade_payer(request.getParameter("order_trade_payer"));
		orderbean.setOrder_trade_type("온라인입금");
		
		//net.order.db  OrderDAO
		OrderDAO orderdao=new OrderDAO();
		//결제	 lg u+ 전자결제	 http://ecredit.uplus.co.kr/
		// 메일보내기 , 문자보내기
		
		// 메서드 addOrder(orderbean,vector)
		orderdao.addOrder(orderbean, vector);
		//장바구니 삭제
		//상품 개수 수정 
		//이동 ./OrderOk.or
		forward.setRedirect(true);
		forward.setPath("./OrderOk.or");
		return forward;
	}
}




