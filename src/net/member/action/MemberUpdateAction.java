package net.member.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdateAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdateAction");
		
		request.setCharacterEncoding("utf8");
		
		MemberBean memberBean = new MemberBean();
		memberBean.setId(request.getParameter("id"));
		memberBean.setPasswd(request.getParameter("passwd"));
		memberBean.setAge(Integer.parseInt(request.getParameter("age")));
		memberBean.setEmail(request.getParameter("email"));
		memberBean.setGender(request.getParameter("gender"));
		memberBean.setName(request.getParameter("name"));
		
		MemberDAO memberDAO = new MemberDAO();
		int check = memberDAO.updateMember(memberBean);
		ActionForward forward = new ActionForward();
		if(check == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디 없음');");
			out.println("location.href='./MemberUpdate.me'");
			out.println("</script>");
			out.close();
			return null;
		} else if(check == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 틀림');");
			out.println("location.href='./MemberUpdate.me'");
			out.println("</script>");
			out.close();
			return null;
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('수정 성공');");
		out.println("location.href='./MemberMain.me'");
		out.println("</script>");
		out.close();
		return null;
	}
}
