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
		// 한글처리
		request.setCharacterEncoding("utf8");
		// 자바빈 객체생성 boardBean
		BoardBean boardBean = new BoardBean();
		// 폼 => 자바빈 저장
		boardBean.setName(request.getParameter("name"));
		boardBean.setSubject(request.getParameter("subject"));
		boardBean.setContent(request.getParameter("content"));
		boardBean.setPasswd(request.getParameter("passwd"));
		boardBean.setDate(new Timestamp(System.currentTimeMillis()));
		boardBean.setIp(request.getRemoteAddr());
		// 디비객체 생성 boardDAO
		BoardDAO boardDAO = new BoardDAO();
		// 메서드 호출 insertBoard(boardBean)
		boardDAO.insertBoard(boardBean);
		// 이동 ./BoardList.bo
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		return forward;
	}
}
