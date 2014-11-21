package net.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardAddAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("BoardAddAction");
		
		BoardBean boardBean = new BoardBean();
		boardBean.setName(request.getParameter("name"));
		boardBean.setSubject(request.getParameter("subject"));
		boardBean.setContent(request.getParameter("content"));
		boardBean.setPasswd(request.getParameter("passwd"));
		boardBean.setDate(new Timestamp(System.currentTimeMillis()));
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.insertBoard(boardBean);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		return forward;
	}
}
