package net.admin.goods.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.admin.goods.db.AdminGoodsDAO;
import net.admin.goods.db.GoodsBean;

public class GoodsListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GoodsListAction");
		// 디비객체 생성
		AdminGoodsDAO agd = new AdminGoodsDAO();
		// ArrayList list = getGoodsList()
		ArrayList<GoodsBean> list = agd.getGoodsList();
		// 저장
		request.setAttribute("list", list);
		// 이동 ./admingoods/ag_list.jsp
		ActionForward forward = new ActionForward();
		forward.setPath("./admingoods/ag_list.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
