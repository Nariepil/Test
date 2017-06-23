package com.cy.modules.g_user.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cy.beans.CommonDto;
import com.cy.modules.g_user.model.GUser;

/**
 * autogenerate V1.0 by dongao
 */
public interface GUserMapper {
	/**
	 * 根据主键删除GUser
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 新增GUser
	 * 
	 * @param entity
	 * @return
	 */
	int insert(GUser entity);

	/**
	 * 根据主键查询GUser
	 * 
	 * @param id
	 * @return
	 */
	GUser load(Long id);

	/**
	 * 根据条件查询GUser（带分页）
	 * 
	 * @param entity
	 * @return
	 */
	List<GUser> selectByPage(GUser entity);
	/**
	 * 更新GUser
	 * 
	 * @param entity
	 * @return
	 */
	int update(GUser entity);
	/**
	 * GUser：查询全部
	 * 
	 */
	List<GUser> selectAll();
	/**
	 * GUser：统计全部
	 * 
	 */
	int countAll();
	/**
	 * GUser：自定义sql查询
	 * 
	 */
	List<GUser> commonSelectBySql(CommonDto commonDto);
	/**
	 * GUser：自定义sql统计
	 * 
	 */
	int commonCountBySql(CommonDto commonDto);
	/**
	 * GUser：自定义sql分页
	 * 
	 */
	List<GUser> commonBySqlPage(CommonDto commonDto);
	List<GUser> findUserByOrgCode(String orgCode);
	
	List<GUser> findUserLikeOrgCode(String orgCode);

	int countUserByOrgCode(String orgCode);

	int loginCodeUniqueValid(@Param("name") String name, @Param("idStr") String idStr);

	GUser login(@Param("name")String name, @Param("password")String password);
	
	/**
	 * 启用GUser
	 * 
	 * @param id
	 * @return
	 */
	int enableUser(long id);
	
	long getCurrentSeqId();
}
