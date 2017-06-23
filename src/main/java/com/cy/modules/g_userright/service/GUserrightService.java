package com.cy.modules.g_userright.service;

import java.util.List;

import com.cy.beans.CommonDto;
import com.cy.beans.Pagination;
import com.cy.modules.g_userright.model.GUserright;
/**
 * autogenerate V1.0 by dongao
 */
public interface GUserrightService  {
	
	 /**
     * 分页查询所有GUserright
     * 
     * @param gUserright
     * @return
     */
    Pagination<GUserright> findByPage(GUserright gUserright);
    /**
     * 增加GUserright
     * 
     * @param gUserright
     * @return 主键
     */
    Long save(GUserright gUserright);

    /**
     * 获取GUserright
     * 
     * @param id
     * @return
     */
    GUserright load(Long id);

    /**
     * 修改GUserright
     * 
     * @param gUserright
     */
    void update(GUserright gUserright);
    /**
     * 删除GUserright
     * 
     * @param id
     * @return
     */
    void delete(Long id);
    /**
	 * GUserright：查询全部
	 * 
	 */
	List<GUserright> selectAll();
	/**
	 * GUserright：统计全部
	 * 
	 */
	int countAll();
	/**
	 * GUserright：自定义sql查询
	 * 
	 */
	List<GUserright> commonSelectBySql(String sql);
	/**
	 * GUserright：自定义sql统计
	 * 
	 */
	int commonCountBySql(String sql);
	/**
	 * GUserright：自定义sql分页
	 * 
	 */
	Pagination<GUserright> commonBySqlPage(CommonDto commonDto);
	/**
	 * 根据用户id查询已选中的权限
	 * @param valueOf
	 * @return
	 */
	List<Long> findByUserId(Long valueOf);
	/**
	 * 删除
	 * @param userId	用户id：1
	 * @param needDelIds	需要删除的rightIds：'1','2','3'
	 */
	void delByRightIds(Long userId, List<Long> needDelIds);
	/**
	 * 新增
	 * @param userId	用户id：1
	 * @param needAddIds	需要新增的rightIds：'1','2','3'
	 */
	void addByRightIds(Long userId, List<Long> needAddIds);
	/**
	 * 复制其他用户的权限页面:根据复制的多个用户id查询出不重复的rightId
	 * @param checkedUserIds
	 * @return
	 */
	List<Long> findByUserIds(String[] checkedUserIds);
	/** 
	* 根据用户id和项目project_id查询已选中的权限
	* @param userId
	* @param projectId
	* @return List<Long> 
	*/ 
	List<Long> findByUserIdAndProjectId(Long userId,Long projectId);
}
