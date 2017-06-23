package com.cy.modules.g_right.model;

import com.cy.beans.BasePojo;
 
 /**
 *权限列表
 *@date 2014-01
 *@author dongao
 *@version  V1.0
 */
@SuppressWarnings("serial")
public class GRight extends BasePojo implements java.io.Serializable {

	/****/
	private java.lang.Long id;
	/**模块编码**/
	private java.lang.String code;
	/**模块名称**/
	private java.lang.String name;
	/**父节点编码**/
	private java.lang.Long parentid;
	/**是否叶子节点,0否1是**/
	private java.lang.Integer isleaf;
	/**按钮id**/
	private java.lang.String buttonId;
	/**项目id**/
	private java.lang.Long projectId;
	/**菜单链接**/
	private java.lang.String menuurl;
	/**1：按钮。0：菜单**/
	private java.lang.Integer type;
	/**排序字段**/
	private java.lang.Integer sequence;

	public  java.lang.Long getId() {
		return id;
	}
	public  java.lang.String getCode() {
		return code;
	}
	public  java.lang.String getName() {
		return name;
	}
	public  java.lang.Long getParentid() {
		return parentid;
	}
	public  java.lang.Integer getIsleaf() {
		return isleaf;
	}
	public  java.lang.String getButtonId() {
		return buttonId;
	}
	public  java.lang.Long getProjectId() {
		return projectId;
	}
	public  java.lang.String getMenuurl() {
		return menuurl;
	}
	public  java.lang.Integer getType() {
		return type;
	}
	public  java.lang.Integer getSequence() {
		return sequence;
	}


	public void setId( java.lang.Long id) {
	    this.id = id;
	}
	public void setCode( java.lang.String code) {
	    this.code = code;
	}
	public void setName( java.lang.String name) {
	    this.name = name;
	}
	public void setParentid( java.lang.Long parentid) {
	    this.parentid = parentid;
	}
	public void setIsleaf( java.lang.Integer isleaf) {
	    this.isleaf = isleaf;
	}
	public void setButtonId( java.lang.String buttonId) {
	    this.buttonId = buttonId;
	}
	public void setProjectId( java.lang.Long projectId) {
	    this.projectId = projectId;
	}
	public void setMenuurl( java.lang.String menuurl) {
	    this.menuurl = menuurl;
	}
	public void setType( java.lang.Integer type) {
	    this.type = type;
	}
	public void setSequence( java.lang.Integer sequence) {
	    this.sequence = sequence;
	}
}
