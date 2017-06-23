package com.cy.modules.g_right.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.cy.modules.g_right.model.GRight;
import com.cy.modules.g_right.service.GRightService;

import java.util.Date;

/**
 *GRight(权限列表)管理
 * autogenerate V1.0 by dongao
 */
@Controller
@RequestMapping("/gright/gRight")
public class GRightController extends MyBaseController {
    @Autowired
    private GRightService gRightService;
    
    /**初始化gRight管理页面*/
	@RequestMapping(value = {"/gRightList",""}, method = RequestMethod.GET)
	public String gRightlist(Model model, HttpServletRequest request) {
		//TODO...
		model.addAttribute("menuId", "gRightManage");
		return "modules/gright/gRight_list";
	}
	/**
	 * ajax分页查询
	 */
	@RequestMapping(value = "/findGRightsAjax")
	public @ResponseBody
	Pagination<GRight> findGRightsAjax(@ModelAttribute("gRight") GRight gRight, HttpServletRequest request) {
		gRight.setPageParameter(getpagePageParameter());
		//TODO...
		Pagination<GRight> pagination = gRightService.findByPage(gRight);
		return pagination;
	}
	/**
	 * ajax 支持组合查询的单表分页：页面驱动命名规则参考springside
	 */
	@RequestMapping(value = "/customPageAjax")
	public @ResponseBody
	Pagination<GRight> customPageAjax(HttpServletRequest request) {
		Pagination<GRight> pagination = gRightService.commonBySqlPage(CommonDto.build(super.getFilters(), super.getOrders(),GRight.class ,super.getpagePageParameter()));
		return pagination;
	}
	/**增加前的准备*/
    @RequestMapping(value = "/toAddGRight")
    public String toAddGRight(HttpServletRequest request,Model model) {
    	//TODO...
        return "modules/gright/gRight_add";
    }
    /**执行增加*/
    @RequestMapping(value = "/saveGRight", method = RequestMethod.POST)
    public String saveGRight(@ModelAttribute("gRight") GRight gRight, HttpServletRequest request,RedirectAttributes redirectAttributes) {
        //TODO...
        gRightService.save(gRight);
        redirectAttributes.addFlashAttribute("message", "增加成功");
        return "redirect:/gright/gRight/";
    }
    /**更新之前的准备**/
    @RequestMapping(value = "/editGRight/{id}")
    public String editGRight(@PathVariable("id") Long id, HttpServletRequest request,Model model) {
    	GRight gRight = gRightService.load(id);
        request.setAttribute("gRight", gRight);
        return "modules/gright/gRight_edit";
    }
    /**执行修改*/
    @RequestMapping(value = "/updateGRight", method = RequestMethod.POST)
    public String updateGRight(@ModelAttribute("gRight") GRight gRight, HttpServletRequest request,RedirectAttributes redirectAttributes) {
    	gRightService.update(gRight);
    	redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/gright/gRight/";
    }
    /**批量删除*/
    @RequestMapping(value = "/batchDeleteGRight")
    @ResponseBody
    public String batchDeleteGRight(@RequestParam(value = "ids[]", required = true) Long[] ids,RedirectAttributes redirectAttributes) {
        for(Long id:ids){
			gRightService.delete(id);
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
    		GRight gRight=gRightService.load(id);
    		gRight.setStatus(status);
    		gRightService.update(gRight);
    	}
        return "true";
    }*/
    
    /**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出GRight对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getGRight(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id != -1) {
			model.addAttribute("gRight", gRightService.load(id));
		}
	}
	

}