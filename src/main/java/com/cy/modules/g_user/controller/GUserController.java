package com.cy.modules.g_user.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cy.base.MyBaseController;
import com.cy.base.MySearchFilter;
import com.cy.beans.CommonDto;
import com.cy.beans.Pagination;
import com.cy.modules.g_user.model.GUser;
import com.cy.modules.g_user.service.GUserService;
import com.cy.util.Collections3;
import com.cy.util.Constants;
import com.cy.util.CusAccessObjectUtil;
import com.cy.util.Md5Util;
import com.cy.util.RequestUtils;
import com.cy.util.ReturnMessage;

/**
 *GUser(管理员表)管理
 * autogenerate V1.0 by dongao
 */
@Controller
@RequestMapping("/g_user/gUser")
public class GUserController extends MyBaseController {
    @Autowired
    private GUserService gUserService;
    
    /**初始化gUser管理页面*/
	@RequestMapping(value = {"/gUserList",""}, method = RequestMethod.GET)
	public String gUserlist(Model model, HttpServletRequest request) {
		//TODO...
		model.addAttribute("menuId", "gUserManage");
		return "modules/g_user/gUser_list";
	}
	/**
	 * ajax分页查询
	 */
	@RequestMapping(value = "/findGUsersAjax")
	public @ResponseBody
	Pagination<GUser> findGUsersAjax(@ModelAttribute("gUser") GUser gUser, HttpServletRequest request) {
		gUser.setPageParameter(getpagePageParameter());
		//TODO...
		Pagination<GUser> pagination = gUserService.findByPage(gUser);
		return pagination;
	}
	/**
	 * ajax 支持组合查询的单表分页：页面驱动命名规则参考springside
	 */
	@RequestMapping(value = "/customPageAjax")
	public @ResponseBody
	Pagination<GUser> customPageAjax(HttpServletRequest request) {
		Pagination<GUser> pagination = gUserService.commonBySqlPage(CommonDto.build(super.getFilters(), super.getOrders(),GUser.class ,super.getpagePageParameter()));
		return pagination;
	}
	/**增加前的准备*/
    @RequestMapping(value = "/toAddGUser")
    public String toAddGUser(HttpServletRequest request,Model model) {
    	//TODO...
    	String parameter = request.getParameter("orgCode");
    	model.addAttribute("orgCode", parameter);
        return "modules/g_user/gUser_add";
    }
    /**执行增加*/
    @RequestMapping(value = "/saveGUser", method = RequestMethod.POST)
    public String saveGUser(@ModelAttribute("gUser") GUser gUser, HttpServletRequest request,RedirectAttributes redirectAttributes) {
        //TODO...
    	gUser.setLoginid(gUser.getName());
        gUserService.save(gUser);
        redirectAttributes.addFlashAttribute("message", "增加成功");
        return "redirect:/g_user/gUser/";
    }
    /**更新之前的准备**/
    @RequestMapping(value = "/editGUser/{id}")
    public String editGUser(@PathVariable("id") Long id, HttpServletRequest request,Model model) {
    	GUser gUser = gUserService.load(id);
        request.setAttribute("gUser", gUser);
        return "modules/g_user/gUser_edit";
    }
    /**执行修改*/
    @RequestMapping(value = "/updateGUser", method = RequestMethod.POST)
    public String updateGUser(@ModelAttribute("gUser") GUser gUser, HttpServletRequest request,RedirectAttributes redirectAttributes) {
    	gUserService.update(gUser);
    	redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/g_user/gUser/";
    }
    /**批量删除*/
    @RequestMapping(value = "/batchDeleteGUser")
    @ResponseBody
    public String batchDeleteGUser(@RequestParam(value = "ids[]", required = true) Long[] ids,RedirectAttributes redirectAttributes) {
        for(Long id:ids){
			gUserService.delete(id);
        }
        return "true";
    }
/*  //状态更新,如果有需要,请打开注释
    @RequestMapping(value = "/updatetStatus", method = RequestMethod.POST)
    @ResponseBody
    public String batchUpdatetStatus(
    		@RequestParam(value = "status", required = true) Integer status,
			@RequestParam(value = "ids[]", required = true) Long[] ids) {
    	for(Long id:ids){
    		GUser gUser=gUserService.load(id);
    		gUser.setStatus(status);
    		gUserService.update(gUser);
    	}
        return "true";
    }*/
    
