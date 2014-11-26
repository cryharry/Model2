package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class ZipCodeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("ZipcodeAction");
		String dong = request.getParameter("dong");
		MemberDAO memberDAO = new MemberDAO();
		memberDAO.searchZipCode(dong);
		return null;
	}
}
