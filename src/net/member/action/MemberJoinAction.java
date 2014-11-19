package net.member.action;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.member.db.*;

public class MemberJoinAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinAction");
		// 한글처리
		request.setCharacterEncoding("utf8");
		// 자바빈 파일 패키지 net.member.db
		// 자바빈 객체 생성
		MemberBean memberBean = new MemberBean();
		// 폼 => 자바빈 저장
		memberBean.setAge(Integer.parseInt(request.getParameter("age")));
		memberBean.setEmail(request.getParameter("email"));
		memberBean.setGender(request.getParameter("gender"));
		memberBean.setId(request.getParameter("id"));
		memberBean.setName(request.getParameter("name"));
		memberBean.setPasswd(request.getParameter("passwd"));
		memberBean.setReg_date(new Timestamp(System.currentTimeMillis()));
		// 디비 객체 생성
		MemberDAO memberDAO = new MemberDAO();
		// 메서드 호출()
		memberDAO.insertMember(memberBean);
		// ActionForward 이동정보저장
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);
		// false jsp이동, 정보를 가지고 이동
		return forward;
	}
}