    /**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出GUser对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getGUser(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id != -1) {
			model.addAttribute("gUser", gUserService.load(id));
		}
	}
	
	
	/**
	 * loginCode唯一性验证，不区分用户名、登录用户编码（简称）；2个字段合在一起的唯一性
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/loginCodeUniqueValid", method =  {RequestMethod.GET })
	@ResponseBody
	public ReturnMessage loginCodeUniqueValid(HttpServletRequest request) throws UnsupportedEncodingException {
		String name = request.getParameter("loginCode");
		String idStr = request.getParameter("id");
		ReturnMessage rm = new ReturnMessage();
		if(StringUtils.isEmpty(name)){
			rm.setFlag("false");
			rm.setMessage("不能为空！");
		}else{
			name = name.trim();
			String sql = null;
	    	int count = gUserService.loginCodeUniqueValid(name,idStr);
	    	if(count > 0){
	    		rm.setFlag("false");
	    		rm.setMessage("已经存在!");
	    	}else{
	    		rm.setFlag("true");
	    	}
		}
		return rm;
	}
	
    /**
	 * 重置密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/resetPassword", method =  {RequestMethod.POST })
	@ResponseBody
	public ReturnMessage resetPassword(@RequestParam(value = "id") Long id, HttpServletRequest request) {
		ReturnMessage rm = new ReturnMessage();
		rm.setFlag("true");
		if(id == null){
			rm.setFlag("false");
			rm.setMessage("未选中用户！");
		}else{
			GUser user = new GUser();
			user.setId(id);
			user.setPassword(Md5Util.encrypt(Constants.USER_REGISTER_PASSWORD));
			gUserService.update(user);
		}
		return rm;
	}
	 /**批量启用
	 * @throws IOException */
    @RequestMapping(value = "/enableUser")
    @ResponseBody
    public ReturnMessage enableUser(@RequestParam(value = "ids[]", required = true) Long[] ids,RedirectAttributes redirectAttributes) throws IOException {
    	ReturnMessage rm = new ReturnMessage();
    	rm.setFlag("true");
        for(Long id:ids){
    		if(id == null){
    			rm.setFlag("false");
    			rm.setMessage("未选中用户！");
    		}else{
    			GUser user = gUserService.load(id);
    			if(user == null){
    				rm.setFlag("false");
    				rm.setMessage("该用户不存在！");
    			}else if(user.getIsused()==1){
    				rm.setFlag("false");
    				rm.setMessage("该用户已经处于启用状态！");
    			}else{
    				GUser userParam = new GUser();
    				userParam.setId(id);
    				userParam.setIsused(1);
    				gUserService.update(userParam);
    				//updateRedisGUser(id);
    				rm.setMessage("该用户启用成功！");
    			}
    		}
        }
        return rm;
    }
    /**批量禁用
     * @throws IOException */
    @RequestMapping(value = "/disableUser")
    @ResponseBody
    public ReturnMessage disableUser(@RequestParam(value = "ids[]", required = true) Long[] ids,RedirectAttributes redirectAttributes) throws IOException {
    	ReturnMessage rm = new ReturnMessage();
    	rm.setFlag("true");
    	for(Long id:ids){
    		if(id == null){
    			rm.setFlag("false");
    			rm.setMessage("未选中用户！");
    		}else{
    			GUser user = gUserService.load(id);
    			if(user == null){
    				rm.setFlag("false");
    				rm.setMessage("该用户不存在！");
    			}else if(user.getIsused()==0){
    				rm.setFlag("false");
    				rm.setMessage("该用户已经处于禁用状态！");
    			}else{
    				GUser userParam = new GUser();
    				userParam.setId(id);
    				userParam.setIsused(0);
    				gUserService.update(userParam);
    				//updateRedisGUser(id);
    				rm.setMessage("该用户禁用成功！");
    			}
    		}
    	}
    	return rm;
    }
	
