package net.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardReplyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("BoardRplyAction");
		// 한글처리
		request.setCharacterEncoding("utf8");
		// 자바빈 객체 생성
		BoardBean boardBean = new BoardBean();
		// 폼 => 자바빈 저장
		boardBean.setNum(Integer.parseInt(request.getParameter("num")));
		boardBean.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		boardBean.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		boardBean.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		boardBean.setName(request.getParameter("name"));
		boardBean.setSubject(request.getParameter("subject"));
		boardBean.setContent(request.getParameter("content"));
		boardBean.setPasswd(request.getParameter("passwd"));
		boardBean.setDate(new Timestamp(System.currentTimeMillis()));
		// DB 객체 생성
		BoardDAO boardDAO = new BoardDAO();
		// 메서드 호출 reInsertBoard(boardBean);
		boardDAO.reInsertBoard(boardBean);
		// 이동
		ActionForward forward = new ActionForward();
		forward.setPath("/BoardList.bo");
		forward.setRedirect(false);
		return forward;
	}
}
