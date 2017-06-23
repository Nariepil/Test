package com.cy.modules.GResetUser.model;

 
 /**
 *用于重置密码的实体
 *@date 2014-01
 *@author dongao
 *@version  V1.0
 */
@SuppressWarnings("serial")
public class GResetPwdUser implements java.io.Serializable {

	private java.lang.Long id;
	private java.lang.String loginCode;
	private java.lang.String type;
	private java.lang.String name;
	public java.lang.String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(java.lang.String loginCode) {
		this.loginCode = loginCode;
	}
	
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.String getType() {
		return type;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
}
