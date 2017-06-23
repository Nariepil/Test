package com.cy.modules.g_userright.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSONObject;
import com.cy.base.MyBaseController;
import com.cy.beans.CommonDto;
import com.cy.beans.Pagination;
import com.cy.modules.acc_organization.model.AccOrganization;
import com.cy.modules.acc_organization.model.ZTreeNode;
import com.cy.modules.acc_organization.service.AccOrganizationService;
import com.cy.modules.g_right.model.GRight;
import com.cy.modules.g_right.service.GRightService;
import com.cy.modules.g_user.model.GUser;
import com.cy.modules.g_user.service.GUserService;
import com.cy.modules.g_userright.model.GUserright;
import com.cy.modules.g_userright.service.GUserrightService;
import com.cy.util.Constants;
import com.cy.util.JsonMapper;
import com.cy.util.JsonUtil;
import com.cy.util.Md5Util;
import com.cy.util.RequestUtils;
import com.cy.util.ReturnMessage;

/**
 *GUserright(用户权限表)管理
 * autogenerate V1.0 by dongao
 */
@Controller
@RequestMapping("/g_userright/gUserright")
public class GUserrightController extends MyBaseController {
    @Autowired
    private GUserrightService gUserrightService;
    @Autowired
    private GUserService gUserService;
    @Autowired
    private GRightService gRightService;
    @Autowired
    private AccOrganizationService accOrganizationService;
/**
 * 
 * 20161101开发
 * 
 * **/
	
	 /**
     * 查询当前地区下的所有用户
     */
    @RequestMapping(value = "/findUserByOrgCode", method ={ RequestMethod.POST,RequestMethod.GET })
    @ResponseBody
    public List<ZTreeNode>  findUserByOrgCode(HttpServletRequest request) {
    	String orgCode = request.getParameter("orgCode");
    	if(StringUtils.isEmpty(orgCode)){
    		return this.findChildren(request);
    	}
    	List<GUser> userList =  null;
    	List<ZTreeNode> nodeList = new ArrayList<ZTreeNode>();
    	if(StringUtils.isNotEmpty(orgCode)){
    		userList =  gUserService.findUserByOrgCode(orgCode);
        	for (GUser dpt : userList) {
        		ZTreeNode node = new ZTreeNode();
        		node.setId(dpt.getId().toString());
        		node.setName(dpt.getName());
        		node.setOpen(true);
        		node.setIsParent("false");
        		//进行启用禁用样式区分。0：禁用，1：启用
        		if(dpt.getIsused() == 0){
        			Map map = new HashMap();
        			map.put("background-color", "black");
        			map.put("color", "white");
        			node.setFont(map);
        		}
        		nodeList.add(node);
        	}
    	}
    	return nodeList;
    }
	/**
	 * 查询所属的所有地区包括子区
	 */
	private List<ZTreeNode>  findChildren(HttpServletRequest request) {
		GUser gUser = RequestUtils.getUser(request);
		//GUser gUser = gUserService.load(2l);
		String orgCode = gUser.getOrgCode();
		//用户级别（类型），0：普通用户 1：超级用户 2：地(市)、县（区）管理员
		if(gUser.getGrade() == 1){
			orgCode = "";
		}
		List<AccOrganization> areaList = accOrganizationService.findOrganizaByOrgCode(orgCode);
		
		List<ZTreeNode> nodeList = new ArrayList<ZTreeNode>(areaList.size());
		for (AccOrganization dpt : areaList) {
			ZTreeNode node = new ZTreeNode();
			//int userCount = gUserService.countUserByOrgCode(dpt.getOrgCode());
			node.setId(dpt.getOrgCode());
			node.setName(dpt.getOrgName());
			node.setOpen(true);
			//node.setIsParent(dpt.getIsleaf()==0 ? "true" : "false");
			node.setIsParent("true");
			node.setChkDisabled(true);
			nodeList.add(node);
		}
		return nodeList;
	}
	
