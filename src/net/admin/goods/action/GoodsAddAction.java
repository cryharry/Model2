package net.admin.goods.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.admin.goods.db.AdminGoodsDAO;
import net.admin.goods.db.GoodsBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class GoodsAddAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GoodsAddAction");
		request.setCharacterEncoding("utf8");
		// MultipartRequest multi객체 생성
		String realPath = request.getRealPath("upload");
		System.out.println(realPath);
		int maxSize = 1024*1024*5;
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		// 자바빈 객체 생성
		GoodsBean goodsBean = new GoodsBean();
		// multi => 자바빈 저장
		goodsBean.setG_amount(Integer.parseInt(multi.getParameter("g_amount")));
		goodsBean.setG_best(Integer.parseInt(multi.getParameter("g_best")));
		goodsBean.setG_category(multi.getParameter("g_category"));
		goodsBean.setG_color(multi.getParameter("g_color"));
		goodsBean.setG_content(multi.getParameter("g_content"));
		goodsBean.setG_name(multi.getParameter("g_name"));
		goodsBean.setG_price(Integer.parseInt(multi.getParameter("g_price")));
		goodsBean.setG_size(multi.getParameter("g_size"));
		
		List savefiles = new ArrayList();
		Enumeration files = multi.getFileNames();
		while(files.hasMoreElements()){
			String name = (String)files.nextElement();
			System.out.println(name);
			if(files.hasMoreElements()){
				savefiles.add(multi.getFilesystemName(name)+",");
			} else {
				savefiles.add(multi.getFilesystemName(name));
			}
		}
		System.out.println(savefiles);
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<savefiles.size();i++){
			sb.append(savefiles.get(i));
		}
		System.out.println(sb.toString());
		goodsBean.setG_image(sb.toString());
		// 디비객체 생성
		AdminGoodsDAO agd = new AdminGoodsDAO();
		// 메서드 호출 insertGoods(goodsBean);
		//int result = agd.insertGoods(goodsBean);
		int result = agd.insertGoods(goodsBean);
		// result 0보다 작거나 같다 등록실패 뒤로이동
		if(result <= 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		// 이동 ./GoodsList.ag
		ActionForward forward = new ActionForward();
		forward.setPath("./GoodsList.ag");
		forward.setRedirect(true);
		return forward;
	}
}
