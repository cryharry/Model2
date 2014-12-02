package net.basket.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketDAO;

public class BasketListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//세션 값 가져오기
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		//세션 제어
		ActionForward forward=new ActionForward();
		if(id==null){
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		//디비객체 생성  basketdao
		BasketDAO basketdao=new BasketDAO();
		//Vector vector =  메서드 호출 getBasketList(id)
		Vector vector=basketdao.getBasketList(id);
		List basketlist=(ArrayList)vector.get(0);
		List goodslist=(ArrayList)vector.get(1);
		//저장 basketlist  goodslist
		request.setAttribute("basketlist", basketlist);
		request.setAttribute("goodslist", goodslist);
		//이동 ./order/basket_list.jsp
		forward.setRedirect(false);
		forward.setPath("./order/basket_list.jsp");
		return forward;
	}
}
