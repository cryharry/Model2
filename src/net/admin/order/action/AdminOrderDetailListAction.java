package net.admin.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.admin.order.db.AdminOrderDAO;


public class AdminOrderDetailListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("AdminOrderDetailListAction");
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
String num=request.getParameter("num");
AdminOrderDAO orderdao=new AdminOrderDAO();
//List goods_order_detail_list =   getOrderList(id)
		List goods_order_detail_list= orderdao.getOrderDetailList(num);
		//저장 goods_order_detail_list
		request.setAttribute("goods_order_detail_list", goods_order_detail_list);
		//이동 ./order/order_detail_list.jsp
		forward.setRedirect(false);
		forward.setPath("./adminorder/order_detail_list.jsp");
				return forward;
	}

}
