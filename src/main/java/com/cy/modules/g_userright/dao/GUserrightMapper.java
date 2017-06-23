package com.cy.modules.g_userright.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cy.beans.CommonDto;
import com.cy.modules.g_userright.model.GUserright;

/**
 * autogenerate V1.0 by dongao
 */
public interface GUserrightMapper {
	/**
	 * 根据主键删除GUserright
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 新增GUserright
	 * 
	 * @param entity
	 * @return
	 */
	int insert(GUserright entity);

	/**
	 * 根据主键查询GUserright
	 * 
	 * @param id
	 * @return
	 */
	GUserright load(Long id);

	/**
	 * 根据条件查询GUserright（带分页）
	 * 
	 * @param entity
	 * @return
	 */
	List<GUserright> selectByPage(GUserright entity);
	/**
	 * 更新GUserright
	 * 
	 * @param entity
	 * @return
	 */
	int update(GUserright entity);
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
	List<GUserright> commonSelectBySql(CommonDto commonDto);
	/**
	 * GUserright：自定义sql统计
	 * 
	 */
	int commonCountBySql(CommonDto commonDto);
	/**
	 * GUserright：自定义sql分页
	 * 
	 */
	List<GUserright> commonBySqlPage(CommonDto commonDto);

	List<Long> findByUserId(@Param("UserId")Long UserId);

	void delByRightIds(@Param("userId")Long userId, @Param("needDelIds")List<Long> needDelIds);

	void addByRightIds(@Param("userId")Long userId, @Param("needAddIds")List<Long> needAddIds);

	List<Long> findByUserIds(@Param("checkedUserIds")String[] checkedUserIds);
	List<Long> findByUserIdAndProjectId(@Param("userId")Long userId,@Param("projectId")Long projectId);
	
}
