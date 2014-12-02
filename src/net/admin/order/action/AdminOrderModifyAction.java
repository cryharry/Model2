package net.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.admin.order.db.AdminOrderDAO;

public class AdminOrderModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("AdminOrderModifyAction");
		
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
				//파라미터 값 가져오기
				// order_trade_num  //order_status
				String num = request.getParameter("order_trade_num");
				int status = Integer.parseInt(request.getParameter("order_status"));
				//수정할 내용이 여러개 자바빈 저장
				
				//디비객체 생성
				AdminOrderDAO aod = new AdminOrderDAO();
				//메서드호출 modifyOrder(num,status)
				aod.modifyOrder(num, status);
				
				//이동 ./AdminOrderList.ao
				forward.setPath("./AdminOrderList.ao");
				forward.setRedirect(true);
				return forward;
	}

}
