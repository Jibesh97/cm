package com.horizon.common.util.tree;

import java.util.List;

/**
 * <P>
 * FileName: TreeNodeBean.java
 * 
 * @author peiyy
 *         <P>
 *         CreateTime: 2012-10-08
 *         <P>
 *         Description:
 *         <P>
 *         Version:v1.0
 *         <P>
 *         History:
 */
public class TreeNodeBean implements java.io.Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private String id;

	/**
	 * id所属表名
	 */
	private String tableName;

	/**
	 * 消息资源键值
	 */
	private String key;
	
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 父节点id
	 */
	private String pId;

	/**
	 * 连接
	 */
	private String url;

	/**
	 * url目标
	 */
	private String target;

	/**
	 * 容器名称
	 */
	private String rel;

	/**
	 * 节点是否被选中
	 */
	private boolean checked;
	
	/**
	 * 节点所在的等级
	 */
	private Integer nodeLevel;
	/**
	 * 节点所在级别
	 */
	private String treeLevel;
	
	/**
	 * 展开图片
	 */
	private String iconOpen;
	
	/**
	 * 关闭图片
	 */
	private String iconClose;
	
	/**
	 * 默认图片
	 */
	private String icon;
	
	/**
	 * 是否展开
	 */
	private boolean open;
	
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 会员标示
	 */
	private String isMember;
	/**
	 * 子节点
	 */
	private List<TreeNodeBean> children;

	public String getId() {
		return id;
	}

	public String getIsMember() {
		return isMember;
	}

	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPId() {
		return pId;
	}

	public void setPId(String pId) {
		this.pId = pId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Integer getNodeLevel() {
		return nodeLevel;
	}

	public void setNodeLevel(Integer nodeLevel) {
		this.nodeLevel = nodeLevel;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconClose() {
		return iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<TreeNodeBean> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeBean> children) {
		this.children = children;
	}

	public String getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(String treeLevel) {
		this.treeLevel = treeLevel;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}


}
