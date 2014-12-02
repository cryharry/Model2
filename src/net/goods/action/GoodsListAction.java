package net.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.goods.db.GoodsDAO;

public class GoodsListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GoodsListAction");
		//카테고리  item 파라미터 가져오기
		String item=request.getParameter("item");
		if(request.getParameter("item")==null){
			item="all";
		}
		//디비작업파일 net.goods.db   GoodsDAO
		//디비객체 생성  goodsdao
		GoodsDAO goodsdao=new GoodsDAO();
		//List itemList =  메서드 호출 getItemList(item) 
		List itemList=goodsdao.getItemList(item);
		//저장 itemList
		request.setAttribute("itemList", itemList);
		request.setAttribute("item", item);
		//이동 ./goods/goods_list.jsp
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./goods/goods_list.jsp");
		return forward;
	}

}
