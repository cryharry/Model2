package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// ...Pro.jsp
	// 처리 Action
	// 처리할 추상메서드 정의
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
