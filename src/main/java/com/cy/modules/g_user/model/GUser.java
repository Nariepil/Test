package com.cy.modules.g_user.model;

import com.cy.beans.BasePojo;
 
 /**
 *管理员表
 *@date 2014-01
 *@author dongao
 *@version  V1.0
 */
@SuppressWarnings("serial")
public class GUser extends BasePojo implements java.io.Serializable {

	/**主键，自动递增**/
	private java.lang.Long id;
	/**用户名**/
	private java.lang.String name;
	/**登录用户编码（简称）**/
	private java.lang.String loginid;
	/**用户密码，要求MD5加密**/
	private java.lang.String password;
	/**用户级别（类型），0：普通管理员1：超级用户 2：班主任**/
	private java.lang.Integer grade;
	/**用户停启用，0：停用，停用时拒绝登录，1：启用**/
	private java.lang.Integer isused;
	/**创建人ID**/
	private java.lang.Long createUserId;
	/**创建人姓名**/
	private java.lang.String createUserName;
	/**创建时间**/
	private java.util.Date createTime;
	/**修改人ID**/
	private java.lang.Long updateUserId;
	/**修改人姓名**/
	private java.lang.String updateUserName;
	/**修改时间**/
	private java.util.Date updateTime;
	/**删除标识（0：已删除，1：未删除）**/
	private java.lang.Integer deleteFlag;
	/**部门编码，对应acc_organization表org_code**/
	private java.lang.String orgCode;

	
	public java.lang.String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(java.lang.String orgCode) {
		this.orgCode = orgCode;
	}
	public  java.lang.Long getId() {
		return id;
	}
	public  java.lang.String getName() {
		return name;
	}
	public  java.lang.String getLoginid() {
		return loginid;
	}
	public  java.lang.String getPassword() {
		return password;
	}
	public  java.lang.Integer getGrade() {
		return grade;
	}
	public  java.lang.Integer getIsused() {
		return isused;
	}
	public  java.lang.Long getCreateUserId() {
		return createUserId;
	}
	public  java.lang.String getCreateUserName() {
		return createUserName;
	}
	public  java.util.Date getCreateTime() {
		return createTime;
	}
	public  java.lang.Long getUpdateUserId() {
		return updateUserId;
	}
	public  java.lang.String getUpdateUserName() {
		return updateUserName;
	}
	public  java.util.Date getUpdateTime() {
		return updateTime;
	}
	public  java.lang.Integer getDeleteFlag() {
		return deleteFlag;
	}


	public void setId( java.lang.Long id) {
	    this.id = id;
	}
	public void setName( java.lang.String name) {
	    this.name = name;
	}
	public void setLoginid( java.lang.String loginid) {
	    this.loginid = loginid;
	}
	public void setPassword( java.lang.String password) {
	    this.password = password;
	}
	public void setGrade( java.lang.Integer grade) {
	    this.grade = grade;
	}
	public void setIsused( java.lang.Integer isused) {
	    this.isused = isused;
	}
	public void setCreateUserId( java.lang.Long createUserId) {
	    this.createUserId = createUserId;
	}
	public void setCreateUserName( java.lang.String createUserName) {
	    this.createUserName = createUserName;
	}
	public void setCreateTime( java.util.Date createTime) {
	    this.createTime = createTime;
	}
	public void setUpdateUserId( java.lang.Long updateUserId) {
	    this.updateUserId = updateUserId;
	}
	public void setUpdateUserName( java.lang.String updateUserName) {
	    this.updateUserName = updateUserName;
	}
	public void setUpdateTime( java.util.Date updateTime) {
	    this.updateTime = updateTime;
	}
	public void setDeleteFlag( java.lang.Integer deleteFlag) {
	    this.deleteFlag = deleteFlag;
	}
}
