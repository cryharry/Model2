package net.order.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketDAO;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class OrderStarAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("OrderStarAction");
		//세션값 가져오기
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		//세션값 없으면 ./MemberLogin.me
		ActionForward forward=new ActionForward();
		if(id==null){
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		//장바구니 정보가죠오기
		//basketdao 객체 생성
		BasketDAO basketdao=new BasketDAO();
		//vector=  getBasketList(id)메서드호출
		//  basketlist =  vector (0)
		//  goodslist = vector(1)
		Vector vector=basketdao.getBasketList(id);
		List basketlist=(ArrayList)vector.get(0);
		List goodslist=(ArrayList)vector.get(1);
		//memberdao 객체생성
		MemberDAO memberdao=new MemberDAO();
		// 자바빈 memberbean =   getMember(id)
		MemberBean memberbean=memberdao.getMember(id);
		
		//저장  basketlist  goodslist  memberbean
		request.setAttribute("basketlist",basketlist);
		request.setAttribute("goodslist", goodslist);
		request.setAttribute("memberbean", memberbean);
		//이동  ./order/goods_buy.jsp
		forward.setRedirect(false);
		forward.setPath("./order/goods_buy.jsp");
		return forward;
	}
}
