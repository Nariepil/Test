package com.cy.modules.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cy.modules.g_right.model.GRight;
import com.cy.modules.g_right.service.GRightService;
import com.cy.modules.g_user.model.GUser;
import com.cy.modules.g_user.service.GUserService;
import com.cy.util.Constants;
import com.cy.util.CusAccessObjectUtil;
import com.cy.util.Md5Util;
import com.cy.util.RandomValidateCode;
import com.cy.util.RequestUtils;
import com.cy.util.ReturnMessage;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
    private GUserService gUserService;
	@Autowired
	private GRightService gRightService;
	
	@RequestMapping(value = "")
	public String toIndex(HttpServletRequest request,Model model){
		return "admin/login1";
	}
	/**登录*/
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMessage login(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		ReturnMessage rm = new ReturnMessage();
		rm.setFlag("false");
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String addIp   = CusAccessObjectUtil.getIpAddress(request);
    	System.out.println("IP:::::"+addIp);
		String verifyCode = request.getParameter("verifyCode");
		String verificationNum = (String) request.getSession().getAttribute("verificationNum");
		/*if (!verificationNum.toUpperCase().equals(verifyCode.toUpperCase())) {
			//验证码输入不正确
			rm.setFlag("false");
			rm.setMessage("验证码不正确！");
			return rm;
		}*/
    	if(username !=null && password != null){
    		password = Md5Util.encrypt(password);
    		GUser gUser2 = gUserService.login(username,password);
    		if(gUser2 == null){
    			rm.setFlag("false");
    			rm.setMessage("用户名密码有误！");
    			return rm;
    		}/*else 
    			if(gUser2.getLockIp()!=null && !gUser2.getLockIp().equals(addIp)){
    			rm.setFlag("false");
    			rm.setMessage("该用户不能在该ip登录！");
    			return rm;
    		}*/else
    		{
    			//是否启用 ： 0：停用，停用时拒绝登录，1：启用
    			if(gUser2.getIsused() == 0){
    				rm.setFlag("false");
        			rm.setMessage("该用户处于停用状态！");
    			}else{
    				rm.setFlag("true");
    				request.getSession().setAttribute("loginUserId", gUser2.getId());
    			}
    		}
    	}
        return rm;
    }
	
	/**
	 * 验证码
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/verifyCode/{random}", method = RequestMethod.GET)
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            //验证码的key为当前浏览器的会话id，没有设置有效期
        	String verificationNum = randomValidateCode.getRandcode(request, response);// 输出图片方法
        	request.getSession().setAttribute("verificationNum", verificationNum);
        } catch (Exception e) {
            //logger.error(e.getMessage());
        }
    }
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toLoginView(Model model, HttpServletRequest request, HttpServletResponse response) {
		Long loginUserId = (Long) request.getSession().getAttribute("loginUserId");
    	if(loginUserId == null){
    		return "redirect:/login";
    	}
    	GUser user = gUserService.load(Long.valueOf(loginUserId));
    	if(user == null){
    		return "redirect:/login";
    	}
    	RequestUtils.setUser(request, user);
    	
    	
    	List<GRight> gRightList = null;
		//超级用户：admin
		if(user.getGrade() == 1){
			gRightList = gRightService.selectAllHead();
		}else{
			gRightList = gRightService.selectUserMenuHead(user.getId());
		}
		Map<String , List> ejMenuMap = new HashMap<String, List>();
		for (GRight gRight : gRightList) {
			List<GRight> list = null;
			//超级用户：admin
			if(user.getGrade() == 1){
				Long id = gRight.getId();
				GRight gRight1 = new GRight();
				gRight1.setParentid(id);
				gRight1.setIsleaf(1);
				list = gRightService.selectByEntity(gRight1);
			}else{
				list = gRightService.selectUserMenuLeaf(user.getId(), gRight.getId());
				//普通用户 没有 《用户管理》/《系统管理》的菜单权限 ， 需要去掉,在前台循环展示时去判断
			}
			ejMenuMap.put("menu"+gRight.getCode(), list);
		}
		model.addAttribute("ejMenuMap", ejMenuMap);	
		model.addAttribute("menu", gRightList);
		model.addAttribute("menuId", "indexManage");
		GUser gUser = RequestUtils.getUser(request);
		model.addAttribute("gUser", gUser);	
		return "modules/index/index";
    }
	/**
     * 跳转到首页
     * 
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	request.getSession().invalidate();
        return "redirect:"+Constants.NOUSER_REDIRECTTO;
    }
}
