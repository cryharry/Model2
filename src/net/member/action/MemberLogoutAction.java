package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("MemberLogOut");
		// 세션값 삭제
		HttpSession session = request.getSession();
		session.invalidate();
		// 이동
		/* Forward로 이동
			ActionForward forward = new ActionForward();
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		*/
		// script로 이동
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('로그아웃');");
		out.println("location.href='./MemberLogin.me'");
		out.println("</script>");
		out.close();
		return null;
	}// 메서드
}//클래스