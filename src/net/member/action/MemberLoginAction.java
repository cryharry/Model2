package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberLoginAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("MemberLoginAction");
		// 파라미터값 가져오기 => 자바빈 저장
		MemberBean memberBean = new MemberBean();
		memberBean.setId(request.getParameter("id"));
		memberBean.setPasswd(request.getParameter("passwd"));
		// MemberDAO 객체 생성 memberDAO
		MemberDAO memberDAO = new MemberDAO();
		// int check = 메서드 호출 userCheck(id, passwd);
		int check = memberDAO.userCheck(memberBean.getId(), memberBean.getPasswd());
		// check == 1 세션값생성 "id" main.jsp이동
		// check == 0 "비밀번호틀림" /MemberLogin.me 이동
		// check == -1 "아이디없음" /MemberLogin.me 이동
		if(check == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호틀림');");
			out.println("location.href='./MemberLogin.me'");
			out.println("</script>");
			out.close();
			return null;
		} else if(check == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디없음');");
			out.println("location.href='./MemberLogin.me'");
			out.println("</script>");
			out.close();
			return null;
		}
		// 세션값생성 "id" MemberMain.me 이동
		HttpSession session = request.getSession();
		session.setAttribute("id", memberBean.getId());
		
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberMain.me");
		forward.setRedirect(true);
		return forward;
	}
}
