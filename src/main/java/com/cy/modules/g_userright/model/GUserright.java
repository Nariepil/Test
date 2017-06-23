package com.cy.modules.g_userright.model;

import com.cy.beans.BasePojo;
 
 /**
 *用户权限表
 *@date 2014-01
 *@author dongao
 *@version  V1.0
 */
@SuppressWarnings("serial")
public class GUserright extends BasePojo implements java.io.Serializable {

	/**主键**/
	private java.lang.Long id;
	/**用户编号，对应g_user表id**/
	private java.lang.Long userId;
	/**权限编号，对应g_right表id**/
	private java.lang.Long rightId;

	public  java.lang.Long getId() {
		return id;
	}
	public  java.lang.Long getUserId() {
		return userId;
	}
	public  java.lang.Long getRightId() {
		return rightId;
	}


	public void setId( java.lang.Long id) {
	    this.id = id;
	}
	public void setUserId( java.lang.Long userId) {
	    this.userId = userId;
	}
	public void setRightId( java.lang.Long rightId) {
	    this.rightId = rightId;
	}
}
