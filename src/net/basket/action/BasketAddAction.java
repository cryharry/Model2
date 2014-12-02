package net.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketBean;
import net.basket.db.BasketDAO;

public class BasketAddAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
		request.setCharacterEncoding("utf-8");
		// net.basket.db   BasketBean 파일생성
		// basketbean  객체 생성 
		BasketBean basketbean=new BasketBean();
		// 폼 => basketbean 저장
		basketbean.setBasket_member_id(id);
		basketbean.setBasket_goods_num(Integer.parseInt(request.getParameter("num")));
		basketbean.setBasket_goods_amount(Integer.parseInt(request.getParameter("amount")));
		basketbean.setBasket_goods_color(request.getParameter("color"));
		basketbean.setBasket_goods_size(request.getParameter("size"));
		// net.basket.db  BasketDAO   파일 생성
		//객체 생성 basketdao
		BasketDAO basketdao=new BasketDAO();
		//   장바구니 저장  basketAdd(basketbean)
		basketdao.basketAdd(basketbean);
		//이동 ./BasketList.ba
		forward.setRedirect(true);
		forward.setPath("./BasketList.ba");
		return forward;
	}
}
