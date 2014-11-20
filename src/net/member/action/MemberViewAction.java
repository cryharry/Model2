package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberViewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("MemberViewAction");
		
		ActionForward forward = new ActionForward();
		// 아이디에 해당되는 정보를 디비에서 가져오기
		// 세션값 가져오기
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		// 세션값 없으면 MemberLogin.me 이동
		if(id == null) {
			forward.setPath("/MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		// 디비연결
		// 디비객체 생성 memberDAO
		MemberDAO memberDAO = new MemberDAO();
		// MemberBean memberBean = 메서드호출 getMember(id)
		MemberBean memberBean = memberDAO.getMember(id);
		// 정보저장 memberBean
		request.setAttribute("memberBean", memberBean);
		// 이동 ./member/info.jsp
		forward.setPath("./member/info.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
