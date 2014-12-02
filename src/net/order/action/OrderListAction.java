package net.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.order.db.OrderDAO;

public class OrderListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//세션값 제어하기
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		//세션값 없으면 ./MemberLogin.me
		ActionForward forward=new ActionForward();
		if(id==null){
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		//디비객체 생성 orderdao
		OrderDAO orderdao=new OrderDAO();
		//List goods_order_list =   getOrderList(id)
		List goods_order_list= orderdao.getOrderList(id);
		//저장 goods_order_list
		request.setAttribute("goods_order_list", goods_order_list);
		//이동 ./order/order_list.jsp
		forward.setRedirect(false);
		forward.setPath("./order/order_list.jsp");
		return forward;
	}
}