	 /**跳转到修改密码界面**/
    @RequestMapping(value = "/toMPwdView",method =  {RequestMethod.GET })
    public String toMPwdView( HttpServletRequest request,Model model) {
    	GUser gUser = RequestUtils.getUser(request);
        request.setAttribute("gUser", gUser);
        return "modules/g_user/gUser_modifyPassword";
    }
    /**修改密码**/
    @RequestMapping(value = "/modifyPassword")
    @ResponseBody
    public ReturnMessage modifyPassword( HttpServletRequest request,Model model) {
    	ReturnMessage rm = new ReturnMessage();
		rm.setFlag("true");
    	String oldPassword = request.getParameter("oldPassword");
    	String newPassword = request.getParameter("newPassword");
    	GUser gUser = RequestUtils.getUser(request);
    	if(StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(oldPassword)){
    		rm.setFlag("false");
    		rm.setMessage("密码不能为空！");
    		return rm;
    	}
    	if(gUser.getPassword().equals(oldPassword)){
    		gUser.setPassword(newPassword);
    		gUserService.update(gUser);
    	}else{
    		rm.setFlag("false");
    		rm.setMessage("旧密码不正确！");
    	}
    	return rm;
    }
    /**跳转到ip设置页面**/
    @RequestMapping(value = "/toBindIp",method =  {RequestMethod.GET })
    public String toBindIp( HttpServletRequest request,Model model) {
    	GUser gUser = RequestUtils.getUser(request);
    	request.setAttribute("gUser", gUser);
    	request.setAttribute("currentIp",CusAccessObjectUtil.getIpAddress(request));
    	return "modules/g_user/gUser_bindIp";
    }
    /**修改绑定ip地址**/
    @RequestMapping(value = "/bindIp")
    @ResponseBody
    public ReturnMessage bindIp( HttpServletRequest request,Model model) {
    	ReturnMessage rm = new ReturnMessage();
    	rm.setFlag("true");
    	String newIpAddr = request.getParameter("newIpAddr");
    	GUser gUser = RequestUtils.getUser(request);
    	if(StringUtils.isEmpty(newIpAddr)||isValidIP(newIpAddr)){
//    		gUser.setLockIp(newIpAddr);
    		gUserService.update(gUser);
    	}else{
    		rm.setFlag("false");
    		rm.setMessage("ip地址格式非法！");
    	}
    	return rm;
    }
/*  //状态更新,如果有需要,请打开注释
    @RequestMapping(value = "/updatetStatus", method = RequestMethod.POST)
    @ResponseBody
    public String batchUpdatetStatus(
    		@RequestParam(value = "status", required = true) Integer status,
			@RequestParam(value = "ids[]", required = true) Long[] ids) {
    	for(Long id:ids){
    		GUser gUser=gUserService.load(id);
    		gUser.setStatus(status);
    		gUserService.update(gUser);
    	}
        return "true";
    }*/
    
    public static boolean isValidIP(String ipAddress)   

    {   

          String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";    

          Pattern pattern = Pattern.compile(ip);    

          Matcher matcher = pattern.matcher(ipAddress);    

          return matcher.matches();    

    } 
    /**
	 * 校验工具名称
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/validaName")
	public @ResponseBody String validaToolName(HttpServletRequest request, Model model) {
		String name = request.getParameter("param");

		String info = "验证通过！";
		String status = "y";
		List<MySearchFilter> filters = new ArrayList<MySearchFilter>();
		filters.add(MySearchFilter.filter("name", MySearchFilter.Operator.EQ, Collections3.getValue(name)));
		String sql=MySearchFilter.getCountSql(filters,GUser.class);
		int count = gUserService.commonCountBySql(sql);
		if(count>0){
			status = "n";
			info = "该用户名已存在";
		}

		return "{ \"info\":\""+info+"\", \"status\":\""+status+"\" }";
	}

}