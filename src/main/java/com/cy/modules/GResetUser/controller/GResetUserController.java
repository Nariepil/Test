package com.cy.modules.GResetUser.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cy.base.MyBaseController;
import com.cy.modules.GResetUser.model.GResetPwdUser;
import com.cy.modules.g_right.service.GRightService;
import com.cy.modules.g_user.service.GUserService;
import com.cy.modules.g_userright.service.GUserrightService;

/**
 *GUser(用户表)管理
 * autogenerate V1.0 by dongao
 */
@Controller
@RequestMapping("/resetPwd/reset")
public class GResetUserController extends MyBaseController {
    @Autowired
    private GUserService gUserService;
    @Autowired
    private GUserrightService gUserrightService;
    @Autowired
    private GRightService gRightService;
    
    /**初始化gUser管理页面*/
/*	@RequestMapping(value = {"/resetpwd",""}, method = RequestMethod.GET)
	public String gUserlist(Model model, HttpServletRequest request) {
		//TODO...
		GResetPwdUser gResetPwdUser = new GResetPwdUser();
		String code = request.getParameter("code");
		String gtype = request.getParameter("type");
		String sql = null;
		if (StringUtil.isNotEmptyString(code)) {
			if(gtype.equals("1")){//教师
				List<AccTeacher>  teacherList = accTeacherService.findTeacherByCode(code);
				for (AccTeacher accteacher : teacherList) {
					gResetPwdUser.setName(accteacher.getTeacherName());
					gResetPwdUser.setId(accteacher.getId());
					gResetPwdUser.setLoginCode(code);
					gResetPwdUser.setType(gtype);
				}
			}else if(gtype.equals("2")){//学生
				List<AccStudent>  studentList= accStudentService.selectByCode(code);
				for (AccStudent accStudent : studentList) {
					gResetPwdUser.setId(accStudent.getId());
					gResetPwdUser.setName(accStudent.getStudentName());
					gResetPwdUser.setLoginCode(code);
					gResetPwdUser.setType(gtype);
				}
			}else if(gtype.equals("0")){//教务
				MySearchFilter[] filters = {MySearchFilter.filter("name", Operator.EQ, new String[]{code})};
				sql = MySearchFilter.getSelectSql(Arrays.asList(filters), GUser.class);
				List<GUser> gUserList=  gUserService.commonSelectBySql(sql);
				for (GUser gUser : gUserList) {
					gResetPwdUser.setId(gUser.getId());
					gResetPwdUser.setName(gUser.getLoginid());
					gResetPwdUser.setLoginCode(code);
					gResetPwdUser.setType(gtype);
				}
			}
		}
		model.addAttribute("gResetPwdUser", gResetPwdUser);
		model.addAttribute("menuId", "gUserManage");
		model.addAttribute("code", code);
		model.addAttribute("type", gtype);
		return "modules/g_user/gUser_resetPassword";
	}*/
    /**执行查询*/
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchUser(@ModelAttribute("gResetPwdUser") GResetPwdUser gResetPwdUser, HttpServletRequest request,RedirectAttributes redirectAttributes) {
    	String gtype = gResetPwdUser.getType();	
    	String code = gResetPwdUser.getLoginCode();
        return "redirect:/resetPwd/reset/resetpwd?type="+gtype+"&code="+code;
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
    
    /**
	 * 重置密码
	 * @param request
	 * @return
	 */
	/*@RequestMapping(value = "/resetPassword", method =  {RequestMethod.POST })
	@ResponseBody
	public ReturnMessage resetPassword(@RequestParam(value = "id") Long id,@RequestParam(value = "type") String type, HttpServletRequest request) {
		ReturnMessage rm = new ReturnMessage();
		rm.setFlag("true");
		if(type .equals("")){
			rm.setFlag("false");
			rm.setMessage("未选择用户类型！");
		}else if(type.equals("0")){
			GUser user = new GUser();
			user.setId(id);
			user.setPassword(Md5Util.encrypt(Constants.USER_REGISTER_PASSWORD));
			gUserService.update(user);
			rm.setFlag("true");
		}else if(type.equals("1")){
			AccTeacher accTeacher = new AccTeacher();
			accTeacher.setId(id);
			accTeacher.setPassword(Md5Util.encrypt(Constants.USER_REGISTER_PASSWORD));
			accTeacherService.update(accTeacher);
			rm.setFlag("true");
		}else if(type.equals("2")){
			AccStudent accStudent = new AccStudent();
			accStudent.setId(id);
			accStudent.setPassword(Md5Util.encrypt(Constants.USER_REGISTER_PASSWORD));
			accStudentService.update(accStudent);
			rm.setFlag("true");
		}
		return rm;
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
	

}