	/**
	 * 用户 所选菜单、按钮 管理界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"/userMenuList"})
	public String userMenuList(Model model,HttpServletRequest request) {
		String userId = request.getParameter("userId");
		if(StringUtils.isNotEmpty(userId) && StringUtils.isNumeric(userId)){
			//根据用户id查询已选中的权限id	
			List<Long> rightList = gUserrightService.findByUserId(Long.valueOf(userId));
			List<GRight> allRightList1 = gRightService.selectAllByProjectId(1);
			List<GRight> allRightList2 = gRightService.selectAllByProjectId(2);
			List<ZTreeNode> nodeList1 = new ArrayList<ZTreeNode>(allRightList1.size());
			List<ZTreeNode> nodeList2 = new ArrayList<ZTreeNode>(allRightList2.size());
			
			JSONObject jsonObj = new JSONObject();
			for (GRight dpt : allRightList1) {
				ZTreeNode node = new ZTreeNode();
				node.setId(dpt.getId().toString());
				node.setName(dpt.getCode()+" "+dpt.getName());
				node.setOpen(true);
				if(rightList.contains(dpt.getId())){
					node.setChecked(true);
				}
				//node.setIsParent(dpt.getIsleaf()==0 ? "true" : "false");
				node.setpId(dpt.getParentid().toString());
				nodeList1.add(node);
			}
			for (GRight dpt : allRightList2) {
				ZTreeNode node = new ZTreeNode();
				node.setId(dpt.getId().toString());
				node.setName(dpt.getCode()+" "+dpt.getName());
				node.setOpen(true);
				if(rightList.contains(dpt.getId())){
					node.setChecked(true);
				}
				//node.setIsParent(dpt.getIsleaf()==0 ? "true" : "false");
				node.setpId(dpt.getParentid().toString());
				nodeList2.add(node);
			}
			
			model.addAttribute("allRightList1", JsonMapper.toJson(nodeList1));
			model.addAttribute("allRightList2", JsonMapper.toJson(nodeList1));
			model.addAttribute("userId", userId);
			
		}
		return "modules/g_userright/gUser_right";
	}
	/**
	 * 保存用户权限
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "/saveUserRight", method =  {RequestMethod.POST })
	@ResponseBody
	public ReturnMessage saveUserRight(HttpServletRequest request) throws NumberFormatException, IOException {
		ReturnMessage rm = new ReturnMessage();
		rm.setFlag("true");
		//yonghu de id
		String userId = request.getParameter("userId");
		String projectId = request.getParameter("projectId");
		//quanxian de id
		String checkedIds = request.getParameter("checkedIds");
		if(StringUtils.isEmpty(userId) || !StringUtils.isNumeric(userId)){
			rm.setFlag("false");
			rm.setMessage("未选中用户！");
			return rm;
		}
//		else if(StringUtils.isEmpty(checkedIds)){
//			rm.setFlag("false");
//			rm.setMessage("未选中权限！");
//			return rm;
//		}
		GUser user = gUserService.load(Long.valueOf(userId));
		if(user == null){
			rm.setFlag("false");
			rm.setMessage("该用户不存在！");
		}else{
			//根据用户id查询已选中的权限id	
//			List<Long> rightList = gUserrightService.findByUserId(Long.valueOf(userId));
			List<Long> rightList = gUserrightService.findByUserIdAndProjectId(Long.valueOf(userId), Long.valueOf(projectId));
			String[] checkedIdArray = checkedIds.split(",");
			//需要保存到数据库的rightId集合
			List<Long> needSaveList = new ArrayList<Long>();
			for(String rightId : checkedIdArray){
				if(StringUtils.isNotEmpty(rightId) && StringUtils.isNumeric(rightId)){
					if(rightList.contains(Long.valueOf(rightId))){
						//remove后剩下的就是需要到数据库删除的rightId集合
						rightList.remove(Long.valueOf(rightId));
					}else{
						needSaveList.add(Long.valueOf(rightId));
					}
				}
			}
			//删除多余已有的数据
			if(rightList.size() > 0){
				gUserrightService.delByRightIds(Long.valueOf(userId), rightList);
			}
			//新增数据
			if(needSaveList.size() > 0){
				gUserrightService.addByRightIds(Long.valueOf(userId), needSaveList);
			}
			updateRedisGUserRight(Long.valueOf(userId));
		}
		return rm;
	}
	/**
	 * 将用户的权限信息重新更新到redis里面
	 * @param userId
	 * @throws IOException
	 */
	private void updateRedisGUserRight(Long userId) throws IOException {/*
		GUser user = gUserService.load(userId);
    	//key
        String userKey = TedisKeyConstants.PRE_USER_PRE+user.getId();
    	String functionKey = TedisKeyConstants.PRE_USER_FUNCTION_PRE+user.getId();
    	String disabledButtonKey = TedisKeyConstants.PRE_USER_DISABLED_BUTTON_PRE+user.getId();
    	String disabledRightKey = TedisKeyConstants.PRE_USER_DISABLED_RIGHT_PRE+user.getId();
    	String userRightKey = TedisKeyConstants.PRE_USER_RIGHT_PRE+user.getId();
    	
    	//userKey
    	if(!tedisUtil.isExists(userKey)){
    		return;
    	}
    	//删除缓存
		tedisUtil.tedisDelObj(functionKey);
		tedisUtil.tedisDelObj(disabledButtonKey);
		tedisUtil.tedisDelObj(disabledRightKey);
		tedisUtil.tedisDelObj(userRightKey);
		List<GRight> gRightMenuList = null;
		//超级用户：admin
		if(user.getGrade() == 1){
			GRight gRight1 = new GRight();
			gRight1.setIsleaf(1);
			gRight1.setType(0);//1：按钮。0：菜单
			gRightMenuList = gRightService.selectAll();
		}else{
			gRightMenuList = gRightService.selectUserMenuAll(user);
		}
		
		List<GRight> disabledButtonList = null;
		if(user.getGrade() != 1){
			disabledButtonList = gRightService.selectUserDisabledButton(user.getId());
		}
		List<GRight> disabledRightList = null;
		if(user.getGrade() != 1){
			disabledRightList = gRightService.selectUserDisabledRight(user.getId());
		}
		List<GRight> userGRightList = gRightService.selectUserRightAll(user);

		//存到tedisUtil中
		tedisUtil.tedisSetString(functionKey, JsonUtil.toJson(gRightMenuList));
		tedisUtil.tedisSetString(disabledButtonKey, JsonUtil.toJson(disabledButtonList));
		tedisUtil.tedisSetString(disabledRightKey, JsonUtil.toJson(disabledRightList));
		tedisUtil.tedisSetString(userRightKey, JsonUtil.toJson(userGRightList));
	*/}

