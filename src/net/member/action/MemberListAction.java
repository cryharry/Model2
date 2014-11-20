package net.member.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.member.db.MemberDAO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("MemberListAction");
		ActionForward forward = new ActionForward();
		//세션 가져오기 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id == null || !id.equals("admin")) {
			forward.setPath("/MemberLogin.me");
			forward.setRedirect(true);
		}
		// 디비 객체 생성 메서드 호출
		MemberDAO memberDAO = new MemberDAO();
		List memberList = memberDAO.getMembers();
		// 데이터 저장
		request.setAttribute("memberList", memberList);
		// 이동 list.jsp
		forward.setPath("./member/list.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
