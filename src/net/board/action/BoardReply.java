package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardReply implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("BoardReply");
		int num = Integer.parseInt(request.getParameter("num"));
		int re_ref = Integer.parseInt(request.getParameter("re_ref"));
		int re_lev = Integer.parseInt(request.getParameter("re_lev"));
		int re_seq = Integer.parseInt(request.getParameter("re_seq"));
		
		request.setAttribute("num", num);
		request.setAttribute("re_ref", re_ref);
		request.setAttribute("re_lev", re_lev);
		request.setAttribute("re_seq", re_seq);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/reply.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
