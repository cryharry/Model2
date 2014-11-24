package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardDetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("BoardDetailAction");
		
		int num =Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.updateReadCount(num);
		BoardBean boardBean =  boardDAO.getBoard(num);
		
		request.setAttribute("boardBean", boardBean);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/content.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
