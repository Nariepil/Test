package com.cy.modules.acc_organization.model;
 /**
 *菜单表
 *@date 2014-01
 *@author dongao
 *@version  V1.0
 */
@SuppressWarnings("serial")
public class AccOrganization  implements java.io.Serializable {

	/**编号**/
	private java.lang.Long id;
	/**父级编号**/
	private java.lang.Long parentId;
	/**名称**/
	private java.lang.String orgName;
	/****/
	private java.lang.String orgCode;
	/**是否子节点**/
	private java.lang.Long isLeaf;
	/**备注信息*
	 * 此字段已经作为组织架构的显示编码
	 * */
	private java.lang.String remarks;
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
	private  java.lang.String deleteFlag = "1";
	
	
	public java.lang.String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(java.lang.String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public java.lang.Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(java.lang.Long createUserId) {
		this.createUserId = createUserId;
	}
	public java.lang.String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(java.lang.String createUserName) {
		this.createUserName = createUserName;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.lang.Long getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(java.lang.Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	public java.lang.String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(java.lang.String updateUserName) {
		this.updateUserName = updateUserName;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public  java.lang.Long getId() {
		return id;
	}
	public  java.lang.Long getParentId() {
		return parentId;
	}
	public  java.lang.String getOrgName() {
		return orgName;
	}
	public  java.lang.String getOrgCode() {
		return orgCode;
	}
	public java.lang.Long getIsLeaf() {
		return isLeaf;
	}
	public  java.lang.String getRemarks() {
		return remarks;
	}

	public void setId( java.lang.Long id) {
	    this.id = id;
	}
	public void setParentId( java.lang.Long parentId) {
	    this.parentId = parentId;
	}
	public void setOrgName( java.lang.String orgName) {
	    this.orgName = orgName;
	}
	public void setOrgCode( java.lang.String orgCode) {
	    this.orgCode = orgCode;
	}
	public void setIsLeaf(java.lang.Long isLeaf) {
		this.isLeaf = isLeaf;
	}
	public void setRemarks( java.lang.String remarks) {
	    this.remarks = remarks;
	}
}
