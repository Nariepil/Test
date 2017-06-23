package com.cy.util;

/**
 * 用于json返回
 * @author guozd
 *
 */
public class ReturnMessage {
	private String flag;
	private String message;//返回信息详情
	private Object obj;//返回的对象
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
