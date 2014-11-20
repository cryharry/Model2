/*
 * MemberFrontController.java 2014. 11. 19.
 *
 * Copyright itwillbs All rights Reserved.
 */
package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class 내용 기술
 *
 * @author : user
 *
 */
public class MemberFrontController extends HttpServlet {

    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("서블릿의 doProcess()메서드");
        // URL주소에서 가상의 주소 뽑기
        // http://localhost:8888/Model2/MemberJoin.me
        String RequestURI = request.getRequestURI();
        System.out.println(RequestURI); // URI=>/Model2/MemberJoin.me
        String contextPath = request.getContextPath();
        System.out.println(contextPath); // ContextPath = /Model2
        String command = RequestURI.substring(contextPath.length());
        System.out.println(command);
        
        
        ActionForward forward = null;
        Action action = null;
        // 가상의 주소 비교
        if(command.equals("/MemberJoin.me")){
            System.out.println("가상의 주소 /MemberJoin.me");
            // 회원가입폼 연결
            // response.sendRedirect("./member/insertForm.jsp");
            // 이동정보저장
            forward = new ActionForward();
            forward.setPath("./member/insertForm.jsp");
            forward.setRedirect(false); // forward
        } else if(command.equals("/MemberJoinAction.me")) {
        	// MemberJoinAction 자바 파일 메서드호출()
        	System.out.println("가상의 주소 /MemberJoinAction.me");
        	action = new MemberJoinAction();
        	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
        } else if(command.equals("/MemberLogin.me")){
        	System.out.println("가상의 주소 /MemberLogin.me");
        	forward = new ActionForward();
        	forward.setPath("./member/loginForm.jsp");
        	forward.setRedirect(false);
        } else if(command.equals("/MemberLoginAction.me")) {
        	// MemberLoginAction 파일 만들기
        	action = new MemberLoginAction();
        	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
        } else if(command.equals("/MemberMain.me")) {
        	forward = new ActionForward();
        	forward.setPath("./member/main.jsp");
        	forward.setRedirect(false);
        } else if(command.equals("/MemberViewAction.me")) {
        	// MemberViewAction 파일 생성, 객체 생성, 메서드 호출
        	action = new MemberViewAction();
        	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
        } else if(command.equals("/MemberListAction.me")) {
        	action = new MemberListAction();
        	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        // 이동 따로 수행
        if(forward != null) {
        	if(forward.isRedirect()){
        		// true		sendRedirect
        		response.sendRedirect(forward.getPath());
        	} else {
        		// false	forward
        		RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
        		dis.forward(request, response);
        	}
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }
   
}
