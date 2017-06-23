package com.cy.modules.g_right.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cy.beans.CommonDto;
import com.cy.modules.g_right.model.GRight;
import com.cy.modules.g_user.model.GUser;

/**
 * autogenerate V1.0 by dongao
 */
public interface GRightMapper {
	/**
	 * 根据主键删除GRight
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 新增GRight
	 * 
	 * @param entity
	 * @return
	 */
	int insert(GRight entity);

	/**
	 * 根据主键查询GRight
	 * 
	 * @param id
	 * @return
	 */
	GRight load(Long id);

	/**
	 * 根据条件查询GRight（带分页）
	 * 
	 * @param entity
	 * @return
	 */
	List<GRight> selectByPage(GRight entity);
	/**
	 * 更新GRight
	 * 
	 * @param entity
	 * @return
	 */
	int update(GRight entity);
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
	List<GRight> commonSelectBySql(CommonDto commonDto);
	/**
	 * GRight：自定义sql统计
	 * 
	 */
	int commonCountBySql(CommonDto commonDto);
	/**
	 * GRight：自定义sql分页
	 * 
	 */
	List<GRight> commonBySqlPage(CommonDto commonDto);

	List<GRight> selectAllHead();

	java.util.List<GRight> selectByEntity(GRight gRight);

	List<GRight> selectAllByProjectId(@Param("projectId")Integer projectId);

	List<GRight> selectUserMenuHead(@Param("userId")Long userId);

	List<GRight> selectUserMenuLeaf(@Param("userId")Long userId, @Param("headId")Long headId);

	List<GRight> selectUserMenuAll(GUser user);

	List<GRight> selectUserDisabledButton(Long userId);

	List<GRight> findChildren(@Param("parentId")Long parentId, @Param("type") Integer type);

	int countByCode(@Param("code")String code);

	Integer getMaxSequence(@Param("pId")Long pId);

	void deleteByCode(@Param("code")String code);

	List<GRight> selectUserDisabledRight(Long userId);

	List<GRight> selectUserRightAll(GUser user);
	String getAllNodes(Long id);
}
