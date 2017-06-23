package com.cy.util;

import javax.servlet.http.HttpServletRequest;

import com.cy.modules.g_user.model.GUser;

/**
 * request公用类
 * @author guozd
 *
 */
public class RequestUtils {
	/**
	 * 公用获得后台用户信息
	 * @param request
	 * @return
	 */
	public static GUser getUser(HttpServletRequest request){
		GUser gUser = (GUser) request.getAttribute("loginUser");
		return gUser;
	}
	/**
	 * 公用获得后台用户信息
	 * @param request
	 */
	public static void setUser(HttpServletRequest request, GUser gUser){
		request.setAttribute("loginUser",gUser);//将对象放到request中
		request.getSession().setAttribute("loginUserId",gUser.getId());
		request.getSession().setAttribute("loginUser",gUser);
	}
}
