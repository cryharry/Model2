/*
 * ActionForward.java 2014. 11. 19.
 *
 * Copyright itwillbs All rights Reserved.
 */
package net.member.action;

/**
 * Class 내용 기술
 *
 * @author : user
 *
 */
public class ActionForward {
	private boolean isRedirect = false;
	// 이동방식 forwoard/ sendReidrect
	private String path = null; //주소
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
