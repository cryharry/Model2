package net.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BoardDAO boardDAO = new BoardDAO();
		//int count= getBoardCount()   //count(*)
		int count = boardDAO.getBoardCount();
		// 한 페이지 보여줄 글수
		int pageSize = 3;
		// 현 페이지 가져오기
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
		    pageNum = "1";
		}
		// 시작하는 행 구하기
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		// 끝나는 행 구하기
		int endRow = currentPage*pageSize;
		
		// Board 관련 List
		List boardList = boardDAO.getBoards(startRow, pageSize);
		request.setAttribute("boardList", boardList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardListView.bo");
		forward.setRedirect(false);
		return forward;
	}
}
