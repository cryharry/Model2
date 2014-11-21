package net.member.action;

import java.nio.channels.SeekableByteChannel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdate implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdateAction");
		// 파라미터값 가져오기
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MemberDAO memberDAO = new MemberDAO();
		MemberBean memberBean = memberDAO.getMember(id);
		request.setAttribute("memberBean", memberBean);
		ActionForward forward = new ActionForward();
		forward.setPath("./member/update.jsp");
		forward.setRedirect(false);
		return forward;
	}
}