package com.cy.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.registry.infomodel.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.cy.util.BaseController;
import com.cy.util.Constants;
import com.cy.util.PageParameter;
import com.cy.util.RealPathResolver;
import com.cy.util.Servlets;

/**
 * @author dongao
 *
 */
public abstract class MyBaseController extends BaseController{
	@Autowired
	private RealPathResolver absoluteRealPathResolver;
	public static final String JSONPCALLBACK = "callback";

	
 	@InitBinder
	public void initBinder(WebDataBinder binder) {
		/**empty as null*/
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	public Collection<MySearchFilter> getFilters(){
		Map<String, String[]> params = Servlets.getParameterValuesMap(this.getRequest(),
				Constants.SEARCH_PREFIX);
		Map<String, MySearchFilter> map = MySearchFilter.parse(params);
		return map.values();
	}
	/**
	 * 用于改变参数，从而改变拼接sql
	 * UPDATE_VALUE：改变参数value值
	 * UPDATE_KAY: 改变key值，例如：改变拼接sql 的isnull 或isnotnull
	 * @param map  改变参数，格式Map<String , String[]> 其中String 为对应参数key, String[]:{UPDATE_VALUE/UPDATE_KAY , 改变内容 }
	 * @param head 对应Constants.SEARCH_PREFIX
	 * @return
	 */
	public Collection<MySearchFilter> getFiltersByTerm(Map<String,String[]> map,String head){
		Map<String, String[]> params = Servlets.getParameterValuesMap(this.getRequest(),
				head);
		
		Map<String,String[]> par = new HashMap<String, String[]>();
			for (String k : params.keySet()) {
				if(map != null && map.size() > 0){
					boolean isHash = false;
					for (String key : map.keySet()) {
						if(key.equals(k)){
							if("UPDATE_VALUE".equals(map.get(key)[0])){
								par.put(k,new String[]{map.get(key)[1]});
								isHash = true;
							}else if("UPDATE_KEY".equals(map.get(key)[0])){
								par.put(map.get(key)[1], params.get(k));
								isHash = true;
							}
						}
					}
					if(isHash == false){
						par.put(k, params.get(k));
					}
				}else{
					par.put(k, params.get(k));
				}
			}
		Map<String, MySearchFilter> reMap = MySearchFilter.parse(par);
		return reMap.values();
	}
	public Collection<Order> getOrders(){
		Map<String, String[]> params = Servlets.getParameterValuesMap(this.getRequest(),
				Constants.ORDER_PREFIX);
		Map<String, Order> map = Order.parse(params);
		return map.values();
	}
	
	/**获取当前用户*/
	public User getCurrentUser(){
		return (User)super.getRequest().getAttribute("user");
	}
	
	
	@Override
	public PageParameter getpagePageParameter(){
		return super.getpagePageParameter();
	}
	@Override
	public HttpServletRequest getRequest(){
		return super.getRequest();
	}
	
	public String getJsonString(String json){
		StringBuffer sb=new StringBuffer();
		String jsonp = super.getRequest().getParameter(JSONPCALLBACK);
		if(jsonp!=null&&!"".equals(jsonp)){
			sb.append(jsonp).append("(").append(json).append(")");
			return sb.toString();
		}else{
			return json;
		}
	}
	
	/**获取模板管理的基础路径*/
	public String getTemplatePath(){
		String string = absoluteRealPathResolver.get(Constants.TEMPLATE_PATH);
		File f=new File(string);
		if(!f.exists()){
			f.mkdirs();
		}
		return string;
	}

}
