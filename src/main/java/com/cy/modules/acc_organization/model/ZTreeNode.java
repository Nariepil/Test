package com.cy.modules.acc_organization.model;
/**
 * ZTree专用返回json树结构
 * @author guozd
 *
 */
public class ZTreeNode implements java.io.Serializable {

	private static final long serialVersionUID = -8969147760938959419L;
	private String id;//可为code可为id
	private String name;
	private boolean open;// 初始化展开还是关闭
	private String isParent;// 是：则代表有子节点,Ztree会显示+，并点击会再加载子节点
	private String iconSkin;
	private Object font;//样式json
	private String pId;//父id 
	private boolean checked;// check时是否选中;true选中，
	/**
	 * 禁用节点 checkbox / radio 
	 * true 表示此节点的 checkbox / radio 被禁用。
	 *	false 表示此节点的 checkbox / radio 可以使用。
	 */
	private boolean chkDisabled; 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public String getIconSkin() {
		return iconSkin;
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	public Object getFont() {
		return font;
	}
	public void setFont(Object font) {
		this.font = font;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	
	
}