	 /**初始化gUser管理页面*/
		@RequestMapping(value = {"/gUserList",""}, method = RequestMethod.GET)
		public String gUserlist(Model model, HttpServletRequest request) {
			model.addAttribute("menuId", "gUserManage");
			GUser gUser = RequestUtils.getUser(request);
			//用户级别（类型），0：普通用户 1：超级用户 2：地(市)、县（区）管理员
			if(gUser.getGrade() == 0){
				return "modules/g_userright/gUser_limit";
			}
			return "modules/g_userright/gUser_list";
		}
		
	/**
	 * 重置密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/resetPassword/{id}", method =  {RequestMethod.POST })
	@ResponseBody
	public ReturnMessage resetPassword(@PathVariable("id") Long id, HttpServletRequest request) {
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
	/**
	 * 启用用户
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/enableUser/{id}", method =  {RequestMethod.POST })
	@ResponseBody
	public ReturnMessage enableUser(@PathVariable("id") Long id, HttpServletRequest request) throws IOException {
		ReturnMessage rm = new ReturnMessage();
		rm.setFlag("true");
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
				updateRedisGUser(id);
			}
		}
		return rm;
	}
	/**
	 * 禁用用户
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/disableUser/{id}", method =  {RequestMethod.POST })
	@ResponseBody
	public ReturnMessage disableUser(@PathVariable("id") Long id, HttpServletRequest request) throws IOException {
		ReturnMessage rm = new ReturnMessage();
		rm.setFlag("true");
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
			}else if(user.getGrade() == 1){
				rm.setFlag("false");
				rm.setMessage("超级用户不可禁用！");
			}else{
				GUser userParam = new GUser();
				userParam.setId(id);
				userParam.setIsused(0);
				gUserService.update(userParam);
				updateRedisGUser(id);
			}
		}
		return rm;
	}
	/**
	 * 将用户的信息重新更新到redis里面
	 * @param userId
	 * @throws IOException 
	 */
	private void updateRedisGUser(Long userId) throws IOException {/*
		GUser guser = gUserService.load(userId);
		String userKey = TedisKeyConstants.PRE_USER_PRE+userId;
		if(!tedisUtil.isExists(userKey)){
			return;
		}
		tedisUtil.tedisDelObj(userKey);
		tedisUtil.tedisSetString(userKey,JsonUtil.toJson(guser));
	*/}
    /**初始化gUserright管理页面*/
//	@RequestMapping(value = {"/gUserrightList",""}, method = RequestMethod.GET)
//	public String gUserrightlist(Model model, HttpServletRequest request) {
//		//TODO...
//		model.addAttribute("menuId", "gUserrightManage");
//		return "modules/g_userright/gUserright_list";
//	}
	/**
	 * ajax分页查询
	 */
	@RequestMapping(value = "/findGUserrightsAjax", method = RequestMethod.GET)
	public @ResponseBody
	Pagination<GUserright> findPayBanks(@ModelAttribute("gUserright") GUserright gUserright, HttpServletRequest request) {
		gUserright.setPageParameter(getpagePageParameter());
		//TODO...
		Pagination<GUserright> pagination = gUserrightService.findByPage(gUserright);
		return pagination;
	}
	/**
	 * ajax 支持组合查询的单表分页：页面驱动命名规则参考springside
	 */
	@RequestMapping(value = "/customPageAjax")
	public @ResponseBody
	Pagination<GUserright> customPageAjax(HttpServletRequest request) {
		Pagination<GUserright> pagination = gUserrightService.commonBySqlPage(CommonDto.build(super.getFilters(), super.getOrders(),GUserright.class ,super.getpagePageParameter()));
		return pagination;
	}
	/**增加前的准备*/
    @RequestMapping(value = "/toAddGUserright")
    public String toAddGUserright(HttpServletRequest request,Model model) {
    	//TODO...
        return "modules/g_userright/gUserright_add";
    }
    /**执行增加*/
    @RequestMapping(value = "/saveGUserright", method = RequestMethod.POST)
    public String saveGUserright(@ModelAttribute("gUserright") GUserright gUserright, HttpServletRequest request,RedirectAttributes redirectAttributes) {
        //TODO...
        gUserrightService.save(gUserright);
        redirectAttributes.addFlashAttribute("message", "增加成功");
        return "redirect:/g_userright/gUserright/";
    }
    /**更新之前的准备**/
    @RequestMapping(value = "/editGUserright/{id}")
    public String editGUserright(@PathVariable("id") Long id, HttpServletRequest request,Model model) {
    	GUserright gUserright = gUserrightService.load(id);
        request.setAttribute("gUserright", gUserright);
        return "modules/g_userright/gUserright_edit";
    }
    /**执行修改*/
    @RequestMapping(value = "/updateGUserright", method = RequestMethod.POST)
    public String updateGUserright(@ModelAttribute("gUserright") GUserright gUserright, HttpServletRequest request,RedirectAttributes redirectAttributes) {
    	gUserrightService.update(gUserright);
    	redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/g_userright/gUserright/";
    }
    /**批量删除*/
    @RequestMapping(value = "/batchDeleteGUserright")
    @ResponseBody
    public String batchDeleteGUserright(@RequestParam(value = "ids[]", required = true) Long[] ids,RedirectAttributes redirectAttributes) {
        for(Long id:ids){
			gUserrightService.delete(id);
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
    		GUserright gUserright=gUserrightService.load(id);
    		gUserright.setStatus(status);
    		gUserrightService.update(gUserright);
    	}
        return "true";
    }*/
    
    /**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出GUserright对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getGUserright(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id != -1) {
			model.addAttribute("gUserright", gUserrightService.load(id));
		}
	}
	

}