package net.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.goods.db.GoodsBean;
import net.goods.db.GoodsDAO;

public class GoodsDetailAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//int num = goods_num   item  가져오기
		int num=Integer.parseInt(request.getParameter("goods_num"));
		String item=request.getParameter("item");
		//디비객체 생성 goodsdao
		GoodsDAO goodsdao=new GoodsDAO();
		//자바빈 goodsbean = 메서드 호출 getGoods(num)
		GoodsBean goodsbean=goodsdao.getGoods(num);
		//저장  item  goodsbean
		request.setAttribute("item", item);
		request.setAttribute("goodsbean", goodsbean);
		//이동 ./goods/goods_detail.jsp
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./goods/goods_detail.jsp");
		return forward;
	}
}
