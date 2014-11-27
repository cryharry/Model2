package net.member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class ZipCodeAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("ZipcodeAction");
		// 한글처리
		request.setCharacterEncoding("utf8");
		// searchdong = dong 가져오기
		String searchdong = request.getParameter("dong");
		// 디비객체 생성
		MemberDAO memberDAO = new MemberDAO();
		// 메서드 호출 List zipcodeList = searchZipCode(searchdong)
		List zipcodeList = new ArrayList();
		zipcodeList = memberDAO.searchZipCode(searchdong);
		// 저장
		request.setAttribute("zipcodeList", zipcodeList);
		// 이동 zipcode.jsp
		ActionForward forward = new ActionForward();
		forward.setPath("/Zipcode.me");
		forward.setRedirect(false);
		return forward;
	}
}
