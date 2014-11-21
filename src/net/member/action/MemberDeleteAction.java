package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("MemberDeleteAction");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String passwd = request.getParameter("passwd");
		
		ActionForward forward = new ActionForward();
		
		MemberDAO memberDAO = new MemberDAO();
		int check = memberDAO.deleteMember(id, passwd);
		
		if(check == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		} else if(check == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 틀림');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return null;
		}
		session.invalidate();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제 성공');");
		out.println("location.href='./MemberLogin.me'");
		out.println("</script>");
		out.close();
		return null;
	}
}
