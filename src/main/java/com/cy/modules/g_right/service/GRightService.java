package com.cy.modules.g_right.service;

import java.util.List;

import com.cy.beans.CommonDto;
import com.cy.beans.Pagination;
import com.cy.modules.g_right.model.GRight;
import com.cy.modules.g_user.model.GUser;
/**
 * autogenerate V1.0 by dongao
 */
public interface GRightService  {
	
	 /**
     * 分页查询所有GRight
     * 
     * @param gRight
     * @return
     */
    Pagination<GRight> findByPage(GRight gRight);
    /**
     * 增加GRight
     * 
     * @param gRight
     * @return 主键
     */
    Long save(GRight gRight);
    List<GRight> selectByEntity(GRight gRight);
    /**
     * 获取GRight
     * 
     * @param id
     * @return
     */
    GRight load(Long id);

    /**
     * 修改GRight
     * 
     * @param gRight
     */
    void update(GRight gRight);
    /**
     * 删除GRight
     * 
     * @param id
     * @return
     */
    void delete(Long id);
    /**
	 * GRight：查询全部
	 * 
	 */
	List<GRight> selectAll();
	/**
	 * GRight：统计全部
	 * 
	 */
	int countAll();
	/**
	 * GRight：自定义sql查询
	 * 
	 */
	List<GRight> commonSelectBySql(String sql);
	/**
	 * GRight：自定义sql统计
	 * 
	 */
	int commonCountBySql(String sql);
	/**
	 * GRight：自定义sql分页
	 * 
	 */
	Pagination<GRight> commonBySqlPage(CommonDto commonDto);
	List<GRight> selectAllHead();
	/**
	 * 根据projectId查询出所有的菜单和按钮
	 * @param projectId
	 * @return
	 */
	List<GRight> selectAllByProjectId(Integer projectId);
	/**
	 * 根据用户的id获得一级菜单
	 * @param id
	 * @return
	 */
	List<GRight> selectUserMenuHead(Long id);
	/**
	 * 根据用户的id和父id
	 * @param userId
	 * @param headId
	 * @return
	 */
	java.util.List<GRight> selectUserMenuLeaf(Long userId, Long headId);
	/**
	 * 根据用户Id获得所有菜单（只包括菜单）
	 * @param user
	 * @return
	 */
	List<GRight> selectUserMenuAll(GUser user);
	/**
	 * 根据用户id获得所有禁用的菜单（只包括按钮）
	 * @param userId
	 * @return
	 */
	List<GRight> selectUserDisabledButton(Long userId);
	/**
	 * 查询子树,
	 * @param parentId
	 * @param type 1：按钮。0：菜单
	 * @return
	 */
	List<GRight> findChildren(Long parentId, Integer type);
	/**
	 * 查询关联的菜单和按钮的个数
	 * @param code
	 * @return
	 */
	int countByCode(String code);
	Integer getMaxSequence(Long pId);
	/**
	 * 删除结点及其子树 like 'code%'
	 * @param code
	 */
	void deleteByCode(String code);
	/**
	 * 根据用户id获得所有禁用的菜单（包括按钮和菜单）
	 * @param userId
	 * @return
	 */
	List<GRight> selectUserDisabledRight(Long userId);
	List<GRight> selectUserRightAll(GUser user);
	String getAllNodes(Long id); 
}
