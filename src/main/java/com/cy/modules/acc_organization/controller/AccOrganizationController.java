package com.cy.modules.acc_organization.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
import com.cy.beans.CommonDto;
import com.cy.beans.Pagination;
import com.cy.modules.acc_organization.model.AccOrganization;
import com.cy.modules.acc_organization.model.ZTreeNode;
import com.cy.modules.acc_organization.service.AccOrganizationService;
import com.cy.util.ReturnMessage;

/**
 *AccOrganization(菜单表)管理
 * autogenerate V1.0 by dongao
 */
@Controller
@RequestMapping("/acc_organization/accOrganization")
public class AccOrganizationController extends MyBaseController {
    @Autowired
    private AccOrganizationService accOrganizationService;
    
    /**初始化accOrganization管理页面*/
	@RequestMapping(value = {"/accOrganizationList",""}, method = RequestMethod.GET)
	public String accOrganizationlist(Model model, HttpServletRequest request) {
		//TODO...
		model.addAttribute("menuId", "accOrganizationManage");
		return "modules/acc_organization/accOrganization_list";
	}
	
	/**
	 * 查询地区子树
	 */
	@RequestMapping(value = "/findChildren", method =  { RequestMethod.POST,RequestMethod.GET },produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ZTreeNode>  findChildren(HttpServletRequest request) {
		String parentIdStr = request.getParameter("id");
		Long parentId = null;
		if(StringUtils.isEmpty(parentIdStr)){
			parentId = 0l;
		}else{
			parentId = Long.parseLong(parentIdStr);
		}
		List<AccOrganization> list = accOrganizationService.findChildren(parentId);
		List<ZTreeNode> nodeList = new ArrayList<ZTreeNode>(list.size());
		for (AccOrganization dpt : list) {
			ZTreeNode node = new ZTreeNode();
			node.setId(dpt.getId().toString());
			node.setName(dpt.getRemarks()+" "+dpt.getOrgName());
			node.setOpen(false);
			node.setIsParent(dpt.getIsLeaf()==0 ? "true" : "false");
			nodeList.add(node);
		}
		return nodeList;
	}
	
	 /**
		 * 路转至查看组织结构的页面
		 * @param model
		 * @param id
		 * @return
		 */
		@RequestMapping(value = "/viewAccOrganization", method = RequestMethod.GET)
		public String viewAccOrganization(Model model,HttpServletRequest request) {
			String idStr = request.getParameter("id");
			long id = 0l;
			if(!StringUtils.isEmpty(idStr)){
				id = Long.parseLong(idStr);
			}
			AccOrganization accOrganization = accOrganizationService.load(id);
			model.addAttribute("accOrganization", accOrganization);
			return "modules/acc_organization/accOrganization_view";
		}
	
	/**
	 * 全部查询
	 */
	@RequestMapping(value = "/findAllAccOrganizationsList", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody
	Pagination<AccOrganization> findAllAccOrganizationsList(@ModelAttribute("accOrganization") AccOrganization accOrganization, HttpServletRequest request) {
		//TODO...
		Pagination<AccOrganization> pagination = new Pagination<AccOrganization>();
		List<AccOrganization> accOrganizationList = accOrganizationService.selectAll();
		pagination.setList(accOrganizationList);
		return pagination;
	}
	
	/**
	 * ajax 支持组合查询的单表分页：页面驱动命名规则参考springside
	 */
	@RequestMapping(value = "/customPageAjax")
	public @ResponseBody
	Pagination<AccOrganization> customPageAjax(HttpServletRequest request) {
		Pagination<AccOrganization> pagination = accOrganizationService.commonBySqlPage(CommonDto.build(super.getFilters(), super.getOrders(),AccOrganization.class ,super.getpagePageParameter()));
		return pagination;
	}
	/**增加前的准备*/
    @RequestMapping(value = "/toAddAccOrganization")
    public String toAddAccOrganization(@RequestParam("pId") String pId,HttpServletRequest request,Model model) {
    	//TODO...
    	model.addAttribute("pId", pId);
        return "modules/acc_organization/accOrganization_add";
    }
    /**
  	 * code唯一性验证
  	 * @param request
  	 * @return
  	 * @throws UnsupportedEncodingException 
  	 */
  	@RequestMapping(value = "/codeUniqueValid", method =  {RequestMethod.GET })
  	@ResponseBody
  	public ReturnMessage codeUniqueValid(HttpServletRequest request) throws UnsupportedEncodingException {
  		String orgCode = request.getParameter("orgCode");
  		ReturnMessage rm = new ReturnMessage();
  		if(StringUtils.isEmpty(orgCode)){
  			rm.setFlag("false");
  			rm.setMessage("编码不能为空！");
  		}else{
  			orgCode = orgCode.trim();
  	    	AccOrganization accOrganization = accOrganizationService.findByOrgCode(orgCode);
  	    	if(accOrganization != null){
  	    		rm.setFlag("false");
  	    		rm.setMessage("编码已经存在!");
  	    	}else{
  	    		rm.setFlag("true");
  	    	}
  		}
  		return rm;
  	}
  	
  
    /**更新之前的准备**/
    @RequestMapping(value = "/editAccOrganization/{id}")
    public String editAccOrganization(@PathVariable("id") Long id, HttpServletRequest request,Model model) {
    	AccOrganization accOrganization = accOrganizationService.load(id);
        request.setAttribute("accOrganization", accOrganization);
        return "modules/acc_organization/accOrganization_edit";
    }
   
    /**批量删除*/
    @RequestMapping(value = "/batchDeleteAccOrganization")
    @ResponseBody
    public String batchDeleteAccOrganization(@RequestParam(value = "ids[]", required = true) Long[] ids,RedirectAttributes redirectAttributes) {
        for(Long id:ids){
			accOrganizationService.delete(id);
        }
        //SystemLogUtils.addSystemLog("组织结构管理-删除","/acc_organization/accOrganization/batchDeleteAccOrganization","");
        return "true";
    }
/*  //状态更新,如果有需要,请打开注释
    @RequestMapping(value = "/updatetStatus", method = RequestMethod.POST)
    @ResponseBody
    public String batchUpdatetStatus(
    		@RequestParam(value = "status", required = true) Integer status,
			@RequestParam(value = "ids[]", required = true) Long[] ids) {
    	for(Long id:ids){
    		AccOrganization accOrganization=accOrganizationService.load(id);
    		accOrganization.setStatus(status);
    		accOrganizationService.update(accOrganization);
    	}
        return "true";
    }*/
    
    /**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出AccOrganization对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getAccOrganization(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id != -1) {
			model.addAttribute("accOrganization", accOrganizationService.load(id));
		}
	}
	

}