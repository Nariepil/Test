package com.cy.core.mycomponent;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cy.modules.g_user.model.GUser;
import com.cy.util.Constants;
import com.cy.util.FreeUrlConfigHelper;
import com.cy.util.LogUtils;
import com.cy.util.RequestUtils;

/**
 * 后台用户权限判断：
 * 1.检测后台用户是否存在，不存在则返回登录页面
 * 2.菜单（利用action去拦截）权限
 * 3.按钮（利用action去拦截）权限
 * 4.判断用户的停用、启用
 */
public class UserRightInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//String requestUrl = request.getRequestURL().toString();//http://127.0.0.1:8080/da/g_user/gUser/editGUser/2
		//String requestUrI = request.getRequestURI();///da/g_user/gUser/editGUser/2
		String requestContextPath = request.getContextPath();///da
		//String requestServletPath = request.getServletPath();///g_user/gUser/editGUser/2
		GUser gUser = null;
		
		//如果没有用户，则跳转登录界面
		Long loginUserId = (Long)request.getSession().getAttribute("loginUserId");
		if(loginUserId == null){
			LogUtils.logError("用户丢失");
			topUrl(response, requestContextPath);
			return false;
		}
		//判断用户是否存在
		gUser = (GUser) request.getSession().getAttribute("loginUser");
		if(gUser == null){
			LogUtils.logError("用户丢失");
			topUrl(response, requestContextPath);
			return false;
		}
		RequestUtils.setUser(request, gUser);
		
    	//超级用户：admin
		//if(gUser.getGrade() != 1 && !isIgnoreURL(requestUrl)){
			//first : 判断URL是否包含用户functionUrl
			/*List<GRight> functionList = JsonUtil.fromJson(tedisUtil.tedisGetString(functionKey),new TypeReference<List<GRight>>(){});
			boolean isCanVisit = true;//是否能够访问
	        for (int i = 0; functionList!=null && i < functionList.size(); i++) {
	            if(null!=functionList.get(i).getMenuurl() && isMatch(requestServletPath, functionList.get(i).getMenuurl())){
            		isCanVisit = true;
                    break;
	            }
	        }*/
			
			
			/*List<GRight> disabledRightList = JsonUtil.fromJson(tedisUtil.tedisGetString(disabledRightKey),new TypeReference<List<GRight>>(){});
			List<GRight> userRightList = JsonUtil.fromJson(tedisUtil.tedisGetString(userRightKey),new TypeReference<List<GRight>>(){});
			List<String> disabledButtonIdList = new ArrayList<String>();
			boolean isCanVisit = false;//是否能够访问
	        for (int i = 0; userRightList!=null && i < userRightList.size(); i++) {
	            if(null!=userRightList.get(i).getMenuurl() && isMatch(requestServletPath, userRightList.get(i).getMenuurl())){
            		isCanVisit = true;
            		for(int j = 0; disabledRightList!=null && j < disabledRightList.size(); j++){
            			if(StringUtils.isNotEmpty(disabledRightList.get(j).getButtonId()) && disabledRightList.get(j).getCode().startsWith(userRightList.get(i).getCode())){
            				disabledButtonIdList.add(disabledRightList.get(j).getButtonId());
            			}
            		}
            		request.setAttribute("disabledButtonIdList", disabledButtonIdList);
                    break;
	            }
	        }
	        
	        if(!isCanVisit){
	        	if(disabledRightList != null && disabledRightList.size() > 0){
	        		for (int i = 0; disabledRightList!=null && i < disabledRightList.size(); i++) {
		        		if(null!=disabledRightList.get(i).getMenuurl() && isMatch(requestServletPath, disabledRightList.get(i).getMenuurl())){
		        			isCanVisit = false;
		        			break;
		        		}
		        		if(i == disabledRightList.size()-1){
		        			isCanVisit = true;
		        		}
		        	}
	        	}else{
	        		isCanVisit = true;
	        	}
	        	
	        }
	        if(!isCanVisit){
	        	response.sendError(HttpServletResponse.SC_FORBIDDEN, "访问了没有权限的链接");
	        	//throw new RuntimeException(gUser.getName()+"访问了没有权限的链接"+requestUrl);
	        	return false;
	        }else{
	        	
	        }*/
		//}
		return true;
	}

	private void topUrl(HttpServletResponse response, String requestContextPath) throws IOException {
		java.io.PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script>");
		out.println("window.open('"+requestContextPath+ Constants.NOUSER_REDIRECTTO+"','_top')");
		out.println("</script>");
		out.println("</html>");
		//response.sendRedirect(requestContextPath+Constants.NOUSER_REDIRECTTO);
		String CONTENT_TYPE = "text/html; charset=GBK";
		response.setContentType(CONTENT_TYPE);
	}

	public void postHandle(HttpServletRequest request,
			   HttpServletResponse response, Object handler,
			   ModelAndView modelAndView) throws IOException{
	}
	
	private boolean isIgnoreURL(String requestUrl){
		List<String> notValidUrlList = FreeUrlConfigHelper.getInstance().getValues();
		
		for(String notValidUrl : notValidUrlList){
			if(requestUrl.contains(notValidUrl)){
				return true;
			}
		}
		
		return false;
	}
	/**
	 * 验证是否允许匹配
	 * @param s1: g_user/gUser/editGUser/2
	 * @param s2: g_user/gUser/editGUser/?
	 * @return
	 */
	public static boolean isMatch(String s1, String s2){
		s1 = s1.substring(1);
		Boolean flag = true;
		if(s2.indexOf("?") == -1){
			if(!s2.equals(s1)){
				flag = false;
			}
		}else{
			String[] s1Array = s1.split("/");
			String[] s2Array = s2.split("/");
			if(s1Array.length == s2Array.length){
				for (int i = 0; i < s2Array.length; i++) {
					if("?".equals(s2Array[i])){
						continue;
					}else{
						if(s2Array[i].equals(s1Array[i])){
							continue;
						}else{
							flag = false;
							break;
						}
					}
				}
			}else{
				flag = false;
			}
		}
		return flag;
	}
}