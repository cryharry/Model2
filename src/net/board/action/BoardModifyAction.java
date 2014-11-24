package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardModifyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("BoardModifyAction");
		request.setCharacterEncoding("utf8");
		String pageNum = request.getParameter("pageNum");
		
		BoardBean boardBean = new BoardBean();
		boardBean.setNum(Integer.parseInt(request.getParameter("num")));
		boardBean.setName(request.getParameter("name"));
		boardBean.setSubject(request.getParameter("subject"));
		boardBean.setContent(request.getParameter("content"));
		boardBean.setPasswd(request.getParameter("passwd"));
		
		BoardDAO boardDAO = new BoardDAO();
		int check = boardDAO.updateBoard(boardBean);
		if(check == 0 || check == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('수정성공');");
		out.println("location.href='./BoardDetailAction.bo?num="+boardBean.getNum()+"&pageNum="+pageNum+"'");
		out.println("</script>");
		out.close();
		return null;
	}
}
