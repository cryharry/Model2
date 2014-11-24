package net.board.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("BoardListAction");
		
		BoardDAO boardDAO = new BoardDAO();
		//int count= getBoardCount()   //count(*)
		int count = boardDAO.getBoardCount();
		// 한 페이지 보여줄 글수
		int pageSize = 5;
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
		List<BoardBean> boardList = null;
		if(count!= 0) {
			boardList = boardDAO.getBoards(startRow, pageSize);
		}
		// 전체 페이지수 구하기 글55 한 페이지에 보여줄 글 수 10
		int pageCount = count/pageSize+(count%pageSize==0?0:1); // 55/10 = 5+1(나머지있을경우)
		// 한화면에 보여줄 페이지수 설정
		int pageBlock = 3;
		// 시작페이지 번호 구하기
		int startPage = ((int)(currentPage/pageBlock)-(currentPage%pageBlock==0?1:0))*pageBlock+1; 
		// 끝페이지 번호 구하기
		int endPage = startPage+pageBlock-1;
		if(endPage > pageCount) {
		    endPage = pageCount;
		}
		int number = 0;
		number = count-(currentPage-1)*pageSize;
		
		List pageList = new ArrayList();
		pageList.add(count);
		pageList.add(currentPage);
		pageList.add(pageCount);
		pageList.add(startPage);
		pageList.add(endPage);
		pageList.add(number);
		pageList.add(pageBlock);
		
		request.setAttribute("pageList", pageList);
		request.setAttribute("boardList", boardList);
		//이동 list.jsp
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardListView.bo");
		forward.setRedirect(false);
		return forward;
	}
}
